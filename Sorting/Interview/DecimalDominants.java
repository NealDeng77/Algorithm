package Interview;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdRandom;

//Given an array with n keys, design an algorithm to find all values 
//that occur more than  n/10 times. The expected running time of your algorithm should be linear.
//We can solve this problem using Boyer-Moore Majority Voting algorithm, 
//https://leetcode.com/problems/majority-element-ii/discuss/63500/JAVA-Easy-Version-To-Understand!!!!!!!!!!!!
//https://blog.csdn.net/weixin_30294295/article/details/94901144
//We can also solve it using quick select
//https://massivealgorithms.blogspot.com/2019/03/decimal-dominants.html
//https://raw.githubusercontent.com/phareskrad/algs4/master/jobinterviewquestions/QuickSort.java
public class DecimalDominants {
    public static List<Integer> majorityElement(int[] nums) {
        if(nums == null || nums.length == 0 ) {
            return new ArrayList<Integer>();
        }
        return findDecimalDominants(nums, 3);
    }
    
	private static List<Integer> findDecimalDominants(int[] arr, int k) {
		List<Integer> results = new ArrayList<Integer>();
		int candidate;
		int length = arr.length;
		for(int i = 1 ; i <= k; i++) {
            length = arr.length * i;
			candidate = find(arr, (length / k) - 1);
			if(!results.contains(candidate)  && verify(arr, candidate, k) ) results.add(candidate);
		}
		return results;
	}
	
	private static boolean verify(int[] arr, int num, int k) {
		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == num) {
				count++;
			}
		}
		if(count > arr.length / k) return true;
		else return false;
	}
	
	
	
	
	private static int find(int[] arr, int k) {
		// Random.shuffle(arr);
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
	
	public static void main(String[] args) {
		int[] arr = {2,2,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9};
		List<Integer> result = majorityElement(arr);
		System.out.println(result);
	}
}
