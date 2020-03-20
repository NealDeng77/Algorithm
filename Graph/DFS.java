
public class DFS {
	private boolean[] marked;    //marked[v] = true if v connected to s 
	private int[] edgeTo;        //edgeTo[v] = previous vertex on path from s to v
	private int s;
	
	public DFS(Graph G, int s) {
		int numberOfVertex = G.V();
		marked = new boolean[numberOfVertex];
		edgeTo = new int[numberOfVertex];
		this.s = s;
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w: G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
				edgeTo[w] = v;
			}
		}
	}
}
