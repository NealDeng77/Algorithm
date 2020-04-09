package MinimumSpanningTree;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Edge;

public class LazyPrimMST {
	private boolean[] marked;
	private MinPQ<Edge> pq;
	private Queue<Edge> mst;
	private double weight;
	
	public LazyPrimMST(EdgeWeightedGraph G) {
		marked = new boolean[G.V()];
		pq = new MinPQ<Edge>();
		mst = new Queue<Edge>();
		
		visit(G, 0);
		while(!pq.isEmpty()) {
			Edge edge = pq.delMin();
			int v = edge.either();
			int w = edge.other(v);
			if(marked[v] && marked[w]) {
				continue;
			}
			else if(!marked[v]) {
				visit(G, v);
			}
			else if(!marked[w]) {
				visit(G, w);
			}
			mst.enqueue(edge);
			weight += edge.weight();
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for(Edge w : G.adj(v)) {
			if(!marked[w.other(v)]) {
				pq.insert(w);
			}
		}
	}
	
	public Iterable<Edge> mst(){
		return mst;
	}
	
	public double weight() {
		return weight;
	}
	
	
}
