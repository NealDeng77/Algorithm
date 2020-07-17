//reference: https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/graphtheory/treealgorithms/TreeCenter.java
//https://www.udemy.com/course/graph-theory-algorithms/learn/lecture/18603268?components=buy_button%2Cdiscount_expiration%2Cgift_this_course%2Cpurchase%2Cdeal_badge%2Credeem_coupon#questions
public class TreeCenter {
	//The input is a tree represented by adjacent list
	public List<Integer> findTreeCenters(List<List<Integer>> trees) {
		int n = tree.size(); //number of nodes in the tree
		int[] degree = new int[n];
		List<Integer> leaves = new ArrayList<Integer>();
		int countLeaves = 0;
		//get the degree of each node
		for(int i = 0; i < n; i++) {
			List<Integer> adj = tree.get(i);
			degree[i] = adj.size();
			if(adj.size() == 0 || adj.size() == 1) {
				leaves.add(i);
				degree[i] = 0;
			}
		}
		countLeaves = leaves.size();
		while(countLeaves < n) {
			List<Integer> newLeaves = new ArrayList<Integer>();
			for(int eachLeaf : leaves) {
				for(int eachNeighbor: tree.get(eachLeaf) ) {
					degree[eachNeighbor] = degree[eachNeighbor] - 1;
					if(degree[eachNeighbor] == 1) newLeaves.add(eachNeighbor);
				}
				degree[eachLeaf] = 0;
			}
			count += newLeaves.size();
			leaves = newLeaves;
		}
		return leaves;
	}
	
	
	//testing
	//build a tree with n nodes, using adjacent list representation
	public static List<List<Integer>> buildEmptyTree(int n) {
		List<List<Integer>> tree = new ArrayList<List<Integer>>();
		for(int i = 0; i < n; i++) {
			List<Integer> adj = new ArrayList<Integer>();
			tree.add(adj);
		}
		return tree;
	}
	
	public static void addEdge(int from, int to, List<List<Integer>> tree) {
		tree.get(from).add(to);
		tree.get(to).add(from);
	}
	
	
}
