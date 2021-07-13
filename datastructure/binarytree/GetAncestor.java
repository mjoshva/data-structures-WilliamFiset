package com.java.datastructure.binarytree;

public class GetAncestor {
	
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
		
		lTemp.right.right = createNode(80);
		lTemp.right.right.right = createNode(90);
		
		kNode = lTemp.right.right;
	}
	
	private int getAncesotr(Node root, Node kNode) {
		if (root == null) return 0;
		
		int lp = getAncesotr(root.left, kNode);
		int rp = getAncesotr(root.right, kNode);
		
		if (root.left == kNode || root.right == kNode || lp == 1 || rp == 1) {
			System.out.println(root.data);
			return 1;
		}
		return 0;
	}
	public static void main(String[] args) {
	
		GetAncestor bt = new GetAncestor();
		bt.helper();
		System.out.println(1 == bt.getAncesotr(bt.root, bt.kNode));
	}
}
