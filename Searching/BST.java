<<<<<<< HEAD
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
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if(node.left == null && node.right == null) {
			return null;
		}
		else if(node.left == null && node.right != null) {
			return node.right;
		} else {
			node.left = deleteMin(node.left);
			node.count = size(node.left) + size(node.right) + 1;
			return node;
		}
	}
	
	
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node node) {
		if(node == null) return null;
		else if(node.left != null && node.right == null) {
			return node.left;
		}
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
			node.count = size(node.left) + size(node.right) + 1;
		} else if(cmp > 0) {
			node.right = delete(node.right, key);
			node.count = size(node.left) + size(node.right) + 1;
		} else {
			if(node.left == null && node.right != null) {
				return node.right;
			}
			//find the largest key in the left subtree
			else {
				Key replacedKey = findLargestKeyInTheLeftSubTree(node.left);
				node.key = replacedKey;
				node.left = deleteMax(node.left);
				node.count = size(node.left) + size(node.right) + 1;
			}
			
		}
		return node;
	}
	
	private Key findLargestKeyInTheLeftSubTree(Node node) {
		
		while(node.right != null) {
			node = node.right;
		}
		return node.key;
	}
	
	
}
=======
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
>>>>>>> branch 'Desktop' of https://github.com/NealDeng77/Algorithm.git
