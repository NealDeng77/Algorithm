import edu.princeton.cs.algs4.IllegalArgumentException;

public class TrieST<Value> {
	private static final int R = 256;
	private Node root;
	private int n;             // number of keys in the trie
	
	private class Node {
		private Object val;
		private Node[] next = new Node[R];
	}
	
	public TrieST() {
	}
	
	public boolean contains(String key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}
	
	public Value get(String key) {
		Node x = get(root, key, 0);
		if(x == null) return null;
		return (Value) x.val;
	}
 	
	public Node get(Node x, String key, int d) {
		if (x == null) return null;
		if(d = key.length()) return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d+1);
	}
	
	public void put(String key, Value val) {
		if(key == null) throw new IllegalArgumentException("first arugument to put() is null"); 
		if(val == null) delete(key);
		else root = put(root, key ,val, 0);
	}
	
	private Node put(Node x, String key, Value val, int d) {
		if(x == null) x = new Node();
		if(d == key.length()) {
			if(x.val == null) n++;
			x.val = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}
	
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}
	
	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> results = new Queue<String>();
		Node x = get(root, prefix, 0);
		
	}
	
	private void collect(Node x, StringBuilder prefix, Queue<String> results) {
		if(x == null) return;
		if(x.val != null) results.enqueue(prefix.toString());
		for(char c = 0; c < R; c++) {
			prefix.append(c);
			collect(x.next[c], prefix, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
	
	public void delete(String key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		root = delete(root, key, 0);
	}
	
	private Node delete(Node x, String key, int d) {
		if(x == null) return null;
		if(d == key.length()) {
			if(x.val != null) n--;
			x.val = null;
		}
		else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d+1);
		}
		
		if(x.val != null) return x;
		for(int c = 0; c < R; c++) {
			if(x.next[c] != null) {
				return x;
			}
		}
		return null;
	}
	
	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> results = new Queue<String>();
		collect(root, new StringBuilder(), pattern, results);
		return results;
	}
	
	private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
		if(x == null) return;
		int d = prefix.length();
		if(d == pattern.length() && x.val != null) {
			results.enqueue(prefix.toString());
		}
		if(d == pattern.length())
			return;
		char c = pattern.charAt(d);
		if(c == '.') {
			for(char ch = 0; ch < R; ch++) {
				prefix.append(ch);
				collect(x.next[ch], prefix, pattern, results);
				//why deletecharat?
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}
		else {
			prefix.append(c);
			collect(x.next[c], prefix, pattern, results);
			//why deletecharat?
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
	
	public String longestPrefixOf(String query) {
		if (query == null) throw new IllegalArgumentException("argument to longestPrefixOf() is null");
		int length = longestPrefixOf(root, query, 0, -1);
		if(length == -1) return null;
		else return query.substring(0, length);
	}
	
	private int longestPrefixOf(Node x, String query, int d, int length) {
		if(x == null) return length;
		if(x.val != null) length = d;
		if(d == query.length()) return length;
		char c = query.charAt(d);
		return longestPrefixOf(x.next[c], query, d+1, length);
	}
	
	
}
