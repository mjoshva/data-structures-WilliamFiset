package com.java.datastructure.binarytree;

public class CheckNodePresentInBT {
	
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
	
	private int isNodePresent(Node root, Node k ) {
		if (root == null) return 0;
		if (root.data == k.data) return 1;
		
		int lp = isNodePresent(root.left, k);
		int rp = isNodePresent(root.right, k);
		
		return lp == 1 ? lp : rp;
	}
	public static void main(String[] args) {
	
		CheckNodePresentInBT bt = new CheckNodePresentInBT();
		bt.helper();
		System.out.println(1 == bt.isNodePresent(bt.root, bt.kNode));
	}
}
