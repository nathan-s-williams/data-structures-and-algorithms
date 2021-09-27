
public class IDedLinkedList<T extends IDedObject> {
	
	private Node<T> head;
	private Node<T> tail;
	private Node<T> searchNode;
	private Node<T> searchPreviousNode;
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
		if(size == 0)
			return null;
		
		searchNode = head;
		
		while(searchNode.data.getID() != x && searchNode != null) {
			searchPreviousNode = searchNode;
			searchNode = searchNode.next;
		}
		
		if(searchNode != null)
			return searchNode.data;
		return null;
		
	}
	
	public boolean insertAtFront(T x) {
		if(findID(x.getID()) != null)
			return false;
		
		if(isEmpty()) {
			head.data = x;
			
		}
		else {
			Node<T> NewNode = new Node<T>(x, head);
			head = NewNode;	
		}
		
		size++;
		return true;
	}
	
	public T deleteFromFront() {
		if(isEmpty())
			return null;
		
		Node<T> deletedNode = head;
		head = new Node<T>(head.next.data,head.next.next);
		
		size--;
		return deletedNode.data;
	}
	
	T delete(int id) {
		if(isEmpty())
			return null;
		
		if(findID(id) == null)
			return null;

		if(searchPreviousNode == null)
			return deleteFromFront();	//Node is at head. 
		
		searchPreviousNode.next = searchNode.next;
		size--;
		return searchNode.data;
	}
	
	public int printTotal() {
		if(isEmpty())
			return -1;
		return size;
	}
}
