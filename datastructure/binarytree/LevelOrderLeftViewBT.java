package com.java.datastructure.binarytree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class LevelOrderLeftViewBT {
	
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
		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		queue.add(root);
		int count;
		
		while (!queue.isEmpty()) {
			count = queue.size();

			while (count > 0) {
				Node curr = queue.poll();
				count--;
				
				stack.push(curr.data);
				
				if (curr.left != null) queue.add(curr.left);
				if (curr.right != null) queue.add(curr.right);
			}
			
			while (stack.size() != 1) {
				stack.pop();
			}
			System.out.print(stack.pop() + " ");
		}
	}
	
	public static void main(String[] args) {
	
		LevelOrderLeftViewBT bt = new LevelOrderLeftViewBT();
		bt.helper();
		bt.levelOrderTraversal(bt.root);
	}
}
