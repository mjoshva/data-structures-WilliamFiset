package com.java.datastructure.graph;

public class DFSMain {

	public static void main(String[] args) {
		DFS bd = new DFS(4);
		
		bd.addEdge(0, 1);
		bd.addEdge(0, 2);
		bd.addEdge(1, 2);
		bd.addEdge(2, 0);
		bd.addEdge(2, 3);
		bd.addEdge(3, 2);
		
		bd.dfs(0);
		
	}
}
