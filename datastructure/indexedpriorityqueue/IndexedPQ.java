package com.java.datastructure.indexedpriorityqueue;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class IndexedPQ<T extends Comparable<T>> {

	int LEN;
	int sz;
	private int[] pm, im;
	private Object[] values;
	
	public IndexedPQ(int len) {
		this.LEN = len;
		
		pm = new int[len];
		im = new int[len];;
		values = new Object[len];
		
		for (int i = 0; i < len; i++) {
			pm[i] = -1;
			im[i] = -1;
		}
	}
	
	public int size() {
		return sz; 
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public boolean contains(int ki) {
		keyInBoundOrThrow(ki);
		
		return pm[ki] != -1;
	}
	
	public int peekMinKeyIndex() {
		
		isNotEmptyOrThrow();
		return im[0];
	}
	
	public int pollMinKeyIndex() {
		
		int minki = peekMinKeyIndex();
		delete(minki);
		return minki;
	}
	
	public T peekMinValue() {
		
		isNotEmptyOrThrow();
		return (T) values[im[0]];
	}
	
	public T pollMinValue() {
		
		T minValue = peekMinValue();
		delete(peekMinKeyIndex());
		return minValue;
	}
	
	public void insert(int ki, T value) {
		
		if (contains(ki)) throw new IllegalArgumentException("index already exists; received: " + ki);
		
		valueNotNullOrThrow(value);
		
		pm[ki] = sz;
		im[sz] = ki;
		values[ki] = value;
		
		swim(sz++);
	}
	
	public T valueOf(int ki) {
		
		keyExistsOrThrow(ki);
		return (T) values[ki];
	}
	
	public T delete(int ki) {
		
		keyExistsOrThrow(ki);
		
		final int i = pm[ki];
		swap(i, --sz);
		
		sink(i);
		
		T value = (T) values[ki];
		values[ki] = null;
		pm[ki] = -1;
		im[sz] = -1;
		
		return value;
	}
	
	public T update(int ki, T value) {
		
		keyExistsOrThrow(ki);
		valueNotNullOrThrow(value);
		
		final int i = pm[ki];
		T oldValue = (T) values[ki];
		
		values[i] = value;
		
		sink(i);
		swim(i);
		
		return oldValue;
	}
	
	public void decrese(int ki, T value) {
		
		keyExistsOrThrow(ki);
		valueNotNullOrThrow(value);
		
		if (less(value, values[ki])) {
			
			values[ki] = value;
			swim(pm[ki]);
		}
	}
	
	public void increase(int ki, T value) {
		
		keyExistsOrThrow(ki);
		valueNotNullOrThrow(value);
		
		if (less(values[ki], value)) {
			
			values[ki] = value;
			sink(pm[ki]);
		}
	}
	
	private boolean less(int i, int j) {
		return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
	}
	
	private boolean less(Object obj1, Object obj2) {
		return ((Comparable<? super T>) obj1).compareTo((T) obj2) <0;
	}
	
	private void swap(int i, int j) {
		
		pm[im[i]] = j;
		pm[im[j]] = i;
		
		int temp = im[i];
		im[i] = im[j];
		im[j] = temp;
	}
	
	private void swim(int i) {
		
		int parent = (i - 1) / 2;
		
		while (i > 0 && less(i, parent)) {
			swap(i, parent);
			i = parent;
			parent = (i - 1) / 2;
		}
	}
	
	private void sink(int i) {
		
		int left, right, smallest = 0;
		
		while (true) {
			
			left = (2 * i) + 1;
			right = (2 * i ) + 2;
			smallest = left;
			
			if (right < sz && less(right, smallest)) smallest = right;
			
			if (left >= sz || less(i, smallest)) break;
			
			swap(smallest, i);
			i = smallest;
		}
	}
	
	private void isNotEmptyOrThrow() {
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
	}
	
	private void valueNotNullOrThrow(Object value) {
		if (value == null) throw new IllegalArgumentException("value cannot be null"); 
	}
	
	private void keyExistsOrThrow(int ki) {
		if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
	}
	
	private void keyInBoundOrThrow(int ki) {
		if (ki < 0 || ki >= LEN) throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
	}
	
	public void show() {
		//for (int i = 0; i < LEN; i++) System.out.println(i + " - " + pm[i]);
		
		//System.out.println();
		
		for (int i = 0; i < LEN; i++) System.out.println(i + " - " + im[i]);
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		IndexedPQ<Integer> ipq = new IndexedPQ<>(4);
		
		ipq.insert(0, 3);
		ipq.pollMinKeyIndex();
		ipq.insert(1, 15);
		ipq.insert(2, 11);
		ipq.insert(3, 7);
		
		//System.out.println(ipq.size());
		ipq.show();
		
		//System.out.println("value of: " + ipq.valueOf(2));
		
		//System.out.println("deleted: " + ipq.delete(2));
		
		//int oldValue = ipq.update(2, 0);
		//System.out.println(oldValue);
		//ipq.show();
		
		//ipq.decrese(3, 1);
		//ipq.show();
		
		//ipq.increase(0, 40);
		//ipq.show();
		
//		System.out.println("Min key: " + ipq.peekMinKeyIndex());
//		
//		System.out.println("Min value: " + ipq.peekMinValue());
//		
//		System.out.println("Poll min key: " + ipq.pollMinKeyIndex());
//		
//		System.out.println("Poll min value: " + ipq.pollMinValue());

		//ipq.show();
	}
}
