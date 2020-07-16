//Problem statement and solution: https://www.udemy.com/course/graph-theory-algorithms/learn/lecture/10794128#announcements
import java.util.LinkedList;
import java.util.Queue;
public class DungeonGame {
	private int startR;
	private int startC;
	private int numOfRows;
	private int numOfCols;
	private boolean reachEnd;
	private boolean[][] visited;
	private char[][] matrix;
	private int nodesLeftInCurrentLevel;
	private int nodesInNextLevel;
	
	
	public DungeonGame(int startR, int startC, int R, int C, char[][] matrix) {
		this.startR = startR;
		this.startC = startC;
		numOfRows = R;
		numOfCols = C;
		this.matrix = matrix;
	}
	
	//using bfs
	public int solve() {
		Queue<Integer> queueR = new LinkedList<Integer>();
		Queue<Integer> queueC = new LinkedList<Integer>();
		queueR.add(startR);
		queueC.add(startC);
		int stepCount = 0;
		nodesLeftInCurrentLevel = 1;
		nodesInNextLevel = 0;
		while(queueR.size() > 0) {
			int r = queueR.remove();
			int c = queueC.remove();
			//reach the end
			if(matrix[r][c] == 'E') {
				reachEnd = true;
				break;
			}
			explore(r, c);
			nodesLeftInCurrentLevel--;
			if(nodesLeftInCurrentLevel == 0) {
				nodesLeftInCurrentLevel = nodesInNextLevel;
				nodesInNextLevel = 0;
				stepCount++;
			}
		}
		if(reachEnd) return stepCount;
		else return -1;
	}
	
	private void explore(int r, int c, Queue<Integer> queueR, Queue<Integer> queueC) {
		//four directions, north, south, east, west
		int[] dr = [-1, 1, 0, 0];
		int[] dc = [0, 0, 1, -1];
		for(int i = 0; i < 4; i++) {
			int nextR = r + dr[i];
			int nextC = c + dc[i];
			//check if it's a valid node
			if(nextR < 0 || nextC < 0) continue;
			if(nextR >= this.numOfRows || nextC >= this.numOfCols) continue;
			
			//if visited continue
			if(visited[nextR][nextC]) continue;
			//if it's a rock node, continue
			if(matrix[nextR][nextC] == '#') continue;
			//else add to the queue, and update nodes in the next level
			queueR.add(nextR);
			queueC.add(nextC);
			visited[nextR][nextC] = true;
			nodesInNextLevel++;
		}
	}
}
