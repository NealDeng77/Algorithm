public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
	private Node root;
	private class Node {
		Key key;
		Value val;
		Node left;
		Node right;
		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}
	
	public Value get(Key key) {
		Node x = root;
		while(x != null) {
			int cmp = key.compareTo(x.val);
			if(cmp < 0) {
				x = x.left;
			} else if(cmp > 0) {
				x = x.right;
			} else {
				break;
			}
		}
		return x.val;
	}
	
	public void put(Key key, Value val) {
		if(root == null) {
			root = new Node(key, val);
			return;
		}
		Node x = root;
		Node parent = null;
		while(x != null) {
			parent = x;
			int cmp = key.compareTo(x.key);
			if(cmp < 0) {
				x = x.left;
			} else if(cmp > 0) {
				x = x.right;
			} else {
				break;
			}
		}
		if(x == null) {
			int cmp = key.compareTo(parent.key);
			x = new Node(key, val);
			if(cmp < 0) parent.left = x;
			else parent.right = x;
		}
		else {
			x.val = val;
		}
	}
	
	//in order traversal
	public Iterable<Key> keys() {
		Stack<Node> stack = new Stack<Node>();
		Queue<Key> queue = new Queue<Key>();
		Node x = root;
		while(x != null || !stack.isEmpty()) {
			if(x != null) {
				stack.push(x);
				x = x.left;
			} else {
				x = stack.pop();
				queue.enqueue(x.key);
				x = x.right;
			}
		}
		return queue;
	}
	
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		int n = a.length;
		NonrecursiveBST<String, Integer> st = new NonrecursiveBST<String, Integer>();
		for(int i = 0; i < n;i++) {
			st.put(a[i], i);
		}
		for(String s: st.keys()) {
			StdOut.println(s + " " + st.get(s));
		}
	}
	
}