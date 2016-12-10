
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
public class RubiksStub {

		public static void main(String[] args) {
			
	        Scanner read = new Scanner(System.in);
			System.out.println("Enter the configuration of the cube");
			String [] cubeInput1d = new String[24];
			String [][] cubeInput2d = {{"Y","W","G","G"}, {"B", "O", "G", "B"}, {"R","O","R","R"}, {"B","R","B","O"}, {"W","W","Y","W"}, {"Y","Y","O","G"}};
      
			Cube rubiksCube = new Cube(cubeInput2d);
		
			rubiksCube.solveCube();
            for (int counter = 0; counter < 6; counter++) {
				
				for (int counter2 = 0; counter2 < 4; counter2++) {
					
					System.out.println(rubiksCube.getCube()[counter][counter2]);
					
				}
				
				
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
