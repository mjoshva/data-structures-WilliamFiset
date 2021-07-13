package com.java.datastructure.unionfind;

public class UnionFind {

	// No.of elt in this Union Find.
	private int size;
	
	// Used to track the size of the each component.
	private int[] sz;
	
	// id[i] point to the parent node of i, if id[i] == i then i is a root node.
	private int[] id;
	
	// Track the no.of components in the Union Find
	private int numComponent;
	
	public UnionFind(int size) {
		
		if (size <= 0) throw new IllegalArgumentException();
		
		this.size = numComponent = size;
		sz = new int[size];
		id = new int[size];
		
		for (int i = 0; i < size; i++) {
			id[i] = i;						// Link to itself (self root)
			sz[i] = 1;						// Each Component is originally of one size
		}
	}
	
	public int find(int p) {
		
		int root = p;
		
		// Find the root of the component/set
		while (root != id[root])
			root = id[root];
		
		// And Path-Compression
		while (p != root) {
			int next = id[p];
			id[p] = root;
			p = next;
		}
		
		return root;
	}
	
	public int findRec(int p) {
		
		int ap = id[p];
		
		if (p != ap) {
			ap = findRec(ap);
			id[p] = ap;
		}
		return ap;
	}
	
	// Return whether or not the elt 'p' and 'q' are in the same component/set
	public boolean connected(int p, int q) {
		System.out.println("findRec fn: " + (findRec(p) == findRec(q)));
		return find(p) == find(q);
	}
	
	// Return the size of the component/set belongs to
	public int componentSize(int p) {
		return sz[find(p)];
	}
	
	// Return the no.of elt in the UnionFind/Disjoint set.
	public int size() {
		return size;
	}
	
	// Return the no.of remaining components/sets
	public int components() {
		return numComponent;
	}
	
	// union/unify the components/sets containing elt 'p' and 'q'.
	public void unify(int p, int q) {
		
		int root1 = find(p);
		int root2 = find(q);
		
		// These elts are already in the same group.
		if (root1 == root2) return;
		
		// Merge two components/sets together
		// Merge smaller component/set into the larger one
		if (sz[root1] < sz[root2]) {
			sz[root2] += sz[root1];
			id[root1] = root2;
		} else {
			sz[root1] += sz[root2];
			id[root2] = root1;
		}
		
		// Since the root components are different we know that the no.of
		// components/sets has decreased by one
		numComponent--;
	}
	
	public void print() {
		for(int i = 0; i < size; i++) {
			System.out.println(i + " - " + id[i]);
		}
	}
	
}
