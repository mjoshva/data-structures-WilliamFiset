package com.java.datastructure.binarytree;

public class SumFromRootToLeaf {
	
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
	
	private void heightOfTree(Node root, int sum) {
		if (root == null) return;
		
		sum += root.data;
		
		if (root.left == null && root.right == null) {
			System.out.println(sum);
			return;
		}
			
		heightOfTree(root.left, sum);
		heightOfTree(root.right, sum);
	}

	
	public static void main(String[] args) {
	
		SumFromRootToLeaf bt = new SumFromRootToLeaf();
		bt.helper();
		bt.heightOfTree(bt.root, 0);
	}
}
