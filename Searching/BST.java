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
		return size(root);
	}
	
	private int size(Node node) {
		if(node == null) return 0;
		return node.count;
	}
	
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
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
	
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    } 

    private Node max(Node x) {
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 

	
	public Key floor(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to floor() is null");
		if(isEmpty()) throw new NoSuchElementException("tree is empty");
		Node returnedNode = floor(root, key);
		if(returnedNode == null) throw new NoSuchElementException("argument to floor() is too small");
		return returnedNode.key;
	}
	
	private Node floor(Node node, Key key) {
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		Node tempnode;
		if(cmp < 0) {
			return  floor(node.left, key);
		} else if(cmp > 0) {
			tempnode = floor(node.right, key);
			if(tempnode != null) {
				return tempnode;
			} 
			//there is no right link to the current node
			else {
				return node;
			}
		} 
		//there is a node in the tree which key is equal to the floor key
		//cmp == 0
		else {
			return node;
		}
	}
	
	/*
	 * similar idea as flooring
	 */
	public Key ceiling(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
		if(isEmpty()) throw new NoSuchElementException("tree is empty");
		Node returnedNode = ceiling(root, key);
		if(returnedNode == null) throw new NoSuchElementException("argument to ceiling() is too large");
		return returnedNode.key;
	}
	
	private Node ceiling(Node node, Key key) {
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		Node tempnode;
		if(cmp < 0) {
			tempnode = ceiling(node.left, key);
			if(tempnode != null) {
				return tempnode;
			} else {
				return node;
			}
		} else if(cmp > 0) {
			return ceiling(node.right, key);
		} 
		else {
			return node;
		}
	}
	
	
	//Select k means find the key of rank k such that precisely k other keys in the BST are smaller 
	public Key select(int k) {
		if(k < 0 || k >= root.count) throw new IllegalArgumentException("can't select() with argument less than 0 "
				+ "or >= n");
		return select(root, k).key;
	}
	
	private Node select(Node node, int k) {
		if(node == null) {
			return null;
		}
		int leftSize = size(node.left);
		if(k < leftSize) {
			return select(node.left, k);
		} 
		else if(k > leftSize) {
			return select(node.right, k - leftSize - 1);
		}
		else {
			return node;
		}
	}
	
	
	//return keys in the tree that are less than this key 
	public int rank(Key key) {
		return rank(root, key);
	}
	
	private int rank(Node node, Key key) {
		if(node == null) return 0;
		int cmp = key.compareTo(node.key);
		if(cmp == 0) {
			return size(node.left);
		} else if(cmp < 0) {
			return rank(node.left, key);
		}
		else {
			return rank(node.right, key) + size(node.left) + 1;
		}
	}
	
	//need test
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if(node.left == null) return node.right;
		else {
			node.left = deleteMin(node.left);
			node.count = size(node.left) + size(node.right) + 1;
			return node;
		}
	}
	
	
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node node) {
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		if(node.right == null) return node.left;
		else {
			node.right = deleteMax(node.right);
			node.count = size(node.left) + size(node.right) + 1;
			return node;
		}
	}
	
	public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
	}
	
	private Node delete(Node node, Key key) {
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp < 0) {
			node.left = delete(node.left, key);
		} else if(cmp > 0) {
			node.right = delete(node.right, key);
		} else {
			if(node.left == null) return node.right;
			if(node.right == null) return node.left;
			//if node has left and right child
			Node x = node;
			node = min(x.right);
			node.right = deleteMin(x.right);
			node.left = x.left;
		}
		node.count = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	
	private Iterable<Key> keys(Node node, Queue<Key> queue, Key lo, Key hi) {
		if(x == null) return;
		int cmp = node.key.compareTo(lo);
		int cmp2 = node.key.compareTo(hi);
		if(cmp < 0) keys(node.right, queue, lo, hi);
		else if(cmp2 > 0) keys(node.left, queue, lo, hi);
		else if(cmp >= 0 && cmp2 <= 0) queue.enqueue(node.key);
	}
	
	/*
	 * Return the number of keys in the given range
	 */
	public int size(Key lo, Key hi) {
		if(lo.compareTo(hi) > 0) return 0;
		if(contains(hi)) return rank(hi) - rank(lo) + 1;
		else {
			return rank(hi) - rank(lo);
		}
	}
	
	public int height() {
		return height(root);
	}
	
	private int height(Node x) {
		if(x == null) return -1;
		return 1 + Math.max(height(x.left), (height(x.right));
	}

	public Iterable<Key> levelOrder() {
		Queue<Key> keys = new Queue<Key>;
		Queue<Node> queue = new Queue<Node>;
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			Node x = queue.dequeue();
			if(x == null) continue;
			keys.enqueue(x.key);
			queue.enqueue(x.left);
			queue.enqueue(x.right);
		}
		return keys;
	}
	
	private boolean isBST() {
		return isBST(root, null, null);
	}
	
	private boolean isBST(Node node, Key min, Key max) {
		if(min != null && node.key.compareTo(min) < 0) return false;
		if(max != null && node.key.compareTo(max) > 0) return false;
		return isBST(node, min, node.key) && isBST(node, node.key, max);
	}
	
	private boolean isSizeConsistent() {return isSizeConsistent(root);}
	private boolean isSizeConsistent(Node node) {
		if(node == null) return true;
		if(node.size != size(node.left) + size(node.right) + 1) return false;
		return isSizeConsistent(node.left) && isSizeConsistent(node.right)
	}
	
	private boolean isRankConsistent() {
		for(int i = 0 ; i < size(); i++) {
			if( i != rank(select(i))) return false;
		}
		
		
	}
}

