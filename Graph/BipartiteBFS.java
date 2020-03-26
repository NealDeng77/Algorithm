import edu.princeton.cs.algs4.Queue;

public class BipartiteBFS {
	private static final boolean WHITE = false;
	private static final boolean BLACK = false;
	private boolean color[];
	private boolean isBipartite;
	private boolean marked[];
	private int edgeTo[];
	private Queue<Integer> cycle;
	
	private BipartiteBFS(Graph G) {
        isBipartite = true;
        color  = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for(int v = 0; v < G.V() && isBipartite; v++) {
        	if(!marked[v]) {
        		bfs(G, v);
        	}
        }
        assert check(G);
	}
	
	private void bfs(Graph G, int s) {
		marked[s] = true;
		color[s] = WHITE;
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		while(!q.isEmpty()) {
			int v = q.dequeue();
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					marked[w] = true;
					color[w] = !color[v];
					edgeTo[w] = v;
					q.enqueue(w);
				}
				else if(color[w] == color[v]) {
					isBipartite = false;
                    // to form odd cycle, consider s-v path and s-w path
                    // and let x be closest node to v and w common to two paths
                    // then (w-x path) + (x-v path) + (edge v-w) is an odd-length cycle
                    // Note: distTo[v] == distTo[w];
					/*
					 *              0
					 *         1          2
					 *            3    4
					 *        
					 */
					cycle = new Queue<Integer>();
					Stack<Integer> stack = new Stack<Integer>();
					int x = v, y = w;
					while(x != y) {
						cycle.enqueue(w);
						stack.push(x);
						x = edgeTo[x];
						y = edgeTo[y];
					}
					cycle.enqueue(x);
					while(!stack.isEmpty()) {
						cycle.enqueue(stack.pop());
					}
					cycle.enqueue(w);
					return;
				}
			}
		}
	}
	
    public boolean color(int v) {
        validateVertex(v);
        if (!isBipartite)
            throw new UnsupportedOperationException("Graph is not bipartite");
        return color[v];
    }
    
    public boolean isBipartite() {
        return isBipartite;
    }
    
    public Iterable<Integer> oddCycle() {
        return cycle; 
    }
    
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    private boolean check(Graph G) {
    	if(isBipartite) {
    		for (int v = 0; v < G.V(); v++) {
    			for(int w : G.adj(v)) {
    				if(color[v] == color[w]) {
    					System.err.printf("edge %d-%d with %d and %d in same side of bipartition\n", v, w, v, w);
    					return false;
    				}
    			}
    		}
    	}
    	
    	//graph has an odd-length cycle
    	else {
    		//verify cycle
    		int first = -1, last = -1;
    		for( int v : oddCycle()) {
    			if(first == -1) first = v;
    			last = v;
    		}
    		if(first != last) {
    			System.err.printf("cycle begins with %d and ends with %d\n", first, last);
    			return false;
    		}
    	}
    	return true;
    }
    
}
