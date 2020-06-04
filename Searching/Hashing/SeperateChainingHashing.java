import edu.princeton.cs.algs4.IllegalArgumentException;

public class SeperateChainingHashingST<Key, Value> {
	private static final int INIT_CAPACITY = 4;
	private int n;
	private int m;
	private SequentialSearchST<Key, Value>[] st;
	
	public SeperateChainingHashingST(int m) {
		this.m = m;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++) {
			st[i] = new SequentialSearchST<Key, Value>();
		}
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
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
    	int i = hash(key);
    	return st[i].get(key);
    }
    
    public Iterable<Key> keys() {
    	Queue<Key> queue = new Queue<Key>();
    	for(int i = 0; i < m; i++) {
    		for(Key key: st[i].keys) {
    			queue.enqueue(key);
    		}
    	}
    	return queue;
    }
    
    public void put(Key key, Value val) {
    	if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        
        // double table size if average length of list >= 10
        if (n >= 10*m) resize(2*m);
        
        int i = hash(key);
        if(!st[i].contains(key)) n++;
        st[i].put(key, val);
    }
    
    private void resize(int i) {
    	SequentialSearchST<Key, Value> temp = new SequentialSearchST<Key, Value>(i);
    	for(int i = 0; i < m; i++) {
    		for(Key key: st[i]) {
    			temp.put(key, st[i].get(key));
    		}
    	}
    	this.m = temp.m;
    	this.n = temp.n;
    	this.st = temp.st;
    }
    
    public void delete(Key key) {
    	if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);
        
        // halve table size if average length of list <= 2
        if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
    }
    
    
}