package com.java.datastructure.fenwicktree;

import java.util.Arrays;

// This Fenwick Tree, assume array index start from 1.
public class FenwickTree {

	private int[] fenwick;
	
	public void createFenwick(int[] values) {
		
		if (values == null) throw new IllegalArgumentException("Array cannot be null");
		
		int len = values.length;
		fenwick = new int[len];
		//fenwick[0] = 0;
	
		fenwick = values.clone();
		
		int next;
		for (int i = 1; i < len; i++) {
			
			next = getNextResNode(i);

			if (next < len) {
				//System.out.println("index: " + i + " next: "+ next + " len: "+ len);
				fenwick[next] += fenwick[i];
				//System.out.println(Arrays.toString(fenwick));
			}
			
		}
		//System.out.println(Arrays.toString(fenwick));
	}
	
	public int sum(int index) {
		int sum = 0;
		
		while (index > 0) {
			sum = sum + fenwick[index];
			index = getParent(index);
		}
		return sum;
	}
	
	public int sumRange(int left, int right) {
		
		if (left > right) throw new IllegalArgumentException("make sure right >= left"); 
		
		return sum(right) - sum(left - 1); 
	}
	
	public void add(int index, int value) {
		int len = fenwick.length;
		
		while (index < len) {
			fenwick[index] += value;
			index= getNextResNode(index);
		}
	}
	
	public void set(int index, int value) {
		 
		int oldValue = sumRange(index, index);

		add(index, value - oldValue);
	}
	
	private int getNextResNode(int i) {
		return i + (i & -i);
	}
	
	private int getParent(int i) {
		return i - (i & -i);
	}
	
	public String toString() {
		return Arrays.toString(fenwick);
	}
	public static void main(String[] args) {
		
		FenwickTree fw = new FenwickTree();
		//int[] values = {0, 3, 2, -1, 6, 5, 4, -3, 3};
		int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		fw.createFenwick(values);
		
		//System.out.println(fw.sum(5));
		
		//System.out.println(fw.sumRange(4, 5));
		
		//fw.add(3, 10);
		//System.out.println(fw);
		//fw.set(3, 5);
		//System.out.println(fw);
	}
}
