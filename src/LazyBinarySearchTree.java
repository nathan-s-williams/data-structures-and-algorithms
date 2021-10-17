/********************************************************************************
 * Project #3
 * Purpose:
 * Implement in java a binary search tree with lazy deletion. The BST class
 * should contain a nested tree node class that is used to implement the BST
 * 
 * Created on 10/11/21 By Nathan Williams (nsw200000)
 * Course: 3345.005 Data Structures and Introduction to Algorithmic Analysis
 * 
 * UML Diagram
 * _______________________________________________________________
 * |															  |
 * |						LazyBinaryTree						  |
 * |______________________________________________________________|
 * |-root: TreeNode												  |
 * |______________________________________________________________|<----|
 * |-validateInput(key: int): void								  |     |
 * |+isEmpty(): boolean											  |     |
 * |+insert(key: int): boolean {throws IllegalArgumentException}  |		|
 * |-insertKey(k: int, n: TreeNode): TreeNode					  |     |
 * |+delete(key: int): boolean {throws IllegalArgumentException}  |		|
 * |-deleteKey(k: int, n: TreeNode): TreeNode					  |     |
 * |+findMin(): int												  |		|
 * |-findMinKey(minKey: int, n: TreeNode): int					  |     |
 * |+findMax(): int												  |		|
 * |-findMaxKey(maxKey: int, n: TreeNode): int					  |     |
 * |+contains(key: int): boolean {throws IllegalArgumentException}| 	|
 * |-containsNode(k: int, n: TreeNode): boolean					  |     |
 * |+toString(): String											  |		|
 * |-preorderPrint(str: String, n: TreeNode): String			  |     |
 * |+height(): int												  |		|
 * |-nodeHeight(n: TreeNode): int								  |     |
 * |+size(): int												  |		|
 * |															  |		|
 * |______________________________________________________________|		|
 * 								  										|
 * 				|-------------------------------------------------------|
 * 				|
 *  ____________|____________
 * |						 |
 * |		TreeNode		 |
 * |_________________________|
 * |-key: int				 |
 * |-leftChild: TreeNode	 |
 * |-rightChild: TreeNode	 |
 * |-deleted: boolean		 |
 * |-count					 |
 * |-deletedCount			 |
 * |_________________________|
 * |-treeNodeDelete(): void  |
 * |-treeNodeUndelete(): void|
 * |-compareNode(int): int	 |
 * |_________________________|
 * 
********************************************************************************/

/**
 * Class LazyBinarySearchTree:
 * Implementation of a binary search tree that instead of regular deletion,
 * performs lazy deletion. That is no node is physically deleted. Rather, it is
 * denoted as deleted via a boolean field in the TreeNode class.
 * 
 * Input limited to integers 1-99. Any integers or non-integers outside this
 * range, the program will throw an IllegalArgumentException.
 * 
 * Class will be used with another driver class that reads in a file
 * from the command line and outputs a subsequent file performing the commands
 * listed in the input file.
 * 
 * @author nate
 *
 */
public class LazyBinarySearchTree {
	TreeNode root;	//Tree root node.
	
	/**
	 * TreeNode:
	 * Node class representing each node of the lazy BST.
	 * 
	 * Data Members:
	 * key: int - integer input for each node.
	 * leftChild: TreeNode - Linked TreeNode that represents an integer lower
	 * than the current node's value.
	 * rightChild: TreeNode - Linked TreeNode that represents an integer higher
	 * than the current node's value.
	 * deleted: boolean - flag that marks whether or not the node has been deleted.
	 * count: static int - gives the total count of nodes in TreeNode class.
	 * deletedCount: static int - gives the total count of deleted nodes in TreeNode class.
	 * 
	 * @author nate
	 *
	 */
	private static class TreeNode{
		
		private int key;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private boolean deleted;
		private static int count = 0;
		private static int deletedCount = 0;
		
		TreeNode(int k){				//Constructor
			key = k;
			leftChild = null;
			rightChild = null;
			deleted = false;
			count++;
		}
		/**
		 * Mark node as deleted and increment deletedCount.
		 */
		private void treeNodeDelete() {
			deleted = true;
			deletedCount++;
		}
		
		/**
		 * Mark node as not deleted and decrement deletedCount
		 */
		private void treeNodeUndelete() {
			deleted = false;
			deletedCount--;
		}
		
		/**
		 * Returns the difference between current node's key and num.
		 * @param num - key used for comparison with current node.
		 * @return difference between key and num: Negative - key is smaller, Positive - key is bigger,
		 * Zero - equivalent.
		 */
		private int compareNode(int num) {
			return key - num;
		}
	}
	
	/**
	 * Constructor that sets root node to null.
	 */
	LazyBinarySearchTree(){
		root = null;
	}
	
	/**
	 * Check integer input. If it is outside of [1-99] (inclusive) then throw IllegalArgumentException error.
	 * @param key - integer validated.
	 */
	private void validateInput(int key){
		if(key < 1 || key > 99) 
			throw new IllegalArgumentException();
		
	}
	
	/**
	 * Check if tree is empty. If root is null, then no integers have been inserted into tree. If the node
	 * count is equal to the deletedCount, then all nodes have been deleted and thus the tree is empty.
	 * @return return true if tree is empty, else return false.
	 */
	public boolean isEmpty() {	//Empty if root is null or the number deleted = number inserted.
		if(root == null || root.count == root.deletedCount)
			return true;
		return false;
	}
	
	/**
	 * Insert key into tree. If key was recently deleted, then return that key to a non-deleted state. If key is already
	 * present in tree, do nothing.
	 * @param key - integer input stored in BST.
	 * @return return true if node was inserted, else false.
	 */
	public boolean insert(int key) throws IllegalArgumentException {
		validateInput(key);							//Validate input.
		int countBefore = root.count;				//Set current node count before insert.
		int deletedBefore = root.deletedCount;		//Set current deletedCount before insert
		root = insertKey(key, root);
		return root.count > countBefore || root.deletedCount < deletedBefore;//Return true if physically inserted node into tree.
		
	}
	
	/**
	 * Inserts node into tree recursively. If key is already inserted in the tree, then 
	 * nothing will be done. If the key is a duplicate of a deleted element, then the method 
	 * will undelete the item in the tree.
	 *
	 * @param k - key to insert into table.
	 * @param n - current node.
	 * @return return current node.
	 */
	private TreeNode insertKey(int k, TreeNode n) {
		if(n == null)								//Found insert location. Return new TreeNode with key = k.
			return new TreeNode(k);
		
		int compareInt = n.compareNode(k);
		
		if(compareInt > 0)							//Traverse tree based on comparison value.
			n.leftChild = insertKey(k, n.leftChild);
		else if(compareInt < 0)
			n.rightChild = insertKey(k,n.rightChild);
		else
			if(n.deleted)							//If node was deleted, undo deletion to make it active again.
				n.treeNodeUndelete();
		
		return n;
	}
	
	/**
	 * Deletes key in tree. If key doesn't exist, do nothing.
	 * @param key - integer input stored in BST.
	 * @return return true if node was deleted, else false.
	 */
	public boolean delete(int key) throws IllegalArgumentException {
		validateInput(key);							//Validate input.
		int countBefore = root.deletedCount;		//Set current deletedCount.
		root = deleteKey(key, root);
		return root.deletedCount > countBefore;		//If deletedCount is greater than before, return true.
	}
	
	/**
	 * Deletes node in tree recursively.
	 * The method uses lazy deletion. No physical deletion is done on the tree. Rather, the 
	 * deleted node is flagged for deletion using the "deleted" boolean in the node's data.
	 * If key is already deleted, then method will do nothing.
	 * 
	 * @param k - key to delete from table.
	 * @param n - current node.
	 * @return return current node.
	 */
	private TreeNode deleteKey(int k, TreeNode n) {
		if(n == null)								//Node not found. Return current node.
			return n;
		
		int compareInt = n.compareNode(k);
		
		if(compareInt > 0)							//Traverse tree based on comparison value.
			n.leftChild = deleteKey(k, n.leftChild);
		else if(compareInt < 0)
			n.rightChild = deleteKey(k,n.rightChild);
		else
			if(!n.deleted)							//If node not already deleted, delete. Otherwise do nothing.
				n.treeNodeDelete();
		
		return n;
	}
	
	/**
	 * Returns the value of the minimum non-deleted key, or -1 if none exists.
	 * @return return the minimum integer.
	 */
	public int findMin() {
		if(isEmpty())	//Return -1 if tree is empty.
			return -1;
		
		int minReturned = findMinKey(root.key, root.leftChild);
		if(minReturned == root.key && root.deleted) {	//If root is the min node and it was deleted
			TreeNode n = root;							//traverse right side until the first non-deleted
			while(n.deleted) {							//key is reached.
				n = n.rightChild;
			}
			
			minReturned = n.key;
		}
		
		return minReturned;
	}
	
	/**
	 * Traverse left subtree recursively to find minimum value.
	 * @param minKey - minimum key value.
	 * @param n - current node.
	 * @return return the minimum integer.
	 */
	private int findMinKey(int minKey, TreeNode n) {
		if(n == null)									//Node not found. Return minKey.
			return minKey;
		
		if(n.compareNode(minKey) < 0 && !n.deleted)		//If current node is not deleted and its value is
			minKey = n.key;								//smaller than minKey, set minKey to current node.
				
		int left = findMinKey(minKey, n.leftChild);
		int right = findMinKey(minKey, n.rightChild);
		
		if(left < right)								//Return smallest int from recursive calls.
			return left;
		else if(left > right)
			return right;
		else return left;
		
	}
	
	
	/**
	 * Returns the value of the maximum non-deleted key, or -1 if none exists.
	 * @return return maximum integer.
	 */
	public int findMax() {
		if(isEmpty())	//Return -1 if tree is empty.
			return -1;
		
		int maxReturned = findMaxKey(root.key, root.rightChild);
		if(maxReturned == root.key && root.deleted) {	//If root is the max node and it was deleted
			TreeNode n = root;							//traverse left side until the first non-deleted
			while(n.deleted) {							//key is reached.
				n = n.leftChild;
			}
			
			maxReturned = n.key;
		}
		
		return maxReturned;
	}
	
	/**
	 * Traverse right subtree recursively to find max value.
	 * @param maxKey - maximum key value.
	 * @param n - current node.
	 * @return return maximum integer.
	 */
	private int findMaxKey(int maxKey, TreeNode n) {
		if(n == null)
			return maxKey;
		
		if(n.compareNode(maxKey) > 0 && !n.deleted)
			maxKey = n.key;
		
		int left = findMaxKey(maxKey, n.leftChild);
		int right = findMaxKey(maxKey, n.rightChild);
		
		if(left > right)
			return left;
		else if(left < right)
			return right;
		else return right;
				
	}
	
	/**
	 * Returns whether or not a key exists in tree and is non-deleted.
	 * Validates input and throws exception if out of range, [1-99] (inclusive).
	 * @param key - integer input stored in BST.
	 * @return returns true if key is in tree and not deleted, else false.
	 */
	public boolean contains(int key) throws IllegalArgumentException{
		validateInput(key);
		return containsNode(key,root);
	}
	
	/**
	 * Traverse tree recursively to find node. If found, checks if node is flagged as deleted.
	 * @param k - integer to find.
	 * @param n - current node.
	 * @return return true if found and node is not deleted, else false.
	 */
	private boolean containsNode(int k, TreeNode n) {
		if(n == null)								
			return false;
		
		int compareInt = n.compareNode(k);
		
		if(compareInt > 0)							//Traverse tree based on comparison value.
			return containsNode(k, n.leftChild);
		else if(compareInt < 0)
			return containsNode(k,n.rightChild);
		else
			if(!n.deleted)							//If node is not deleted, return true.
				return true;
			else
				return false;
	}
	
	/**
	 * Prints the total contents of the tree in preorder. Deleted nodes are prefixed with a "*".
	 * @return String
	 */
	public String toString() {
		String str = "";
		return preorderPrint(str, root);
	}
	
	/**
	 * Traverse tree recursively appending string variable with each node's value. Traversal
	 * is done in preorder (top-down). Includes nodes that were deleted. If deleted, these
	 * nodes are prefixed by "*".
	 * @param str - previous nodes key value. Marked with "*" if deleted.
	 * @param n - current node.
	 * @return returns previous string and current string's key values.
	 */
	private String preorderPrint(String str, TreeNode n) {
		if(n == null)
			return str;
		
		if(n.deleted)											//Append string with "*" if deleted, else without.
			str += "*" + n.key + " ";
		else
			str += "" + n.key + " ";
		
		str = preorderPrint(str, n.leftChild);
		str = preorderPrint(str, n.rightChild);
		
		return str;
	}
	
	/**
	 * Returns the height (int) of the tree, including deleted elements.
	 * @return integer height of tree.
	 */
	public int height() {
		return nodeHeight(root) - 1; 							//Subtract 1 since edges = node - 1.
	}
	
	/**
	 * Traverse the whole tree recursively counting each node. For each branch, return the highest
	 * of the two numbers. Max value should be returned. This is the count of all the nodes in the
	 * largest path.
	 * @param n - current node.
	 * @return returns the count of all nodes in the largest path.
	 */
	private int nodeHeight(TreeNode n) {
		if(n == null)
			return 0;
		
		int right = nodeHeight(n.rightChild) + 1;
		int left = nodeHeight(n.leftChild) + 1;
		
		if(right > left)
			return right;
		else if(right < left)
			return left;
		else return right;
		 
	}
	
	/**
	 * Returns the count of elements in the tree, including deleted elements.
	 * @return returns total count of nodes present in tree regardless of deletion.
	 */
	public int size() {
		return root.count;					//Count comes from inner class. Counts all nodes physically inserted into tree.
	}
}
