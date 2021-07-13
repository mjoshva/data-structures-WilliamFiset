package com.java.datastructure.binarytree;

import java.util.LinkedList;


public class LevelOrderRightViewFromBottomBT {
	
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
		LinkedList<Integer> rightFromBottom = new LinkedList<Integer>();
		
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
			rightFromBottom.push(stack.pop());
			
			while (stack.size() != 0) {
				stack.pop();
			}
		}
		while (rightFromBottom.size() != 0) {
			System.out.print(rightFromBottom.pop() + " ");
		}
	}
	
	public static void main(String[] args) {
	
		LevelOrderRightViewFromBottomBT bt = new LevelOrderRightViewFromBottomBT();
		bt.helper();
		bt.levelOrderTraversal(bt.root);
	}
}
