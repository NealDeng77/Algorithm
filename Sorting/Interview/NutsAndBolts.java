package Interview;

import edu.princeton.cs.algs4.StdRandom;

//A disorganized carpenter has a mixed pile of nn nuts and nn bolts. 
//The goal is to find the corresponding pairs of nuts and bolts. 
//Each nut fits exactly one bolt and each bolt fits exactly one nut. 
//By fitting a nut and a bolt together, the carpenter can see which 
//one is bigger (but the carpenter cannot compare two nuts or two bolts directly). 
//Design an algorithm for the problem that uses at most proportional to nlogn 
//compares (probabilistically).
//reference: https://www.geeksforgeeks.org/nuts-bolts-problem-lock-key-problem/
//As we apply partitioning on nuts and bolts both so the total time complexity 
//will be O(2*nlogn) = O(nlogn) on average.

public class NutsAndBolts {
	public static void sort(int[] nuts, int[] bolts) {
		StdRandom.shuffle(nuts);
		StdRandom.shuffle(bolts);
		sort(nuts, bolts, 0, nuts.length - 1);
	}
	
	
	private static void sort(int[] nuts, int[] bolts, int lo, int hi) {
		if(hi <= lo) return;
		int partitioningElement = nuts[lo];
		int index = partition(bolts, partitioningElement, lo, hi);
		partitioningElement = bolts[index];
		index = partition(nuts, partitioningElement, lo, hi);
		sort(nuts, bolts, lo, index - 1);
		sort(nuts, bolts, index + 1, hi);
	}
	
	private static int partition(int[] arr, int element, int lo ,int hi) {
		int i = lo;
		for(int j = lo; j < hi; j++) {
			if(arr[j] < element) {
				exchange(arr, i, j);
				i++;
			} 
			//put the equal element at the last of the array
			else if(arr[j] == element) {
				exchange(arr, j, hi);
				j--;
			}
		}
		exchange(arr, i , hi);
		return i;
		
	}
	
	private static void exchange(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static int fit(int i, int j) {
		if(i < j) return -1;
		else if(i > j) return 1;
		else return 0;
	}
}
