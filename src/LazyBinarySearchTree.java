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
 * |+insert(key: int): boolean {throws IllegalArgumentException}  |		|
 * |+delete(key: int): boolean {throws IllegalArgumentException}  |		|
 * |+findMin(): int												  |		|
 * |+findMax(): int												  |		|
 * |+contains(key: int): boolean {throws IllegalArgumentException}| 	|
 * |+toString(): String											  |		|
 * |+height(): int												  |		|
 * |+size(): int												  |		|
 * |															  |		|
 * |______________________________________________________________|		|
 * 								  										|
 * 				|-------------------------------------------------------|
 * 				|
 * __________________________
 * |						|
 * |		TreeNode		|
 * |________________________|
 * |+key: int				|
 * |+leftChild: TreeNode	|
 * |+rightChild: TreeNode	|
 * |+deleted: boolean		|
 * |________________________|
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
	TreeNode root;
	
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
	
	public boolean isEmpty() {
		if(root == null || root.count == root.deletedCount)
			return true;
		return false;
	}
	
	/**
	 * Inserts the key (integer) input from file. Integer must be between 1-99 (inclusive).
	 * If it is outside the range, class will throw an IllegalArgumentException.
	 * 
	 * If key is already inserted in the tree, then nothing should be done. If the key is 
	 * a duplicate of a deleted element, then the method should undelete the item in the tree.
	 * 
	 * Method returns a boolean indicating: true - a node was inserted and done so physically
	 * or false - a node was not inserted or it was logically inserted but not physically.
	 * @param key - integer input stored in BST.
	 * @return boolean
	 */
	public boolean insert(int key) {
		int countBefore = root.count;		//Set current node count before insert.
		root = insertKey(key, root);
		return root.count > countBefore;	//Return true if physically inserted node into tree.
		
	}
	
	private TreeNode insertKey(int k, TreeNode n) {
		if(n == null)						//Found insert location. Return new TreeNode with key = k.
			return new TreeNode(k);
		
		int compareInt = n.compareNode(k);
		
		if(compareInt > 0)					//Traverse tree based on comparison value.
			n.leftChild = insertKey(k, n.leftChild);
		else if(compareInt < 0)
			n.rightChild = insertKey(k,n.rightChild);
		else
			if(n.deleted)					//
				n.treeNodeUndelete();
		
		return n;
	}
	
	/**
	 * Deletes the key (integer) input from file. IInteger must be between 1-99 (inclusive).
	 * If it is outside the range, class will throw an IllegalArgumentException. 
	 * 
	 * The method uses lazy deletion. No physical deletion is done on the tree. Rather, the 
	 * deleted node is flagged for deletion using the "deleted" boolean in the node's data.
	 * 
	 * If key is already deleted, then method should do nothing.
	 * 
	 * Method returns a boolean indicating success or failure. 
	 * @param key - integer input stored in BST.
	 * @return boolean
	 */
	public boolean delete(int key) {
		
		return false; //placeholder
	}
	
	/**
	 * Returns the value of the minimum non-deleted element, or -1 if none exists.
	 */
	public void findMin() {
		
	}
	
	/**
	 * Returns the value of the minimum non-deleted element, or -1 if none exists.
	 */
	public void findMax() {
		
	}
	
	/**
	 * Returns whether or not key exists in tree and is non-deleted.
	 * @param key - integer input stored in BST.
	 * @return boolean
	 */
	public boolean contains(int key) {
		
		return false; //placeholder
	}
	
	/**
	 * Performs a pre-order traversal of the tree to print the value 
	 * of each element. Includes deleted items which are preceded by 
	 * an asterisk.
	 * @return String
	 */
	public String toString() {
		
		return ""; //placeholder
	}
	
	/**
	 * Returns the height (int) of the tree, including deleted elements.
	 * @return int
	 */
	public int height() {
		
		return 0; //placeholder
	}
	
	/**
	 * Returns the count of elements in the tree, including deleted elements.
	 * @return
	 */
	public int size() {
		
		return 0; //placeholder
	}
}