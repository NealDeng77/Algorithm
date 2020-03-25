/*
 * Idea: For every visited vertex ‘v’, if there is an adjacent ‘u’ such that u is 
 * already visited and u is not parent of v,
 * then there is a cycle in graph. If we don’t find such an adjacent for any vertex, 
 * we say that there is no cycle.
 * Reference: https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 */
public class Cycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	
	public Cycle(Graph G) {
		if(hasSelfLoop(G)) return;
		if(hasParallelEdges(G)) return;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, -1, v);
			}
		}
	}
	
	/*
	 * Check if there is a cycle
	 */
	public boolean hasCycle() {
		return cycle != null;
	}
	
	/*
	 * Return a cycle 
	 */
	public Iterable<Integer> cycle() {
		return cycle;
	}
	
	/*
	 * Check if the graph has self loop
	 */
	private boolean hasSelfLoop(Graph G) {
		for(int v = 0; v < G.V(); v++) {
			for(int w : G.adj(v)) {
				if(v == w) {
					cycle = new Stack<Integer>();
					cycle.push(v);
					cycle.push(w);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * check if there is parallel edges existed
	 */
	private boolean hasParallelEdges(Graph G) {
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			
			//check
			//idea: if there is parallel edges say between 2 and 3, when adding edges(see addEdge in Graph class),
			//the same edge would be added twice, so that
			//adj(2) would be [3, 3],  adj(3) would be [2, 2].
			//so go through each adjacent list, if marked[w] == true, means there is one parallel edge.
			for(int w : G.adj(v)) {
				if (marked[w]) {
					cycle = new Stack<Integer>();
					cycle.push(v);
					cycle.push(w);
					cycle.push(v);
					return true;
				}
				marked[w] = true;
			}
			
			//reset all marked[] to false so that it could be used later.
			for(int w : G.adj(v)) {
				marked[w] = false;
			}
		}
		return false;
	}
	
	
	private void dfs(Graph G, int u, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			
			//check if the cycle has been found
			if(cycle != null) return;
			
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, v, w);
			}
			
			 /* Idea: For every visited vertex ‘v’, if there is an adjacent ‘w’ such that w is 
			 * already visited and w is not parent of v,
			 * then there is a cycle in graph. u is the parent. If we don’t find such an adjacent for any vertex, 
			 * we say that there is no cycle.
			 */
			else if(w != u) {
				cycle = new Stack<Integer>();
				for(int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}	
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		Cycle finder = new Cycle(G);
		if (finder.hasCycle()) {
			for (int v : finder.cycle()) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
		else {
			StdOut.println("Graph is acyclic");
		}
	}
}
