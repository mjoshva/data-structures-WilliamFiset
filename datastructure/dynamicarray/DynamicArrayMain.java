package com.java.datastructure.dynamicarray;

public class DynamicArrayMain {

	public static void main(String[] args) {
		DynamicArray<Integer> arr = new DynamicArray<Integer>();
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
