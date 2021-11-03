/***************************************************************************
 * Project 4: Hash Table
 * Implement a java hash structure using linear probing collision strategy.
 * @author nate
 * 
 * Created on 10/23/21 By Nathan Williams (nsw200000)
 * Course: 3345.005 Data Structures and Introduction to Algorithmic Analysis
***************************************************************************/
import java.util.*;

/**
 * Driver class used to test the HashTableLinearProbe class.
 * 
 * @author nate
 *
 */
public class P4Driver {

	/**
	 * Console based menu will allow user to insert, find, delete, getHashValue or quit.
	 * <Integer, String> and <String, String> hash table objects are used. If the user
	 * enters an integer for the key then the integer based table is used. If the user 
	 * enters a string, then the string based table is used.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		
		//Declare and instantiate input variables. iKey refers to an integer key and sKey refers to a string key.
		int choice, iKey = 0;
		String sKey = null, value = null;
		
		//Instantiate an integer table and string table.
		HashTableLinearProbe<Integer, String> iHT = new HashTableLinearProbe<>();
		HashTableLinearProbe<String, String> sHT = new HashTableLinearProbe<>();

loop:	do { //do while loop. Continues program until user exits.
			//try-catch block. Outputs error message for InputMismatchException and all other general exceptions.
			try {
				//Menu prompt
				System.out.println("===========================================");
				System.out.println("                   Menu");
				System.out.println("===========================================");
				System.out.println("Type 1 to <insert>.");
				System.out.println("Type 2 to <find>.");
				System.out.println("Type 3 to <delete>.");
				System.out.println("Type 4 to <get hash value>.");
				System.out.println("Type 5 to <quit>.");
				System.out.print("Please choose an operation: ");
				choice = console.nextInt();
				switch(choice) {
				case(1):	//Insert into integer or string table. 
					System.out.print("Enter key: ");
					//Set iKey if next value is int, otherwise string.
					if(console.hasNextInt()) {
						iKey = console.nextInt();
					}
					else {
						sKey = console.next();
					}
					
					System.out.print("Enter value: ");
					value = console.next();
					//If iKey was used, insert into int table. Otherwise, string table.
					if(sKey == null) {
						System.out.println("Insert: " + ((iHT.insert(iKey, value)) ? "success" : "failed"));
						System.out.println();
					}
					else {
						System.out.println("Insert: " + ((sHT.insert(sKey, value)) ? "success" : "failed"));
						System.out.println();
					}
					
					break;
				case(2): //Find value based off of key provided.
					System.out.print("Enter key: ");
					//Set iKey if next value is int, otherwise string.
					if(console.hasNextInt()) {
						iKey = console.nextInt();
					}
					else {
						sKey = console.next();
					}
					
					//If iKey was used, find in int table. Otherwise, string table.
					if(sKey == null) {
						System.out.print("Value: ");
						if(iHT.find(iKey) == null)
							System.out.println("No value found.");
						else
							System.out.println(iHT.find(iKey));
						System.out.println();
					}
					else {
						System.out.print("Value: ");
						if(sHT.find(sKey) == null)
							System.out.println("No value found.");
						else
							System.out.println(sHT.find(sKey));
						System.out.println();
					}
					
					break;
				case(3): //Delete element from array.
					System.out.print("Enter key: ");
					//Set iKey if next value is int, otherwise string.
					if(console.hasNextInt()) {
						iKey = console.nextInt();
					}
					else {
						sKey = console.next();
					}
					
					//If iKey was used, delete from int table. Otherwise, string table.
					if(sKey == null) {
						System.out.println("Delete: " + ((iHT.delete(iKey)) ? "success": "failed"));
						System.out.println();
					}
					else {
						System.out.println("Delete: " + ((sHT.delete(sKey)) ? "success": "failed"));
						System.out.println();
					}
					
					break;
				case(4): //Get hash value from key.
					System.out.print("Enter key: ");
					//Set iKey if next value is int, otherwise string.
					if(console.hasNextInt()) {
						iKey = console.nextInt();
					}
					else {
						sKey = console.next();
					}
					
					//If iKey was used, get hash value for int table. Otherwise, string table.
					if(sKey == null) {
						System.out.println("Hash Value: " + iHT.getHashValue(iKey));
						System.out.println();
					}
					else {
						System.out.println("Hash Value: " + sHT.getHashValue(sKey));
						System.out.println();
					}
					break;
				case(5):	//Quit program.
					System.out.println("Program Ended.");
					break loop;
				default:	//Error
					System.out.println("Error: Please enter a valid option.");
					break;
				
				}
				//Reset variable values
				iKey = 0;
				sKey = null;
				value = null;
				
			} catch(InputMismatchException ime) {
				System.out.println("Please enter a valid option.");
				console.nextLine();
			} catch(Exception e) {
				System.out.println(e.toString());
				console.nextLine();
			}
			
		}while(true);

		console.close();	//close console

	}

}
