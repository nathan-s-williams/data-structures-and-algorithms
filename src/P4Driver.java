import java.util.*;

public class P4Driver {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int choice;
		
		HashTableLinearProbe<Integer, String> LP = new HashTableLinearProbe<>();
		
		System.out.println(LP.insert(15, "Hello"));
		System.out.println(LP.insert(200, "Hello"));
		System.out.println(LP.insert(15, "Hello"));
		System.out.println(LP.insert(15, "Whatsup"));
		System.out.println(LP.delete(15));
		System.out.println(LP.insert(19, "Yo"));
		System.out.println(LP.insert(18, "Whatsup"));
		
/*

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
*/	
		console.close();

	}

}
