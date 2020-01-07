/*
 * Weighted quick union without path compression
 */

public class WeightedQuickUnionUF {
	private int[] parent;  //parent[i] = parent of i
	private int[] size;    //size[i] = number of sites in subtree rooted at i
	private int count;     //number of components

	public WeightedQuickUnionUF(int n) {
		count = n;
		parent = new int[n];
		size = new int[n];
		for(int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}
	
	public int count()
	{
		return count;
	}
	
    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }
    
	public void union(int p , int q)
	{
		int pid = find(p);
		int qid = find(q);
		if(pid == qid) return;
		
		//make smaller root point to larger one
		if (size[pid] < size[qid]) {
			parent[pid] = qid;
			size[qid] += size[pid];
		}
		else {
			parent[qid] = pid;
			size[pid] += size[qid];
		}
		count--;
	}
    
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }
	
	public boolean connected(int p , int q)
	{
		return find(p) == find(q);
	}
	
	
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}
