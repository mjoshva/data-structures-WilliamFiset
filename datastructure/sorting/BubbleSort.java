package com.java.datastructure.sorting;

import java.util.Arrays;

public class BubbleSort {

	public static void sort(int[] arr) {
		int len = arr.length;
		int i, j, flag;
		//int topThree = arr.length;		// This is used to find top 3 three element in array
		for (i = 0; i < len - 1; i++) {
			flag = 1;
			
			for (j = 0; j < len - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					flag = 0;

					arr[j] = arr[j] + arr[j + 1];
					arr[j + 1] = arr[j] - arr[j + 1];
					arr[j] = arr[j] - arr[j + 1];
				}				
			}
			if (flag == 1) return;
			//System.out.println(arr[--topThree]);
			//if ((len -3) == topThree) return arr;
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {5, 0, 3, 2, 1};
		//int[] arr = {5, 0, 1, 2, 3};
		//int[] arr = {1, 2, 3, 4, 5};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
