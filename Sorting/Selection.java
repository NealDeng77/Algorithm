import java.util.Comparator;

public class Selection{
	public static void sort(Comparable[] a) {
		int N = a.length;
		for(int i = 0 ; i < N; i++) {
			int min = i;
			for(int j = i + 1; j < N; j++) {
				if(less(a[j], a[min])) {
					min = j;
				}
			}
			exch(a, i, min);
		}
	}
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	private static boolean less(Comparator comparator, Object v, Object w) {
		return comparator.compare(v, w) < 0;
	}
	
	private static void exch(Comparable[] a, int v, int w) {
		Comparable temp = a[v];
		a[v] = a[w];
		a[w] = temp;
	}
	
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}
	
	private static boolean isSorted(Comparable[]a, int low, int high) {
		for(int i = low + 1; i <= high; i++) {
			if(less(a[i], a[i - 1])) return false;
		}
		return true;
	}
	
	private static boolean isSorted(Object[] a, Comparator comparator) {
		return isSorted(a, comparator, 0, a.length - 1);
	}
	
	private static boolean isSorted(Object[] a, Comparator comparator, int low, int high) {
		for(int i = low + 1; i <= high; i++) {
			if(less(comparator, a[i], a[i - 1])) return false;
		}
		return true;
	}
	
}
