/*
 * Implement depth-first search in an undirected graph without using recursion.
 * Idea: Using a stack
 * java DFSNoneRecursive tinyCG.txt 0
 * 0 to 0: 0
 * 0 to 1: 0-1
 * 0 to 2: 0-2
 * 0 to 3: 0-5-3
 * 0 to 4: 0-5-3-4
 * 0 to 5: 0-5
*/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Graph;
public class DFSNoneRecursive {
	private Stack<Integer> stack;
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;    //source vertex
	
	public DFSNoneRecursive(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		validateVertex(s);
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
			}
		}
	}
	
	private void dfs(Graph G, int s) {
		stack = new Stack<Integer>();
		marked[s] = true;
		stack.push(s);
		while(!stack.isEmpty()) {
			int vertex = stack.pop();
			for(int w : G.adj(vertex)) {
				if(!marked[w]) {
					marked[w] = true;
					edgeTo[w] = vertex;
					stack.push(w);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		validateVertex(v);
		if(!hasPathTo(v)) return null;
		Stack<Integer> pathStack = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x]) {
			pathStack.push(x);
		}
		pathStack.push(s);
		return pathStack;
	}
	
	private void validateVertex(int v) {
		int V = marked.length;
		if(v >= V || v < 0) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph graph = new Graph(in);
		int s = Integer.parseInt(args[1]);
		DFSNoneRecursive dfs = new DFSNoneRecursive(graph, s);
		
		for(int v = 0; v < graph.V(); v++) {
			if(dfs.hasPathTo(v)) {
				StdOut.printf("%d to %d: ", s, v);
				for(int x : dfs.pathTo(v)) {
					if(x == s) StdOut.print(x);
					else	StdOut.print("-" + x);
				}
				StdOut.println();
			}
			
			else {
				StdOut.printf("%d to %d not connected", s, v);
			}
		}
	}
}
