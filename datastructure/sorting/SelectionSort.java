package com.java.datastructure.sorting;

import java.util.Arrays;

public class SelectionSort {

	public static void sort(int[] arr) {
		int len = arr.length;
		int i, j, mid;
		//int minThree = 0;
		
		for (i = 0; i < len; i++) {
			mid = i;
			for (j = i + 1; j < len; j++) {
				if (arr[j] < arr[mid]) {
					mid = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[mid];
			arr[mid] = temp;
			
			//System.out.println(arr[minThree++]);
			//if (minThree == 3) return;
		}
		
		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		int[] arr = {27, 35, 14, 16, 10};
		sort(arr);
	}
}
