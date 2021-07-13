package com.java.datastructure.priorityqueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PQueue<T extends Comparable<T>> {

	private int heapSize = 0;
	
	private int heapCapacity = 0;
	
	private List<T> heap = null;
	
	private Map<T, TreeSet<Integer>> map = new HashMap<>();
	
	public PQueue() {
		this(1);
	}
	
	public PQueue(int size) {
		heap = new ArrayList<>(size);
	}
	
	public PQueue(T[] elem) {
		heapSize = heapCapacity = elem.length;
		
		heap = new ArrayList<>(heapCapacity);
		
		for (int i = 0; i < heapSize; i++) {
			mapADD(elem[i], i);
			heap.add(elem[i]);
		}
		
		for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
			sink(i);
		}
	}
	
	public PQueue(Collection<T> elems) {
		
		this(elems.size());
		
		for (T elem : elems) {
			add(elem);
		}
	}
	
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	public void clear() {
		
		// for (int i = 0; i < heapCapacity; i++)
		// 	heap.add(i, null);
		
		heapSize = 0;

		heap.clear();
		
		map.clear();
	}
	
	public int size() {
		return heapSize;
	}
	
	public T peek() {
		
		if (isEmpty()) return null;
		
		return heap.get(0);
	}
	
	public T poll() {
		
		return removeAt(0); 
	}
	
	public boolean contains(T elem) {
		
		if (elem == null) return false;
		
		return map.containsKey(elem);			// time = O(1)
		
		
		// Or do linear search					// time = O(n)
		// for (int i = 0; i < heapSize; i++) {
		//     if (heap.get(i).equals(elem))
		//         return true;
		// return false; 
	}
	
	public void add(T elem) {
		
		if (elem == null) throw new IllegalArgumentException();
		
		// if (heapSize > heapCapacity) heapCapacity++;
		
		heap.add(elem);
		heapSize++;
		
		int indexOfLastElem = size() - 1;
		
		mapADD(elem, indexOfLastElem);
		
		swim(indexOfLastElem);
	}
	
	private boolean less(int i, int j) {
		
		T node1 = heap.get(i);
		T node2 = heap.get(j);
		
		return node1.compareTo(node2) <= 0 ;
	}
	
	private void swim(int k) {
	
		int parent = (k - 1) / 2;
		
		while (k > 0 && less(k, parent)) {
			
			swap(parent, k);
			
			k = parent;
			
			parent = (k - 1) / 2;
		}
	}
	
	// This is heapify process.
	private void sink(int k) {
		
		while (true) {
			int left = 2 * k + 1;
			int right = 2 * k + 2;
			int smallest = left;
			
			if (right < heapSize && less(right, left)) smallest = right;

			if (left >= heapSize || less(k, smallest)) break;	
			
			swap(smallest, k);
			
			k = smallest;
		}
	}
	
	private void swap(int i, int j) {
		
		T iElem = heap.get(i);
		T jElem = heap.get(j);
		
		heap.set(i, jElem);
		heap.set(j, iElem);
		
		mapSwap(iElem, jElem, i, j);
	}
	
	public boolean remove(T elem) {
		
		if (elem == null) return false;
		
		Integer index = mapGet(elem);
		
		if (index != null) removeAt(index);
		
		return index != null;
	}
	
	private T removeAt(int i) {
		
		if (isEmpty()) return null;
		
		int indexOfLastElem = size() - 1;
		
		heapSize--;
		
		T removedData = heap.get(i);
		swap(i, indexOfLastElem);				// In implicitly mapSwap will occurs. mapSwap() is inside in swap().
		
		// heap.set(indexOfLastElem, null);
		heap.remove(indexOfLastElem);
		mapRemove(removedData, indexOfLastElem);
		
		if (i == indexOfLastElem) return removedData;
		
		T elem = heap.get(i);

		sink(i);
		if (heap.get(i).equals(elem)) {	
			 swim(i);
		}
		
		return removedData;
	}
	
	public boolean isMinHeap(int k) {
		
		if (k >= heapSize) return true;
		
		int left = 2 * k + 1;
		int right = 2 * k + 2;
		
		if (left < heapSize && !less(k, left)) return false;
		if (right < heapSize && !less(k, right)) return false;
		
		return isMinHeap(left) && isMinHeap(right);
	}
	
	private void mapADD(T value, int index) {
		
		TreeSet<Integer> set = map.get(value);
		
		if (set == null) {
			set = new TreeSet<>();
			set.add(index);
			map.put(value, set);
		} else {
			set.add(index);
		}
	}
	
	private void mapRemove(T value , int index) {
		
		TreeSet<Integer> set = map.get(value);
		
		set.remove(index);
		
		if (set.size() == 0) map.remove(value);
	}
	
	private Integer mapGet(T elem) {
		
		TreeSet<Integer> set = map.get(elem);
		
		if (set != null) return set.last();
		
		return null;
	}
	
	private void mapSwap(T val1, T val2, int val1Index, int val2Index) {
		
		Set<Integer> set1 = map.get(val1);
		Set<Integer> set2 = map.get(val2);
		
		set1.remove(val1Index);
		set2.remove(val2Index);
		
		set1.add(val2Index);
		set2.add(val1Index);
	}
	
	public String toString() {
		return heap.toString();
	}
	
	public static void main(String[] args) {
		
		//Integer[] arr = {3, 1, 0, 1, 2};
		List<Integer> arr = new ArrayList<>();
		arr.add(2);
		arr.add(8);
		arr.add(3);
		arr.add(1);
		arr.add(14);
		arr.add(2);
		arr.add(7);
		arr.add(9);
		arr.add(4);
		arr.add(4);
		arr.add(2);
		arr.add(6);
		
		PQueue<Integer> pq = new PQueue<>(arr);
		
		System.out.println(pq.toString());
		pq.add(3);
		System.out.println(pq.toString());
		
		pq.remove(2);
		//pq.poll();
		System.out.println(pq.toString());
		pq.add(3);
		pq.add(4);
		pq.add(7);
		pq.remove(4);
		System.out.println(pq.toString());
		
		System.out.println(pq.isMinHeap(0));
		
		//pq.clear();
		
	}
}
