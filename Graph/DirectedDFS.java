import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
public class DirectedDFS {
	private boolean marked[];
	private int count;              //number of vertices reachable from sources
	public DirectedDFS(Digraph g, Iterable<Integer> sources) {
		marked = new boolean[g.V()];
		validateVertices(sources);
		for(int w : sources) {
			dfs(g, w);
		}
	}
	public DirectedDFS(Digraph g, int v) {
		marked = new boolean[g.V()];
		dfs(g, v);
	}
	

	
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		count++;
		for(int w : g.adj(v)) {
			if(!marked[w]) {
				dfs(g, w);
			}
		}
	}
	
	public boolean marked(int v) {
		validateVertex(v);
		return marked[v];
	}
	
    public int count() {
        return count;
    }
	
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
	
    
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }
    
    public static void main(String[] args) {
    	In in = new In(args[0]);
    	Digraph G = new Digraph(in);
    	
    	// read in sources from command-line arguments
    	Bag<Integer> sources = new Bag<Integer>();
    	for(int i = 1; i < args.length; i++) {
    		int s = Integer.parseInt(args[i]);
    		sources.add(s);
    	}
    	
    	DirectedDFS dfs = new DirectedDFS(G, sources);
    	
    	for(int v = 0; v < G.V(); v++) {
    		if(dfs.marked(v)) StdOut.print(v + " ");
    	}
    	StdOut.println();
    }
	
}
