import edu.princeton.cs.algs4.Graph;

//reference: https://www.udemy.com/course/graph-theory-algorithms/learn/lecture/18603262#announcements
//given a graph, and the rooting node, build a rooted tree

public class RootingTheTree {
	private class TreeNode {
		private int nodeId;
		private TreeNode parent;   //pointer to his parent
		private List<TreeNode> children;  //adjacent list
		public TreeNode(int nodeId, TreeNode parent) {
			this.nodeId = nodeId;
			this.parent = parent;
			children = new ArrayList<TreeNode>();
		}
	}
	private TreeNode root;
	
	public void buildTree(Graph G, int initialRoot) {
		root = new TreeNode(initialRoot, null);
		buildTree(root, null, G);
	}
	
	//use dfs to build the rooted Tree
	private void buildTree(TreeNode currentNode, TreeNode parent, Graph G) {
		
		for(int eachAdj : G.adj(currentNode.nodeId)) {
			//avoid adding and edge pointing back to the parent
			if(parent != null && eachAdj == parent.nodeId) continue;
			
			TreeNode chid = new TreeNode(eachAdj, currentNode);
			currentNode.children.add(child);
			buildTree(child, currentNode, G);
		}
	}
}
