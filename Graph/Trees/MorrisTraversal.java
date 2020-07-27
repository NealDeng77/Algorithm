//Reference: https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/MorrisTraversal.java
//https://www.youtube.com/watch?v=wGXB9OWhPTg&t=1s
public class MorrisTraversal {
	public void MorrisInorderTraverse(Node root) {
		Node current = root;
		while(current != null) {
			//there is no left part
			if(current.left == null) {
				System.out.println(current.val);
				current = current.right;
			}
			else {
				Node predecessor = current.left;
				//find predecessor
				while(predecessor.right != null && predecessor.right != current) {
					predecessor = predecessor.right;
				}
				//add a link from predecessor back to current
				//then go left
				if(predecessor.right == null) {
					predecessor.right = current;
					current = current.left;
				} 
				//second visited, meaning the left part has been visited
				else {
					predecessor.right = null;
					System.out.println(current.val);
					current = current.right;
				}
			}
		}
	}
	
	public void MorrisPreOrderTraverse(Node root) {
		Node current = root;
		while(current != null) {
			//there is no left part
			if(current.left == null) {
				System.out.println(current.val);
				current = current.right;
			}
			else {
				Node predecessor = current.left;
				//find predecessor
				while(predecessor.right != null && predecessor.right != current) {
					predecessor = predecessor.right;
				}
				//add a link from predecessor back to current
				//then go left
				if(predecessor.right == null) {
					predecessor.right = current;
					current = current.left;
					System.out.println(current.val);
				} 
				//second visited, meaning the left part has been visited
				else {
					predecessor.right = null;
					current = current.right;
				}
			}
		}
	}
}
