//reference: https://www.youtube.com/watch?v=TXkDpqjDMHA&list=PLDV1Zeh2NRsDGO4--qE8yH72HFL1Km93P&index=16
//https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/graphtheory/TopologicalSortAdjacencyList.java
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
public class ShortestPathInDAG {
	static class Edge {
		int to;
		int from;
		int weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	private static int[] prevStep;  //store the shortest
	
	

	public static int[] shortestPathInDag(Map<Integer, List<Edge>> graph, int start, int numOfNodes) {
		int[] dist = new int[numOfNodes];  //store the distance
		Arrays.fill(dist, Integer.MAX_VALUE);
		prevStep = new int[numOfNodes];
		int[] topologicalOrder = topologicalSort(graph, numOfNodes);
		dist[start] = 0;
		for(int i = 0; i < numOfNodes; i++) {
			int node = topologicalOrder[i];
			List<Edge> adjList = graph.get(node);
			for(Edge edge: adjList) {
				int to = edge.to;
				int newDist = edge.weight + dist[node];
				if(newDist < dist[to]) {
					dist[to] = newDist;
					prevStep[to] = node;
				}
			}
		}
		return dist;
	}
	
	//return the shortest length from the start to each node, if the return value is
	//-1, this node is not reachable
	public static int getTheShortestDis(int node, int[] dist) {
		if(dist[node] == Integer.MAX_VALUE) {
			return -1;
		} else {
			return dist[node];
		}
	}
		
	public static int[] topologicalSort(Map<Integer, List<Edge>> graph, int numOfNodes) {
		List<Integer> topologicalOrder = new ArrayList<Integer>();
		boolean[] visited = new boolean[numOfNodes];
		int[] order;
      for(int i = 0; i < numOfNodes; i++) {
			if(visited[i] == false) {
				dfs(graph, i, visited, topologicalOrder);
			}
		}
		Collections.reverse(topologicalOrder);
      order = new int[topologicalOrder.size()];
      for(int i = 0; i < order.length; i++) {
         order[i] = topologicalOrder.get(i);
      }
		return order;
	}
	
	private static void dfs(Map<Integer, List<Edge>> graph, int target, boolean[] visited, List<Integer> topologicalOrder) {
		visited[target] = true;
		List<Edge> adjList = graph.get(target);
		if(adjList != null) {
			for(Edge edge: adjList) {
				if(visited[edge.to] == false) {
					dfs(graph, edge.to, visited, topologicalOrder);
				}
			}
		}
		topologicalOrder.add(target);
	}
	
	public static void main(String[] args) {
		final int N = 7;
		Map<Integer, List<Edge>> graph = new HashMap<>();
		for(int i = 0; i < N; i++) {
			graph.put(i, new ArrayList<>());
		}
	    graph.get(0).add(new Edge(0, 1, 3));
	    graph.get(0).add(new Edge(0, 2, 2));
	    graph.get(0).add(new Edge(0, 5, 3));
	    graph.get(1).add(new Edge(1, 3, 1));
	    graph.get(1).add(new Edge(1, 2, 6));
	    graph.get(2).add(new Edge(2, 3, 1));
	    graph.get(2).add(new Edge(2, 4, 10));
	    graph.get(3).add(new Edge(3, 4, 5));
	    graph.get(5).add(new Edge(5, 4, 7));
	    
	    int[] ordering = topologicalSort(graph, N);
	    System.out.println(java.util.Arrays.toString(ordering));
        // Finds all the shortest paths starting at node 0
        int[] dists = shortestPathInDag(graph, 0, N);
	   
	    // Find the shortest path from 0 to 4 which is 8.0
	    System.out.println(getTheShortestDis(4, dists));
	    for(int i = prevStep[4]; i != 0; i  = prevStep[i]) {
	    	System.out.print("i prevStep: " + i);
	    	System.out.println();
	    }
	    
	   
	    // Find the shortest path from 0 to 6 which
	    // is null since 6 is not reachable!
	    System.out.println(getTheShortestDis(6, dists));
       
	}
}
