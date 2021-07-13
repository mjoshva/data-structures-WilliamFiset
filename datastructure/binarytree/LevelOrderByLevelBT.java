package com.java.datastructure.binarytree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class LevelOrderByLevelBT {
	
	private Node root;
	
	static class Node {
		Node left;
		int data;
		Node right;
	}
	
	private Node createNode() {
		BufferedReader br = null;
		InputStreamReader ins = null;
		
		Node temp = new Node();
		ins = new InputStreamReader(System.in);
		br = new BufferedReader(ins);
		
		System.out.println("enter data: ");
	    try {
			temp.data = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		temp.left = temp.right = null;
		return temp;
	}
	
	public void helper() {
		root = createNode();
		root.left = createNode();
		root.right = createNode();
		
		Node lTemp = root.left;
		Node rTemp = root.right;
		lTemp.left = createNode();
		lTemp.right = createNode();
		rTemp.left = createNode();
		rTemp.right = createNode();
	}
	
	public void levelOrderTraversal(Node root) {
	
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		int count;
		int level = 0;
		
		while (!queue.isEmpty()) {
			count = queue.size();

			while (count > 0) {
				Node curr = queue.poll();
				count--;
				curr.data = level;
				System.out.print(curr.data + " ");
				if (curr.left != null) queue.add(curr.left);
				if (curr.right != null) queue.add(curr.right);
			}
			System.out.println();
			++level;
		}
	}
	
	public static void main(String[] args) {
	
		LevelOrderByLevelBT bt = new LevelOrderByLevelBT();
		bt.helper();
		bt.levelOrderTraversal(bt.root);
	}
}
