package Interview;
//reference: https://www.geeksforgeeks.org/counting-inversions/
//reference : https://medium.com/@ssbothwell/counting-inversions-with-merge-sort-4d9910dc95f0
//reference : https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pdf/05DivideandConquerI.pdf
public class CountInversion {
    
	// a[lo, mid]  and a[mid + 1, hi] is already sorted.
	public static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
        long inversion = 0;
    	for(int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }    
        int i = lo;
        int j = mid + 1;
        for(int k = lo; k <=hi; k++) {
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(aux[i] <= aux[j]) a[k] = aux[i++];
            //if a[i] > a[j], all the elements left in the subarray a[lo, mid]
            //are greater than a[j], so that inversions exist, add them on.
            else{
                inversion += mid - i + 1;
                a[k] = aux[j++];
            }
        }
        return inversion;
    }
    
	public static long count(int[] a) {
		//use a new copied array b to avoid sorting the original array a
		int[] b = new int[a.length];
		int[] aux = new int[a.length];
		for(int i = 0; i < a.length - 1; i++) {
			b[i] = a[i];
		}
		long inversions = count(a, b, aux, 0, a.length - 1);
		return inversions;
	}
	
	
	private static long count(int[] a, int[] b, int[] aux, int lo, int hi) {
		long inversions = 0;
		if(hi <= lo) return 0;
		int mid = lo + (hi - lo) / 2;
		inversions += count(a, b, aux, lo, mid);
		inversions += count(a, b, aux, mid + 1, hi);
		inversions += merge(b, aux, lo, mid, hi);
		assert inversions == brute(a, lo, hi);
		return inversions;
	}
    
	private static long brute(int[] a, int lo, int hi) {
		long inversions = 0;
		for(int i = lo; i < hi; i++) {
			for(int j = i + 1; j < hi; j++) {
				if(a[j] < a[i]) inversions++;
			}
		}
		return inversions;
	}
    
	
	public static void main (String[] args) {
	    int[] a = {2, 4, 1, 3, 5};
		System.out.println(count(a));
	}
}
