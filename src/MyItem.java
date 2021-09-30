/*******************************************************************************
 * Project 2: 
 * Implement in Java a singly linked list of a specific generic type. Project
 * consists of one interface and three user defined classes that implement a
 * list so as to take input from a file and output another based on certain 
 * linked list operations.
 *
 * Created on 9/25/21 By Nathan Williams (nsw200000)
 * Course: 3345.005 Data Structures and Introduction to Algorithmic Analysis
 * @author nate
********************************************************************************/

import java.util.*;

public class MyItem implements IDedObject{

	private int itemID;
	private int itemPrice;
	private List<Integer> itemDescription;
	
	MyItem( int id, int price, List<Integer> arr){
		setID(id);
		setPrice(price);
		setItemDescription(arr);
	}
	
	public void setID(int id) {
		itemID = id;
	}
	
	public void setPrice(int price) {
		itemPrice = price;
	}
	
	public void setItemDescription(List<Integer> arr) {
		itemDescription = new LinkedList<Integer>(arr);
	}
	
	public void addItemDescription(Integer num) {
		itemDescription.add(num);
	}
	
	
	public boolean deleteItemDescription(Integer num) {
		java.util.Iterator<Integer> itr = itemDescription.iterator();
		
		if (itemDescription.isEmpty())
			return false;
		
		while(itr.hasNext()) {
			if(num.compareTo(itr.next()) == 0) {
				itr.remove();
				return true;
			}
		}
		
		return false;
	}
	
	public int getID() {
		return itemID;
	}
	
	public int getPrice() {
		return itemPrice;
	}

	public List<Integer> getItemDescription(){
		return itemDescription;
	}
	
	public String printItemDescription() {
		String str = "";
		for (Integer item : itemDescription) {
			str += item + " ";
		}
		
		return str;
	}

	public String printID() {
		return getID() + " " + getPrice() + " " + printItemDescription();
		
	}

}
