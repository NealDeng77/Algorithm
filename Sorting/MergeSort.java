
public class MergeSort {
	private static final int CUTOFF = 50;
	private static Comparable[] aux;
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for(int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}
		
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++) {
			if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else if(aux[i].compareTo(aux[j]) <= 0 ) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if(hi <= lo) return;
		//improvement: if it's a small array, just use the insertion sort because it doesn't need
		// the extra array and move between arrays and it performs well when the array is partially sorted
		if(hi <= lo + (CUTOFF - 1)) {
			InsertionSort.sort(a, lo, hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		//Improvement: if the array is already sorted, return
		if(a[mid + 1].compareTo(a[mid]) >= 0) {
			return;
		}
		merge(a, aux, lo, mid, hi);
	}
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}
}
