import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
 * Using BFS to find the shortest paths (number of edges) from a source vertex to every other vertex in the graph
 *  This implementation uses breadth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>,
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  Each call to {@link #distTo(int)} and {@link #hasPathTo(int)} takes constant time;
 *  each call to {@link #pathTo(int)} takes time proportional to the length
 *  of the path.
 *  It uses extra space (not including the digraph) proportional to <em>V</em>.
 *  <p>
 */
public class BFS {
	private boolean marked[];       //marked[v]  is there a path from source to v
	private int[] edgeTo;           //edgeTo[v] = last edge on the shortest path s->v
	private int[] distTo;           //distTo[v] = length of shortest s -> v path
	private static final int INFINITY = Integer.MAX_VALUE;
	
	public BFS(Graph G, int s) {
		int numberOfVertices = G.V();
		marked = new boolean[numberOfVertices];
		edgeTo = new int[numberOfVertices];
		distTo = new int[numberOfVertices];
		validateVertex(s);
		bfs(G, s);
		
		assert check(G, s);
	}
	
	// BFS from a single source
	private void bfs(Graph G,int s) {
		Queue<Integer> q = new Queue<Integer>();
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = INFINITY;
		}
		q.enqueue(s);
		marked[s] = true;
		distTo[s] = 0;
		while(!q.isEmpty()) { 
			int v = q.dequeue();
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					q.enqueue(w);
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
				}
			}
		}
	}
	
    /**
     * Is there a path between the source vertex {@code s} (or sources) and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */	
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return marked[v];
	}
	
    /**
     * Returns the number of edges in a shortest path between the source vertex {@code s}
     * (or sources) and vertex {@code v}?
     * @param v the vertex
     * @return the number of edges in a shortest path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }
    
    public Iterable<Integer> pathTo(int v) {
    	validateVertex(v);
    	if(!hasPathTo(v)) return null;
    	Stack<Integer> path = new Stack<Integer>();
    	int x;
    	for(x = v; distTo[x] != 0; x = edgeTo[x]) {
    		path.push(x);
    	}
    	path.push(x);
    	return path;
    }
	
    /*
     * check optimality conditions for single source
     */
    private boolean check(Graph G, int s) {
    	//check that the distance of s = 0
    	if (distTo[s] != 0) {
    		StdOut.println("distance of source " + s + " to itself = " + distTo[s]);
    		return false;
    	}
    	
    	//check that for each edge v-w dist[w] <= dist[v] + 1
    	//provided v is reachable from s
    	for(int v = 0; v < G.V(); v++ ) {
    		for(int w : G.adj(v)) {
    			if(hasPathTo(v) != hasPathTo(w)) {
    				StdOut.println("edge " + v + "-" + w);
    				StdOut.println("hasPathTo(" + v + ")= " + hasPathTo(v));
    				StdOut.println("hasPathTo(" + w + ")= " + hasPathTo(w));
    				return false;
    			}
    			if(hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
    				StdOut.println("edge " + v + "-" + w);
    				StdOut.println("distTo[" + v + "]" + distTo[v]);
    				StdOut.println("distTo[" + w + "]" + distTo[w]);
    				return false;
    			}
    		}
    	}
    	
    	//check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
    	//provided v is reachable from s
    	for(int w = 0; w < G.V(); w++) {
    		if(!hasPathTo(w) || w == s) continue;
    		int v = edgeTo[w];
    		if(distTo[w] != distTo[v] + 1) {
                StdOut.println("shortest path edge " + v + "-" + w);
                StdOut.println("distTo[" + v + "] = " + distTo[v]);
                StdOut.println("distTo[" + w + "] = " + distTo[w]);
                return false;
    		}
    	}
    	
    	return true;
    }
    
    
	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if( v < 0 || v > marked.length) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (marked.length));
		}
	}
}
