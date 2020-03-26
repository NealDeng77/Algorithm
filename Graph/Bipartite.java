import edu.princeton.cs.algs4.StdOut;

/**
 *  The {@code Bipartite} class represents a data type for 
 *  determining whether an undirected graph is bipartite or whether
 *  it has an odd-length cycle.
 *  The <em>isBipartite</em> operation determines whether the graph is
 *  bipartite. If so, the <em>color</em> operation determines a
 *  bipartition; if not, the <em>oddCycle</em> operation determines a
 *  cycle with an odd number of edges.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  Afterwards, the <em>isBipartite</em> and <em>color</em> operations
 *  take constant time; the <em>oddCycle</em> operation takes time proportional
 *  to the length of the cycle.
 */
public class Bipartite {
	private boolean marked[];
	private boolean isBipartite;
	private boolean colored[];
	private int[] edgeTo;
	private Stack<Integer> cycle;          //odd-length cycle
	
    /**
     * Determines whether an undirected graph is bipartite and finds either a
     * bipartition or an odd-length cycle.
     *
     * @param  G the graph
     */
	public Bipartite(Graph G) {
		marked = new boolean[G.V()];
		colored = new boolean[G.V()];
		edgeTo = new int[G.V()];
		isBipartite = true;
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
			}
		}
		assert check(G);
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			
			//return if odd-length cycle found
			if(cycle != null) return;
			
			if(!marked[w]) {
				colored[w] = !colored[v];
				edgeTo[w] = v;
				dfs(G, w);
			} 
			// if v-w create an odd-length cycle, find it
			else if(colored[w] == colored[v]) {
				isBipartite = false;
				cycle = new Stack<Integer>();
				cycle.push(w);                    //the start vertex is included twice to check odd-length cycle
				for(int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
			}
		}
	}
	
	
	private boolean check(Graph G) {
		//graph is bipartite
		if(isBipartite) {
			for(int v = 0; v < G.V(); v++) {
				for(int w : G.adj(v)) {
					if(colored[w] == colored[v]) {
						System.err.printf("edge %d-%d with %d and %d in same side of bipartition\n", v, w, v, w);
						return false;
					}
				}
			}
		}
		
		//graph has an odd-length cycle
		else {
			int first = -1, last = -1;
			for(int v : oddCycle()) {
				if(first == -1) first = v;
				last = v;
			}
			if(last != first) {
				System.err.printf("cycle begins with %d and ends with %d\n", first, last);
				return false;
			}
		}
		
		return true;
	}
	
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
	
	
	
	public boolean isBipartite() {
		return isBipartite;
	}
	
	
	public Iterable<Integer> oddCycle() {
		return cycle;
	}
	
    public boolean color(int v) {
        validateVertex(v);
        if (!isBipartite)
            throw new UnsupportedOperationException("graph is not bipartite");
        return colored[v];
    }
	
    public static void main(String[] args) {
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);
        Bipartite test1 = new Bipartite(graph1);
        StdOut.println(test1.isBipartite() + " Expected: true");
        StdOut.println("Graph is bipartite");
        for (int v = 0; v < graph1.V(); v++) {
            StdOut.println(v + ": " + test1.color(v));
        }       

        Graph graph2 = new Graph(5);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 4);
        graph2.addEdge(4, 0);
        Bipartite test2 = new Bipartite(graph2);
        StdOut.println(test2.isBipartite() + " Expected: false");
        StdOut.print("Graph has an odd-length cycle: ");
        for (int x : test2.oddCycle()) {
            StdOut.print(x + " ");
        }
        StdOut.println();
    }
	
}
