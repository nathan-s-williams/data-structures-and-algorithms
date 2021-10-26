
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
	
	@SuppressWarnings("unchecked")
	HashTableLinearProbe(int num){
		hashtable = new HashEntry[nextPrime(num)];
		makeEmpty();
	}
	
	
	public void makeEmpty() {
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
	
	private int findPosition(K key) {	//Return -1 if key is null
		int hashCode = hashFunction(key);
		
		if(hashCode == -1)
			return -1;
		
		if(hashtable[hashCode] == null) {
			 return hashCode;
		}
		else {
			do {
				if(++hashCode >= hashtable.length) {
					hashCode -= hashtable.length;
				}
			}while(hashtable[hashCode] != null);
			
			return hashCode;
		}
	}
	
	private void rehash() {
		
	}
	
	public V find(K key) {
		
		return null; //placeholder
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
		
		//Check if array is big enough to rehash
			//Check should be after insert. Do not need to do before insert since check
			//we are always checking after each insert.
		return true;
	}
	
	public boolean delete(K key) {
		
		return false; //placeholder
	}
}
