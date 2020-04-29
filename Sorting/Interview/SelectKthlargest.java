package Interview;
//find the kTh largest element
//linear time
//reference: Princeton
import edu.princeton.cs.algs4.StdRandom;

public class SelectKthlargest {
	public static int find(int[] arr, int k) {
		StdRandom.shuffle(arr);
		int lo = 0;
		int hi = arr.length - 1;
		int partition;
		while( lo < hi) {
			partition = partition(arr, lo, hi);
			if(partition > k) {
				hi = partition - 1;
			}
			else if(partition < k) {
				lo = partition + 1;
			}
			else {
				return arr[partition];
			}
		}
		return arr[lo];
	}
	
	private static int partition(int[] arr, int lo, int hi) {
		int element = arr[lo];
		int i = lo, j = hi + 1;
		while(true) {
			while(less(arr[++i], element)) {
				if(i == hi) break;
			}
			
			while(less(element, arr[--j])) {
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
}
