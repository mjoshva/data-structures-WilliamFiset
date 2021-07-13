package com.java.datastructure.binarytree;

import java.util.LinkedList;

public class ConvertToMirrorTree {
	
	private Node root;
	public Node kNode;
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
		
		kNode = createNode(120);
	}
	
	public void levelOrderTraversal(Node root) {
		
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		int count;
		
		while (!queue.isEmpty()) {
			count = queue.size();

			while (count > 0) {
				Node curr = queue.poll();
				count--;
				System.out.print(curr.data + " ");
				if (curr.left != null) queue.add(curr.left);
				if (curr.right != null) queue.add(curr.right);
			}
			System.out.println();
		}
	}
	
	private void convertToMirror(Node root) {
		if (root == null) return;
		
		convertToMirror(root.left);
		convertToMirror(root.right);
		
		Node temp = root.left;
		root.left = root.right;
		root.right = temp;
		
	}
	public static void main(String[] args) {
	
		ConvertToMirrorTree bt = new ConvertToMirrorTree();
		bt.helper();
		bt.convertToMirror(bt.root);
		bt.levelOrderTraversal(bt.root);;
		System.out.println(bt.root.left.left.data);
	}
}
