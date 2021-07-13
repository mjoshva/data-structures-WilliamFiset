package com.java.datastructure.sorting;

import java.util.Arrays;

public class MergeSortRecursive {

	private void merger(int[] arr, int start, int mid, int end) {
		
		int leftSize = mid - start + 1, rightSize = end - mid;
		int[] leftArr = new int[leftSize], rightArr = new int[rightSize];
		int i, j;
		
		for (i = 0; i < leftSize; i++)
			leftArr[i] = arr[i + start];
		for (j = 0; j < rightSize; j++)
			rightArr[j] = arr[mid + j + 1];
		
		//System.out.println(Arrays.toString(leftArr) + " " + start + ",  " + mid + ", " + end + " " + Arrays.toString(rightArr));
		
		i = j = 0;
		int k = start;
		
		while (i < leftSize && j < rightSize) {
			
			if (leftArr[i] < rightArr[j])
				arr[k++] = leftArr[i++];
			else arr[k++] = rightArr[j++];
		}
		
		while (i < leftSize) arr[k++] = leftArr[i++];
		while (j < rightSize) arr[k++] = rightArr[j++];
		
		
		//System.out.println(Arrays.toString(arr));
	}
	
	public void mergeSort(int[] arr, int start, int end) {
		if (start < end) {
			int mid = (start + end) /2;
			mergeSort(arr, start, mid);
			mergeSort(arr, mid + 1, end);
			merger(arr, start, mid, end);
		}
	}
	
	public static void main(String[] args) {
		MergeSortRecursive ms = new MergeSortRecursive();
		int[] arr = {13, 21, 41, 27, 16, 11, 12};
		//int[] arr = {90,23,101,45,65,23,67,89,34,23}; 
		ms.mergeSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
}
