package com.java.datastructure.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueBFS {

	private Queue<Node> queue;
	
	static class Node {
		int data;
		boolean visited;
		List<Node> neighbours;
		
		Node(int data) {
			this.data = data;
			this.neighbours = new ArrayList<>();
		}
		
		public void addNeighbours(Node neighbourNode ) {
			neighbours.add(neighbourNode);
		}
		
		public List<Node> getNeighbours() {
			return neighbours;
		}
		
		public void setNeighbours(List<Node> neighbours) {
			this.neighbours = neighbours;
		}
	}
	
	public QueueBFS() {
		queue = new LinkedList<Node>();
	}
	
	public void bfs(Node node) {
		queue.add(node);
		node.visited = true;
		
		while (!queue.isEmpty()) {
			Node element = queue.remove();
			
			System.out.println(element.data);
			
			for (Node neighbour: element.getNeighbours()) {
				
				if(neighbour != null && !neighbour.visited) {
					neighbour.visited = true;
					queue.add(neighbour);
				}
			}
		}
	}
}
