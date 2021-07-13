package com.java.datastructure.binarytree;

public class TowBTIdentical {
	
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
	
	public void helper1() {
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
	
	public void helper2() {
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
	
	private int isIdentical(Node root1, Node root2) {
		if (root1 == null && root2 == null) return 1;
		
		if (root1 == null || root2 == null) return 0;

		if (root1.data != root2.data) return 0;
		
		int lp = isIdentical(root1.left, root2.left);
		int rp = isIdentical(root1.right, root2.right);

		return lp == 1 && rp == 1 ? 1 : 0;
	}
	public static void main(String[] args) {
	
		TowBTIdentical bt = new TowBTIdentical();
		TowBTIdentical bt1 = new TowBTIdentical();
		bt.helper1();
		bt1.helper2();
		System.out.println(1 == bt.isIdentical(bt.root, bt1.root));
	}
}
