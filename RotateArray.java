// Abraham Aldana
// Chad Manning
// CMPS 3390 - HW03
// RotateArray.java

/**
 * TODO make sure to put names
 */
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RotateArray {
	/*
	 *Prints the command menu
	 */
	public static void printMenu() {
		System.out.print("		======================== CS 3390 Assignment 1 ===============\n" +
				"		G/g:   Ask for a N, and generate N X N array of integers and\n" +
				"               fill the array with random integers 3 digits or say\n" +
				"               between 100 and 999. \n" +
				"\n" +
				"		S/s/ : Display the array in N columns and N rows.\n" +
				"                       ascending order.\n" +
				"\n" +
				"		V/v/ : Rotate each of rechtagles circle one position.\n" +
				"\n" +
				"		--------------------------------------------------\n" +
				"		H/h/?: Display this menu.\n" +
				"		E/e  : Exit\n" +
				"		==============================================================\n");
	}
	/*
	 * Generates and fills a 2d array
	 * @param size desired size of 2d array
	 * @return arr address to 2d array of desired size
	 */
	public static char[][] generateArray(){
		char [][] arr = new char[4][4];
		for(int i = 0; i < 4; ++i){
			arr[0][i] = "0123".charAt(i);
		}
		for(int i = 0; i < 4; ++i){
			arr[1][i] = "4567".charAt(i);
		}
		for(int i = 0; i < 4; ++i){
			arr[2][i] = "89ab".charAt(i);
		}
		for(int i = 0; i < 4; ++i){
			arr[3][i] = "cdef".charAt(i);
		}

		return arr;
	}

	/*
	 * prints contents of array
	 *
	 * @param arr address of 2d array
	 */
	public static void printArr(char[][] arr) {
		for (int row = 0; row < arr.length; ++row) {
			System.out.print("\t\t\t\t");
			for (int col = 0; col < arr.length; ++col) {

				System.out.printf("%3c ", arr[row][col]);
			}
			System.out.println("\n");
		}
		System.out.println("\n");
	}

	/*
	 * Rotate array in clockwise pattern
	 *
	 * @param arr 2d array to be rotated
	 */
	public static void rotArray(char[][] arr) {
		int sRow, sCol, eRow, eCol;
		sRow = sCol = 0;
		eRow = eCol = arr[0].length - 1;

		// iterates through all possible rings of array
		while (sCol < eCol) {
			char temp = arr[sRow][sCol]; // temp value for data lost during reassignment

			for(int i = 0; i < (eRow-sRow); ++i){
				arr[sRow+i][sCol] =  arr[sRow+i+1][sCol];
			}
			for(int i = 0; i < (eRow-sCol); ++i){
				arr[eRow][sCol+i] =  arr[eRow][sCol+i+1];
			}
			for(int i = 0; i < (eRow-sRow); ++i){
				arr[eRow-i][eCol] =  arr[eRow-i-1][eCol];
			}
			for(int i = 0; i < (eCol-sCol); ++i){
				arr[sRow][eCol-i] =  arr[sRow][eCol-i-1];
			}
			arr[sRow][sCol+1] = temp;

			sRow = sCol += 1;
			eRow = eCol -= 1;
		}
		printArr(arr);
	}

	public static void main(String[] args) {
		char array[][] = null;
		char c = 'c';
		Scanner scanner = new Scanner(System.in);

		while (c != 'e') {
			System.out.print("Enter H/h/?, or command : ");
			c = scanner.next().charAt(0);

			switch (c) {
				case 'g':
				case 'G':
					array = generateArray();
					System.out.printf("%dx%d array created.\n\n", 4, 4);
					break;
				case 's':
				case 'S':
					int cycle = 0;
					while(c != 'n' && c != 'N') {
						if(cycle < 10) {
							rotArray(array);
							++cycle;
						} else {
							System.out.printf("\nContinue? (y/n) : ");
							cycle = 0;
							c = scanner.next().charAt(0);
						}

						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e){}
					}
					break;
				case 'v':
				case 'V':
					if(array != null)
						printArr(array);
					break;
				case 'e':
				case 'E':
					break;
				case 'h' :
				case 'H' :
				case '?' :
					printMenu();
					break;
				default:
					System.out.println("Unknown command.");
					break;
			}
		}
		scanner.close();
		System.out.println("Have a good day!");

	}

}
