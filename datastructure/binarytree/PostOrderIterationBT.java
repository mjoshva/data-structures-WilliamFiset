package com.java.datastructure.binarytree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class PostOrderIterationBT<T> {
	
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
	
	public void innerOrder(Node<T> root) {
		LinkedList<Node<T>> stack = new LinkedList<>();
		LinkedList<T> ans = new LinkedList<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node<T> curr = stack.pop();
			ans.push(curr.data);
			
			if (curr.left != null) stack.push(curr.left);
			if (curr.right != null) stack.push(curr.right);
		}
		
		while (!ans.isEmpty())
			System.out.println(ans.pop());
		
	}
	
	public static void main(String[] args) {
	
		PostOrderIterationBT<Integer> bt = new PostOrderIterationBT<Integer>();
		bt.helper();
		bt.innerOrder(bt.root);
	}
}
