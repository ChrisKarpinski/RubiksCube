import java.util.Random;
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
	
	public Cube (String [][] cubeInput) {
		
		this._cube = cubeInput;
		
	}
	
	public String [][] getCube () {
		
		return this._cube;
		
	}
	
	public void solveCube() {
		// solves the cube recursively
		Random rand = new Random();
		int faceChoice = rand.nextInt(3) + 1;
		
		if (faceChoice == 1) {
			System.out.println("Turn top face cw");
			this.rotateTop();
		}
		else if (faceChoice == 2) {
			System.out.println("Turn left face cw");
			this.rotateLeft();
			
		}
		else {
			System.out.println("Turn front face cw");
			this.rotateFront();
		}
		
		
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

}
