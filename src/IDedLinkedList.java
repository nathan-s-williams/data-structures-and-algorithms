
public class IDedLinkedList<T extends IDedObject> {
	
	private Node<T> head;
	private Node<T> tail;
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
		size = 0;
	}
	
	public T findID(int x) {
		
		
		
		return null; //placeholder
		
	}
	
	public boolean insertAtFront(T x) {
		Node<T> NewNode = new Node<T>(x, head);
		head = NewNode;
		
		return true;
	}
	
	public T deleteFromFront() {
		Node<T> deletedNode = head;
		head = new Node<T>(head.next.data,head.next.next);
		
		return deletedNode.data;
	}
	
	T delete(int id) {
		T x = null;
		return x; //placeholder
	}
	
	public int printTotal() {
		return 0; //placeholder
	}
}
