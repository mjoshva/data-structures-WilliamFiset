package com.java.datastructure.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Entry<K, V> {
	
	int hash;
	K key;
	V value;
	
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
		this.hash = key.hashCode();
	}
	
	public boolean equals(Entry<K, V> other) {
		
		if (hash != other.hash) return false;
		
		return key.equals(other.key);
	}
	
	public String toString() {
		return key + " => " + value;
	}
}

@SuppressWarnings("unchecked")
public class HashTableSepareChaining<K, V> implements Iterable<K> {

	private static final int DEFAULT_CAPACITY = 12;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	
	private double maxLoadFactor;
	private int capacity, threshold, size = 0;
	
	private LinkedList<Entry<K, V>>[] table;
	
	public HashTableSepareChaining() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public HashTableSepareChaining(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}
	
	public HashTableSepareChaining(int capacity, Double maxLoadFactor) {
		
		if (capacity < 0) throw new IllegalArgumentException("Illegal capacity");
		
		if (maxLoadFactor < 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
			throw new IllegalArgumentException("Illegal load factor");
		
		this.maxLoadFactor = maxLoadFactor;
		this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
		threshold = (int) (this.capacity * maxLoadFactor);
		
		table = new LinkedList[capacity];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public void clear() {
		Arrays.fill(table, null);
		size = 0;
	}
	
	public boolean containsKey(K key) {
		return hashKey(key);
	}
	
	private int normalizedIndex(int keyHash) {
		return (keyHash & 0x7FFFFFFF) % capacity;
	}
	
	// Returns true/false depending on whether a key is in the hash table
	public boolean hashKey(K key) {
		
		int bucketIndex = normalizedIndex(key.hashCode());
		
		return bucketSeekEntry(bucketIndex, key) != null;
	}
	
	// Insert, put and add all place a value in the hash-table
	public V put(K key, V value) {
		return insert(key, value);
	}
	
	public V add(K key, V value) {
		return insert(key, value);
	}
	
	private V insert(K key, V value) {
		
		if (key == null) throw new IllegalArgumentException("Null key");
		
		Entry<K, V> newEntry = new Entry<>(key, value);
		int bucketIndex = normalizedIndex(newEntry.hash);
		
		return bucketInsertEntry(bucketIndex, newEntry);
	}
	
	// Gets a key's values from the map and returns the value.
	// NOTE: returns null if the value is null AND also returns
	// null if the key does not exists, so watch out..
	public V get(K key) {
		
		if (key == null) return null;
		
		int bucketIndex = normalizedIndex(key.hashCode());
		
		Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
		
		if (entry != null) return entry.value;
		
		return null;
	}
	
	public V remove(K key) {
		
		if (key == null) return null;
		
		int bucketIndex = normalizedIndex(key.hashCode());
		
		return bucketRemoveEntry(bucketIndex, key);
	}
	
	// Finds and returns a particular entry in a given bucket if it exists, returns null otherwise
	private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
		
		if (key == null) return null;
		
		LinkedList<Entry<K, V>> bucket = table[bucketIndex];
		
		if (bucket == null) return null;
		
		for (Entry<K, V> entry : bucket) {
			
			if (entry.key.equals(key)) {
				return entry;
			}
		}
		return null;
	}
	
	// Inserts an entry in a given bucket only if the entry does not already
	// exist in the given bucket, but if it does then update the entry value
	private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {
	
		LinkedList<Entry<K,V>> bucket = table[bucketIndex];
		
		if (bucket == null) table[bucketIndex] = bucket = new LinkedList<>();
		
		Entry<K, V> existenEntry = bucketSeekEntry(bucketIndex, entry.key);
		
		if (existenEntry == null) {
			
			bucket.add(entry);
			
			if (++size > threshold) resizeTable();
			return null;	// Use null to indicate that there was no previous entry
		} else {
			
			V oldValue = existenEntry.value;
			
			existenEntry.value = entry.value;
			
			return oldValue;
		}
	}
	
	// Removes an entry from a given bucket if it exists
	private V bucketRemoveEntry(int bucketIndex, K key) {
		
		Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
		
		if (entry != null) {
			
			LinkedList<Entry<K, V>> links = table[bucketIndex];
			
			links.remove(entry);
			--size;
			
			return entry.value;
		} else return null;
	}
	
	// Resizes the internal table holding buckets of entries
	private void resizeTable() {
		
		capacity *= 2;
		threshold = (int) (capacity * maxLoadFactor);
		
		LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];
		
		for (int i = 0; i < size(); i++) {
			
			if (table[i] != null) {
				
				for (Entry<K, V> entry : table[i]) {
					
					int bucketIndex = normalizedIndex(entry.hash);
					
					LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];			// Check whether current bucketIndex is already created or null
					
					if (bucket == null) newTable[bucketIndex] = bucket = new LinkedList<>();
					
					bucket.add(entry);
				}
				
				// Avoid memory leak.
				table[i].clear();
				table[i] = null;
			}
		}
		table = newTable;
	}
	
	// Returns the list of keys found within the hash table
	public List<K> keys() {
		
		List<K> keys = new ArrayList<>(size());
		
		for (LinkedList<Entry<K, V>> bucket : table) {
			
			if (bucket != null) for (Entry<K, V> entry : bucket) keys.add(entry.key);

		}
		return keys;
	}
	
	// Returns the list of values found within the hash table
	public List<V> values() {
		
		List<V> values = new ArrayList<>(size());
		
		for (LinkedList<Entry<K, V>> bucket : table) {
			
			if (bucket != null) for (Entry<K, V> entry : bucket) values.add(entry.value);

		}
		return values;
	}	
	
	@Override
	public Iterator<K> iterator() {
		return null;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
		for (int i = 0; i < capacity; i++) {
			
			if (table[i] == null) continue;
			
			for (Entry<K, V> entry : table[i]) sb.append(entry).append(", ");
		}
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) {
		
		HashTableSepareChaining<String, Integer> ht = new HashTableSepareChaining<>();
		
		ht.add("apple", 1);
		ht.add("orange", 2);
		ht.add("bike", 3);
		ht.add("car", 4);
		ht.add("bus", 5);
		ht.add("cas", 6);
		
		ht.put("apple", 10);
		
		//ht.remove(4);
		
		System.out.println(ht.get("apple"));
		
		//System.out.println(ht.keys().toString());
		//System.out.println(ht.values().toString());
		
		//System.out.println(ht.toString());
	}
}
