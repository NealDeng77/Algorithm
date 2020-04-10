
public class InsertionSort {
	public static void sort(Comparable[] a) {
		int N = a.length;
		for(int i = 1; i < N; i++) {
			for(int j = i; j > 0; j--) {
				if(less(a[j], a[j - 1])) exch(a, j, j - 1);
				else break;
			}
		}
	}
	
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void sort(Comparable[] a, int low, int high) {
		int N = a.length;
		for(int i = low + 1; i < high; i++) {
			for(int j = i; j > low; j--) {
				if(less(a[j], a[j - 1])) exch(a, j, j - 1);
				else break;
			}
		}
	}
}
