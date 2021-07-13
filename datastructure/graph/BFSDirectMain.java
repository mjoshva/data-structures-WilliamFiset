package com.java.datastructure.graph;

public class BFSDirectMain {

	public static void main(String[] args) {
		BFSDirect bd = new BFSDirect(4);
		
		bd.addEdge(0, 1);
		bd.addEdge(0, 2);
		bd.addEdge(1, 3);
		bd.addEdge(2, 0);
		bd.addEdge(2, 1);
		bd.addEdge(3, 3);
		
		bd.bfs(2);
		
	}
}
