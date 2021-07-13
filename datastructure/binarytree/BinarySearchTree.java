package com.java.datastructure.binarytree;

//import java.util.Iterator;
import java.util.LinkedList;

public class BinarySearchTree<T extends Comparable<T>> {

	private int nodeCount;
	
	public Node root;
	
	private class Node {
		T data;
		Node left;
		Node right;
		
		public Node(T data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return nodeCount;
	}
	
	public boolean add(T elem) {
		
		if (contains(elem)) {
			return false;
		} else {
			root  = add(root, elem);
			nodeCount++;
			return true;
		}
	}
	
	private Node add(Node node, T elem) {
		
		if (node == null) {
		
			node = new Node(elem, null, null);
			
		} else {
			
			if (elem.compareTo(node.data) < 0) {
				node.left = add(node.left, elem);
			} else {
				node.right = add(node.right, elem);
			}
		}
		return node;
	}
	
	public boolean remove(T elem) {
		
		if (contains(elem)) {
			root = remove(root, elem);
			--nodeCount;
			return true;
		}
		return false;
	}
	
	private Node remove(Node node, T elem) {
		
		if (node == null) return null;
		
		int compareValue = elem.compareTo(node.data);
		
		if (compareValue < 0) {
			
			node.left = remove(node.left, elem);
			
		} else if (compareValue > 0) {
			
			node.right = remove(node.right, elem);
			
		} else {
			
			if (node.left == null) {
				
				return node.right;
				
			} else if (node.right == null) {
				
				return node.left;
				
			} else {
				
				// Find min in right subtree or max in left subtree
				// Node temp = findMax(node.left);
				Node temp = findMin(node.right);
				
				node.data = temp.data;
				
				// For max value
				// node.right = remove(node.left, temp.data);
				node.left = remove(node.right, temp.data);
			}	
		}
		return node;
	}
	
	public boolean contains(T elem) {
		return contains(root, elem);
	}
	
	private boolean contains(Node node, T elem) {
		
		if (node == null) return false;
		
		int compareValue = elem.compareTo(node.data);
		
		if (compareValue < 0 ) return contains(node.left, elem);
		
		else if (compareValue > 0) return contains(node.right, elem);
		
		else return true;
	}
	
	private Node findMin(Node node) {
		
		while (node.left != null) node = node.left;
		return node;
	}
	
	private Node findMax(Node node) {
		
		while (node.right != null) node = node.right;
		return node;
	}
	
	public int height() {
		return height(root);
	}
	
	private int height(Node node) {
		
		if (node == null) return 0;
		
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	public void inOrder(Node node) {
		if (node == null) return;
		
		inOrder(node.left);
		System.out.print(node.data + ", ");
		inOrder(node.right);
	}
	
	public void levelByLevel() {
		
		LinkedList<Node> queue = new LinkedList<Node>();
		int count = 0;
		queue.add(root);
		
		while (!queue.isEmpty()) {
			count = queue.size();
			
			while (count > 0) {
				Node temp = queue.poll();
				count--;
				System.out.print(temp.data + " ");
				
				if (temp.left != null) queue.add(temp.left);
				if (temp.right != null) queue.add(temp.right);
				
				
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(5);
		bst.add(3);
		bst.add(4);
		bst.add(1);
		bst.add(7);
		bst.add(6);
		bst.add(8);
		
		//System.out.println(bst.remove(5));
		
		//System.out.println(bst.height());
		
		//bst.inOrder(bst.root);
		//System.out.println();
		
		//bst.levelByLevel();
		
		
		
	}
}
