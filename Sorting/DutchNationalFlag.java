//Dutch national flag. Given an array of n buckets, each containing a red, white, or blue pebble, 
//sort them by color. The allowed operations are:
//
//swap(i, j): swap the pebble in bucket ii with the pebble in bucket j.
//color(i): determine the color of the pebble in bucket ii.
//The performance requirements are as follows:
//
//At most n calls to color().
//At most n calls to swap().
//Constant extra space.


//Reference: https://algorithmsandme.com/dutch-national-flag-problem/


public class DutchNationalFlag {
	public static void sort(int[] arr) {
		int start = 0;
		int pointer = 0;
		int last = arr.length - 1;
		
		for(int i = 0;i <= last; i++) {
			if(arr[i] == 0) {
				swap(arr, start, pointer);
				start++;
				pointer++;
			} else if(arr[i] == 1) {
				pointer++;
			} else if(arr[i] == 2) {
				swap(arr, pointer, last);
				pointer++;
				last--;
			}
		}
		
		
		
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
