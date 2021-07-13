package com.java.datastructure.stack;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

	private java.util.LinkedList<T> list = new java.util.LinkedList<T>();

	public Stack() {}
	
	public Stack(T elem) {
		list.addLast(elem);
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public void push(T elem) {
		list.addLast(elem);
	}
	
	public T pop() {
		if (isEmpty()) throw new EmptyStackException();
		return list.removeLast();
	}
	
	public T peek() {
		if (isEmpty()) throw new EmptyStackException();
		return list.peekLast();
	}
	
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
		
}
