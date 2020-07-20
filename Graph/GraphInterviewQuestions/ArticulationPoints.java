
//Idea: the articulation point could occur at the bridge, or the start of a cycle
//It also can occur at the root if the root has more than one outgoing edge
//Reference: https://www.udemy.com/course/graph-theory-algorithms/learn/lecture/10794156#overview
//https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/graphtheory/ArticulationPointsAdjacencyList.java
//usage: find the single point failure in the network
import java.util.ArrayList;
import java.util.List;
public class ArticulationPoints {
	private int n;        //num of nodes;
	private int[] id;     //store the current visited id
	private int[] lowestNodeId;       //store the connected lowest node id
	private boolean[] visited;
	private int count;   //count how many nodes have been visited, used to store the sequence of node visiting
	private List<List<Integer>> graph;
	private boolean solved;
	private int outEdges;       //count the outEdges of each node
	private boolean[] isArt;    //check if it's articulation point
	public ArticulationPoints(List<List<Integer>> graph) {
		if(graph == null || graph.size() <= 0) throw new IllegalArgumentException("graph is invalid");
		n = graph.size();
		this.graph = graph;
	}
	
	private void findPoints() {
		id = new int[n];
		lowestNodeId = new int[n];
		count = 0;
		visited = new boolean[n];
		isArt = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				outEdges = 0;
				dfs(i, i, -1);

				//easy to make mistake here, 
				//in the dfs process, the root is set to articulation point
				//since id[root] < lowestNodeId[adj] is always true
				//So we need to check if the 
				//root has an outEdges > 1, if not we need to set
				//it to false;
				isArt[i] = (outEdges > 1);
			}
		}
		solved = true;
	}
	
	private void dfs(int root, int current, int parent) {
		if(root == parent) outEdges++;
		id[current] = count;
		lowestNodeId[current] = count;
		count++;
		visited[current] = true;
		for(int adj:graph.get(current)) {
			if(adj == parent) continue;
			if(!visited[adj]) {
				dfs(root, adj, current);
				//from the callback, update the lowestNodeId for the current node
				lowestNodeId[current] = Math.min(lowestNodeId[current], lowestNodeId[adj]);
				//articulation point found through bridge
				if(id[current] < lowestNodeId[adj]) {
					isArt[current] = true;
				}
				//articulation point found through cycle
				if(id[current] == lowestNodeId[adj]) {
					isArt[current] = true;
				}
			}
			//if already visited
			//update the lowestNodeId for the current node
			else {
				lowestNodeId[current] = Math.min(lowestNodeId[current], id[adj]);
			}
		}
	}
	
	public boolean[] getArticulationPoints() {
		if(!solved) findPoints();
		return isArt;
	}
	
	  // Initialize graph with 'n' nodes.
	  public static List<List<Integer>> createGraph(int n) {
	    List<List<Integer>> graph = new ArrayList<>();
	    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
	    return graph;
	  }
	
	  // Add undirected edge to graph.
	  public static void addEdge(List<List<Integer>> graph, int from, int to) {
	    graph.get(from).add(to);
	    graph.get(to).add(from);
	  }
	  
		public static void main(String[] args) {
			test1();
			test2();
		}
		
		private static void test1() {
		    int n = 9;
		    List<List<Integer>> graph = createGraph(n);
		    addEdge(graph, 0, 1);
		    addEdge(graph, 0, 2);
		    addEdge(graph, 1, 2);
		    addEdge(graph, 2, 3);
		    addEdge(graph, 3, 4);
		    addEdge(graph, 2, 5);
		    addEdge(graph, 5, 6);
		    addEdge(graph, 6, 7);
		    addEdge(graph, 7, 8);
		    addEdge(graph, 8, 5);
		    ArticulationPoints solver = new ArticulationPoints(graph);
		    boolean[] isArt = solver.getArticulationPoints();
		    
		    System.out.println("test 1:");
		    //node 2, 3, 5 are articulation points
		    for(int i = 0; i < n; i++) {
		    	if(isArt[i]) {
		    		System.out.printf("Nodes %d is an articulation point.\n", i);
		    	}
		    }
		}
		
		private static void test2() {
		    int n = 3;
		    List<List<Integer>> graph = createGraph(n);
 
		    addEdge(graph, 0, 1);
		    addEdge(graph, 1, 2);

		    //point 1 is articulation point
		    ArticulationPoints solver = new ArticulationPoints(graph);
		    boolean[] isArt = solver.getArticulationPoints();
		    System.out.println("test 2:");
		    for(int i = 0; i < n; i++) {
		    	if(isArt[i]) {
		    		System.out.printf("Nodes %d is an articulation point.\n", i);
		    	}
		    }
		}
}
