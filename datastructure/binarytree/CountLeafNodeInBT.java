package com.java.datastructure.binarytree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountLeafNodeInBT {
	
	private Node root;
	
	static class Node {
		Node left;
		int data;
		Node right;
	}
	
	private Node createNode() {
		BufferedReader br = null;
		InputStreamReader ins = null;
		
		Node temp = new Node();
		ins = new InputStreamReader(System.in);
		br = new BufferedReader(ins);
		
		System.out.println("enter data: ");
	    try {
			temp.data = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		temp.left = temp.right = null;
		return temp;
	}
	
	public void helper() {
		root = createNode();
		root.left = createNode();
		root.right = createNode();
		
		Node lTemp = root.left;
		Node rTemp = root.right;
		lTemp.left = createNode();
		lTemp.right = createNode();
		rTemp.left = createNode();
		rTemp.right = createNode();
	}
	
	public int countNodes(Node root) {
		if (root == null) return 0;
		
		if ( root.left == null && root.right == null)  return 1;
		
		int leftPart = countNodes(root.left);
		int rightPart = countNodes(root.right);
		return leftPart + rightPart;
	}
	
	public static void main(String[] args) {
	
		CountLeafNodeInBT bt = new CountLeafNodeInBT();
		bt.helper();
		System.out.println(bt.countNodes(bt.root));
	}
}
