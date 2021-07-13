package com.java.datastructure.binarytree;

import java.util.LinkedList;

public class LevelOrderLeftViewFromBottomBT {
	
	private Node root;
	
	static class Node {
		Node left;
		int data;
		Node right;
	}
	
	private Node createNode(int value) {
		Node temp = new Node();
		temp.data = value;
		
		temp.left = temp.right = null;
		return temp;
	}
	
	public void helper() {
		root = createNode(10);
		root.left = createNode(20);
		root.right = createNode(30);
		
		Node lTemp = root.left;
		Node rTemp = root.right;
		lTemp.left = createNode(40);
		lTemp.right = createNode(50);
		rTemp.left = createNode(60);
		rTemp.right = createNode(70);
	}
	
	public void levelOrderTraversal(Node root) {
	
		LinkedList<Node> queue = new LinkedList<Node>();
		LinkedList<Integer> stack = new LinkedList<Integer>();
		LinkedList<Integer> leftFromBottom = new LinkedList<Integer>();
		
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
			leftFromBottom.add(stack.pop());
		}
		while (leftFromBottom.size() != 0) {
			System.out.print(leftFromBottom.poll() + " ");
		}
	}
	
	public static void main(String[] args) {
	
		LevelOrderLeftViewFromBottomBT bt = new LevelOrderLeftViewFromBottomBT();
		bt.helper();
		bt.levelOrderTraversal(bt.root);
	}
}
