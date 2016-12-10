import java.util.ArrayList;
public class Cube {

	private enum _colours {
		
		B,
		R,
		W,
		Y,
		O,
		G
		
	}
	private int _NUMBER_OF_FACES = 6;
	private int _NUMBER_OF_SQUARE_FACE = 4;
	private String[][] _cube = new String[this._NUMBER_OF_FACES][this._NUMBER_OF_SQUARE_FACE];
	private ArrayList<String> _moves = new ArrayList<String>();
	
	public Cube (String [][] cubeInput) {
		// constructor to initialize cube 
		this._cube = cubeInput;
		
	}
	
	public String [][] getCube () {
		
		return this._cube;
		
	}
	
	public void solveCube() {
		// solves the cube recursively
		
		ArrayList<String> solution = new ArrayList<String>();
		
		for (int counter = 0; counter < 33; counter++) {
			// up to 33 because 33 is the maximum number of moves that a 2 by 2 rubiks cube 
			// can be solved in if it is solvable.
			if (!this.checkSolved()) {
				
				solution = this.getMoves(0, counter);
				// asking - can the cube be solved in each number of moves about up to max number of moves.
			}
			else {
				break;
			}
			
			
		}
		this.printMoves();
	}
	
	public ArrayList<String> getMoves (int count, int iterator) {
		
		if (count < iterator) {
			
			
			if (this.checkSolved()) {
				
				return this._moves;
			
			}
			else {
				
				for (int counter = 0; counter < 3; counter++) {
					
					if (counter == 0) {
						this._moves.add("Rotate front");
						this.rotateFront();
						
					}
					else if (counter == 1) {
						this._moves.add("Rotate top");
						this.rotateTop();
						
					}
					else {
						this._moves.add("Rotate left");
						this.rotateLeft();
					}
					
					this._moves = getMoves(count+1,iterator); // move onto the next rotation (next recursive branch)
					
					if (this.checkSolved()) {
						
						return this._moves;
						
					}
					
					for (int counter2 = 0; counter2 < 3; counter2++) {
						// undo the current move by rotating it 3 times
						if (counter == 0) {
							
							this.rotateFront();
							
						}
						else if (counter == 1) {
							
							this.rotateTop();
							
						}
						else {
							this.rotateLeft();
						}
						
					}
					this._moves.remove(count); // remove the current instruction index
				}
				
				
			}
			
			
		}
		return this._moves;
		
	}
	
	public boolean checkSolved() {
		// checks if the current cube is solved
		boolean cubeSolved = false;
		
		for (String [] face : this._cube) {
			
			for (int counter = 0; counter < 4; counter++) {
				
				if (face[counter] == face[0]) {
					
					cubeSolved = true;
					
				}
				else {
					
					return false;
					
				}
				
			}
			
		}
		
		return cubeSolved;
		
	}
	
	public void rotateFront () {
		// rotates front face clockwise
		String [][] temp = new String [6][4];
		
		for (int counter = 0; counter < 6; counter++) {
			
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
			
			this._cube[0][(counter+1) % 4] = temp[0][counter];
			
		}
	}
	
    public void rotateTop () {
		// rotates top face clockwise
        String [][] temp = new String [6][4];
		
		for (int counter = 0; counter < 6; counter++) {
			
			temp[counter] = this._cube[counter].clone();
			
			
		}
        for (int counter = 0; counter < 4; counter++) {
			
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
        String [][] temp = new String [6][4];
		
		for (int counter = 0; counter < 6; counter++) {
			
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
			
			this._cube[4][(counter+1) % 4] = temp[4][counter];
			
		}
    }
    
    public void printMoves () {
    	// prints out all the moves to solve the cube
    	for (int counter = 0; counter < this._moves.size(); counter++) {
    		
    		System.out.println(this._moves.get(counter));
    		
    	}
    	
    	if (this._moves.size() == 0) {
    		System.out.println("This cube is not solvable");
    	}
    	
    }

}
