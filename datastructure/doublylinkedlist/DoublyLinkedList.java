package com.java.datastructure.doublylinkedlist;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T>{

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;
	
	// Internal node class to represent data 
	private static class Node<T> {
		T data;
		Node<T> prev, next;		// Pointers for each nodes
		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
		//@Override
		//public String toString() {
			//return data.toString();
		//}
	}
	
	public void clear() {
		Node<T> trav = head;
		while(trav != null) {
			Node<T> next = trav.next;
			trav.prev = trav.next = null;
			trav.data = null;
			trav = next;
		}
		head = tail = trav = null;
		size = 0;
	}
	
	public int size() { return size; }
	
	public boolean isEmpty() { return size() == 0; }
	
	// Add element to the tail of the linked list
	public void add(T elem) {
		addLast(elem);
	}
	
	// Add element to the beginning of the linked list
	public void addFirst(T elem) {
		if (isEmpty()) {
			head = tail = new Node<T>(elem, null, null);
		} else {
			head.prev = new Node<T>(elem, null, head);
			head = head.prev;
		}
		size++;
	}
	
	public void addLast(T elem) {
		if (isEmpty()) {
			head = tail = new Node<T>(elem, null, null);
		} else {
			tail.next = new Node<T>(elem, tail, null);
			tail = tail.next;
		}
		size++;
	}
	
	public void insert(int index, T elem) {
		if (index < 0 || index >= size) throw new IllegalArgumentException();
		
		Node<T> trav = null;
		int position = 0;
		for (trav = head; trav != null; trav = trav.next, position++) {
			if (index == position) {
				
				trav.prev.next = new Node<T>(elem, trav.prev, trav.prev.next);
				trav.prev = trav.prev.next;
			}
		}
	}
	
	// Check the value of the first node if it exist
	public T peekFirst() {
		if (isEmpty()) throw new RuntimeException("Emplty list");
		return head.data;
	}
	
	// Check the value of the last node if it exist
	public T peekLast() {
		if (isEmpty()) throw new RuntimeException("Empty list");
		return tail.data;
	}
	
	public T removeFirst() {
		if (isEmpty()) throw new RuntimeException("Empty list");
		
		T data = head.data;
		head = head.next;
		--size;
		
		// If the list is empty set the tail to null as well
		if (isEmpty()) tail = null;
		
		// Do a memory clean process for previous node
		else head.prev = null;
		
		return data;
	}
	
	public T removeLast() {
		if (isEmpty()) throw new RuntimeException("Empty list");
		
		T data = tail.data;
		tail = tail.prev;
		--size;
		
		if (isEmpty()) head = null;
		
		else tail.next = null;
		
		return data;
	}
	
	private T remove(Node<T> node) {
		if (node.prev == null) return removeFirst();
		if (node.next == null) return removeLast();
				
		node.next.prev = node.prev;
		node.prev.next = node.next;
		
		T data = node.data;
		
		node.data = null;
		node = node.prev = node.next = null;
		
		--size; 
		
		return data;
	}
	
	public T removeAt(int index) {
		if (index < 0 || index >= size) throw new IllegalArgumentException();
		
		int i;
		Node<T> trav;
		
		// Search from front
		if(index < size / 2 ) {
			for (i = 0, trav = head; i != index; i++) {
				trav = trav.next;
			}
		}
		// Search from back
		else {
			for (i = size - 1, trav = tail; i != index; i--) {
				trav = trav.prev;
			}
		}
		
		return remove(trav);
	}
	
	public boolean remove(Object obj) {
		Node<T> trav = null;
		
		if (obj == null) {
			for (trav = head; trav != null; trav = trav.next) {
				if (trav.data == null) {
					remove(trav);
					return true;
				}
			}
		} else {
			for (trav = head; trav != null; trav = trav.next) {
				if (obj.equals(trav.data)) {
					remove(trav);
					return true;
				}
			}
		}
		return false;
		
	}
	
	public int indexOf(Object obj) {
		int index = 0;
		Node<T> trav = null;
		
		if (obj == null) {
			for (trav = head; trav != null; trav = trav.next, index++) {
				if (trav.data == null) {
					return index;
				}
			}
		} else {
			for (trav = head; trav != null; trav = trav.next, index++) {
				if (trav.data.equals(obj)) {
					return index;
				}
			}
		}
		return -1;
	}
	
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> trav = head;
			
			public boolean hasNext() {				
				return trav != null;
			}
			
			public T next() {
				T data = trav.data;
				trav = trav.next;
				return data;
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		Node<T> trav = head;
		while(trav != null) {
			sb.append(trav.data + ", ");
			trav= trav.next;			
		}
		sb.append(" ]");
		return sb.toString();
	}
}
