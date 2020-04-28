import java.util.Map;

//Given an array of size n, find the majority element. The majority element is the element that appears more than floor(n/2) times.
//
//You may assume that the array is non-empty and the majority element always exist in the array.
//Reference: https://leetcode.com/problems/majority-element/solution/

public class MajorityElement {
	//Solution 1: using hashmap
	//Time complexity: O(n)
	//Space complexity: O(n)
	private static Map<Integer, Integer> count(int[] nums) {
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		for(int num : nums) {
			if(!counts.containsKey(num)) {
				counts.put(num, 1);
			}
			else {
				counts.put(num, counts.get(num) + 1);
			}
		}
		return counts;
	}
	
	public static int majorityElement(int[] nums) {
		Map<Integer, Integer> counts = count(nums);
		
		Map.Entry<Integer, Integer> majorityEntry = null;
		for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
			if(majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
				majorityEntry = entry;
			}
		}
		
		return majorityEntry.getKey();
	}
	
	
	
	//Solution 2: Divide and Conquer
	//Time complexity: O(nlogn) because each recursion performs two recursive calls on 
	//subslices of size n/2 and and two linear scans of length n. T(n) = 2T(n / 2) + 2n;
	//
	private static int majorityElementCount(int[] nums, int lo, int hi) {
		if(lo == hi) {
			return nums[lo];
		}
		
		int mid = lo + (hi - lo) / 2;
		int left = majorityElementCount(nums, lo, mid);
		int right = majorityElementCount(nums, mid + 1, hi);
		
		if(left == right) {
			return nums[left];
		} else {
			int leftCount = count(nums, lo, hi, left);
			int rightCount = count(nums, lo, hi, right);
			return leftCount > rightCount ? left : right;
		}
	}
	
	private static int count(int[] nums, int lo, int hi, int number) {
		int count = 0;
		for(int i = lo; i <= hi; i++) {
			if(nums[i] == number) count++;
		}
		return count;
	}
	
	public static int majorityElement(int[] nums) {
		return majorityElementCount(nums, 0, nums.length - 1);
	}
	
	
	//Solution 3: Boyer-Moore voting algorithm
	//Time complexity: O(n)
	//Space: O(1)
	public static int majorityElement(int[] nums) {
		int count = 0;
		int candidate = null;
		for(int num: nums) {
			if(count == 0) {
				candidate = num;
			}
			
			count += (candidate == num) ? 1 : -1;
		}
		return candidate;
	}
}
