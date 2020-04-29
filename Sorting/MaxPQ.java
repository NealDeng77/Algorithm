import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key>{
	private Key[] pq;
	private int n;
	private Comparator<Key> comparator;      //optional comparator
	
	public MaxPQ(int initCapacity) {
		pq = (Key[])new Object[initCapacity + 1];
		n = 0;
	}
	
	public MaxPQ() {
		this(1);
	}
	
	public MaxPQ(int initCapacity, Comparator<Key> comparator) {
		this(1);
		this.comparator = comparator;
	}
	
	public MaxPQ(Key[] keys) {
		n = keys.length;
		pq = (Key[]) new Object[n + 1];
		for(int i = 1; i <= n; i++) {
			pq[i] = keys[i - 1];
		}
		for(int j = n/2; j >= 1; j--) {
			sink(j);
		}
		assert isMaxHeap();
	}
	
	public void insert(Key key) {
		
		if(n == pq.length - 1) {
			resize(pq.length * 2);
		}
		pq[++n] = key;
		swim(n);
		assert isMaxHeap();
	}
	
	private void swim(int index) {
		while(index > 1 && less(index/2, index)) {
			exchange(index, index / 2);
			index = index / 2;
		}
	}
	
	private void sink(int index) {
		while(index * 2 <= n) {
			int j = index * 2;
			if(j < n && less(j, j + 1)) j++;
			if(!less(index, j)) break;
			exchange(index, j);
			index = j;
		}
	}
	
	private boolean isEmpty() {
		return n == 0;
	}
	
	private Key delMax() {
		if(isEmpty()) throw new NoSuchElementException("Empty priorityQueue");
		Key max = pq[n];
		exchange(1, n--);
		pq[n+1] = null;
		sink(1);
		
		if((n > 0 && (n == (pq.length  - 1) / 4)))  resize(pq.length / 2);
		assert isMaxHeap();
		return max;
	}
	
	private void exchange(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	private boolean less(int i, int j) {
		
		if(comparator == null) {
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) < 0;
		}
	}
	
	private void resize(int len) {
		assert len > n;
		Key[] temp = (Key[])new Object[len];
		for(int i = 0; i < pq.length; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}
	
	private boolean isMaxHeap() {
		for(int i = 1; i <= n; i++) {
			if(pq[i] == null) return false;
		}
		
		for(int i = n + 1; i < pq.length; i++) {
			if(pq[i] != null) return false;
		}
		
		if(pq[0] != null) return false;
		return isMaxHeapOrdered(1);
	}
	
	private boolean isMaxHeapOrdered(int k) {
		//if k > n means the subtrees above it is all max heap ordered
		if(k > n) return true;
		int left = 2 * k;
		int right = 2 * k + 1;
		if(left <= n && less(k, left)) return false;
		if(right <= n && less(k, right)) return false;
		return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
	}
	
	private int size() {
		return n;
	}
	
	   /***************************************************************************
	    * Iterator.
	    ***************************************************************************/

	    /**
	     * Returns an iterator that iterates over the keys on this priority queue
	     * in descending order.
	     * The iterator doesn't implement {@code remove()} since it's optional.
	     *
	     * @return an iterator that iterates over the keys in descending order
	     */
		
	public Iterator<Key> iterator() {
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Key> {
		private MaxPQ<Key> copy;
		
		public HeapIterator() {
			copy = new MaxPQ<Key>(size());
			for(int i = 1; i <= n ; i++) {
				copy.insert(pq[i]);
			}
		}
		
		public boolean hasNext() {
			return !copy.isEmpty();
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Key next() {
			if(!hasNext()) throw new NoSuchElementException();
			return copy.delMax();
		}
	}
}
