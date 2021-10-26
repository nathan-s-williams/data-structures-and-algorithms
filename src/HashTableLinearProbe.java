
public class HashTableLinearProbe<K, V> {
	private static final int DEFAULT_TABLE_SIZE = 3; //Default table size
	int size;	//Number of elements in table.
	HashEntry<K,V> hashtable[];
	
	
	private static class HashEntry<K,V>{
		K key;
		V value;
		boolean deleted;
		
		HashEntry(K key, V val){
			this.key = key;
			this.value = val;
			this.deleted = false;
		}
	}
	
	HashTableLinearProbe(){
		this(DEFAULT_TABLE_SIZE);
	}
	
	HashTableLinearProbe(int num){
		createTable(num);
	}
	
	@SuppressWarnings("unchecked")
	private void createTable(int num) {
		hashtable = new HashEntry[nextPrime(num)];
		makeEmpty();
	}
	
	private void makeEmpty() {
		for(int i = 0; i < hashtable.length; i++) {
			hashtable[i] = null;//new HashTableLinearProbe.HashEntry<K, V>(null,null); //should i use this over just hashentry?
		}
	}
	
	private int nextPrime(int num) {
		while(!isPrime(num)) {
			num++;
		}
		return num;
	}
	
	private boolean isPrime(int num) {
		int sqrt = (int) Math.pow(num, 0.5);
		for(int i = 2; i <= sqrt; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private int hashFunction(K key) {	//Return -1 if key is null
		if(key == null)
			return -1;
		
		int myHashCode = key.hashCode();
		
		myHashCode %= hashtable.length;
		if(myHashCode < 0)
			myHashCode += hashtable.length;
		
		return myHashCode;	//placeholder
	}
	
	//Must only be used with insert since method does not assume a full array.
	//Insert increases array when needed.
	private int findPosition(K key) {	//Return -1 if key is null
		int myHashCode = hashFunction(key);
		
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
	
	public int getHashValue(K key) {
		return 0; //placeholder
	}
	
	public V find(K key) {
		int myHashCode = hashFunction(key);
		if(myHashCode == -1)
			return null;
		for(int i = 0; i < hashtable.length; i++) {
			if(hashtable[myHashCode].key.equals(key)) {
				return hashtable[myHashCode].value;
			}
			else if(++myHashCode >= hashtable.length) {
				myHashCode -= hashtable.length;
			}
		}
		return null;
	}
	
	public HashEntry<K,V> findHashEntry(K key) {
		int myHashCode = hashFunction(key);
		if(myHashCode == -1)
			return null;
		for(int i = 0; i < hashtable.length; i++) {
			if(hashtable[myHashCode].key.equals(key)) {
				return hashtable[myHashCode];
			}
			else if(++myHashCode >= hashtable.length) {
				myHashCode -= hashtable.length;
			}
		}
		return null;
	}
	
	private void rehash() {
		HashEntry<K,V> hashtabledup[];
		hashtabledup = hashtable;
		
		createTable(size * 2);
		for(int i = 0; i < hashtabledup.length; i++) {
			if(!hashtabledup[i].deleted)
				insert(hashtabledup[i].key,hashtabledup[i].value);
		}
	}
	
	public boolean insert(K key, V value) {
		//Check if insert is a duplicate
		if(find(key) != null) 
			return false;
		//use findPosition to find open spot to insert
		try {
			hashtable[findPosition(key)] = new HashEntry<K, V>(key, value);
		} catch(ArrayIndexOutOfBoundsException e) {	//Null key will return -1 which throws out of bounds exception.
			e.toString();
			System.out.println("ErrorDetails: key cannot be null.");
		}
		
		//Increment array size
		size++;
		
		if(size == hashtable.length)
			rehash();
		
		return true;
	}
	
	public boolean delete(K key) {
		HashEntry<K,V> deleteItem = findHashEntry(key);
		if(find(key) == null || deleteItem.deleted == true)
			return false;
		deleteItem.deleted = true;
		return true;
	}
}
