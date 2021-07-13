package com.java.datastructure.graph;

import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class BFSDirect {

	private int noOfEdges;
	private LinkedList<Integer> adj[];
	
	BFSDirect(int noOfEdges) {
		this.noOfEdges = noOfEdges;
		adj = new LinkedList[noOfEdges]; 
		for (int i = 0; i< noOfEdges; i++)
			adj[i] = new LinkedList<>();
	}
	
	public void addEdge(int from, int to) {
		adj[from].add(to);
	}
	
	public void bfs(int elem) {
		boolean[] visited = new boolean[noOfEdges];
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(elem);
		visited[elem] = true;
		
		while (queue.size() != 0) {
			elem = queue.poll();
			System.out.println(elem);
			
			Iterator<Integer> iter = adj[elem].listIterator();
			
			while (iter.hasNext()) {
				int next = iter.next();
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
		
	}
}
