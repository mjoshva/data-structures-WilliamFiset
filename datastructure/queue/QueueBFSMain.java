package com.java.datastructure.queue;

import com.java.datastructure.queue.QueueBFS.Node;

public class QueueBFSMain {

	public static void main(String[] args) {
		
		QueueBFS qb = new QueueBFS();
		
		Node n0 = new Node(0);
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		Node n9 = new Node(9);
		Node n10 = new Node(10);
		Node n11 = new Node(11);
		//Node n12 = new Node(12);
		
		n0.addNeighbours(n1);
		n0.addNeighbours(n9);
		n1.addNeighbours(n8);
		n9.addNeighbours(n8);
		n8.addNeighbours(n7);
		n7.addNeighbours(n3);
		n7.addNeighbours(n6);
		n7.addNeighbours(n10);
		n7.addNeighbours(n11);
		n3.addNeighbours(n2);
		n3.addNeighbours(n4);
		n6.addNeighbours(n5);
		
		qb.bfs(n0);
		
	}
}
