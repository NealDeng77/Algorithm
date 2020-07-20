//eager version
//reference: princeton 
public class DijkstraShortestPath {
	private boolean[] visited;
	private int numOfNodes;
	private int[] edgeTo;
	private IndexMinPQ<Double> pq;
	private int[] dist;
	
	private class Edge {
		private int from;
		private int to;
		private double weight;
		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public int getTo() {
			return to;
		}
		public int getFrom() {
			return from;
		}
		public double getWeight() {
			return weight;
		}
	}
	
	public DijkstraShortestPath(List<List<Edge>> graph, int start) {
		numOfNodes = graph.size();
		dist = new int[numOfNodes];
		for(int i = 0; i < numOfNodes; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
		}
		edgeTo = new int[numOfNodes];
		//set the starting point
		dist[start] = 0;
		pq = new IndexMinPQ(numOfNodes);
		pq.insert(start, dist[start]);
		while(!pq.isEmpty()) {
			//get the next visited
			int nextNode = pq.delMin();
			if(visited[nextNode]) continue;
			visited[nextNode] = true;
			List<Edge> adj = graph.get(nextNode);
			if(adj != null) {
				for(Edge edge: adj) {
					//if visited, we continue, we can't get a shorter path by 
					//revisiting the node we have visited
					if(visited[edge.to]) continue;
					double newDist = dist[nextNode] + edge.getWeight();
					if(newDist < dist[edge.getTo()]) {
						//update the distance
						dist[edge.getTo()] = newDist;
						//update the key in the priority
						if(pq.contains(edge.getTo())) {
							pq.decreseKey(edge.getTo(), newDist);
						} else {
							pq.insert(edge.getTo(), newDist);
						}
						edgeTo[edge.getTo()] = nextNode;
					}
				}
			}
		}
	}
	
	public int[] getDist() {
		return dist;
	}
}
