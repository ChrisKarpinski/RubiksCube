/****************************************************************************
 *
 * Created by: Chris Karpinski
 * Created on: Dec 2016
 * This class models the cube to be solved and runs all of the necessary 
 *     logic to solve the cube
 *
 ****************************************************************************/

import java.util.ArrayList;
public class Cube {

	public enum _colours {
		// enum for the colours on the faces of the cube
		B,
		R,
		W,
		Y,
		O,
		G
		
	}
	private _colours[][] _cube = new _colours[6][4];
	private ArrayList<String> _moves = new ArrayList<String>();
	private long _startTime = System.currentTimeMillis();
	private long _runTime = 0;
	
	public Cube (_colours [][] cubeInput) {
		// constructor to initialize cube 
		this._cube = cubeInput;
		
	}
	
	public void solveCube() {
		// solves the cube recursively
		
		for (int counter = 0;; counter++) {
			// we don't need a limit on the counter because the limit is taken care
			// of by the run time constraint for non-solvability
			if (!this.checkSolved() && this._runTime < 600000) {
				
				this.getMoves(0, counter);
				// asking - can the cube be solved in each number of moves until the time limit
				// for a solution is reached?
			}
			else {
				// once a solution is found for a certain number of moves, stop looking for more solutions
				// and print out the shortest found solution
				if (this._runTime > 600000) {
					// clear out any generated moves if the time limit of 10 minutes has been reached
					// (because the cube was probably not solvable) 
					this._moves.clear();
				}
				this.printMoves();
				return;
			}
			
		}
		
	}

	public void getMoves (int currentMove, int movesToCheck) {
		long currentTime = System.currentTimeMillis();
		this._runTime = currentTime - this._startTime;
		
		if (this._runTime > 600000) {
			// if the program is looking for solutions for more than 10 minutes 
			// (600000 milliseconds), there probably is no solution.
			// I have not been able to find a cube that takes more than 10 minutes
			// to solve (maximum for several tests has been around 3 minutes), 
			// so I would assume that all (or at least the vast majority) cubes can be 
			// solved in less than 10 minutes if they're solvable at all.
			return;
			
		}
		// generates the moves needed to solve the cube in a given number of moves
		// if possible
		if (currentMove < movesToCheck) {
			// recursively loop through each move number up to the number of moves 
			// that are being checked for to work through ordered combinations of moves 
			// until a working arrangement of moves is found for that number of moves
			
			for (int faceIndex = 0; faceIndex < 3; faceIndex++) {
				// do each of the three possible moves and add their 
				// instruction to the list of moves
				if (faceIndex == 0) {
						
					this._moves.add("Rotate front");
					this.rotateFront();
						
				}
				else if (faceIndex == 1) {
						
					this._moves.add("Rotate top");
					this.rotateTop();
						
				}
				else {
						
					this._moves.add("Rotate left");
					this.rotateLeft();
						
				}
					
				getMoves(currentMove+1, movesToCheck); // move onto the next move (next branch of recursion)
				
				// once all of the moves have been generated, collapse the stack and start checking 
				// each of them
				
				if (this.checkSolved()) {
					// if the cube is solved after the last rotation, get out of the method and keep that
					// solution
					return;
						
				}
				
				this._moves.remove(currentMove); // remove the current instruction index
				
				for (int counter = 0; counter < 3; counter++) {
					// if the cube is not solved after the last rotation 
					// (not solved for that given combination of moves)
					// undo the last move by rotating the current face 3 more times clockwise
					
					if (faceIndex == 0) {
							
						this.rotateFront();
							
					}
					else if (faceIndex == 1) {
							
						this.rotateTop();
							
					}
					else {
						this.rotateLeft();
					}
						
				}
					
			}
				
		}
		
	}
	
	public boolean checkSolved() {
		// checks if the current cube is solved
		
		for (_colours [] face : this._cube) {
			// for each face in the cube, if all the colours are not the same as the 
			// first colour, then it's not solved
			// otherwise, it is solved.
			for (int counter = 0; counter < 4; counter++) {
				
				if (face[counter] != face[0]) {
					
					return false;
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
	public void rotateFront () {
		// rotates front face clockwise
		_colours [][] temp = new _colours [6][4];
		
		for (int counter = 0; counter < 6; counter++) {
			// clones the original 2d array so that the colours can be
			// swapped without changing the cube that you're swapping from
			
			temp[counter] = this._cube[counter].clone();
			
		}
		this._cube[2][1] = temp[4][0];
		this._cube[2][2] = temp[4][1];
		this._cube[3][0] = temp[5][3];
		this._cube[3][3] = temp[5][2];
		this._cube[4][0] = temp[3][3];
		this._cube[4][1] = temp[3][0];
		this._cube[5][2] = temp[2][1];
		this._cube[5][3] = temp[2][2];
		
		for (int counter = 0; counter < 4; counter++) {
			// rotate the colours on the face being turned
			this._cube[0][(counter+1) % 4] = temp[0][counter];
			
		}
	}
	
    public void rotateTop () {
		// rotates top face clockwise
    	_colours [][] temp = new _colours [6][4];
		
		for (int counter = 0; counter < 6; counter++) {
			// clones the original 2d array so that the colours can be
			// swapped without changing the cube that you're swapping from
			
			temp[counter] = this._cube[counter].clone();
			
		}
        for (int counter = 0; counter < 4; counter++) {
        	// rotate the colours on the face being turned
			this._cube[2][(counter+1) % 4] = temp[2][counter];
			
		}
		this._cube[0][0] = temp[5][0];
		this._cube[0][3] = temp[5][3];
		this._cube[1][0] = temp[4][0];
		this._cube[1][3] = temp[4][3];
		this._cube[4][0] = temp[0][0];
		this._cube[4][3] = temp[0][3];
		this._cube[5][0] = temp[1][0];
		this._cube[5][3] = temp[1][3];
		
        
	}
	
    public void rotateLeft () {
	    // rotates the left face of the cube clockwise
    	_colours [][] temp = new _colours [6][4];
		
		for (int counter = 0; counter < 6; counter++) {
			// clones the original 2d array so that the colours can be
			// swapped without changing the cube that you're swapping from
			
			temp[counter] = this._cube[counter].clone();
			
		}
		this._cube[0][3] = temp[2][3];
		this._cube[0][2] = temp[2][2];
		this._cube[1][0] = temp[3][2];
		this._cube[1][1] = temp[3][3];
		this._cube[2][3] = temp[1][1];
		this._cube[2][2] = temp[1][0];
		this._cube[3][3] = temp[0][3];
		this._cube[3][2] = temp[0][2];
	
        for (int counter = 0; counter < 4; counter++) {
        	// rotate the colours on the face being turned
			this._cube[4][(counter+1) % 4] = temp[4][counter];
			
		}
    }
    
    public void printMoves () {
    	// prints out all the moves to solve the cube
    	
    	for (int counter = 0; counter < this._moves.size(); counter++) {
    		
    		System.out.println(this._moves.get(counter));
    		
    	}
    	
    	if (this._moves.size() == 0) {
    		// if no moves were able to be generated (ie the cube could not
    		// be solved), say that the cube doesnt have a solution
    		System.out.println("This cube is not solvable");
    	}
    	else {
    		System.out.println("Solved");
    	}
    	
    }

}
