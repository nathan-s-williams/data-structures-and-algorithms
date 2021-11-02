import java.util.*;

public class P4Driver {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int choice, iKey = 0;
		String sKey = null, value = null;
		
		HashTableLinearProbe<Integer, String> iHT = new HashTableLinearProbe<>();
		HashTableLinearProbe<String, String> sHT = new HashTableLinearProbe<>();
		
//		System.out.println(LP.insert(15, "Hello"));
//		System.out.println(LP.insert(200, "Hello"));
//		System.out.println(LP.insert(15, "Hello"));
//		System.out.println(LP.insert(15, "Whatsup"));
//		System.out.println(LP.delete(15));
//		System.out.println(LP.insert(19, "Yo"));
//		System.out.println(LP.insert(18, "Whatsup"));
		
//		System.out.println(sHT.insert("first", "Hello"));
//		System.out.println(sHT.insert("second", "Hello"));
//		System.out.println(sHT.insert("second", "Whatsup"));
//		System.out.println(sHT.delete("first"));
//		System.out.println(sHT.insert("first", "Hello"));
//		System.out.println(sHT.insert("fourth", "Whatsup"));
//		System.out.println(sHT.insert("", "Whatsup"));
//		System.out.println(sHT);

loop:	do {
			try {
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
				case(1):	//Insert
					System.out.print("Enter key: ");
					if(console.hasNextInt()) {
						iKey = console.nextInt();
					}
					else {
						sKey = console.next();
					}
					System.out.println("Enter value: ");
					value = console.next();
					if(sKey == null) {
						System.out.println("Insert: " + ((iHT.insert(iKey, value)) ? "success" : "failed"));
						System.out.println();
					}
					else {
						System.out.println("Insert: " + ((sHT.insert(sKey, value)) ? "success" : "failed"));
						System.out.println();
					}
					
					break;
				case(2): //Find
					System.out.print("Enter key: ");
					if(console.hasNextInt()) {
						iKey = console.nextInt();
					}
					else {
						sKey = console.next();
					}
					
					if(sKey == null) {
						System.out.println("Value is: " + iHT.find(iKey));
						System.out.println();
					}
					else {
						System.out.println("Value is: " + sHT.find(sKey));
						System.out.println();
					}
					
					break;
				case(3): //Delete
					System.out.print("Enter key: ");
					if(console.hasNextInt()) {
						iKey = console.nextInt();
					}
					else {
						sKey = console.next();
					}
					
					if(sKey == null) {
						System.out.println("Delete: " + ((iHT.delete(iKey)) ? "success": "failed"));
						System.out.println();
					}
					else {
						System.out.println("Delete: " + ((sHT.delete(sKey)) ? "success": "failed"));
						System.out.println();
					}
					
					break;
				case(4): //getHashValue
					System.out.print("Enter key: ");
					if(console.hasNextInt()) {
						iKey = console.nextInt();
					}
					else {
						sKey = console.next();
					}
					
					if(sKey == null) {
						System.out.println("Hash Value: " + iHT.getHashValue(iKey));
						System.out.println();
					}
					else {
						System.out.println("Hash Value: " + sHT.getHashValue(sKey));
						System.out.println();
					}
					break;
				case(5):	//Quit
					System.out.println("Program Ended.");
					break loop;
				default:
					System.out.println("Error: Please enter a valid option.");
					break;
				
				}
				iKey = 0;
				sKey = null;
				value = null;
			
			} catch(InputMismatchException ime) {
				System.out.println(ime.toString());
				System.out.println("Please enter a valid option.");
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			
		}while(true);

		console.close();

	}

}
