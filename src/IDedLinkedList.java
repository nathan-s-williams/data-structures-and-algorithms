
public class IDedLinkedList<T extends IDedObject> {
	
	
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
		
	}
	
	public boolean insertAtFront(T x) {
		return false; //placeholder
	}
	
	public T deleteFromFront() {
		T x = null;
		return x; //placeholder
	}
	
	T delete(int id) {
		T x = null;
		return x; //placeholder
	}
	
	public int printTotal() {
		return 0; //placeholder
	}
}
