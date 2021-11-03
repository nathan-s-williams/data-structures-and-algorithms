/***************************************************************************
 * Project 4: Hash Table
 * Implement a java hash structure using linear probing collision strategy.
 * @author nate
 * 
 * Created on 10/23/21 By Nathan Williams (nsw200000)
 * Course: 3345.005 Data Structures and Introduction to Algorithmic Analysis
***************************************************************************/

public class HashTableLinearProbe<K, V> {
	private static final int DEFAULT_TABLE_SIZE = 3; //Default table size
	private int size = 0;	//Number of elements currently in table.
	private HashEntry<K,V> hashtable[];		//Main array table for data structure
	private HashEntry<K,V> tablePointer;	//Pointer used to access HashEntry 
	
	/**
	 * Private class used as the elements of the hash table array. Each entry stores an integer
	 * or string denoted K and any type denoted by V. Each instance is marked as deleted or
	 * not deleted with a boolean variable "deleted".
	 * @author nate
	 *
	 * @param <K> Generic parameter of integer and string.
	 * @param <V> Generic parameter defined by the user.
	 */
	private static class HashEntry<K,V>{
		private K key;
		private V value;
		private boolean deleted;
		
		HashEntry(K key, V val){	//Constructor - requires generic K and generic V to instantiate.
			this.key = key;
			this.value = val;
			this.deleted = false;
		}
	}
	
	/**
	 * Default constructor. Uses the default constant size 3.
	 */
	HashTableLinearProbe(){
		this(DEFAULT_TABLE_SIZE);
	}
	
	/**
	 * Overloaded constructor with int variable for defining array size.
	 * @param num Integer value used to define array size.
	 */
	HashTableLinearProbe(int num){
		createTable(num);
	}
	
	
	/**
	 * Creates a hashtable where the size is defined by num. Uses nextPrime() to get the next
	 * prime number greater than the value in num. Calls makeEmpty method after table instantiation.
	 * @param num Integer value used to define array size.
	 */
	@SuppressWarnings("unchecked")
	private void createTable(int num) {
		hashtable = new HashEntry[nextPrime(num)];	
		makeEmpty();
	}
	
	/**
	 * Sets size class variable to 0 to mark new table. Instantiates each element in the array by setting each to null.
	 */
	private void makeEmpty() {
		size = 0;
		for(int i = 0; i < hashtable.length; i++) {
			hashtable[i] = null;
		}
		
	}
	
	/**
	 * Finds the next prime number greater than num.
	 * @param num Integer parameter.
	 * @return next prime number after num.
	 */
	private int nextPrime(int num) {
		while(!isPrime(num)) {
			num++;
		}
		return num;
	}
	
	/**
	 * Loops from 2 to square root of num to see if there are any factors that would signify this number is not prime.
	 * @param num Integer parameter.
	 * @return true if num is prime or false if num is not prime.
	 */
	private boolean isPrime(int num) {
		int sqrt = (int) Math.pow(num, 0.5);
		for(int i = 2; i <= sqrt; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Computes the hash value based on the key parameter. Different functions are used depending on the type of key:
	 * 1) Integer: number mod table size.
	 * 2) String: Summation of each character's ascii value with the current sum times 37, then mod'ed with table size.
	 * 
	 * Method returns -1 if key is null or not an instance of Integer or String.
	 * @param key Integer or String parameter.
	 * @return the table index value.
	 */
	private int getRawHashValue(K key) {
		int myHashCode = 0;
		if(key == null) {
			return -1;
		}
		else if(key instanceof Integer) {
			myHashCode = (int)key % hashtable.length;
		}
		else if(key instanceof String) {
			String keystr = (String)key;
			for(int i = 0; i < keystr.length(); i++) {
				myHashCode = 37 * myHashCode + keystr.charAt(i);
			}
			
			myHashCode %= hashtable.length;

		}
		else {
			return -1;
		}
		
		//If myHashCode is negative, add the length of the table to bring the negative value within the array's range.
		if(myHashCode < 0)
			myHashCode += hashtable.length;
		
		return myHashCode;
	}
	
	/**
	 * If the location in the array specified by the hash value is null, return the index.
	 * If the location is occupied by another element, increment myHashCode by 1 until a null location is found.
	 * 
	 * Method returns -1 if key is null or not an instance of Integer or String.
	 * @param key Integer or String parameter.
	 * @return null index in table.
	 */
	public int getHashValue(K key) {
		int myHashCode = getRawHashValue(key);
		if(myHashCode == -1)
			return -1;
		
		if(hashtable[myHashCode] == null) {
			 return myHashCode;
		}
		else {
			do {
				if(++myHashCode >= hashtable.length)
					myHashCode -= hashtable.length;
			}while(hashtable[myHashCode] != null);
			
			return myHashCode;
		}
		
	}
	
	/**
	 * Loops through the table searching for the key specified by the key parameter. Starts the loop at the location
	 * where getRawHashValue() returns an index. If the key is present in the array, the loop should only traverse
	 * for as long as a primary cluster exists. The method will return null if either the initial index lands on a 
	 * null location (indicating the key has yet to be inserted) or the entire table is traversed without finding the 
	 * key.
	 * 
	 * Method returns null if key is null or not an instance of Integer or String. Designated by -1 returned from 
	 * getRawHashValue() method.
	 * 
	 * Class variable tablePointer traverses with loop and is initialized to the element object of each traversal. 
	 * This allows find to pass the element along with the value (if any) to methods that call it.
	 * @param key Integer or String parameter.
	 * @return V at the index specified by key.
	 */
	public V find(K key) {
		tablePointer = null;
		int myHashCode = getRawHashValue(key);
		if(myHashCode == -1)
			return null;
		for(int i = 0; i < hashtable.length; i++) {	//Search table for key.
			if(hashtable[myHashCode] == null) {		//Return null if original index is null (not found).
				break;
			}
			else if(hashtable[myHashCode].key.equals(key)) {	//If key is found and element is not deleted, return value.
				tablePointer = hashtable[myHashCode];			//If key is found and element is deleted, return null.
				if(!hashtable[myHashCode].deleted)				//In both cases, class variable - tablePointer - is set to current element.
					return hashtable[myHashCode].value;
				else
					return null;
			}
			else if(++myHashCode >= hashtable.length) {
				myHashCode -= hashtable.length;
			}
		}
		return null;
	}
	
	/**
	 * Duplicate current table using a temp variable. Call createTable with double the size
	 * of the current table. Loop through the temp table inserting all elements that are 
	 * not deleted using a new hash value.
	 */
	private void rehash() {
		HashEntry<K,V> hashtabledup[];
		hashtabledup = hashtable;
		
		createTable(size * 2);
		for(int i = 0; i < hashtabledup.length; i++) {
			if(!hashtabledup[i].deleted)
				insert(hashtabledup[i].key,hashtabledup[i].value);
		}
	}
	
	/**
	 * First looks for the key in the current table. If the key is found, value is returned. Method then returns false.
	 * If null is returned but the tablePointer is not null but element is deleted, change deleted flag to false.
	 * If null is returned and element does not exist, instantiate new element at table index returned by getHashValue().
	 * Once new element is placed in the array, the class variable size is incremented by one. If size is equal to table size
	 * then rehash() is called to increase table size.
	 * 
	 * An ArrayIndexOutOfBoundsException is caught if getHashValue() returns -1. A resulting error message prompts the user that
	 * the key cannot be null, and must be an Integer or String type.
	 * 
	 * @param key Integer or String parameter.
	 * @param value value of type defined by user.
	 * @return true if insert was successful and false if not successful.
	 */
	public boolean insert(K key, V value) {
		//Check if insert is a duplicate
		if(find(key) != null) 
			return false;
		
		//If insert is a deleted item, switch existing item to active.
		if(tablePointer != null && tablePointer.deleted) {
			tablePointer.deleted = false;
		}
		else {
			try {
				hashtable[getHashValue(key)] = new HashEntry<K, V>(key, value);
			} catch(ArrayIndexOutOfBoundsException e) {	//Null key will return -1 which throws out of bounds exception.
				System.out.println("Error: key is invalid. Key must not be null and must be an Integer or String type.");
				return false;
			}
			
			size++;	//Increment array size
			
			if(size == hashtable.length)
				rehash();
			
		}
		
		return true;
	}
	
	/**
	 * Marks the element deleted in the array if find returns a value.
	 * @param key Integer or String parameter.
	 * @return true if element is deleted, else false.
	 */
	public boolean delete(K key) {
		if(find(key) == null || tablePointer.deleted) {
			return false;
		}
		else {
			return tablePointer.deleted = true;
			
		}
		
	}
	
}
