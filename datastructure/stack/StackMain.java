package com.java.datastructure.stack;

import java.util.Iterator;

public class StackMain {

	public static void main(String[] args) {
		Stack<Integer> st = new Stack<Integer>();
		st.push(1);
		st.push(2);
		st.push(3);
		st.push(4);
		//System.out.println(st.size());
		System.out.println(st.isEmpty());
		st.pop();
		
		Iterator<Integer> iter = st.iterator();
		
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		st.pop();
		st.pop();
		st.pop();
		//st.pop();
		System.out.println(st.isEmpty());
		//System.out.println(st.peek());
	}
}
