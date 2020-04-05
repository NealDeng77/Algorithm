public class DirectedCycle {
	private int[] edgeTo;
	private boolean[] marked;
	private Stack<Integer> cycle;
	private boolean[] onStack;
	
	public DirectedCycle(Digraph graph) {
		edgeTo = new int[graph.V()];
		marked = new boolean[graph.V()];
		onStack = new boolean[graph.V()];
		for(int v = 0; v < graph.V(); v++) {
			if(!marked[v]) {
				dfs(graph, v);				
			}
		}
	}
	
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		// put the current working v on stack
		onStack[v] = true;
		for(int w : g.adj(v)) {
			if(hasCycle()) {
				return;
			}
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(g, w);
			} else if(onStack[v]) {
				cycle = new Stack<Integer>();
				for(int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		//finish working on v, pop from stack
		onStack[v] = false;
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle() {
		return cycle;
	}

}
