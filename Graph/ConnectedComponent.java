
public class ConnectedComponent {
	private boolean[] marked;         // marked[v] = has vertex v been marked?
	private int[] id;                 // id[v] = id of connected component containing v
	private int[] size;               // size[id] = number of vertices in given component
	private int count;                // number of connected components
	
	/*
	 * Compute the connected component of the undirected graph
	 */
	public ConnectedComponent(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		size = new int[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
				count++;
			}
		}
	}
	
	/*
	 * dfs for a Graph
	 */
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		size[count]++;
		for(int w: G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	/*
	 * return the component id of the connected component containing vertex
	 */
	public int id(int v) {
		validateVertex(v);
		return id[v];
	}
	
	/*
	 * return the number of vertices in the connected component containing vertex
	 */
	public int size(int v) {
		validateVertex(v);
		return size[id[v]];
	}
	
	/*
	 * return number of connected components in the graph
	 */
	public int count() {
		return count;
	}
	
	/*
	 * return true if vertex v and vertex w are in the same connected component
	 */
	public boolean connected(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		return id[v] == id[w];
	}
	
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
	
}
