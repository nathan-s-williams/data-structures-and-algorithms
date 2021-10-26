import java.util.*;

public class P4Driver {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int choice;

loop:	do {
			try {
				System.out.print("Please choose an operation: ");
				choice = console.nextInt();
				switch(choice) {
				case(1):
					//Insert
					break;
				case(2):
					//Find
					break;
				case(3):
					//Delete
					break;
				case(4):
					//getHashValue
					break;
				case(5):
					//rehash
					break;
				case(6):
					//Quit
					System.out.println("Program Ended.");
					break loop;
				default:
					System.out.println("Error: Please enter a valid option.");
					break;
					
				}
			
			} catch(InputMismatchException ime) {
				System.out.println(ime.toString());
				System.out.println("Please enter a valid option.");
			}
			
		}while(true);
		
		console.close();

	}

}
