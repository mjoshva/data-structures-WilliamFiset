package com.java.datastructure.binarytree;

public class DiameterOfWidthInBT {
	
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
		
		//Node lTemp = root.left;
		Node rTemp = root.right;
		rTemp.left = createNode(40);
		rTemp.right = createNode(50);
		//rTemp.left = createNode(60);
		//rTemp.right = createNode(70);
		rTemp.left.left = createNode(50);;
		rTemp.left.left.right = createNode(50);
		rTemp.right.right = createNode(50);;
		rTemp.right.right.left = createNode(50);
	}
	
	private int height(Node root) {
		if (root == null) return 0;
		
		return 1 + Math.max(height(root.left), height(root.right));
	}

	public int getWidth(Node root) {
		if (root == null) return 0;
		
		int leftHeight = height(root.left);
		int rightHeight = height(root.right);
		int leftWith = getWidth(root.left);
		int rightWidth = getWidth(root.right);
		
		return Math.max(leftHeight + rightHeight + 1, Math.max(leftWith, rightWidth));
	}
	
	public static void main(String[] args) {
	
		DiameterOfWidthInBT bt = new DiameterOfWidthInBT();
		bt.helper();
		System.out.println(bt.getWidth(bt.root));
	}
}
