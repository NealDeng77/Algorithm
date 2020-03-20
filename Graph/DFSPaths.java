/*
 * DFS, find paths from a source vertex to every other vertex in an undirected graph
 */
public class DFSPaths {
	private boolean[] marked;    //marked[v] = true if v connected to s 
	private int[] edgeTo;        //edgeTo[v] = previous vertex on path from s to v
	private final int s;         //source vertex
	
	/*
	 * use dfs to compute a path between source vertex s and every other vertex in Graph G
	 */
	public DFSPaths(Graph G, int s) {
		int numberOfVertex = G.V();
		marked = new boolean[numberOfVertex];
		edgeTo = new int[numberOfVertex];
		this.s = s;
		validateVertex(s);
		dfs(G, s);
	}
	
	/*
	 * Runs depth first search from v
	 */
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w: G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
				edgeTo[w] = v;
			}
		}
	}
	
	/*
	 * Check if there is a path between v and the source vertex
	 */
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return marked[v];
	}
	
    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         {@code s} and vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
	public Iterable<Integer> pathTo(int v) {
		validateVertex(v);
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}
	
	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		int V = marked.length;
		if(v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}
}
