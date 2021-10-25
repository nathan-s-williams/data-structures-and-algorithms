
public class HashTableLinearProbe<K, V> {
	private static final int DEFAULT_TABLE_SIZE = 3;
	int size;	//Number of elements in table.
	
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
	
	HashEntry<K,V> hashtable[];
	
	@SuppressWarnings("unchecked")
	HashTableLinearProbe(){
		hashtable = new HashEntry[DEFAULT_TABLE_SIZE];
		makeEmpty();
	}
	
	@SuppressWarnings("unchecked")
	HashTableLinearProbe(int num){
		hashtable = new HashEntry[nextPrime(num)];
		makeEmpty();
	}
	
	
	private void makeEmpty() {
		for(int i = 0; i < hashtable.length; i++) {
			hashtable[i] = new HashTableLinearProbe.HashEntry<K, V>(null,null); //should i use this over just hashentry?
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
	
	private int hashFunction(K key) {
		
		int myHashCode = key.hashCode();
		
		myHashCode %= hashtable.length;
		if(myHashCode < 0)
			myHashCode += hashtable.length;
		
		return myHashCode;	//placeholder
	}
	
	private int findPosition(K key) {
		return 0; //placeholder
	}
	
	public boolean insert(K key, V value) {
		
		return false;	//placeholder
	}
}
