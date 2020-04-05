import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolDigraph {
	private ST<String, Integer> st;     //String -> index
	private Digraph g;
	private String[] keys;    // index -> String
	
	/*
	 * Build Digraph specified in filename using delimiter to separate vertex names
	 */
	public SymbolDigraph(String filename, String delimiter) {
		st = new ST<String, Integer>();
		In in = new In(filename);
		//add all unique <String, Integer> to the symbol table
		while(in.hasNextLine()) {
			String[] line = in.readLine().split(delimiter);
			for(int i = 0; i < line.length; i++) {
				if(!st.contains(line[i])) {
					st.put(line[i], st.size());
				}
			}
		}
		
		//put all keys of the symbol table to keys[],
		keys = new String[st.size()];
		for(String name : st.keys()) {
			keys[st.get(name)] = name; 
		}
		
		//build the Digraph
		g = new Digraph(keys.length);
		in = new In(filename);
		while(in.hasNextLine()) {
			String[] line = in.readLine().split(delimiter);
			int v = st.get(line[0]);
			for(int i = 1; i < line.length; i++) {
				int w = st.get(line[i]);
				g.addEdge(v, w);
			}
		}
	}
	
	/*
	 * Is key a vertex
	 */
	public boolean contains(String key) {
		return st.contains(key);
	}
	
	/*
	 * index associated with key
	 */
	public int indexOf(String key) {
		return st.get(key);
	}
	
	/*
	 * key associated with index v
	 */
	public String nameOf(int v) {
		return keys[v];
	}
	
	/*
	 * underlying Digraph
	 */
	public Digraph Digraph() {
		return g;
	}
	
	/*
	 * Test client
	 */
	public void main(String[] args) {
		String filename = args[0];
		String delimiter = args[1];
		SymbolDigraph symbolDigraph = new SymbolDigraph(filename, delimiter);
		Digraph g = symbolDigraph.Digraph();
		while(StdIn.hasNextLine()) {
			String start = StdIn.readLine();
			int v = symbolDigraph.indexOf(start);
			for(int w : g.adj(v)) {
				StdOut.println("  " + symbolDigraph.nameOf(w));
			}
		}
		
	}
}