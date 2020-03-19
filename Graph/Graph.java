import edu.princeton.cs.algs4.Bag;

// Graph api
//Adjacency-list graph representation, maintain vertex-indexed array of lists
public class Graph {
	private Bag<Integer>[] adj;     //using bag to implement the graph
	private final int V;            //number of vertices
	
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
	
//	/*
//	 * Create  a graph from input stream
//	 */
//	public Graph(In in) {
//		
//	}
	
	/*
	 * Add an edge v-w
	 */
	public void addEdge(int v, int w) {
		//add edge v-w
		adj[v].add(w);
		adj[w].add(v);
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
		
	}
	
	/*
	 * number of edges
	 */
	int E() {
		
	}
	
	/*
	 * String representation
	 */
	String toString() {
		
	}
	
	//a simple client to use the API
	public void main(String[] args) {
		In in = new In(args[0]);   //read graph from input stream
		Graph G = new Graph(in);
		
		for (int v = 0; v < G.V(); v++) {
			for (int w : G.adj(v)) {                //print out each edge(twice)
				StdOut.println( v + "-" + w);
			}
		}
	}
}
