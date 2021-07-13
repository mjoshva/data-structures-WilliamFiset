package com.java.datastructure.shortestpath;

import java.util.Arrays;

public class Dijkstra {

	private int selectMinVertex(int[] value, boolean[] processed) {
		
		int minimum = Integer.MAX_VALUE;
		int vertex = -1;
		int len = value.length;
		
		for (int i = 0; i < len; i++) {
			
			if (!processed[i] && value[i] < minimum) {
				minimum = value[i];
				vertex = i;
			}
		}
		return vertex;
	}
	
	public void dijkstra(int[][] graph) {
		
		int len = graph.length;
		
		// Parent array.
		int[] parent = new int[len];
		
		// Value array.
		int[] value = new int[len];
		
		// Processed/Visited array.
		boolean[] processed = new boolean[len];
		
		// i - stars from 1 because source node 0(zero) value is 0.
		for (int i = 1; i < len; i++) {
			value[i] = Integer.MAX_VALUE;
		}
		parent[0] = -1;
		
		for (int i = 0; i < len - 1; i++) {
			
			// Select minimum value index.
			int u = selectMinVertex(value, processed);
			processed[u] = true;						// Include new vertex in shortest path or mart as visited.

			for (int j = 0; j < len; j++) {
				
				if (graph[u][j] != 0 && !processed[j] && (value[u] + graph[u][j] < value[j])) {

					value[j] = value[u] + graph[u][j];
					parent[j] = u;
				}
			}
		}
		
		System.out.println("value: " + Arrays.toString(value));
		System.out.println("\nparent: " + Arrays.toString(parent));
	}
	
	public static void main(String[] args) {
		
		int[][] graph = { 
				{0, 1, 4, 0, 0, 0},
				{1, 0, 4, 2, 7, 0},
				{4, 4, 0, 3, 5, 0},
				{0, 2, 3, 0, 4, 6},
				{0, 7, 5, 4, 0, 7},
				{0, 0, 0, 6, 7, 0}
		};
		
		Dijkstra d = new Dijkstra();
		d.dijkstra(graph);
	}
}
