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
		System.out.println("Enter the configuration of the cube");
		String [] cubeInput1d = new String[24];
		String [][] cubeInput2d = {{"G","O","W","B"}, {"B", "Y", "O", "W"}, {"R","W","O","Y"}, {"B","G","R","G"}, {"W","O","G","R"}, {"B","Y","Y","R"}};
      
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
			
		if (rubiksCube.checkSolved()) {
				
			System.out.println("Solved");
				
		}
		else {
				
			System.out.println("Not solved");
				
		}
	}
		
	public static String [][] oneToTwo (String [] oneDimArray) {
		// converts 1d input array into 2d array that will be used in program
		String [][] twoDArray = new String [6][4];
			
		for (int counter1 = 0; counter1 < 6; counter1++) {
				
			for (int counter2 = 0; counter2 < 4; counter2++) {
					
				twoDArray[counter1][counter2] = oneDimArray[4*counter1 + counter2];
					
			}	
		}
			
		return twoDArray;
			
		}

	}
