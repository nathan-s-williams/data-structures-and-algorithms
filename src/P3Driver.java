/*******************************************************************************
 * Project #3
 * Purpose:
 * Implement in java a binary search tree with lazy deletion. The BST class
 * should contain a nested tree node class that is used to implement the BST
 * 
 * Created on 10/11/21 By Nathan Williams (nsw200000)
 * Course: 3345.005 Data Structures and Introduction to Algorithmic Analysis
 *
********************************************************************************/
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Driver method for LazyBinarySearchTree class.
 * Takes an input file from the command line and executes all commands using the LazyBinarySearchTree class.
 * Output is stored in a file and sent to another location specified in the command line.
 * @author nate
 *
 */
public class P3Driver {
	public static void main(String[] args) {

		Scanner in;							//Declare standard input
		if (args.length != 2) {				//Check if correct amount of args passed from command line.
			System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
			System.exit(0);

		}
		try {
			File input_file = new File(args[0]);	//Declare and initialize input file and set standard input to file.
			in = new Scanner(input_file);
			in.useDelimiter("[:\n\r]");				//Change delimiter to ":", (carriage return) and (new line).
			File output_file = new File(args[1]);	//Set standard out to file location specified in command line.
			PrintWriter out;
			out = new PrintWriter(output_file);
			
			LazyBinarySearchTree bst = new LazyBinarySearchTree();	//Initialize lazy BST.

			String operation = "";

			int key;
			boolean result;
			
			whileloop: while (in.hasNext()) {
				operation = in.next();
				switch (operation) {
				case "":							//Break switch if string is empty. This is the space between carriage return and new line.
					break;
				case "Insert":						//Execute insert with value given in file.
					try {
						key = in.nextInt();
						result = bst.insert(key);
						// result = Insert the item into BST and get true or false
						out.println(result ? "True" : "False");
					} catch (IllegalArgumentException e) {		//Print error if input is out of range.
						out.println("Error in insert: IllegalArgumentException raised");
					} catch (InputMismatchException mm) {		//Print error if insert given without number, etc.
						out.println("Error in line: " + operation);
					}

					break;
					
				case "Contains":					//Execute contains with value given in file.
					try {
						key = in.nextInt();
						result = bst.contains(key);
						out.println(result ? "True" : "False");
					} catch (IllegalArgumentException e) {		//Print error if contains is out of range.
						out.println("Error in insert: IllegalArgumentException raised");
					} catch (InputMismatchException mm) {		//Print error if contains given without number, etc.
						out.println("Error in line: " + operation);
					}
					
					break;
					
				case "Delete":						//Execute delete with value given in file.
					try {
						key = in.nextInt();
						result = bst.delete(key);

						out.println(result ? "True" : "False");
					} catch (IllegalArgumentException e) {		//Print error if delete is out of range.
						out.println("Error in insert: IllegalArgumentException raised");
					} catch (InputMismatchException mm) {		//Print error if delete given without number, etc.
						out.println("Error in line: " + operation);
					}
					
					break;
					
				case "PrintTree":
					out.println(bst.toString());
					break;
					
				case "FindMin":
					out.println(bst.findMin());
					break;

				case "FindMax":
					out.println(bst.findMax());
					break;
					
				case "Height":
					out.println(bst.height());
					break;
					
				case "Size":
					out.println(bst.size());
					break;
					
				default:
					out.println("Error in Line: " + operation);

				}

			}
			in.close();
			out.close();

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

}
