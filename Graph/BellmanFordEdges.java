
//reference: princeton algorithm
//https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/graphtheory/BellmanFordEdgeList.java
public class BellmanFordEdges {
	private int[] edgeTo;
	private double[] distTo;
	private int numOfNodes;
	public BellmanFordEdges(int numOfNodes) {
		edgeTo = int[numOfNodes];
		distTo = double[numOfNodes];
		this.numOfNodes = numOfNodes;
		for(int i = 0; i < numOfNodes; i++) {
			distTo[i] = Double.POSITIVE_INFINITE; 
		}
	}
	
	public void findPath(List<Edge> graph, int start) {
		distTo[start] = 0;
		boolean relaxedAnEdge = true;
		//if we couldn't relax an edge, means we find the optimal solution
		//so that we stop the for loop
		for(int i = 0; i < numOfNodes && relaxedAnEdge; i++) {
			
			relaxedAnEdge = false;
			for(Edge edge: graph) {
				int from = edge.either();
				int to = edge.other(from);
				if(distTo[from] + edge.weight() < distTo[to]) {
					distTo[to] = distTo[from] + edge.weight();
					edgeTo[to] = from;
					relaxedAnEdge = true;
				}
			}
		}
		
		//run the second time to detect negative cycle
		//if a better distTo find, there is a negative cycle
		relaxedAnEdge = true;
		for(int i = 0; i < numOfNodes  && relaxedAnEdge; i++) {
			relaxedAnEdge = false;
			for(Edge edge: graph) {
				
				int from = edge.either();
				int to = edge.other(from);
				if(distTo[from] + edge.weight() < distTo[to]) {
					distTo[to] = Double.NEGATIVE_INFINITE;
					edgeTo[to] = from;
					relaxedAnEdge = true;
				}
			}
		}
	}
	
	public double[] getDist() {
		return dist;
	}
	
	private class Edge implements Comparable<Edge> {
		private final int v;
		private final int w;
		private final double weight;
		public Edge(int v, int w, double weight) {
	        if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
	        if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
	        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
			this.v = v;
			this.w = w;
			this.weight = weight;
		}
		
		
	    /**
	     * Returns the weight of this edge.
	     *
	     * @return the weight of this edge
	     */
	    public double weight() {
	        return weight;
	    }

	    /**
	     * Returns either endpoint of this edge.
	     *
	     * @return either endpoint of this edge
	     */
	    public int either() {
	        return v;
	    }
	    
	    public int other(int vertex) {
	    	if(vertex == v) return w;
	    	else if(vertex == w) return v;
	    	else throw new IllegalArgumentException("Illegal endpoint");
	    }
	    
		@Override
		public int compareTo(Edge that) {
			return Double.compare(this.weight, that.weight);
		}
}
