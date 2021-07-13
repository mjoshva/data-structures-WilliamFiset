package com.java.datastructure.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class DFS {

	private int noOfEdges;
	private LinkedList<Integer> adj[];
	boolean[] visited;
	DFS(int noOfEdges) {
		this.noOfEdges = noOfEdges;
		adj = new LinkedList[noOfEdges];
		visited = new boolean[noOfEdges];
		
		for (int i = 0; i< noOfEdges; i++)
			adj[i] = new LinkedList<>();
	}
	
	public void addEdge(int from, int to) {
		adj[from].add(to);
	}
	
	public void dfs(int elem) {
		
		visited[elem] = true;
		System.out.println(elem);
		
		Iterator<Integer> iter = adj[elem].listIterator();
		while (iter.hasNext()) {
			int next = iter.next();
			if (!visited[next]) {
				dfs(next);
			}
		}
		
		
//		if (visited[elem]) return;
//		visited[elem] = true;
//		System.out.println(elem);
//		
//		Iterator<Integer> iter = adj[elem].listIterator();
//		while (iter.hasNext()) {
//			dfs(iter.next());
//		}
				
	}
}
