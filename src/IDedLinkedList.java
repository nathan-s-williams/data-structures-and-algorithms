
public class IDedLinkedList<T extends IDedObject> {
	
	private Node<T> head;
	private Node<T> tail;
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
		
		while(current.data != null && current.data.getID() != x) {	//Check for null first to prevent null pointer error
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
		return size;
	}
}
