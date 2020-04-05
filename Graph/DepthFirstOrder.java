import edu.princeton.cs.algs4.Queue;
//If we save the vertex given as argument to the recursive dfs() in a data structure, then iterate through that data structure, we
//see all the graph vertices, in order determined by the nature of the data structure and
//by whether we do the save before or after the recursive calls. Three vertex orderings are
//of interest in typical applications:
//Preorder : Put the vertex on a queue before the recursive calls.
//Postorder : Put the vertex on a queue after the recursive calls.
//Reverse postorder : Put the vertex on a stack after the recursive calls.
public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> preorder;
	private Queue<Integer> postorder;
	private Stack<Integer> reversePost;
	
	public DepthFirstOrder(Digraph G) {
		
		marked = new boolean[G.V()];
		preorder = new Queue<Integer>();
		postorder = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
			}
		}
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		preorder.enqueue(v);
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G, v);
			}
		}
		postorder.enqueue(v);
		reversePost.push(v);
	}
	
	public Iterable<Integer> preorder() {
		return preorder;
	}
	
	public Iterable<Integer> postorder() {
		return postorder;
	}
	
	public Iterable<Integer> reversePostorder() {
		return reversePost;
	}
}


