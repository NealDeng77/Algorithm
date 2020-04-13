package Interview;
//reference: https://www.geeksforgeeks.org/counting-inversions/
public class CountInversion {
//	static int local = 0;
    static int global = 0;

    static void merge(int[] A, int[] B, int lo, int mid, int hi) {
        for(int i = lo; i <= hi; i++) {
            B[i] = A[i];
        }    
        int i = lo;
        int j = mid + 1;
        for(int k = lo; k <=hi; k++) {
            if(i > mid) A[k] = B[j++];
            else if(j > hi) A[k] = B[i++];
            else if(B[i] <= B[j]) A[k] = B[i++];
            else{
                global += mid - i + 1;
                A[k] = B[j++];
            }
        }
    }
    
    static void sort(int[] A, int[] B, int lo, int hi) {
        if(hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(A, B, lo, mid);
        sort(A, B, mid + 1, hi);
        merge(A, B, lo, mid, hi);
    }
    
    static int sort(int[] A) {
        int[] B = new int[A.length];
        sort(A, B, 0, A.length - 1);
        return global;
    }
	
	public static void main (String[] args) {
	    int[] a = {2, 4, 1, 3, 5};
		System.out.println(sort(a));
	}
}
