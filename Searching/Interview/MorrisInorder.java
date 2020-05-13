//Inorder traversal using no stack and nonrecursion, with constant memory
//Reference: https://www.youtube.com/watch?v=wGXB9OWhPTg
//Time: O(n), because we have to traverse n nodes
//Space: O(1) because we only use a node current
public MorrisInorder{
	public static void inorder(Node root) {
		Node current = root;
		while(current != null) {
			//left is null then print the current and go right
			if(current.left == null) {
				System.out.println(current.data+" ");
				current = current.right;
			}
			else {
				//find the predecessor
				Node predecessor = current.left;
				while(predecessor.right != null && predecessor.right != current) {
					predecessor = predecessor.right;
				}
				//if right node is null then go left after setablishing link from predecessor to current
				if(predecessor.right == null) {
					predecessor.right = current;
					current = current.left;
				}
				//finish visiting left subtree
				else(predecessor.right == current) {
					predecessor.right = null;
					System.out.println(current.data+" ");
					current = current.right;
				}
			}
		}
	}
	
	public static void preorder(Node root) {
		Node current = root;
		while(current != null) {
			//left is null then print the current and go right
			if(current.left == null) {
				System.out.println(current.data+" ");
				current = current.right;
			}
			else {
				//find the predecessor
				Node predecessor = current.left;
				while(predecessor.right != null && predecessor.right != current) {
					predecessor = predecessor.right;
				}
				//if right node is null then go left after setablishing link from predecessor to current
				if(predecessor.right == null) {
					predecessor.right = current;
					System.out.println(current.data+" ");
					current = current.left;
				}
				//finish visiting left subtree
				else(predecessor.right == current) {
					predecessor.right = null;
					current = current.right;
				}
			}
		}
	}
}