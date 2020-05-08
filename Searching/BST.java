import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	
	private class Node {
		Key key;
		Value value;
		Node left;
		Node right;
		int count;
		
		public Node(Key key, Value val) {
			this.key = key;
			this.value = val;
			count = 1;
		}
	}
	
	public int size() {
		
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node node, Key key) {
		if(node == null) return null;
		Key currentKey = node.key;
		if(currentKey.compareTo(key) < 0) {
			return get(node.right, key);
		} else if(currentKey.compareTo(key) > 0) {
			return get(node.left, key);
		} else {
			return node.value;
		}
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
	public Node put(Node node, Key key, Value val) {
		if(node == null) {
			return new Node(key, val);
		}
		int cmp = key.compareTo(node.key);
		if(cmp  < 0) {
			node.left =  put(node.left, key, val);
		}
		else if(cmp  > 0) {
			node.right =  put(node.right, key, val);
		}
		else {
			node.value = val;
		}
		node.count = node.left.count + node.right.count + 1;
		return node;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public Key min() {
		if(isEmpty()) throw new NoSuchElementException();
		return min(root).key;
	}
	
	private Node min(Node node) {
		if(node.left != null) {
			return min(node.left);
		} else {
			return node;
		}
	}
	
	public Key floor(Key key) {
		if(isEmpty()) throw new NoSuchElementException();
		
	}
	
	public Key ceiling(Key key) {
		
	}
	
	public Key select(int k) {
		
	}
	
	public int rank(Key key) {
		
	}
}
