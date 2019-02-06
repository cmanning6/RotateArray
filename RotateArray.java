/**
 * TODO make sure to put names
 */
import java.util.Scanner;

public class RotateArray {

	/*
	 *Prints the command menu 
	 */
	public static void printMenu() {
		System.out.print("		======================== CS 3990 Assignment 1 ===============\n" + 
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
	public static int[][] generateArray(int size){
			int[][] arr = new int[size][size];
			
			// fill array with values between 100-999
			for (int row = 0; row < arr.length; ++row) {
				for (int col = 0; col < arr.length; ++col) {
					arr[row][col] = (col + 1) + (row * arr.length);
				}
			}
			
			return arr;
	}
	
	/*
	 * prints contents of array
	 * 
	 * @param arr address of 2d array
	 */
	public static void printArr(int[][] arr) {
		for (int row = 0; row < arr.length; ++row) {
			for (int col = 0; col < arr.length; ++col) {
				System.out.printf("%3d ", arr[row][col]);
			}
			System.out.println("");
		}
		System.out.println();
	}

	/*
	 * Rotate array in clockwise pattern
	 * 
	 * @param arr 2d array to be rotated
	 */
	public static void rotArray(int[][] arr) {
		int start = 0, end = arr[0].length;

		/*
		 *TODO create functions to shift values instead of doing everything in rotArray()
		 */
		
		// iterates through all possible rings of array
		while ((end - start) > 1) {
			int temp1, temp2, temp3 = 0;

			temp1 = arr[start][end - 1]; // temp values for data lost during reassignment
			temp2 = arr[end - 1][end - 1];
			temp3 = arr[end - 1][start];

			// shift values on top of current ring
			for (int i = end - 1; i > start; --i) {
				arr[start][i] = arr[start][i - 1];
			}

			// shift values on right side of current ring
			for (int i = end - 1; i > start; --i) {
				arr[i][end - 1] = arr[i - 1][end - 1];
			}

			// shift values on bottom of current ring
			arr[start + 1][end - 1] = temp1; // Use temp to correct value
			for (int i = start + 1; i < end; ++i) {
				arr[end - 1][i - 1] = arr[end - 1][i];
			}

			// shift values on right side of current ring
			arr[end - 1][end - 2] = temp2; // Use temp to correct value
			for (int i = start + 1; i < end; ++i) {
				arr[i - 1][start] = arr[i][start];
			}
			arr[end - 2][start] = temp3; // Use temp to correct value

			--end;
			++start;
		}
	}

	public static void main(String[] args) {
		int array[][] = null;
		char c = 'c';
		Scanner scanner = new Scanner(System.in);

		while (c != 'e') {
			switch (c) {
			case 'c':
			case 'C':
				System.out.print("Enter H/h/?, or command : ");
				c = scanner.next().charAt(0);
				break;
			case 'g':
			case 'G':
				System.out.print("Enter a number that is 30 or below : ");
				{ //Use temp size value for only this instance
					int tmpSize = scanner.nextInt();
					if(tmpSize > 30) {
						System.out.println("\nNumber too large, resizing to 30");
						tmpSize = 30;
					}
					array = generateArray(tmpSize);
					System.out.printf("%dx%d array created.\n\n", tmpSize, tmpSize);
				}
				c = 'c';
				break;
			case 's':
			case 'S':
				rotArray(array);
				System.out.println("Array rotated one precision\n");
				c = 'c';
				break;
			case 'v':
			case 'V':
				if(array != null)
					printArr(array);
				c = 'c';
				break;
			case 'h' :
			case 'H' :
			case '?' :
				printMenu();
				c = 'c';
				break;
			default:
				System.out.println("Unknown command.");
				c = 'c';
				break;
			}
		}
		scanner.close();
		System.out.println("Have a good day!");

	}

}
