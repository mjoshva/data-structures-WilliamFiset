package com.java.datastructure.sorting;

import java.util.Arrays;

public class ExampleSort {

	private void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	public int partition(int[] arr, int start, int end) {
		
		int pivot = arr[end];
		int pi = start - 1;
		
		for (int i = start; i < end; i++) {
			if (arr[i] < pivot)
				swap(arr, i, ++pi);
		}
		swap(arr, end, ++pi);
		System.out.println(Arrays.toString(arr));
		return pi;
	}
	
	public void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int pi;
			pi = partition(arr, start, end);
			quickSort(arr, start, pi - 1);
			quickSort(arr, pi + 1, end);
		}
	}
	
	public static void main(String[] args) {
		
		ExampleSort s = new ExampleSort();
		int[] arr = {4, 5, 7, 9, 2, 1, 6};
		s.quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
}
