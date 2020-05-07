import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] values;
	private int n = 0;
	private final int INIT_CAPACITY = 2;
	
	public BinarySearchST<Key, Value> () {
		
	}
	
	public BinarySearchST<Key, Value> (int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	
	}
	
	private void resize(int capacity) {
		assert capacity >= n;
		Key[] newkeys = (Key[]) new Comparable[capacity];
		Value[] newvalues = (Value[]) new Object[capacity];
		for(int i = 0; i < n; i++) {
			newkeys[i] = keys[i];
			newvalues[i] = values[i];
		}
		
		keys = newkeys;
		values = newvalues;
	}
	
	public int size() {
		return n;
	}
	
	private int rank(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Argument to rank() is null");
		}
		int lo = 0, hi = n - 1;
		
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0) {
				hi = mid - 1;
			} else if(cmp > 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return lo;
	}
	
	public void put(Key key, Value value) {
		if(key == null) {
			throw new IllegalArgumentException("Argument to put() is null");
		}
		if(value == null) {
			delete(key);
			return;
		}
		
		int rank = rank(key);
		if(rank < n && keys[rank].compareTo(key) == 0) {
			values[rank] = value;
		} else {
			
			for(int i = n ; i > rank; i--) {
				keys[i] = keys[i - 1];
				values[i] = values[i - 1];
			}
			keys[rank] = key;
			values[rank] = value;
			n++;
			if(n == keys.length) resize(2 * n);
		}
	}
	
	private boolean isEmpty() {
		return n == 0;
	}
	
	
	public Value get(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Argument to get() is null");
		}
		if(isEmpty()) return null;
		int rank = rank(key);
		if(rank < n && keys[rank].compareTo(key) == 0) {
			return values[rank];
		}
		else {
			return null;
		}
	}
	
	public void delete(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Argument to delete() is null");
		}
		if(isEmpty()) return;
		
		int rank = rank(key);
		if( rank < n && keys[rank].compareTo(key) == 0) {
			for(int i = rank; i < n - 1 ; i++) {
				keys[i] = keys[i + 1];
				values[i] = values[i + 1];
			}
			keys[n - 1] = null;
			values[n - 1] = null;
			n--;
			if(n > 0 && n == keys.length / 4) {
				resize(n / 2);
			}
		} else {
			//key not in the table
			System.out.println("Key is not in the symbol table");
			return;
		}
	}
	
	public Key min() {
		if(isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return keys[0];
	}
	
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[n-1];
    }
    
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }
    
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }
    
    public Key floor(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Argument to floor() is null");
		}
		if(isEmpty()) return null;
		int rank = rank(key);
		if( rank < n && key.compareTo(keys[rank]) == 0) {
			return keys[rank];
		}
		if( rank == 0) return null;
		else {
			return keys[rank - 1];
		}
    }
    
    public Key ceil(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Argument to ceil() is null");
		}
		if(isEmpty()) return null;
		int rank = rank(key);
		if( rank < n) {
			return keys[rank];
		}
		if( rank == 0) return null;
		return null;
    }
    
    public int size(Key lo, Key hi) {
    	if(lo == null || hi == null) throw new IllegalArgumentException("Argument to size is null");
    	if(lo.compareTo(hi) > 0) return 0;
    	int ranklo = rank(lo);
    	int rankhi = rank(hi);
    	if(contains(hi)) return rankhi - ranklo + 1;
    	else {
    		return rankhi - 1 - ranklo + 1;
    	}
    }
    
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    
    public Iterable<Key> keys() {
    	return keys(min(), max());
    }
    
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");
        
        Queue<Key> queue = new LinkedList<Key>();
        if(lo.compareTo(hi) > 0) return queue;
        for(int i = rank(lo); i < rank(hi); i++) {
        	queue.add(keys[i]);
      
        }
        if(contains(hi)) queue.add(keys[rank(hi)]);
        return queue;
    }
    
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }
    
    
	
}
