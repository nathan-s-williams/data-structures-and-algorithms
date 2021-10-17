
public class LazyBinarySearchDriver {

	public static void main(String[] args) {
		LazyBinarySearchTree bst = new LazyBinarySearchTree();
		
//		bst.insert(98);
//		bst.insert(67);
		bst.insert(55);
//		bst.insert(45);
//		bst.insert(100);
//		bst.insert(75);
		bst.delete(55);
//		bst.insert(55);
//		bst.delete(55);
		
		System.out.println(bst.isEmpty());
		System.out.println();
		
		
//		Insert:98
//		Insert:67
//		Insert:55
//		Insert:45

	}

}
