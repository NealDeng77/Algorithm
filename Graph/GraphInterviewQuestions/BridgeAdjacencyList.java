//using dfs to find bridges
//Idea: We need to record the nodeId we are visiting, and the lowestNodeID in the same component
//We can use dfs to do that
//Once we find that the Id[from] < lowestNodeID[to], the from - to is a bridge
//Usage: Find the bottleNeck in a network
import java.util.ArrayList;
import java.util.List;
public class BridgeAdjacencyList {
	private int n;        //num of nodes;
	private int[] id;     //store the current visited id
	private int[] lowestNodeId;       //store the connected lowest node id
	private List<List<Integer>> bridges;        //store the bridges
	private boolean[] visited;
	private int count;   //count how many nodes have been visited, used to store the sequence of node visiting
	private List<List<Integer>> graph;
	private boolean solved;
	public BridgeAdjacencyList(List<List<Integer>> graph) {
		if(graph == null || graph.size() <= 0) throw new IllegalArgumentException("graph is invalid");
		n = graph.size();
		this.graph = graph;
	}
	
	public void findBridges() {
		id = new int[n];
		lowestNodeId = new int[n];
		bridges = new ArrayList<List<Integer>>();
		count = 0;
		visited = new boolean[n];
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				dfs(i, -1);
			}
		}
		solved = true;
	}
	
	private void dfs(int current, int parent) {
		id[current] = count;
		lowestNodeId[current] = count;
		count++;
		visited[current] = true;
		for(int adj:graph.get(current)) {
			if(adj == parent) continue;
			if(!visited[adj]) {
				dfs(adj, current);
				//from the callback, update the lowestNodeId for the current node
				lowestNodeId[current] = Math.min(lowestNodeId[current], lowestNodeId[adj]);
			}
			//if already visited
			//update the lowestNodeId for the current node
			else {
				lowestNodeId[current] = Math.min(lowestNodeId[current], id[adj]);
			}
			if(id[current] < lowestNodeId[adj]) {
				List<Integer> newBridge = new ArrayList<>();
				newBridge.add(current);
				newBridge.add(adj);
				bridges.add(newBridge);
			}
		}
	}
	
	public List<List<Integer>> getBridges() {
		if(!solved) findBridges();
		return bridges;
	}
	
	public static void main(String[] args) {
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
	    
	    BridgeAdjacencyList solver = new BridgeAdjacencyList(graph);
	    List<List<Integer>> bridges = solver.getBridges();
	    for(List<Integer> eachBridge: bridges) {
	    	int start = eachBridge.get(0);
	    	int end = eachBridge.get(1);
	    	System.out.printf("Bridge between nodes: %d and %d\n", start, end);
	    }
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
}
