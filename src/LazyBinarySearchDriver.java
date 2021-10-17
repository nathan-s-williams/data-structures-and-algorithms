
public class LazyBinarySearchDriver {

	public static void main(String[] args) {
		LazyBinarySearchTree bst = new LazyBinarySearchTree();
		
//		bst.insert(98);
//		bst.insert(67);
//		bst.insert(55);
//		bst.insert(45);
//		bst.insert(100);
//		bst.insert(75);
//		bst.delete(55);
//		bst.insert(55);
//		bst.delete(45);
//		bst.delete(75);
//		bst.delete(67);
//		bst.delete(98);
//		bst.delete(100);
		
		//height example 1
//		bst.insert(95);
//		bst.insert(67);
//		bst.insert(96);
//		bst.insert(55);
//		bst.insert(70);
//		bst.insert(99);
//		bst.insert(69);
//		bst.insert(75);
//		bst.insert(73);
		
		//height example 2
		System.out.println(bst.isEmpty());
		System.out.println(bst.insert(80));
		System.out.println(bst.insert(79));
		System.out.println(bst.insert(85));
		System.out.println(bst.insert(75));
		System.out.println(bst.insert(83));
		System.out.println(bst.insert(86));
		System.out.println(bst.insert(78));
		System.out.println(bst.insert(82));
		System.out.println(bst.insert(84));
		System.out.println(bst.insert(87));
		System.out.println(bst.insert(81));
		System.out.println(bst.insert(88));
		System.out.println(bst.insert(67));
		System.out.println(bst.insert(67));
		
		
		System.out.println();
		System.out.println(bst.delete(45));
		System.out.println(bst.delete(75));
		System.out.println(bst.delete(67));
		System.out.println(bst.delete(81));
		System.out.println(bst.insert(81));
		
		System.out.println();
		System.out.println(bst.isEmpty());
		System.out.println(bst.height());
		System.out.println(bst.contains(80));
		System.out.println(bst.findMax());
		System.out.println(bst.findMin());
		System.out.println(bst.size());
		System.out.println(bst.toString());
		
		
//		System.out.println(bst.insert(55));
//		System.out.println(bst.delete(55));
//		System.out.println(bst.insert(55));
//		System.out.println(bst.contains(99));
//		System.out.println(bst.findMax());
//		System.out.println(bst.findMin());
//		System.out.println(bst.size());
		
		
//		Insert:98
//		Insert:67
//		Insert:55
//		Insert:45
		
//		bst.insert(99);
//		System.out.println(bst.toString());

	}

}
