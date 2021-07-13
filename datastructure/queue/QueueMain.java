package com.java.datastructure.queue;

import java.util.Iterator;

public class QueueMain {

	public static void main(String[] args) {
		Queue<Integer> qu = new Queue<Integer>();
		qu.offer(1);
		qu.offer(2);
		qu.offer(3);
		qu.offer(4);
		qu.offer(5);
		
		System.out.println(qu.size());
		System.out.println(qu.isEmpty());
		
		qu.poll();
		qu.poll();
		qu.poll();
		
		System.out.println(qu.peek());
		
		Iterator<Integer> iter = qu.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
