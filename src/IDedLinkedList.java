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

public class IDedLinkedList<T extends IDedObject> {
	
	private Node<T> head;	//sentinel starting node
	private Node<T> tail;	//sentinel ending node
	private Node<T> current;
	private Node<T> previous;
	private int size;
	
	private static class Node<T extends IDedObject> {
		T data;
		public Node<T> next;
		
		public Node(T d, Node<T> n){
			data = d;
			next = n;
		}
	}
	
	IDedLinkedList() {
		makeEmpty();
	}
	
	public void makeEmpty() {
		head = new Node<T>(null, null);
		tail = new Node<T>(null, null);
		head.next = tail;
		size = 0;
	}
	
	public boolean isEmpty() {
		if(size == 0) 
			return true;
		
		return false;
	}
	
	public T findID(int x) {
		current = null;
		previous = null;
		
		if(size == 0)
			return null;
		
		current = head.next;
		
		while(current != tail && current.data.getID() != x) {
			previous = current;
			current = current.next;
		}
		
		return current.data;	//current will return T if it exists or null if it doesn't exit. This is intended.
		
	}
	
	public boolean insertAtFront(T x) {
		if(findID(x.getID()) != null)
			return false;
		
		Node<T> NewNode = new Node<T>(x, head.next);
		head.next = NewNode;
		
		size++;
		return true;
	}
	
	public T deleteFromFront() {
		if(isEmpty())
			return null;
		
		Node<T> deletedNode = head.next;
		head.next = deletedNode.next;
		
		size--;
		return deletedNode.data;
	}
	
	T delete(int id) {
		if(isEmpty())
			return null;
		
		if(findID(id) == null)
			return null;

		if(previous == null) {	//Previous is null when the node is in the front.
			size--;
			return deleteFromFront();
		} 
		
		previous.next = current.next;
		size--;
		return current.data;
	}
	
	public int printTotal() {
		if(isEmpty())
			return -1;
		
		int sum = 0;
		current = null;
		current = head.next;
		while(current.data != null) {
			sum += current.data.getID();
			current = current.next;
		}
		
		return sum;
	}
}
