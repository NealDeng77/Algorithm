
import edu.princeton.cs.algs4.Shell;

/*
 * Reference: https://www.geeksforgeeks.org/union-and-intersection-of-two-sorted-arrays-2/
 
 */


//Method 1 (Naive)
//Union:
//1) Initialize union U as empty.
//2) Copy all elements of first array to U.
//3) Do following for every element x of second array:
//…..a) If x is not present in first array, then copy x to U.
//4) Return U.
//
//Intersection:
//1) Initialize intersection I as empty.
//2) Do following for every element x of first array
//…..a) If x is present in second array, then copy x to I.
//4) Return I.
//
//Time complexity of this method is O(mn) for both operations. 
//Here m and n are number of elements in arr1[] and arr2[] respectively.


//Method 2 (Use Sorting)
//1) Sort arr1[] and arr2[]. This step takes O(mLogm + nLogn) time.
//2) Use O(m + n) algorithms to find union and intersection of two sorted arrays.
//
//Overall time complexity of this method is O(mLogm + nLogn).

//Method 3( Using binary search)
//Another approach that is useful when difference between sizes of two given arrays is significant.
//The idea is to iterate through the shorter array and do a binary search for every element of short 
//array in big array (note that arrays are sorted). Time complexity of this solution 
//is O(min(mLogn, nLogm)). This solution works better than the above approach when ratio 
//of larger length to smaller is more than logarithmic order.

//Method 4 (Use Hashing)
//Union:
//Union
//1. Initialize an empty hash set hs.
//2. Iterate through the first array and put every element of the first array in the set S.
//3. Repeat the process for the second array.
//4. Print the set hs.
//
//Intersection
//1. Initialize an empty set hs.
//2. Iterate through the first array and put every element of the first array in the set S.
//3. For every element x of the second array, do the following :
//Search x in the set hs. If x is present, then print it.
//
//Time complexity of this method is Θ(m+n) under the assumption that hash table search and 
//insert operations take Θ(1) time.

public class IntersectionOfTwoArrays {
	class Point2D implements Comparable<Point2D> {
		private double x;
		private double y;
		
		public int compareTo(Point2D that) {
			if(this.x > that.x) return 1;
			if(this.x < that.x) return -1;
			if(this.y > that.y) return 1;
			if(this.y < that.y) return -1;
			return 0;
		}
		
	}
	
	public int count(Point2D[] a, Point2D[] b) {
		Shell.sort(a);
		Shell.sort(b);
		int count = 0;
		int i = 0, j = 0;
		while(i < a.length && j < b.length) {
			if(a[i].compareTo(b[j]) > 0) {
				j++;
			}else if ( a[i].compareTo(b[j]) < 0) {
				i++;
			}else {
				count ++;
				i++;
				j++;
			}
		}
		return count;
	}
}


/*
 * Used in method two
 * find union of two sorted array
 * handle duplicates
 * O(m + n)
 */
class FindUnion {
	static void findUnion(int arr1[], int arr2[]) {
		int m = arr1[arr1.length - 1];
		int n = arr2[arr2.length - 1];
		int size = m > n ? m : n;
		int newArray[] = new int[size + 1];
		
		//put first element of the first array to the new array
		//the element 1 means that index number accurs in arr1
		newArray[arr1[0]] = 1;
		for(int i = 1; i < m; i++) {
			if(arr1[i] != arr1[i - 1]) {
				newArray[arr1[i]] = 1;
			}
		}
		
		//only copy the element of arr2 to the new array when it's not presented yet.
		for(int i = 0; i < n; i++) {
			if(newArray[arr2[i]] == 0) {
				newArray[arr2[i]] = 1;
			}
		}
	}
}


