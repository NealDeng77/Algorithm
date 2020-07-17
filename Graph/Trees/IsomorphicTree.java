import RootingTheTree.TreeNode;
import edu.princeton.cs.algs4.Graph;

//reference: https://www.udemy.com/course/graph-theory-algorithms/learn/lecture/18603284#questions

public class IsomorphicTree {
	private class TreeNode {
		private int nodeId;
		private List<TreeNode> children;
		private TreeNode parent;
		public TreeNode(int nodeId, TreeNode parent) {
			this.nodeId = nodeId;
			this.parent = parent;
			children = new ArrayList<TreeNode>();
		}
	}
	
	public boolean areIsomorphicTree(List<List<Integer>> tree1, List<List<Integer>> tree2) {
		if(tree1.isEmpty() || tree2.isEmpty()) throw new Exception("One of the tree is empty");
		List<Integer> centersTreeOne = findCenter(tree1);
		List<Integer> centersTreeTwo = findCenter(tree2);
		TreeNode rootTreeOne = rootTree(tree1, centersTreeOne.get(0));
		String encodeTreeOne = encode(rootTreeOne);
		//see if using one of the center of tree2, can root the tree and
		//get the same encode
		for(int center: centersTreeTwo) {
			TreeNode rootTreeTwo = rootTree(tree2, center);
			String encodeTreeTwo = encode(rootTreeTwo);
			if(encodeTreeTwo.equals(encodeTreeOne)) {
				return true;
			}
		}
		return false;
	}
	
	private List<Integer> findCenter(List<List<Integer>> tree) {
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
	
	private TreeNode rootTree(List<List<Integer>> tree, int initialRoot) {
		TreeNode root = new TreeNode(initialRoot, null);
		return buildTree(root, null, tree);
	}
	
	//use dfs to build the rooted Tree
	private TreeNode buildTree(TreeNode currentNode, TreeNode parent, List<List<Integer>> tree) {
		
		for(int eachAdj : tree.get(currentNode.nodeId)) {
			//avoid adding and edge pointing back to the parent
			if(parent != null && eachAdj == parent.nodeId) continue;
			
			TreeNode chid = new TreeNode(eachAdj, currentNode);
			currentNode.children.add(child);
			buildTree(child, currentNode, tree);
			
		}
		return currentNode;
	}
	
	private String encode(TreeNode node) {
		if(node == null) return "";
		List<String> encode = new ArrayList<String>();
		for(TreeNode child : node.children) {
			String childEncode = encode(child);
			encode.add(childEncode);
		}
		//sort the encode
		Collections.sort(encode);
		StringBuilder sb = new StringBuilder("");
		for(String eachEncode: encode) {
			sb.append(eachEncode);
		}
		return "(" + sb.toString() + ")";
	}
}
