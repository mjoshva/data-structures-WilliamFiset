package com.java.datastructure.sorting;

import java.util.Arrays;

public class QuickSort {

	private void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	private int partion(int[] arr, int start, int end) {
		int pivot = arr[end];
		int pi = start - 1;
		
		for (int i = start; i < end; i++) {
			if (arr[i] < pivot) {
				swap(arr, i, ++pi);
			}
		}
		swap(arr, end, ++pi);
		return pi;
	}
	
	public void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int pi;
			pi = partion(arr, start, end);
			quickSort(arr, start, pi - 1);
			quickSort(arr, pi + 1, end);
		}
	}
	
	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		int[] arr = {57, 31, 49, 65, 81, 10, 42};
		
		qs.quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
}
