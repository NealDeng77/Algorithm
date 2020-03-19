// Graph api
public class Graph {
	/*
	 * Create an empty graph with V vertices
	 */
	Graph(int V) {
		
	}
	
	/*
	 * Create  a graph from input stream
	 */
	Graph(In in) {
		
	}
	
	/*
	 * Add an edge v-w
	 */
	void addEdge(int v, int w) {
		
	}
	
	/*
	 * Vertices adjacent to v
	 */
	Iterable<Integer> adj(int v) {
		
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
