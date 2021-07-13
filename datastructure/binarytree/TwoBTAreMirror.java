package com.java.datastructure.binarytree;

import java.util.LinkedList;

public class TwoBTAreMirror {
	
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
		root.left = createNode(30);
		root.right = createNode(20);
		
		Node lTemp = root.left;
		Node rTemp = root.right;
		lTemp.left = createNode(70);
		lTemp.right = createNode(60);
		rTemp.left = createNode(50);
		rTemp.right = createNode(40);
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
			//System.out.println();
		}
		System.out.println();
	}
	
	private int checkMirror(Node root1, Node root2) {
		if (root1 == null && root2 == null) return 1;
		
		if (root1 == null || root2 == null) return 0;

		if (root1.data != root2.data) return 0;
		
		int lp = checkMirror(root1.left, root2.right);
		int rp = checkMirror(root1.right, root2.left);

		return lp == 1 && rp == 1 ? 1 : 0;
	}
	public static void main(String[] args) {
	
		TwoBTAreMirror bt = new TwoBTAreMirror();
		TwoBTAreMirror bt1 = new TwoBTAreMirror();
		bt.helper1();
		bt1.helper2();
		//bt.levelOrderTraversal(bt.root);
		//bt.levelOrderTraversal(bt1.root);
		System.out.println(1 == bt.checkMirror(bt.root, bt1.root));
	}
}
