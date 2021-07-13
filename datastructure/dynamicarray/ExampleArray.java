package com.java.datastructure.dynamicarray;

@SuppressWarnings("unchecked")
public class ExampleArray<T> implements Iterable<T> {

	private T[] arr;
	private int capacity = 0;
	private int len = 0;
	
	public ExampleArray() {
		this(16);
	}
	
	public ExampleArray(int capacity) {
		this.capacity = capacity;
		arr =(T[]) new Object[this.capacity];
	}
	
	public int size() { return len; }
	
	public boolean isEmpty() { return size() == 0 ;}
	
	public T get(int index) {
		if (index < 0) throw new IndexOutOfBoundsException(index);
		
		return arr[index]; 
	}
	
	public void set(int index, T elem) {
		if (index < 0 || index > len) throw new IndexOutOfBoundsException(index);
		
		arr[index] = elem;
	}
	
	public void clear() {
		for(int i = 0; i < len; i++)
			arr[i] = null;
		len = 0;
	}
	
	public void add(T elem) {
		if (len >= capacity) {
			if (capacity == 0) capacity = 1;
			else capacity *= 2;
			
			T[] newArr = (T[]) new Object[capacity];
			for (int i = 0; i < len; i++)
				newArr[i] = arr[i];
			arr = newArr;
		}
		
		arr[len++] = elem;
	}
	
	public T removeAt(int index) {
		if (index >= len || index < 0) throw new IndexOutOfBoundsException(index);
		
		T removeData = arr[index];
		T[] newArr = (T[]) new Object[len - 1];
		for (int i = 0, j = 0; i < len; i++, j++) {
			if (i == index) j--;
			else newArr[j] = arr[i]; 
		}
		arr = newArr;
		capacity = --len;
		return removeData;
	}
	
	public boolean remove(Object obj) {
		if (obj == null) {
		
			for (int i = 0; i < len; i++) {
				if (arr[i] == null) {
					removeAt(i);
					return true;
				}
			}	
		}
		
		for (int i = 0; i < len; i++) {
			if (arr[i].equals(obj)) {
				removeAt(i);
				return true;
			}
		}
		
		return false;
	}
	
	public int indexOf(Object obj) {
		if (obj == null) {
			
			for (int i = 0; i < len; i++) {
				if (arr[i] == null) {
					return i;
				}
			}	
		}
		
		for (int i = 0; i < len; i++) {
			if (arr[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}
	
	public java.util.Iterator<T> iterator(){
		return new java.util.Iterator<T>() {
			int index = 0;
			public boolean hasNext() { return index < len;}
			public T next() { return arr[index++]; }
		};
	}
	
	public String toString() {
		if (len == 0) return "[]";
		else {
			StringBuilder sb = new StringBuilder(len).append("[");
			for (int i = 0; i < len - 1; i++)
				sb.append(arr[i] + ", ");
			return sb.append(arr[len - 1] + "]").toString();
		}
	}
	
	public static void main(String[] args) {
		ExampleArray<Integer> arr = new ExampleArray<Integer>();
		System.out.println("length: " + arr.size());
		System.out.println("empty: " + arr.isEmpty());
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		System.out.println("length: " +arr.size());
		System.out.println("empty: " + arr.isEmpty());
		System.out.println("element: " + arr.get(3));
		//System.out.println("element: " + arr.get(5));
		
		arr.set(2, 33);
		System.out.println("element: " + arr.get(2));

		//arr.set(5, 33);
		//System.out.println("element: " + arr.get(5));
		
		System.out.println("removeAt: " + arr.removeAt(0));
	    System.out.println("remove: " + arr.remove(2)); 
	    arr.add(55);
	  
	    java.util.Iterator<Integer> iter = arr.iterator();
	    while(iter.hasNext()) {
	    	System.out.println(iter.next()); 
	    }
	  
	    System.out.println(arr.toString());
	  
	    arr.clear(); 
	    System.out.println("length: " + arr.size());
	    System.out.println("empty: " + arr.isEmpty());
	}
}
