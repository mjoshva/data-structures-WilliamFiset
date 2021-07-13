package com.java.datastructure.binarytree;

public class HeightOfTheTree {
	
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
	
	public int heightOfTree(Node root) {
		if (root == null) return 0;
		
		if (root.left == null && root.right == null) return 0;
		
		return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
	}
	
	public static void main(String[] args) {
	
		HeightOfTheTree bt = new HeightOfTheTree();
		bt.helper();
		System.out.println(bt.heightOfTree(bt.root));
	}
}
