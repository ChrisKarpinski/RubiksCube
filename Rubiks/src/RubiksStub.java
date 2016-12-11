/****************************************************************************
 *
 * Created by: Chris Karpinski
 * Created on: Dec 2016
 * This program uses recursion to find the shortest way to solve a 2 by 2
 *     rubiks cube
 *
 ****************************************************************************/

import java.util.Scanner;
public class RubiksStub {

	public static void main(String[] args) {
			
	    Scanner read = new Scanner(System.in);
	    boolean validInput = false;
		System.out.println("Format for entering the cube: ");
		System.out.println("For the colours, enter only the capitalized first letter of each colour"
				+ " ex. enter B for Blue ");
		System.out.println("The square numbers start with the top right square and go clockwise");
		System.out.println();
		System.out.println("Enter the configuration of the cube: ");
		Cube._colours [] cubeInput1d = new Cube._colours[24];
		
		
		for (int counter = 0; counter < cubeInput1d.length; counter++) {
			
			validInput = false;
			
			if ((counter == 0)) {
				System.out.println();
				System.out.println("Front face: ");
				
			}
			
			if (counter == 4) {
				System.out.println();
				System.out.println("Back face: ");
				
			}
			
			if (counter == 8) {
				System.out.println();
				System.out.println("Top face: ");
				
			}
			
                        if (counter == 12) {
            	                System.out.println();
				System.out.println("Bottom face: ");
				
			}
            
                        if (counter == 16) {
            	                System.out.println();
				System.out.println("Left face: ");
				
			}
			
                        if (counter == 20) {
            	                System.out.println();
				System.out.println("Right face: ");
				
			}
            
			while (!validInput) {
				try {
					System.out.println("Square" + " " + ((counter % 4) + 1) + ": ");
					cubeInput1d[counter] = Cube._colours.valueOf(read.nextLine());
					validInput = true;
					
				} catch (IllegalArgumentException e) {
					
					System.err.println("Invalid input - please re-enter");
					validInput = false;
				} 
			}
			
			
		}
	
        Cube._colours [][] cubeInput2d = oneToTwo(cubeInput1d);
		Cube rubiksCube = new Cube(cubeInput2d);
		// instantiate the cube object
		
		if (rubiksCube.checkSolved()) {
				
			System.out.println("The cube is already solved!");
			// if the cube is already solved, tell the user
		}
		else {
			System.out.println("Calculating shortest move combo... Please wait");	
			rubiksCube.solveCube();
				
		}
			
	}
		
	public static Cube._colours [][] oneToTwo (Cube._colours [] oneDimArray) {
		// converts 1d input array into 2d array that will be used in program
		Cube._colours [][] twoDArray = new Cube._colours [6][4];
			
		for (int counter1 = 0; counter1 < 6; counter1++) {
				
			for (int counter2 = 0; counter2 < 4; counter2++) {
					
				twoDArray[counter1][counter2] = oneDimArray[4*counter1 + counter2];
					
			}	
		}
			
		return twoDArray;
			
		}

	}
