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
	private int _NUMBER_OF_FACES = 6;
	private int _NUMBER_OF_SQUARE_FACE = 4;
	private _colours[][] _cube = new _colours[this._NUMBER_OF_FACES][this._NUMBER_OF_SQUARE_FACE];
	private ArrayList<String> _moves = new ArrayList<String>();
	
	public Cube (_colours [][] cubeInput) {
		// constructor to initialize cube 
		this._cube = cubeInput;
		
	}
	
	public void solveCube() {
		// solves the cube recursively
		
		for (int counter = 0; counter < 33; counter++) {
			// up to 33 because 33 is the maximum number of moves that a 2 by 2 rubiks cube 
			// can be solved in if it is solvable.
			if (!this.checkSolved()) {
				
				this.getMoves(0, counter);
				// asking - can the cube be solved in each number of moves up to max number of moves?
			}
			else {
				// once a solution is found for a certain number of moves, stop looking for more solutions
				// and print out the shortest found solution
				this.printMoves();
				return;
			}
			
		}
		
	}
	
	public void getMoves (int currentMove, int movesToCheck) {
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
