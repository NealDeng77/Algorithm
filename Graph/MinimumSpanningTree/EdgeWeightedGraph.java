package MinimumSpanningTree;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedGraph {
	private static final String NEWLINE = System.getProperty("line.separator");
	private final int V;
	private int E;
	private Bag<Edge>[] adj;
	
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = E;
		adj = (Bag<Edge>[]) new Bag[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new Bag<Edge>();
		}
	}
	
	/*
	 * Deep copy
	 */
	public EdgeWeightedGraph(EdgeWeightedGraph G) {
		this.V = G.V;
		this.E = G.E;
		for(int i = 0; i < G.V; i++) {
			Stack<Edge> reverse = new Stack<Edge>();
			for(Edge w : G.adj[i]) {
				reverse.push(w);
			}
			for(Edge v : reverse) {
				this.adj[i].add(v);
			}
		}
	}
	
	public EdgeWeightedGraph(In in) {
		if (in == null) throw new IllegalArgumentException("argument is null");
		
		try {
			V = in.readInt();
			adj = (Bag<Edge>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Edge>();
            }
            
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
            for(int i = 0; i < E; i++) {
            	int v = in.readInt();
            	int w = in.readInt();
            	double weight = in.readDouble();
            	Edge e = new Edge(v, w, weight);
            	addEdge(e);
            }
		}
		catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format", e);
		}
	}
	
    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int E() {
        return E;
    }
    
    public void addEdge(Edge e) {
    	int v = e.either();
    	int w = e.other(v);
    	adj[v].add(e);
    	adj[w].add(e);
    	E++;
    }
    
    public Iterable<Edge> adj(int v){
    	return adj[v];
    }
    
    public int degree(int v) {
    	return adj[v].size();
    }
    
    /*
     * return all edges in the graph
     */
    public Iterable<Edge> edges() {
    	Bag<Edge> bag = new Bag<Edge>();
    	for(int v = 0; v < V; v++) {
    		int selfloop = 0;
    		for(Edge w : adj[v]) {
    			if(w.other(v) > v) {
    				bag.add(w);
    			} 
    			//add only one copy of each self loop
    			else if(w.other(v) == v) {
    				if(selfloop % 2 == 0) {
    					bag.add(w);
    				}
    				selfloop++;
    			}
    		}
    	}
    	return bag;
    }
    
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append(V + " " + E + NEWLINE);
    	for(int v = 0; v < V; v++) {
    		s.append(v + ": ");
    		for(Edge e: adj[v]) {
    			s.append(e + " ");
    		}
    		s.append(NEWLINE);
    	}
    	return s.toString();
    }
}
