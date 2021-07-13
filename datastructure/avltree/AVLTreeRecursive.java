package com.java.datastructure.avltree;

import java.util.Iterator;
import java.util.LinkedList;

public class AVLTreeRecursive<T extends Comparable<T>> implements Iterable<T> {

	public class Node {
		
		// 'bf' is short for Balance Factor
		int bf;
		
		T value;
		int height;
		
		Node left, right;
		
		public Node(T value) {
			this.value = value;
		}
	}
	
	// The root node of the AVL tree.
	private Node root;
	
	private int nodeCount;
	
	public int height() {
		
		if (root == null) return 0;	
		return root.height;
	}
	
	public int size() {
		return nodeCount;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public boolean contains(T value) {
		return contains(root, value);
	}
	
	private boolean contains(Node node, T value) {
		
		if (node == null) return false;
		
		int cmp = value.compareTo(node.value);
		
		if (cmp < 0) return contains(node.left, value);
		
		if (cmp > 0) return contains(node.right, value);
		
		return true;
	}
	
	public boolean insert(T value) {
		
		if (value == null) return false;
		
		if (!contains(value)) {
			
			root = insert(root, value);
			nodeCount++;
			return true;
		}
		return false;
	}
	
	// Inserts a value inside the AVL tree. And also update BF, Height, Balanced Tree
	private Node insert(Node node, T value) {
		
		if (node == null) return new Node(value);
		
		int cmp = value.compareTo(node.value);
		
		if (cmp < 0) node.left = insert(node.left, value);
		
		else node.right = insert(node.right, value);
		
		// Update balance factor and height values.
		update(node);
		
		return balance(node);
	}
	
	private void update(Node node) {
		
		int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
		int rightNodeHeight = (node.right == null) ? -1 : node.right.height;
		
		node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
		
		node.bf = rightNodeHeight - leftNodeHeight;
	}
	
	private Node balance(Node node) {
		
		// Left.
		if (node.bf == -2) {
			
			// Left-Left case.
			if (node.left.bf <= 0) return leftLeft(node);
			
			// Left-Right case.
			else return leftRight(node);
			
		  // Right heavy subtree needs balancing.
		} else if (node.bf == 2) {
			
			// Right-Right case.
			if (node.right.bf >= 0) return rightRight(node);
			
			// Right-Left case.
			else return rightLeft(node);
		}
		
		// Node either has a balance factor of 0, +1 or -1 which is fine.
	    return node;
	}
	
	// LL.
	private Node leftLeft(Node node) {
		return rightRotation(node);
	}
	
	// LR.
	private Node leftRight(Node node) {
		node.left = leftRotation(node.left);
		return rightRotation(node);
	}
	
	// RR.
	private Node rightRight(Node node) {
		return leftRotation(node);
	}
	
	// RL.
	private Node rightLeft(Node node) {
		node.right = rightRotation(node.right);
		return leftRotation(node);
	}
	
	private Node leftRotation(Node node) {
		
		Node newParent = node.right;
		node.right = newParent.left;
		newParent.left = node;
		
		update(node);
		update(newParent);
		
		return newParent;
	}
	
	private Node rightRotation(Node node) {
		
		Node newParent = node.left;
		node.left = newParent.right;
		newParent.right = node;
		
		update(node);
		update(newParent);
		
		return newParent;
	}
	
	public boolean remove(T elem) {
		
		if (elem == null) return false;
		
		if (contains(elem)) {
			root = remove(root, elem);
			--nodeCount;
			return true;
		}
		return false;
	}
	
	private Node remove(Node node, T elem) {
		
		if (node == null) return null;
		
		int cmp = elem.compareTo(node.value);
		
		if (cmp < 0) {
			
			node.left = remove(node.left, elem);
			
		} else if (cmp > 0) {
			
			node.right = remove(node.right, elem);
			
		} else {
			
			if (node.left == null) {
				
				return node.right;
				
			} else if (node.right == null) {
				
				return node.left;
				
			} else {
				
				// Choose to remove left or right. And perform height based. 
				// Remove elt from highest sub tree because balance the BF
				if (node.left.height > node.right.height) {
					
					T successorValue = findMax(node.left);
					node.value = successorValue;
					
					node.left = remove(node.left, successorValue);
					
				} else {
					
					T successorValue = findMin(node.right);
					node.value = successorValue;
					
					node.right = remove(node.right, successorValue);
				}
			}
		}
		update(node);
		return balance(node);
	}
	
	private T findMin(Node node) {	
		while (node.left != null) node = node.left;
		return node.value;
	}
	
	private T findMax(Node node) {
		while (node.right != null) node = node.right;
		return node.value;
	}
	
	public void levelByLevel() {
		
		LinkedList<Node> queue = new LinkedList<>();
		int count = 0;
		queue.add(root);
		
		while (!queue.isEmpty()) {
			count = queue.size();
			
			while (count > 0) {
				
				Node curr = queue.poll();
				count--;
				
				System.out.print(curr.value + " ");
				
				if (curr.left != null) queue.add(curr.left);
				if (curr.right != null) queue.add(curr.right);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public boolean isValidBBST(Node node) {
		
		if (node == null) return true;
		
		T value = node.value;
		boolean isValid = true;
		
		if (node.left != null) isValid = isValid && node.left.value.compareTo(value) < 0;
		if (node.right != null) isValid = isValid && node.right.value.compareTo(value) > 0;
		
		return isValid && isValidBBST(node.left) && isValidBBST(node.right);
	}
	
	@Override
	public Iterator<T> iterator() {
		return null;
	}
	
	public static void main(String[] args) {
		AVLTreeRecursive<Integer> avl = new AVLTreeRecursive<>();
		avl.insert(5);
		avl.insert(2);
		avl.insert(3);
		avl.insert(4);
		avl.insert(6);
		avl.insert(7);
		avl.levelByLevel();
		
		avl.remove(5);
		avl.levelByLevel();
		
		System.out.println(avl.isValidBBST(avl.root));
	}
}
