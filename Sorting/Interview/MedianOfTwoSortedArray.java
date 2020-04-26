package Interview;
//reference: https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java
//https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/share-my-ologminmn-solution-with-explanation
//https://www.youtube.com/watch?v=LPFhl65R7ww
public class MedianOfTwoSortedArray {
	public static double sort(int[] arr1, int [] arr2) {
		int m = arr1.length;
		int n = arr2.length;
		if(m > n) {
			return sort(arr2, arr1);
		}
		int low = 0;
		int hi = m;
		int i, j;
		while(low <= hi) {
			i = (low + hi) / 2;
			j = (m + n + 1) / 2 - i;
			int maxLeftX = (i == 0) ? Integer.MIN_VALUE : arr1[i - 1];
			int minRightX = (i == m) ? Integer.MAX_VALUE : arr1[i];
			
			int maxLeftY = (j == 0) ? Integer.MIN_VALUE : arr2[j - 1];
			int minRightY = (j == n) ? Integer.MAX_VALUE : arr2[j];
			
			
			if(maxLeftX  <= minRightY && maxLeftY <= minRightX) {
				if( (m + n) / 2  == 0) return (double)((Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0);
				else return (double)Math.max(maxLeftX, maxLeftY);
			}
			else if(maxLeftX > minRightY) {
				hi = i - 1;
			}
			else {
				low = i + 1;
			}
		}
		
		throw new IllegalArgumentException();
				
	}
}
