//using floyedWarshall to find the all pairs shortest paths
//reference: https://algs4.cs.princeton.edu/44sp/FloydWarshall.java.html
//https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/graphtheory/FloydWarshallSolver.java
import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
import java.util.*;
public class FloyedWarshall {
	private static final int REACHES_NEGATIVE_CYCLE = -1;
	
	private double[][] distMatrix;      //store the distance
	private Intger[][] edgeTo;             //store the next step, used for reconstructing the path
	private boolean solved;
	private int n;
	
	//take a graph represented by matrix as input
	public FloyedWarshall(double[][] graphMatrix) {
		n = graphMatrix.length;
		distMatrix = new double[n][n];
		edgeTo = new Integer[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				distMatrix[i][j] = graphMatrix[i][j];
				if(graphMatrix[i][j] != POSITIVE_INFINITY) {
					edgeTo[i][j] = j;
				}
			}
		}
	}
	
	private void solve() {
		if(solved) return;
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(distMatrix[i][k] + distMatrix[k][j] < distMatrix[i][j]) {
						distMatrix[i][j] = distMatrix[i][k] + distMatrix[k][j];
						edgeTo[i][j] = edgeTo[i][k];
					}
				}
			}
		}
		
		//detect negative cycle
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(distMatrix[i][k] + distMatrix[k][j] < distMatrix[i][j]) {
						distMatrix[i][j] = NEGATIVE_INFINITE;
						edgeTo[i][j] = REACHES_NEGATIVE_CYCLE;
					}
				}
			}
		}
		solved = true;
	}
	
	//find the path between s and e
	public List<Integer> findPath(int s, int e) {
		List<Integer> path = new ArrayList<>();
		//if dist is +infinity, means unreachable
		if(distMatrix[s][e] == POSITIVE_INFINITY) return path;
		for(int i = s; i != e; i = edgeTo[i][e]) {
			//negative cycle
			if(i == REACHES_NEGATIVE_CYCLE) return null;
			path.add(i);
		}
		//up to this point, the i is reaching the end, i == e
		//check the edge case which the last node is a self infinite loop
		if(edgeTo[e][e] == REACHES_NEGATIVE_CYCLE) return null;
		path.add(e);
		return path;
	}
	
}
