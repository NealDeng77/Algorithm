
public class MergeSortBU {
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		int lo;
		int N = a.length - 1;
		int size;
		for(size = 1; size < N; size = size + size) {
			for(lo = 0; lo < N - size; lo = lo + size + size) {
				merge(a, aux, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
			}
		}
	}
	
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for(int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}
		
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++) {
			if(aux[i].compareTo(aux[j]) <= 0 ) a[k] = aux[i++];
			else if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
}
