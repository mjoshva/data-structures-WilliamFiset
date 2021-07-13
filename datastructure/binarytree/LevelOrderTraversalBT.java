package com.java.datastructure.binarytree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class LevelOrderTraversalBT<T> {
	
	private Node<T> root;
	
	static class Node<T> {
		Node<T> left;
		T data;
		Node<T> right;
	}
	
	@SuppressWarnings("unchecked")
	private Node<T> createNode() {
		BufferedReader br = null;
		InputStreamReader ins = null;
		
		Node<T> temp = new Node<T>();
		ins = new InputStreamReader(System.in);
		br = new BufferedReader(ins);
		
		System.out.println("enter data: ");
	    try {
			temp.data = (T) br.readLine();
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
		
		Node<T> lTemp = root.left;
		Node<T> rTemp = root.right;
		lTemp.left = createNode();
		lTemp.right = createNode();
		rTemp.left = createNode();
		rTemp.right = createNode();
	}
	
	public void levelOrderTraversal(Node<T> root) {
	
		LinkedList<Node<T>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node<T> curr = queue.poll();
			System.out.print(curr.data + " ");
			if (curr.left != null) queue.add(curr.left);
			if (curr.right != null) queue.add(curr.right);
		}
	}
	
	public static void main(String[] args) {
	
		LevelOrderTraversalBT<Integer> bt = new LevelOrderTraversalBT<Integer>();
		bt.helper();
		bt.levelOrderTraversal(bt.root);
	}
}
