package Interview;
//find the kth largest in two sorted array
//reference: https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/

public class SelectionInTwoSortedArrays {
	/*
	 * Not the optimal solution, time complexity O(k), space O(1)
	 */
	public static int find(int[] arr1, int[] arr2, int k) {
		int count = 0 ,i = 0, j = 0;
		int m = arr1.length;
		int n = arr2.length;
		while( i < m && j < n) {
			if(arr1[i] < arr2[j]) {
				count++;
				if(count == k) {
					return arr1[i];
				}
				i++;
			}
			else {
				count++;
				if(count == k) {
					return arr2[j];
				}
				j++;
			}
		}
		
		while(i < m) {
			count++;
			if(count == k) {
				return arr1[i];
			}
			i++;
		}
		
		while(j < n) {
			count++;
			if(count == k) {
				return arr2[j];
			}
			j++;
		}
		throw new IllegalArgumentException();
	}
	
	
}
