package com.java.datastructure.binarytree;

public class PrintLeafNodeInBT {
	
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
	
	public void countNodes(Node root) {
		if (root == null) return;
		
		if ( root.left == null && root.right == null)  System.out.print(root.data + " ");
		
		countNodes(root.left);
		countNodes(root.right);
	}
	
	public static void main(String[] args) {
	
		PrintLeafNodeInBT bt = new PrintLeafNodeInBT();
		bt.helper();
		bt.countNodes(bt.root);
	}
}
