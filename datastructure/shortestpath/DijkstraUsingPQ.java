package com.java.datastructure.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraUsingPQ {

	class Node implements Comparator<Node> {
	
		public int node;
		public int cost;
		
		public Node() {}
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		
		public int compare(Node node1, Node node2) {
			
			if (node1.cost < node2.cost) return -1;
			if (node1.cost > node2.cost) return 1;
			return 0;
		}
	}
	
	public DijkstraUsingPQ() {}
	
	public Object[] dijkstra(List<List<Node>> adj, int len, int src) {
		
		//boolean[] visited = new boolean[len];
		Set<Integer> processed = new HashSet<Integer>(len);
		
		int[] dist = new int[len];
		for (int i = 0; i < len; i++) dist[i] = Integer.MAX_VALUE;
		dist[src] = 0;
		
		int[] prevNode = new int[len];
		prevNode[src] = -1;
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>(len, new Node());
		pq.add(new Node(src, 0));
		
		for (int i = 0 ; i < len ; i++) {
			
			Node nextMin = pq.poll();
			int index = nextMin.node;
			
			//visited[u] = true;
			processed.add(index);
			
			if (dist[index] < nextMin.cost) continue;
			
			for (Node edge : adj.get(index)) {
			
				if (processed.contains(edge.node)) continue;
				
				int newDist = dist[index] + edge.cost;
				
				if (newDist < dist[edge.node]) {
					
					dist[edge.node] = newDist;
					prevNode[edge.node] = index;
					pq.add(new Node(edge.node, newDist));
				}
			}
		}
		
        System.out.println("\nvalue: " + Arrays.toString(dist));
        System.out.println("parent: " + Arrays.toString(prevNode));
        
        Object[] obj = new Object[2];
        obj[0] = dist;
        obj[1] = prevNode;
        
        return obj;
	}
	
public Object[] dijkstra(List<List<Node>> adj, int len, int src, int end) {
		Object[] obj = new Object[2];
	    
		//boolean[] visited = new boolean[len];
		Set<Integer> processed = new HashSet<Integer>(len);
		
		int[] dist = new int[len];
		for (int i = 0; i < len; i++) dist[i] = Integer.MAX_VALUE;
		dist[src] = 0;
		
		int[] prevNode = new int[len];
		prevNode[src] = -1;
		
		obj[0] = dist;
	    obj[1] = prevNode;
	    
		PriorityQueue<Node> pq = new PriorityQueue<Node>(len, new Node());
		pq.add(new Node(src, 0));
		
		for (int i = 0 ; i < len ; i++) {
			
			Node nextMin = pq.poll();
			int index = nextMin.node;
			
			//visited[u] = true;
			processed.add(index);
			
			// This 'if' statements prevent duplicate key-value pair execute.
			if (dist[index] < nextMin.cost) continue;
			
			for (Node edge : adj.get(index)) {
			
				if (processed.contains(edge.node)) continue;
				
				int newDist = dist[index] + edge.cost;
				
				if (newDist < dist[edge.node]) {
					
					dist[edge.node] = newDist;
					prevNode[edge.node] = index;
					pq.add(new Node(edge.node, newDist));
				}
			}
			
			// If 'end' node (to node) is visited then its distance value never changed
			// So we can stop the iteration.
			if (index == end) {
				//System.out.println(dist[end]);
				return obj;
			}
		}
		
        System.out.println("\nvalue: " + Arrays.toString(dist));
        System.out.println("parent: " + Arrays.toString(prevNode));
        
        return obj;
	}
	
	public void findShortestPath(List<List<Node>> adj, int len, int src, int end) {
		
		Object[] obj = dijkstra(adj, len, src, end);
		int[] dist =(int[]) obj[0];
		int[] prev = (int[]) obj[1];
		
		List<Integer> path = new ArrayList<Integer>();
		
		if (dist[end] == Integer.MAX_VALUE) System.out.println("null");
		
		for (int i = end; i != -1; i = prev[i]) path.add(i);
		
		Collections.reverse(path);
		System.out.println(path.toString());
	}
	
	public static void main(String[] args) {
		int len = 5;
		
		DijkstraUsingPQ d = new DijkstraUsingPQ();
		
		List<List<Node>> adj = new ArrayList<List<Node>>();
		
		for (int i = 0; i < len; i++) {
			List<Node> node = new ArrayList<Node>();
			adj.add(node);
		}
		
		adj.get(0).add(d.new Node(1, 4));
		adj.get(0).add(d.new Node(2, 1));
		
		adj.get(1).add(d.new Node(3, 1));
  
        adj.get(2).add(d.new Node(1, 2));
        adj.get(2).add(d.new Node(3, 5));
        
        adj.get(3).add(d.new Node(4, 3));
        //d.dijkstra(adj, len, 0);
        
        d.findShortestPath(adj, len, 0, 3);
	}
}
