
public class HashTableLinearProbe<K, V> {
	private static final int DEFAULT_TABLE_SIZE = 3; //Default table size
	int size;	//Number of elements in table.
	HashEntry<K,V> hashtable[];
	HashEntry<K,V> tablePointer;
	
	
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
	
	public int getHashValue(K key) {
		int myHashCode = 0;
		if(key == null || size == hashtable.length) {
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
		
		if(myHashCode < 0)
			myHashCode += hashtable.length;
		
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
	
	public V find(K key) {
		tablePointer = null;
		int myHashCode = getHashValue(key);
		if(myHashCode == -1)
			return null;
		for(int i = 0; i < hashtable.length; i++) {
			if(hashtable[myHashCode].key.equals(key)) {
				tablePointer = hashtable[myHashCode];
				if(!hashtable[myHashCode].deleted)
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
		
		if(tablePointer != null && tablePointer.deleted) {
			tablePointer.deleted = false;
		}
		else {
			try {
				hashtable[getHashValue(key)] = new HashEntry<K, V>(key, value);
			} catch(ArrayIndexOutOfBoundsException e) {	//Null key will return -1 which throws out of bounds exception.
				e.toString();
				//UPDATE TO ACCOUNT FOR ALL ERRORS!!!
				System.out.println("ErrorDetails: key cannot be null.");
			}
			
			size++;	//Increment array size
			
			if(size == hashtable.length)
				rehash();
			
		}
		
		return true;
	}
	
	public boolean delete(K key) {
		if(find(key) == null || tablePointer.deleted) {
			return false;
		}
		else {
			return tablePointer.deleted = true;
			
		}
		
	}
	
}
