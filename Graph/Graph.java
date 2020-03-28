import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Bag;
// Graph api
//Adjacency-list graph representation, maintain vertex-indexed array of lists
public class Graph {
	private Bag<Integer>[] adj;     //using bag to implement the graph
	private final int V;            //number of vertices
	private int E;                  //number of edges
	
	/*
	 * Create an empty graph with V vertices
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		//create empty graph with V vertices
		adj = (Bag<Integer>[])new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	
	/*
	 * Create  a graph from input stream
	 */
	@SuppressWarnings("unchecked")
	public Graph(In in) {
		if(in == null) throw new IllegalArgumentException("argument is illegal");
		try {
			this.V = in.readInt();
			if(V < 0) throw new IllegalArgumentException("vertices in a graph could not be negative");
			adj = (Bag<Integer>[])new Bag[V];
			for(int v = 0; v < V; v++) {
				adj[v] = new Bag<Integer>();
			}
			this.E = in.readInt();
			if(E < 0) throw new IllegalArgumentException("edges in a graph could not be negative");
			for(int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		}
		catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Graph constructor", e);
		}
	}
	
	/*
	 * Initializes a new graph using deep copy
	 */
	@SuppressWarnings("unchecked")
	public Graph(Graph G) {
		this.V = G.V;
		this.E = G.E;
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		if(E < 0) throw new IllegalArgumentException("edges in a graph could not be negative");
		
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
		
		for(int v = 0; v < G.V(); v++) {
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj(v)) {
				reverse.push(w);
			}
			for(int w : reverse) {
				adj[v].add(w);
			}
		}
	}
	

	/*
	 * Add an edge v-w
	 */
	public void addEdge(int v, int w) {
		//add edge v-w
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	/*
	 * Vertices adjacent to v
	 */
	public Iterable<Integer> adj(int v) {
		//iterator for vertices adjacent to v
		return adj[v];
	}
	
	/*
	 * number of vertices
	 */
	int V() {
		return V;
	}
	
	/*
	 * number of edges
	 */
	int E() {
		return E;
		
	}
	
	/*
	 * return the degree of vertex v
	 */
	public int degree(int v) {
		return adj[v].size();
	}
	
	/*
	 * String representation
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges ");
		for(int v = 0; v < V; v++) {
			s.append(v + ": ");
			for(int w : adj[v]) {
				s.append(w + " ");
			}
		}
		return s.toString();
	}
	
	private void validateVertex(int v) {
		if(v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
		}
	}
	
	//a simple client to use the API
	public void main(String[] args) {
		In in = new In(args[0]);   //read graph from input stream
		Graph G = new Graph(in);
		StdOut.println(G);
	}
}
