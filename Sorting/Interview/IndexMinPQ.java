package Interview;

/******************************************************************************
 *  Compilation:  javac IndexMinPQ.java
 *  Execution:    java IndexMinPQ
 *  Dependencies: StdOut.java
 *
 *  Minimum-oriented indexed PQ implementation using a binary heap.
 *
 ******************************************************************************/


import java.util.Iterator;
import java.util.NoSuchElementException;

//import edu.princeton.cs.algs4.StdOut;

/**
 *  The {@code IndexMinPQ} class represents an indexed priority queue of generic keys.
 *  It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
 *  operations, along with <em>delete</em> and <em>change-the-key</em> 
 *  methods. In order to let the client refer to keys on the priority queue,
 *  an integer between {@code 0} and {@code maxN - 1}
 *  is associated with each key—the client uses this integer to specify
 *  which key to delete or change.
 *  It also supports methods for peeking at the minimum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  <p>
 *  This implementation uses a binary heap along with an array to associate
 *  keys with integers in the given range.
 *  The <em>insert</em>, <em>delete-the-minimum</em>, <em>delete</em>,
 *  <em>change-key</em>, <em>decrease-key</em>, and <em>increase-key</em>
 *  operations take logarithmic time.
 *  The <em>is-empty</em>, <em>size</em>, <em>min-index</em>, <em>min-key</em>,
 *  <em>contains</em>, and <em>key-of</em> operations take constant time.
 *  Construction takes time proportional to the specified capacity.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Key> the generic type of key on this priority queue
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i

    /**
     * Initializes an empty indexed priority queue with indices between {@code 0}
     * and {@code maxN - 1}.
     * @param  maxN the keys on this priority queue are index from {@code 0}
     *         {@code maxN - 1}
     * @throws IllegalArgumentException if {@code maxN < 0}
     */
    @SuppressWarnings("unchecked")
	public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];                   // make this of length maxN??
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Is {@code i} an index on this priority queue?
     *
     * @param  i an index
     * @return {@code true} if {@code i} is an index on this priority queue;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     */
    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        return qp[i] != -1;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Associates key with index {@code i}.
     *
     * @param  i an index
     * @param  key the key to associate with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if there already is an item associated
     *         with index {@code i}
     */
    public void insert(int i, Key key) {
    	if (i < 0 || i >= maxN) throw new IllegalArgumentException();
    	if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
    	n++;
    	pq[n] = i;
    	qp[i] = n;
    	keys[i] = key;
    	swim(n);
    }

    /**
     * Returns an index associated with a minimum key.
     *
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int minIndex() {
    	if(n == 0) throw new NoSuchElementException("Priority queue is empty!");
    	return pq[1];
    }

    /**
     * Returns a minimum key.
     *
     * @return a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key minKey() {
    	if(n == 0) throw new NoSuchElementException("Priority queue is empty!");
    	return keys[pq[1]];
    }

    /**
     * Removes a minimum key and returns its associated index.
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int delMin() {
    	if(n == 0) throw new NoSuchElementException("Priority queue is empty!");
    	int index = pq[1];
    	exch(1, n);
    	n--;
    	sink(1);
    	assert index == pq[n+1];
    	qp[index] = -1;
    	keys[index] = null;
    	pq[n+1] = -1;
    	return index;
    }

    /**
     * Returns the key associated with index {@code i}.
     *
     * @param  i the index of the key to return
     * @return the key associated with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public Key keyOf(int i) {
    	if(!contains(i)) throw new NoSuchElementException("index " + i + "is not in the queue");
    	else return keys[i];
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to change
     * @param  key change the key associated with index {@code i} to this key 
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void changeKey(int i, Key key) throws Exception {
    	validate(i);
    	if(!contains(i)) throw new NoSuchElementException("No key is associated with index");
    	keys[i] = key;
    	swim(qp[i]);
    	sink(qp[i]);
    }


    /**
     * Decrease the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to decrease
     * @param  key decrease the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key >= keyOf(i)}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void decreaseKey(int i, Key key) throws Exception {
    	validate(i);
    	if(key.compareTo(keyOf(i)) >= 0) throw new IllegalArgumentException("key is larger than the original key");
    	keys[i] = key;
    	swim(qp[i]);
    }
    	
    /**
     * Increase the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to increase
     * @param  key increase the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key <= keyOf(i)}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void increaseKey(int i, Key key) throws Exception {
    	validate(i);
    	if(key.compareTo(keyOf(i)) < 0) throw new IllegalArgumentException("The original key is larger"); 
    	if(key.compareTo(keyOf(i)) == 0) throw new IllegalArgumentException("The key is equal to the original key ");
    	keys[i] = key;
    	sink(qp[i]);
    }

    /**
     * Remove the key associated with index {@code i}.
     *
     * @param  i the index of the key to remove
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void delete(int i) throws Exception {
    	validate(i);
    	if(!contains(i)) throw new NoSuchElementException("The index is not in the queue");
    	int index = qp[i];
    	exch(index, n--);
    	swim(index);
    	sink(index);
//    	pq[n+1] = -1;
    	keys[i] = null;
    	qp[i] = -1;
    }


   /***************************************************************************
    * General helper functions.
    ***************************************************************************/
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
    	int temp = pq[i];
    	pq[i] = pq[j];
    	pq[j] = temp;
    	qp[pq[j]] = j;
    	qp[pq[i]] = i;
    }
    
    private void validate(int i) throws Exception {
    	if(i < 0) throw new IllegalArgumentException("index is negative: " + i);
    	if(i >= maxN) throw new IllegalArgumentException("index " + i + "is greater than the capacity");
    }


   /***************************************************************************
    * Heap helper functions.
    ***************************************************************************/
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


   /***************************************************************************
    * Iterators.
    ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
    	private IndexMinPQ<Key> copy;
    	
    	public HeapIterator() {
    		copy = new IndexMinPQ<Key>(pq.length - 1);
    		for(int i = 1; i <= n; i++) {
    			copy.insert(pq[i], keys[pq[i]]);
    		}
    	}
    	
    	public boolean hasNext() {return !copy.isEmpty();}
    	public void remove() {throw new UnsupportedOperationException();}
    	public Integer next() {
    		if(!hasNext()) throw new NoSuchElementException();
    		int index = copy.delMin();
    		return index;
    	}
    }
    
    public static void main(String[] args) {
    	String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };
    	
    	IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
    	for(int i = 0 ; i < strings.length; i++) {
    		pq.insert(i, strings[i]);
    	}
    	
    	//delete and print each key
    	while(!pq.isEmpty()) {
    		int index = pq.delMin();
    		System.out.println(index + " " + strings[index]);
    	}
    	System.out.println();
    	
    	//reinsert
    	for(int i = 0 ; i < strings.length; i++) {
    		pq.insert(i, strings[i]);
    	}
    	
    	for(int i: pq) {
    		System.out.println(i + " " + strings[i]);
    	}
    	while(!pq.isEmpty()) {
    		pq.delMin();
    	}
    }
    

}



