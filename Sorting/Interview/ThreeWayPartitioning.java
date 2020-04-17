package Interview;

import edu.princeton.cs.algs4.StdRandom;

public class ThreeWayPartitioning {
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static boolean less(int i, int j) {
		return i < j;
	}
	
	public static void sort(int[] arr) {
		StdRandom.shuffle(arr);
		sort(arr, 0, arr.length - 1);
	}
	
	public static void sort(int[] arr, int lo, int hi) {
		if(hi <= lo) return;
		int i = lo, lt = i, last = hi;
		while(i <= last) {
			if(less(arr[i++], arr[lt++])) swap(arr, i, lt);
			else if(less(arr[lt], arr[i])) swap(arr, i, last--);
			else {
				i++;
			}
		}
		
		//recursively sort left and right, except the duplicated keys
		sort(arr, lo, lt - 1);
		sort(arr, last + 1, hi);
	}
}
