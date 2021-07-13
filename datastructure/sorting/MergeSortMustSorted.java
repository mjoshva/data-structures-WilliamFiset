package com.java.datastructure.sorting;

import java.util.Arrays;

public class MergeSortMustSorted {

	public static void sort(int[] arr, int start, int mid, int end) {
		
		int leftSize = mid - start + 1, rightSize = end - mid;
		int[] leftArr = new int[leftSize], rightArr = new int[rightSize];
		int i, j;
		
		for (i = 0; i < leftSize; i++)
			leftArr[i] = arr[i];
		for (j = 0; j < rightSize; j++)
			rightArr[j] = arr[mid + j + 1];
		
		i = j = 0;
		int k = 0;
		
		while (i < leftSize && j < rightSize) {
			
			if (leftArr[i] < rightArr[j])
				arr[k++] = leftArr[i++];
			else arr[k++] = rightArr[j++];
		}
		
		while (i < leftSize) arr[k++] = leftArr[i++];
		while (j < rightSize) arr[k++] = rightArr[j++];
		
		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		int[] arr = {16, 24, 54, 69, 8, 47, 49};
		sort(arr, 0, 3, 6);
	}
}
