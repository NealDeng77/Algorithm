import edu.princeton.cs.algs4.Queue;

/**
 *  The {@code KosarajuSharirSCC} class represents a data type for 
 *  determining the strong components in a digraph.
 *  The <em>id</em> operation determines in which strong component
 *  a given vertex lies; the <em>areStronglyConnected</em> operation
 *  determines whether two vertices are in the same strong component;
 *  and the <em>count</em> operation determines the number of strong
 *  components.

 *  The <em>component identifier</em> of a component is one of the
 *  vertices in the strong component: two vertices have the same component
 *  identifier if and only if they are in the same strong component.

 *  <p>
 *  This implementation uses the Kosaraju-Sharir algorithm.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  Afterwards, the <em>id</em>, <em>count</em>, and <em>areStronglyConnected</em>
 *  operations take constant time.
 */
import edu.princeton.cs.algs4.In;
public class KosarajuSharirSCC {
	private boolean[] marked;
	private int[] id;
	private int count;          //count number of strongly-connected components
	
	public KosarajuSharirSCC(Digraph G) {
		DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());
		marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int v : dfs.reversePostorder()) {
        	if(!marked[v]) {
        		dfs(G, v);
        		count++;
        	}
        }
	}
	
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	public int count() {
		return count;
	}
	
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }
    
    public int id(int v) {
        return id[v];
    }
    
    public static void main(String[] args) {
    	In in = new In(args[0]);
    	Digraph G = new Digraph(in);
    	KosarajuSharirSCC scc = new KosarajuSharirSCC(G);
    	
    	int m = scc.count();
    	
    	//compute list of vertices in each strong component
    	Queue<Integer>[] queue = (Queue<Integer>[]) new Queue[m];
    	for(int i = 0; i < m; i++) {
    		queue[i] = new Queue<Integer>();
    	}
    	
    	for(int v = 0; v < G.V(); v++) {
    		queue[scc.id(v)].enqueue(v);
    	}
    	
    	for(int i = 0; i < m; i++) {
    		for(int v : queue[i]) {
    			StdOut.print(v + " ");
    		}
    		StdOut.println();
    	}
    	
    	
    }
    
    
	
}
