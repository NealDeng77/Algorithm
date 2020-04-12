
public class MergeSort {
	private Comparable[] aux;
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for(int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}
		
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++) {
//			if(aux[i].compareTo(aux[j]) <= 0) {
//				a[lo] = aux[i];
//				i++;
//				lo++;
//			} else {
//				a[lo] = aux[j];
//				j++;
//				lo++;
//			}
//			if(i < mid) {
//				for(int x = i; i < mid + 1; i++) {
//					a[lo] = aux[i];
//					lo++;
//				}
//			}
//			if(j < hi) {
//				for(int x = j; j < hi; j++) {
//					a[lo] = aux[j];
//					lo++;
//				}
//			}
			if(aux[i].compareTo(aux[j]) <= 0 ) a[k] = aux[i++];
			else if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if(hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}
}
