import edu.princeton.cs.algs4.StdOut;

public class Topological {
	private Iterable<Integer> order;
	
	public Topological(Digraph G) {
		DirectedCycle cyclefinder = new DirectedCycle(G);
		if(!cyclefinder.hasCycle()) {
			DepthFirstOrder findOrder = new DepthFirstOrder(G);
			order = findOrder.reversePostorder();
		}
	}
	
	public Iterable<Integer> order() {
		return order;
	}
	
	public boolean hasOrder() {
		return order != null;
	}
	
	public void main(String[] args) {
		String filename = args[0];
		String delimiter = args[1];
		SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
		Topological top = new Topological(sg.Digraph());
		for(int v : top.order) {
			StdOut.println(sg.nameOf(v));
		}
	}
}
