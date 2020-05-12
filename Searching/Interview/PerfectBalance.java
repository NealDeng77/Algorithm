//inserts a set of keys into an initially empty BST such that the tree produced is 
//equivalent to binary search, in the sense that the sequence of compares done in 
//the search for any key in the BST is the same as the sequence of compares used by 
//binary search for the same set of keys.
import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BST;
public class PerfectBalance {
	public static void PerfectBalance(BST bst, String[] keys) {
		Arrays.sort(keys);
		perfectBalance(bst, keys, 0, keys.length - 1);
	}
	
	private static void perfectBalance(BST bst, String[] keys, int lo, int hi) {
		if(hi < lo) return;
		int mid = lo + (hi - lo) / 2;
		bst.put(keys[mid], mid);
		StdOut.print(keys[mid] + " ");
		perfectBalance(bst, keys, lo, mid - 1);
		perfectBalance(bst, keys, mid + 1, hi);
	}
	
    public static void main(String[] args) {
        String[] words = StdIn.readAllStrings();
        BST<String, Integer> bst = new BST<String, Integer>();
        PerfectBalance(bst, words);
    }
}