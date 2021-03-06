/**
 *  The {@code CC} class represents a data type for 
 *  determining the connected components in an undirected graph.
 *  The <em>id</em> operation determines in which connected component
 *  a given vertex lies; the <em>connected</em> operation
 *  determines whether two vertices are in the same connected component;
 *  the <em>count</em> operation determines the number of connected
 *  components; and the <em>size</em> operation determines the number
 *  of vertices in the connect component containing a given vertex.

 *  The <em>component identifier</em> of a connected component is one of the
 *  vertices in the connected component: two vertices have the same component
 *  identifier if and only if they are in the same connected component.

 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  Afterwards, the <em>id</em>, <em>count</em>, <em>connected</em>,
 *  and <em>size</em> operations take constant time.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>   
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
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
