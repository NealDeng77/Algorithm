
public class CountParallelEdges {
	public int count(Graph G) {
		boolean marked[] = new boolean[G.V()];
		int count = 0;
		for(int v = 0; v < G.V(); v++) {
			for(int w : G.adj(v)) {
				if(marked[w]) {
					count++;
				}else {
					marked[w] = true;
				}
				
			}
			//it is important to reset the marked otherwise it would miscounting
			for(int w : G.adj(v)) {
				marked[w] = false;
			}
		}
		return count / 2;
	}
	
    public static void main(String[] args) {
    	CountParallelEdges exercise32 = new CountParallelEdges();

        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);
        StdOut.println(exercise32.count(graph1) + " Expected: 0");

        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 0);
        graph2.addEdge(3, 2);
        StdOut.println(exercise32.count(graph2) + " Expected: 1");

        Graph graph3 = new Graph(4);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 0);
        graph3.addEdge(2, 3);
        graph3.addEdge(3, 2);
        graph3.addEdge(3, 2);
        graph3.addEdge(3, 0);
        StdOut.println(exercise32.count(graph3) + " Expected: 3");
    }
}
