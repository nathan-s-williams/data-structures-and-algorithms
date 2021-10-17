/*******************************************************************************
 * Project 3: 
 * 
 *
 * Created on 9/25/21 By Nathan Williams (nsw200000)
 * Course: 3345.005 Data Structures and Introduction to Algorithmic Analysis
 * @author nate
 * 
 * Additional Note:
 * 
 * 

 * Licensing Information:  You are free to use or extend these projects for
 * personal and educational purposes provided that (1) you do not distribute
 * or publish solutions, (2) you retain this notice, and (3) you provide clear
 * attribution to UT Dallas, including a link to http://cs.utdallas.edu.
 *
 * This file is part of Project for CE|CS|SE 3345: DataStructure 
 * and Introduction to Algorithms.
 * 
 * Anjum Chida (anjum.chida@utdallas.edu)
 *
********************************************************************************/
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class P3Driver {
	public static void main(String[] args) {

		Scanner in;
		if (args.length != 2) {
			System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
			System.exit(0);

		}
		try {
			File input_file = new File(args[0]);
			in = new Scanner(input_file);
			in.useDelimiter(":");	//Change delimiter to ":".
			File output_file = new File(args[1]);
			PrintWriter out;
			out = new PrintWriter(output_file);
			
			LazyBinarySearchTree bst = new LazyBinarySearchTree();

			String operation = "";
			int lineno = 0;

			int key;
			boolean result;
			String str;
			
			whileloop: while (in.hasNext()) {
				lineno++;

				operation = in.next();
//				if (operation.charAt(0) == '#') { 	//DELETE
//					in.nextLine();
//					continue;
//				}
				switch (operation) {
//				case "End":							//DELETE
//					break whileloop;
				case "Insert":
					try {
						key = in.nextInt();
						result = bst.insert(key);
						// result = Insert the item into BST and get true or false
						out.println(result ? "True" : "False");
					} catch (IllegalArgumentException e) {
						out.println("Error in insert: IllegalArgumentException raised");
					}

					break;
					
				case "Contains":
					try {
						key = in.nextInt();
						result = bst.contains(key);
						out.println(result ? "True" : "False");
					} catch (IllegalArgumentException e) {
						out.println("Error in insert: IllegalArgumentException raised");
					}
					break;
					
				case "Delete":
					try {
						key = in.nextInt();
						result = bst.delete(key);

						out.println(result ? "True" : "False");
					} catch (IllegalArgumentException e) {
						out.println("Error in insert: IllegalArgumentException raised");
					}
					break;
					
				case "PrintTree":
					out.println(bst.toString());
					break;
					
				case "PrintTotal":
					// Call the printtotal method of the linkedlist and print the given int into the
					// output file.
					int total = LL.printTotal();
					out.println(total);
					break;

				default:
					out.println("ERROR");
					in.nextLine();

				}

			}
			in.close();
			out.close();

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

}
