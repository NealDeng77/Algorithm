/*
 * Suppose n people are sitting in a circular table with names A, B, C, D,…. Given a 
 * name, we need to print all n people (in order) starting from given name
 */
public class CircularArray {
	
	/*
	 * print the elements starting from index. when reaching the end of the array, 
	 * wrap around
	 */
	public static void print(String[] arr, int index) {
		int length = arr.length;
		for(int i = index; i < index + length; i++) {
			System.out.println(arr[i % length] + " ");
		}
	}
	
	public static void main(String[] args) {
		String[] arr = new String[]{"A", "B", "C", "D", "E", "F"};
		print(arr, 2);
		
	}
}
