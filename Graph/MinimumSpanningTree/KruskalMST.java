package MinimumSpanningTree;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.Edge;
public class KruskalMST {
	private Queue<Edge> mst;
	public KruskalMST(EdgeWeightedGraph G) {
		mst = new Queue<Edge>();
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e : G.edges()) {
			pq.insert(e);
		}
		UF uf = new UF(G.V());
		while(!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if(!uf.connected(v, w)) {
				uf.union(v, w);
				mst.enqueue(e);
			}
		}
	}
	
	public Iterable<Edge> getMST() {
		return mst;
	}
}
