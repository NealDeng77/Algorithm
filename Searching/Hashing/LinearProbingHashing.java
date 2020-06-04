import edu.princeton.cs.algs4.IllegalArgumentException;
import edu.princeton.cs.algs4.Iterable;
import edu.princeton.cs.algs4.Queue;

public class LinearProbingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;
	private int n;
	private int m;
	private Key[] keys;
	private Value[] vals;
	
	public LinearProblingHashST(int capacity) {
		m = capacity;
		n = 0;
		keys = (Key[]) new Object[m];
		vals = (Value[]) new Object[m];
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
    
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }
    
    public Value get(Key key) {
    	if (key == null) throw new IllegalArgumentException("argument to get() is null");
    	for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
    		if(keys[i].equals(key)) {
    			return vals[i];
    		}
    	}
    	return null;
    }
    
    private void resize(int capacity) {
    	LinearProblingHashST<Key, Value> temp = new LinearProblingHashST<Key, Value>(capacity);
    	for(int i = 0; i < m; i++) {
    		if(keys[i] != null) {
    			temp.put(keys[i], values[i]);
    		}
    	}
    	keys = temp.keys;
    	values = temp.values;
    	m = temp.m;
    }
    
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }
        
        // double table size if 50% full
        if (n >= m/2) resize(2*m);
        
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] =val;
        n++;
    }
    
    public void delete(Key key) {
    	if (key == null) throw new IllegalArgumentException("argument to delete() is null");
    	if(!contains(key)) return;
    	
    	//find position of the key
    	int i = hash(key);
    	while(!keys[i].equals(key)) {
    		i = (i + 1) % m;
    	}
    	
    	keys[i] = null;
    	vals[i] = null;
    	
    	// rehash all keys in same cluster
    	i = (i + 1) % m;
    	while(keys[i] != null) {
    		Key keyToRehash = keys[i];
    		Value valToRehash = vals[i];
    		keys[i] = null;
    		vals[i] = null;
    		n--;
    		put(keyToRehash, valToRehash);
    		i = (i + 1) % m; 
    	}
    	n--;
    	
        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m/8) resize(m/2);
    	
    }
    
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }
    
    
    
}