
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
public class RubiksStub {

		static ArrayList<String> moveTrack = new ArrayList<String>();
		public static void main(String[] args) {
			
	        Scanner read = new Scanner(System.in);
			System.out.println("Enter the configuration of the cube");
			
			String [][] cubeInput2d = {{"B","R","B","O"}, {"W", "G", "G", "R"}, {"Y","R","W","R"}, {"G","O","O","O"}, {"B","Y","Y","G"}, {"B","W","Y","W"}};
      
			Cube rubiksCube = new Cube(cubeInput2d);
			
			//solveCube(cubeInput2d);
			rubiksCube.solveCube();
			for (int counter = 0; counter < moveTrack.size(); counter++) {
				
				System.out.println(moveTrack.get(counter));
				
			}
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
		
		public static String [] twoToOne (String [][] twoDimArray) {
			
			String [] array1D = new String [24];
			
			for (int counter1 = 0; counter1 < 6; counter1++) {
				
				for (int counter2 = 0; counter2 < 4; counter2++) {
					
					array1D[4*counter1 + counter2] = twoDimArray[counter1][counter2];
					
				}
				
			}
			
			return array1D;
			
		}

		public static String [][] oneToTwo (String [] oneDimArray) {
			
			String [][] twoDArray = new String [6][4];
			
			for (int counter1 = 0; counter1 < 6; counter1++) {
				
				for (int counter2 = 0; counter2 < 4; counter2++) {
					
					twoDArray[counter1][counter2] = oneDimArray[4*counter1 + counter2];
					
				}	
			}
			
			return twoDArray;
			
		}

	}
