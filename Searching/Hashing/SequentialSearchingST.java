import edu.princeton.cs.algs4.IllegalArgumentException;
import edu.princeton.cs.algs4.SequentialSearchST.Node;

public class SequentialSearchingST<Key, Value> {
	private Node first;
	private int n;
	
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }
    
    public SequentialSearchST() {
    
    }
    
    public int size() {
        return n;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public boolean contains(Key key) {
    	if (key == null) throw new IllegalArgumentException("argument to contains() is null");
    	return get(key) != null;
    }
    
    public Value get(Key key) {
    	if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
    	for(Node x = first; x != null; x = x.next ) {
    		if(x.key.equals(key)) {
    			return x.val;
    		}
    	}
    	return null;
    }
    
    public void put(Key key, Value val) {
    	if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 
    	if(val == null) {
    		delete(key);
    		return;
    	}
    	for(Node x = first; x != null; x = x.next ) {
    		if(x.key.equals(key)) {
    			x.val = val;
    			return;
    		}
    	}
    	first = new Node(key, val, first);
    	n++;
    }
    
    public void delete(Key key) {
    	if (key == null) throw new IllegalArgumentException("argument to delete() is null"); 
    	first = delete(first);
    }
    
    public Node delete(Node x, Key key) {
    	if(x == null) return null;
		if(x.key.equals(key)) {
			n--;
			return x.next;
		}
    	x.next = delete(x.next, key);
    	return x;
    }
    
    public Iterable<Key> keys() {
    	Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
    }
}