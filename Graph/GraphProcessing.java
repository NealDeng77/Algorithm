/*
 * Use the Graph API class to process graph
 */
public class GraphProcessing {
	/*
	 * compute the degree of v, number of vertices connected to v via an edge
	 */
	public static int degree(Graph G, int v) {
		int count = 0;
		for(int w: G.adj(v)) {
			count++;
		}
		return count;
	}
	
	/*
	 * compute maximum degree
	 */
	public static int maxDegree(Graph G) {
		int max = 0;
		int vDegree = 0;
		for(int v = 0; v < G.V(); v++) {
			vDegree = degree(G, v);
			if(vDegree > max) max = vDegree;
		}
		return max;
	}
	
	/*
	 * compute average degree
	 */
	public static double averageDegree(Graph G) {
		return 2.0 * G.E() / G.V();
	}
	
	/*
	 * count self-loops
	 */
	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for(int v = 0; v < G.V(); v++) {
			for(int w: G.adj(v)) {
				if(w == v) count++;
			}
		}
		return count/2;     //each edge counted twice
	}
}
