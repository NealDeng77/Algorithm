/******************************************************************************
* Coursera - Algorithms Part II
*
* Week 1 - Interview Questions
*
* Union-find with specific canonical element. Add a method find() to the 
* union-find data type so that find(i) returns the largest element in the 
* connected component containing i. The operations, union(), connected(), and 
* find() should all take logarithmic time or better.
* 
* For example, if one of the connected components is {1,2,6,9}, then the find() 
* method should return 9 for each of the four elements in the connected 
* components because 9 is larger 1, 2, and 6.
******************************************************************************/

/*
 * max[root] always store the max of the elements in the subtree
 * solution: 1. add a max array to store the max
 *           2. when union, change the max[root]
 *           3. find first find the root, then return max[root]
 */


public class FindLargest {

	private int[] id;
	private int count;        //count set
	private int[] sz;
	private int[] max;        //store the largest at each subtree root
	public FindLargest(int n) {
		count = n;
		id = new int[n];
		max = new int[n];
		for(int i = 0; i < n;i++) {
			id[i] = i;
			max[i] = i;
		}
		sz = new int[n];
		for(int i = 0; i < n;i++) {
			sz[i] = 1;
		}
	}
	
	private void validate(int i) {
		int length = id.length;
		if(i < 0 || i >= length) {
			throw new IllegalArgumentException("index" + i + "is not between 0 and " + (length - 1));
		}
	}
	
	private boolean connected(int i, int j) {
		return root(i) == root(j);
	}
	
	private int root(int i ) {
		validate(i);
		while(i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	// find the root first, then return the max
	private int getMaxfind(int i) {
		validate(i);
		int rootP = root(i);
		return max[rootP];
	}
	
	private void union(int i, int j) {
		int x = root(i);
		int y = root(j);
		if(x == y) return;

		if(sz[x] < sz[y]) {
			id[x] = y;
			sz[y] = sz[y] + sz[x];
			// when link x to y, change the max[y] to the maximum of max[x] and max[y]
			max[y] = Math.max(max[x], max[y]);

		}else {
			id[y] = x;
			sz[x] = sz[x] + sz[y];
			// when link y to x, change the max[x] to the maximum of max[x] and max[y]
			max[x] = Math.max(max[x], max[y]);
		}
		count--;
	}
	
	public int count() {
		return count;
	}
	

}

