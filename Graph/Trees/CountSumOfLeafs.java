//reference: https://www.udemy.com/course/graph-theory-algorithms/learn/lecture/18603254#announcements

public class CountSumOfLeafs {
	private class Node {
		int val;
		Node[] children;
	}
	private int sum;
	private Node root;
	public int count() {
		if(root == null) return 0;
		return count(root);
	}
	
	private void count(Node x) {
		if(x == null) return;
		if(x.children == null || x.children.length == 0) {
			sum += x.val;
		}
		for(int i = 0; i < x.children.length; i++) {
			count(x.children[i]);
		}
	}
}
