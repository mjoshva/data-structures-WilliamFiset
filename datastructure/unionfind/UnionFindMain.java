package com.java.datastructure.unionfind;

public class UnionFindMain {

	public static void main(String[] args) {
		
		UnionFind uf = new UnionFind(10);
		uf.unify(1, 2);
		uf.unify(2, 3);
		uf.unify(3, 4);
		uf.unify(5, 6);
		uf.unify(6, 7);
		uf.unify(8, 9);
		uf.unify(4, 5);
		uf.unify(7, 8);
		
		System.out.println(uf.findRec(5));
		
		System.out.println(uf.connected(5, 4));
		//uf.print();
	}
}
