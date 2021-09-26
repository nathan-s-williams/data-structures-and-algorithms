/************************************************************************************
 * Project 1: Sieve of Erotosthenes
 * Write a java program that computes all prime numbers less than or equal to
 * some number N. The algorithm used is Sieve of Erotosthese.
 * 
 * Created on 9/4/21 By Nathan Williams (nsw200000)
 * Course: 3345.005 Data Structures and Introduction to Algorithmic Analysis
 * @author nate
 *
************************************************************************************/

import java.util.*;

public class Project_1 {
	
	public static void main(String[] args) {
		//Declare scanner object, int variable for input and boolean variable for input validation.
		Scanner console = new Scanner(System.in);
		int N = 0;							
		boolean inputError;
		
		//Prompt user for input.
		System.out.print("Please enter an integer value greater than 2 and the program will "
				+ "find all primes up to that number: ");
		
		//Loop that collects user input. If input validation fails prompt user to reenter integer.
		do {
			inputError = false;
			try {
				N = console.nextInt();
				//Check if input is greater than or equal to 2. If not, prompt user again and clear buffer. 
				//Set inputError bool variable to true
				if(N < 2) {
					System.out.print("\nInvalid input. Please ensure that you enter an integer value greater than or equal to 2: ");
					console.nextLine();
					inputError = true;
				}
			}
			//Catch input mismatch. Prompt user again and clear buffer. Set inputError bool variable to true.
			catch(InputMismatchException e) {
				System.out.print("\nInvalid input. Please ensure that you enter an integer value greater than or equal to 2: ");
				console.nextLine();
				inputError = true;
			}
		} while(inputError);
		
		boolean[] boolArray = new boolean[N + 1];	//Declare boolean array. Offset by +1 to match array index with
													//counting integers (i.e. 0,1,2,3... -> 1,2,3...).
		for(int i = 2; i < boolArray.length; i++) {
			boolArray[i] = true;					//Note - first 2 elements will always be false since we do not
													//consider them in this algorithm.
		}
		
		int sqrtN = ((int) Math.pow(N,0.5));		//Set loop max variable equal to sqrt of N.
		for(int i = 2; i <= sqrtN; i++) {			//Loop through array starting at 2 to sqrt(N).
			if(boolArray[i]) {						//If boolArray index value is true move to nested loop.
													//Nested loop will set all i^2 + i integers to false.
				for(int j = (int) Math.pow(i,2);j < boolArray.length; j += i) {
					boolArray[j] = false;
				}
			}
		}
		
		//Print all integers in array that have their bool values set to true.
		for(int i = 2; i < boolArray.length; i++) {
			if(boolArray[i])
				System.out.printf("%d ", i);
		}
	}
}
