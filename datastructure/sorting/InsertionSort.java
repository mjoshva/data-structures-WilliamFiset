package com.java.datastructure.sorting;

import java.util.Arrays;

public class InsertionSort {

	public static void sort(int[] arr) {
		int len = arr.length;
		int key, j;
		
		for (int i = 1; i < len; i++) {
			key = arr[i];
			j = i - 1;
			
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {45, 9, 25, 15, 10};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
