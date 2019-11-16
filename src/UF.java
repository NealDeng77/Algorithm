/*  This implementation uses weighted quick union by rank with path compression
 *  by halving.
 */

 public class UF {
	private int[] parent;  //parent[i] = parent of i
	private int count;     //number of components
	private byte[] rank;   //rank[i] = rank of subtree rooted at i (never more than 31)
	
	// initialize union-find data structure with N objects
	public UF(int n) {
		if (n < 0) throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		rank = new byte[n];
		for(int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	public int find(int p) {
        validate(p);
        while (p != parent[p]) {
        	parent[p] = parent[parent[p]];  //path compression by halving
            p = parent[p];
        }

        return p;
	}
	
    public int count() {
        return count;
    }
	
    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }
    
    
	//add connection between p and q
	public void union(int p, int q) {
		int pid = find(p);
		int qid = find(q);
		if(pid == qid) return;
		
		if(rank[pid] < rank[qid]) {
			parent[pid] = qid;
		} else if(rank[pid] > rank[qid]) {
			parent[qid] = pid;
		} else {
			parent[qid] = pid;
			rank[pid]++;
		}
		count--;
	}
	
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
	
    public static void main(String[] args) {
        int n = StdIn.readInt();
        UF uf = new UF(n);
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
