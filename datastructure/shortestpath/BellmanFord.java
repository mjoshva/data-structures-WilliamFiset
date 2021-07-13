package com.java.datastructure.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFord {

	
	public static class Node {
		
		int from, to;
		double cost;
		
		public Node(int from, int to, double cost) {
	
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Node>[] createGraph(int V) {
		
		List<Node>[] graph = new List[V];
		for (int i = 0; i < V; i++) graph[i] = new ArrayList<Node>();
		
		return graph;
	}
	
	public static void addEdge(List<Node>[] graph, int from, int to, double cost) {
		
		graph[from].add(new Node(from, to, cost));
	}
	
	public static double[] bellmanFord(List<Node>[] graph, int V, int start) {
		
		double[] dist = new double[V];
		Arrays.fill(dist, Double.POSITIVE_INFINITY);
		dist[start] = 0;
		
		int[] prevNode = new int[V];
		prevNode[start] = -1;
		
		for (int i = 0; i < V - 1; i++) {
			
			for (List<Node> edges : graph) {
				
				for (Node edge : edges) {
					
					if (dist[edge.from] + edge.cost < dist[edge.to]) {
						
						dist[edge.to] = dist[edge.from] + edge.cost;
						prevNode[edge.to] = edge.from;
					}
				}
			}
		}
		
		for (List<Node> edges : graph) {
			
			for (Node edge : edges) {
				
				if (dist[edge.from] + edge.cost < dist[edge.to]) {
					
					throw new RuntimeException("cycle detected");
				}
			}
		}
		
		System.out.println("\nvalue: " + Arrays.toString(dist));
	    System.out.println("parent: " + Arrays.toString(prevNode));
		
		return dist;
	}
	
	public static void main(String[] args) {
		
		int E = 10, V = 9, start = 0;
	    List<Node>[] graph = createGraph(V);
	    addEdge(graph, 0, 1, 1);
	    addEdge(graph, 1, 2, 1);
	    addEdge(graph, 2, 4, 1);
	    addEdge(graph, 4, 3, -3);
	    addEdge(graph, 3, 2, 1);
	    addEdge(graph, 1, 5, 4);
	    addEdge(graph, 1, 6, 4);
	    addEdge(graph, 5, 6, 5);
	    addEdge(graph, 6, 7, 4);
	    addEdge(graph, 5, 7, 3);
	    double[] d = bellmanFord(graph, V, start);
	    
	}
}
