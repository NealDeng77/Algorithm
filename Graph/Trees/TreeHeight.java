//Reference: https://www.udemy.com/course/graph-theory-algorithms/learn/lecture/18603254#announcements
//suppose it's a binary tree
public class TreeHeight {
	private class Node {
		Node left;
		Node right;
	}
	private Node root;
	public int height(Node x) {
		//handle empty tree case
		if(x == null) return -1;
		if(x.left == null && x.right == null) {
			return 0;
		}
		
		return Math.max(height(x.left), height(x.right)) + 1;
	}
}
