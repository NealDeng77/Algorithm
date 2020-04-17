

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
	private static int pivot(int[] arr, int lo, int hi) {
		int partitioningElement = arr[lo];
		int i = lo, j = hi + 1;
		while(i < hi) {
			while(less(arr[++i], partitioningElement)) {
				if(i == hi) break;
			}
			while(less(partitioningElement, arr[--j])) {
				if(j == lo) break;
			}
			if(i >= j) break;
			swap(arr, i, j);
		}
		
		swap(arr, lo, j);
		return j;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static boolean less(int i, int j) {
		return i < j;
	}
	
	public void sort(int[] arr) {
		StdRandom.shuffle(arr);
		sort(arr, 0, arr.length - 1);
	}
	
	private void sort(int[] arr, int lo, int hi) {
		if(hi <= lo) return;
		int partitionElement = pivot(arr, lo, hi);
		sort(arr, lo, partitionElement - 1);
		sort(arr, partitionElement + 1, hi);
	}
}
