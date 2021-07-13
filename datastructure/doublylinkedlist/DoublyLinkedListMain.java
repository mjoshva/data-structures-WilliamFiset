package com.java.datastructure.doublylinkedlist;

import java.util.Iterator;

public class DoublyLinkedListMain {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.add(2);
		//list.addFirst(1);
		list.add(3);
		//list.addLast(4);
		
//		System.out.println(list);
//		System.out.println(list.peekFirst());
//		System.out.println(list.peekLast()); 
//		System.out.println(list.removeFirst());
//		System.out.println(list.removeLast());
		
		//list.insert(2, 22);
		//System.out.println(list);
		//System.out.println(list.size());
		
		
		//System.out.println(list.removeAt(3));
		//System.out.println(list);
		
		System.out.println(list.remove(2));
		System.out.println(list);
		
		//System.out.println(list.indexOf(4));
		
		//System.out.println(list.contains(4));
		
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			//System.out.println(iter.next());
		}
	}
}
