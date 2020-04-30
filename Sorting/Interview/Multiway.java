package Interview;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import Interview.IndexMinPQ;
//*  Merges together the sorted input stream given as command-line arguments
//*  into a single sorted output stream on standard output.
public class Multiway {
	private Multiway() { }
	
	private static void merge(In[] streams) {
		int n = streams.length;
		IndexMinPQ<String> pq = new IndexMinPQ<String>(n);
		for(int i = 0; i < n; i++) {
			if(!streams[i].isEmpty()) {
				pq.insert(i, streams[i].readString());
			}
		}
		
		StdOut.println("after first for loop:");
		StdOut.println(pq);
		
		while(!pq.isEmpty()) {
			StdOut.print(pq.minKey() + " ");
			int i = pq.delMin();
			if(!streams[i].isEmpty()) {
				pq.insert(i, streams[i].readString());
			}
			
		}
		StdOut.println("after second for loop:");
		StdOut.println(pq);
		StdOut.println();
		
	}
	
	public static void main(String[] args) {
		int n = args.length;
		In[] streams = new In[n];
		for(int i = 0; i < n; i++) {
			streams[i] = new In(args[i]);
		}
		merge(streams);
	}
	
}
