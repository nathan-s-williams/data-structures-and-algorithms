/************************************************************************************
 * Project 1: Sieve of Erotosthenes
 * Write a java program that computes all prime numbers less than or equal to
 * some number N. The algorithm used is Sieve of Erotosthese.
 * 
 * Created on 9/4/21 By Nathan Williams
 * Course: 3345.005
 * @author nate
 *
************************************************************************************/

import java.util.*;

public class Project_1 {
	//static int N;						//Declare variable for user input.
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int N = 0;							
		boolean inputError;				//Declare variable for input validation.
		
		System.out.print("Please enter a positive integer value for which the program will "
				+ "find all primes up to the specified number: ");
		
		//NOTE TO SELF. CHECK IF NUMBER IS > 1!!!!!!!!
		do {
			inputError = false;
			try {
				N = console.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("\n\nInvalid input. Please ensure that you enter a positive"
						+ " integer.");
				System.out.print("Please enter another integer value: ");
				console.nextLine();		//Force buffer to move to next line in preparation for new input.
				inputError = true;		//Set inputError to true for loop to return to beginning.
			}
		} while(inputError);
		
		boolean[] boolArray = new boolean[N + 1];	//Declare boolean array. Offset by +1 to match array index with
													//counting integers (i.e. 0,1,2,3... -> 1,2,3...).
		for(int i = 2; i < boolArray.length; i++) {
			boolArray[i] = true;					//Note - first 2 elements will always be false since we do not
													//consider them in this algorithm.
		}
		
		
		int sqrtN = ((int) Math.pow(N,0.5));		//Create for loop max length
		for(int i = 2; i <= sqrtN; i++) {
			if(boolArray[i]) {
				for(int j = (int) Math.pow(i,2);j < boolArray.length; j += i) {
					boolArray[j] = false;
				}
			}
		}
		
		for(int i = 2; i < boolArray.length; i++) {
			if(boolArray[i])
				System.out.printf("%d ", i);
		}
		

	}

}
