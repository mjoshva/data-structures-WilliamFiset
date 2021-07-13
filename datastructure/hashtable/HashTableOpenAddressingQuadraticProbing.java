package com.java.datastructure.hashtable;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unchecked")
public class HashTableOpenAddressingQuadraticProbing<K, V> implements Iterable<K> {

	private double loadFactor;
	private int capacity, threshold, modificationCount = 0;		// modificationCount - for iterator.
	
	// 'usedBucket' count the total no.of used bucket inside the hash-table (include cell marked as deleted)
	// 'keyCount' track the no.of unique key inside the table. 
	private int usedBucket = 0, keyCount = 0;
	
	// These are arrays store the key-value pairs.
	private K[] keyTable;
	private V[] valueTable;
	
	//
	private boolean containsFlag = false;
	
	// Special marker token used to indicate the deletion of key-value pairs.
	private final K TOMPSTONE = (K) new Object();
	
	private static final int DEFAULT_CAPACITY = 2;
	private static final double DEFAULT_LOAD_FACTOR = 0.72;
	
	public HashTableOpenAddressingQuadraticProbing() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public HashTableOpenAddressingQuadraticProbing(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}
	
	public HashTableOpenAddressingQuadraticProbing(int capacity, double loadFactor) {
		
		if (capacity <= 0) throw new IllegalArgumentException("Illegal capacity: " + capacity);
		
		if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor))
			throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
		
		this.loadFactor = loadFactor;
		this.capacity = Math.max(DEFAULT_CAPACITY, nextPowerOfTwo(capacity));
		threshold = (int) (this.capacity * loadFactor);
		
		keyTable = (K[]) new Object[this.capacity];
		valueTable = (V[]) new Object[this.capacity];
	}
	
	// Given a number to this method find next power of two above this number.
	// Left shift by one to find next power 2.
	private static int nextPowerOfTwo(int n) {
		return Integer.highestOneBit(n) << 1;
	}
	
	// Quadratic probing function (x^2+x)/2
	// Right shift by one to divide the x value.
	private static int p(int x) {
		return ((x * x) + x) >> 1;
	}
	
	// Converts a hash value to an index. Essentially, this strips the
	// negative sign and places the hash value in the domain [0, capacity)
	private int normalizedIndex(int keyHash) {
		return (keyHash & 0x7FFFFFFF) % capacity;
	}
	
	public void clear() {
		for (int i = 0; i < capacity; i++) {
			keyTable[i] = null;
			valueTable[i] = null;
		}
		keyCount = usedBucket = 0;
		modificationCount++;
	}
	
	// Returns the number of keys currently inside the hash-table
	public int size() {
		return keyCount;
	}
	
	public boolean isEmpty() {
		return keyCount == 0;
	}
	
	public V put(K key, V value) { return insert(key, value); }
	public V add(K key, V value) { return insert(key, value); }

	private V insert(K key, V value) {
		if (key == null) throw new IllegalArgumentException("Null key");
		//System.out.println(" size: " + keyCount);
		//System.out.println("key : " + key +" capacity: "+capacity +" threshold - "+ threshold);
		if (usedBucket >= threshold) resizeTable();
		
		final int hash = normalizedIndex(key.hashCode());
		int index = hash, tompstonePosition = -1, x = 1;
		
		do {
			// The current slot was previously deleted.
			if (keyTable[index] == TOMPSTONE) {
				
				if (tompstonePosition == -1) tompstonePosition = index;
				
			  // The current cell already contains a key	
			} else if (keyTable[index] != null) {
				// The key we're trying to insert already exists in the hash-table,
				// So update its value with the most recent value.
				if (keyTable[index].equals(key)) {
					
					V oldValue = valueTable[index];
					
					if (tompstonePosition == -1) {
						
						valueTable[index] = value;
						
					} else {
						
						keyTable[index] = TOMPSTONE;
						valueTable[index] = null;
						
						keyTable[tompstonePosition] = key;
						valueTable[tompstonePosition] = value;
					}
					modificationCount++;
					return oldValue;
				}
					
			  // Current cell is null so insertion/update can occur
			} else {
				
				if (tompstonePosition == -1) {

					keyTable[index] = key;
					valueTable[index] = value;
					usedBucket++;
					keyCount++;

			      // Previously seen deleted bucket. Instead of inserting
			      // the new element at i where the null element is insert
			      // it where the deleted token was found.
				} else {
					
					keyTable[tompstonePosition] = key;
					valueTable[tompstonePosition] = value;
				}
				
				modificationCount++;
				return null;
			}
			index = normalizedIndex(hash + p(++x));
				
		} while(true);
	}
	
	public boolean contains(K key) {
		return hashKey(key);
	}
	
	private boolean hashKey(K key) {
		get(key);
		return containsFlag;
	}
	
	// Get the value associated with the input key.
	// Note: returns null if the value is null AND also returns
	// null if the key does not exists.
	public V get(K key) {
		
		if (key == null) throw new IllegalArgumentException("Null key");

		final int hash = normalizedIndex(key.hashCode());
		int index = hash, tompstonePosition = -1, x = 1;
		
		do {
			
			if (keyTable[index] == TOMPSTONE) {
				
				if (tompstonePosition == -1)  tompstonePosition = index;
				
			} else if (keyTable[index] != null) {
				
				if (keyTable[index].equals(key)) {
					
					containsFlag = true;
					
					if (tompstonePosition != -1) {
						
						// Copy value to where deleted bucket is.
						keyTable[tompstonePosition] = keyTable[index];
						valueTable[tompstonePosition] = valueTable[index];
						
						// Clear content in the bucket index and mark it as deleted.
						keyTable[index] = TOMPSTONE;
						valueTable[index] = null;
						
						return valueTable[tompstonePosition];
					} else {
						return valueTable[index];
					}
				}
			} else {
				return null;
			}
			index = normalizedIndex(hash + p(x++));
			
		} while (true) ;
	}
	
	// Remove the key from map and return the value.
	// Note: returns null if the value is null AND also returns
	// null if the key does not exists.
	public V remove(K key) {
		
		isNull(key);
		
		final int hash = normalizedIndex(key.hashCode());
		int index = hash, x = 1;
		
		// Starting at the original hash probe until we find a spot where our key is
	    // or we hit a null element in which case our element does not exist.
		for (;; index = normalizedIndex(hash + p(x++))) {
			
			if (keyTable[index] == TOMPSTONE) continue;
			
			if (keyTable[index] == null) return null;
			
			if (keyTable[index].equals(key)) {
				
				keyCount--;
				V oldValue = valueTable[index];
				keyTable[index] = TOMPSTONE;
				valueTable[index] = null;
				
				return oldValue;
			}
		}
	}
	
	private void resizeTable() {
		//System.out.println("capacity: " + capacity + " threshole: " + threshold +" size: " + keyCount);
		capacity *= 2;
		threshold = (int) (capacity * loadFactor);
		
		K[] oldKeyTable = (K[]) new Object[capacity];
		V[] oldValueTable = (V[]) new Object[capacity];
		
		K[] tempKey = keyTable;
		keyTable = oldKeyTable;
		oldKeyTable = tempKey;
		
		V[] tempValue = valueTable;
		valueTable = oldValueTable;
		oldValueTable = tempValue;
		
		keyCount = usedBucket = 0;
		
		for (int i = 0; i < oldKeyTable.length; i++) {
			if (oldKeyTable[i] != null && oldKeyTable[i] != TOMPSTONE) {
				insert(oldKeyTable[i], oldValueTable[i]);
			}
			oldKeyTable[i] = null;
			oldValueTable[i] = null;
		}
		
		System.out.println("capacity: " + capacity + " threshole: " + threshold +" size: " + keyCount);
	}
	
	public List<K> keys() {
		List<K> keys = new ArrayList<>();
		
		for (int i = 0; i < capacity; i++) 
			if (keyTable[i] != null && keyTable[i] != TOMPSTONE) keys.add(keyTable[i]);
		return keys;
	}
	
	public List<V> values() {
		List<V> values = new ArrayList<>();
		
		for (int i = 0; i < capacity; i++) 
			if (keyTable[i] != null && keyTable[i] != TOMPSTONE) values.add(valueTable[i]);
		return values;
	}
	
	@Override
	public Iterator<K> iterator() {
		
		final int MODIFICATION_COUNT = modificationCount;
		
		return new Iterator <K>() {
			
			int keysLeft = keyCount, index = 0;
			
			public boolean hasNext() {
				if (MODIFICATION_COUNT != modificationCount) throw new ConcurrentModificationException();
				//System.out.println(keysLeft);
				return keysLeft != 0;
			}
			
			public K next() {
				while (keyTable[index] == null || keyTable[index] == TOMPSTONE) index++;
				keysLeft--;
				return keyTable[index++];
			}
			
			@Override
		    public void remove() {
		      throw new UnsupportedOperationException();
		    }
		};
	}		
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
		for (int i = 0; i < capacity; i++) {
			if (keyTable[i] != null && keyTable[i] != TOMPSTONE) {
				sb.append(keyTable[i] + " = " + valueTable[i] + ", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	private void isNull(K key) {
		if (key == null) throw new IllegalArgumentException("Null key");
	}
	
	public static void main(String[] args) {
		
		HashTableOpenAddressingQuadraticProbing<String, String> qp = new HashTableOpenAddressingQuadraticProbing<>();
		qp.add("one", "apple");
		qp.add("two", "iphone");
		qp.add("three", "car");
		qp.add("four", "bike");
		
		//qp.add(1, "orange");
		//System.out.println(qp);
		//System.out.println(qp.size());
		
		//System.out.println(qp.contains("4"));
		//System.out.println(qp.get("4"));
		
		//System.out.println(qp.remove("4"));
		//System.out.println(qp);
		//System.out.println(qp.size());
		
		//System.out.println(qp.keys().toString());
		//System.out.println(qp.values().toString());
		
		//qp.add("5", "nokia");
		//System.out.println(qp);
		//System.out.println(qp.capacity);
		//System.out.println(qp.size());
		//qp.add("6", null);
		//System.out.println(qp.keyCount);
		
		//System.out.println(qp.get("4"));
		
		Iterator<String> iter = qp.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
	
}
