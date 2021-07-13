package com.java.datastructure.binarytree;

import java.util.LinkedList;

public class BoundaryAntiClockInBT {
	
	private Node root;
	private LinkedList<Integer> leafNodesStack = new LinkedList<>();
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
//		root = createNode(10);
//		root.left = createNode(20);
//		root.right = createNode(30);
//		
//		Node lTemp = root.left;
//		Node rTemp = root.right;
//		lTemp.left = createNode(40);
//		lTemp.right = createNode(50);
//		rTemp.left = createNode(60);
//		rTemp.right = createNode(70);
		
		root = createNode(31);
		root.left = createNode(98);
		root.right = createNode(65);
		Node lTemp = root.left;
		Node rTemp = root.right;
		lTemp.left = createNode(54);
		lTemp.right = createNode(67);
		//rTemp.left = createNode(60);
		rTemp.right = createNode(81);
		lTemp.left.left = createNode(85);
		lTemp.left.left.right = createNode(92);
		lTemp.right.right = createNode(69);
		lTemp.right.right.left = createNode(78);
		lTemp.right.right.right = createNode(64);
		root.right.right = createNode(81);
		root.right.right.left = createNode(57);
		root.right.right.left.right = createNode(93);
	}
	
	private void leftView(Node root) {
		LinkedList<Node> queue = new LinkedList<>();
		LinkedList<Integer> stack = new LinkedList<>();
		LinkedList<Integer> leftFromBottom = new LinkedList<>();
		queue.add(root);
		int count;
		
		while(!queue.isEmpty()) {
			count = queue.size();
			while (count > 0) {
				Node curr = queue.poll();
				stack.push(curr.data);
				count--;
				
				if (curr.left != null) queue.add(curr.left);
				if (curr.right != null) queue.add(curr.right);
			}
			while (stack.size() != 1)
				stack.pop();
			leftFromBottom.push(stack.pop());
		}
		while (leftFromBottom.size() != 1)
			System.out.print(leftFromBottom.pop() + " ");
		
	}
	
	private void rightView(Node root) {
		LinkedList<Node> queue = new LinkedList<>();
		LinkedList<Integer> stack = new LinkedList<>();
		queue.add(root);
		int count;
		
		while(!queue.isEmpty()) {
			count = queue.size();
			while (count > 0) {
				Node curr = queue.poll();
				stack.push(curr.data);
				count--;
				
				if (curr.left != null) queue.add(curr.left);
				if (curr.right != null) queue.add(curr.right);
			}

			System.out.print(stack.pop() + " ");
			while (stack.size() != 0)
				stack.pop();
		}
		
	}
	
	private void leafNode(Node root) {
		if (root == null) return;
		
		if (root.left == null && root.right == null) leafNodesStack.push(root.data);
		
		leafNode(root.left);
		leafNode(root.right);
	}
	
	public static void main(String[] args) {
	
		BoundaryAntiClockInBT bt = new BoundaryAntiClockInBT();
		bt.helper();
		bt.rightView(bt.root);
		bt.leafNode(bt.root);
		bt.leafNodesStack.pop();
		while (bt.leafNodesStack.size() != 1) {
			System.out.print(bt.leafNodesStack.pop() + " ");
		}
		bt.leftView(bt.root);
		//System.out.println(bt.countNodes(bt.root));
	}
}
