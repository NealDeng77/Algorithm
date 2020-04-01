import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Digraph {
	private static final String NEWLINE = System.getProperty("line.separator");
	private int E;
	private final int V;
	private Bag<Integer>[] adj;
	private boolean marked;
	private int[] indegree;
	
	public Digraph(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		this.V = V;
		adj = (Bag<Integer>[])new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
		indegree = new int[V];
	}
	
    /**  
     * Initializes a digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
	public Digraph(In in) {
		if (in == null) throw new IllegalArgumentException("argument is null");
		try {
			this.V = in.readInt();
			if(V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
			indegree = new int[V];
			adj = (Bag<Integer>[])new Bag[V];
			for(int v = 0; v < V; v++) {
				adj[v] = new Bag<Integer>();
			}
			int E = in.readInt();
			if (E < 0) throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
			for(int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				addEdge(v, w);
			}
		}
		catch(NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
		}
	}
	
    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param  G the digraph to copy
     * @throws IllegalArgumentException if {@code G} is {@code null}
     */
    public Digraph(Digraph G) {
    	if (G == null) throw new IllegalArgumentException("argument is null");
    	this.V = G.V();
    	this.E = G.E();
    	if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		
    	//update adj
    	adj = (Bag<Integer>[])new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
		for(int v = 0; v < V;v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for(int w : G.adj[v]) {
				reverse.push(w);
			}
			for(int w : reverse) {
				adj[v].add(w);
			}
		}
		
		//update indegree
		indegree = new int[V];
		for(int v = 0; v < V; v++) {
			indegree[v] = G.indegree[v];
		}
		
		
		
    	
    }
	
    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}               
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
    	validate(v);
        return indegree[v];
    }
    
    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}               
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
    	validate(v);
        return adj[v].size();
    }
    
	public int V() {
		return V;
	}
	
	public int E() {
		return V;
	}
	
	public void addEdge(int v, int w) {
		validate(v);
		validate(w);
		adj[v].add(w);
		indegree[w]++;
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		validate(v);
		return adj[v];
	}
	
	public Digraph reverse() {
		Digraph reversedGraph = new Digraph(V);
		for(int v = 0; v < V; v++) {
			for(int w : adj[v]) {
				reversedGraph.addEdge(w, v);
			}
		}
		return reversedGraph;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for(int v = 0; v < V; v++) {
			s.append(String.format("%d: ", v));
			for(int w :adj[v]) {
				s.append(String.format("%d: ", w));
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	private void validate(int v) {
		if(v < 0 || v >= V) {
			throw new IllegalArgumentException("v should be within 0 and V - 1");
		}
	}
	
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
    }
}
