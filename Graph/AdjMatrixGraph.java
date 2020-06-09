import java.util.Iterator;
public class AdjMatrixGraph {
	private int V;
	private int E;
	private boolean[][] adj;
	
	public AdjMatrixGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = new boolean[V][V];
	}
	
	public boolean contain(int v, int w) {
		return adj[v][w] == true;
	}
	
	public void addEdge(int v, int w) {
		if(adj[v][w] != true) E++;
		adj[v][w] = true;
		adj[w]v] = true;
	}
	
	public Iterable<Integer> adj(int v) {
		return new AdjIterator(v);
	}
	
	private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
		private int v;
		private int w = 0;
		
		AdjIterator(int v) {
			this.v = v;
		}
		
		public Iterator<Integer> iterator() {
			return this;
		}
		
		public boolean hasNext() {
			while(w < V) {
				if(adj[v][w]) return true;
				w++;
			}
			return false;
		}
		
		public Integer next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return w++;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	
}