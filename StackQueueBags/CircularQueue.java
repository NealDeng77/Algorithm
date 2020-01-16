import java.util.Iterator;
import java.util.NoSuchElementException;


/*
 * Implement a resizing and circular array
 * Enqueue to the tail, dequeue from the head
 * Double the array, when it's full
 * Reference: https://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/
 * https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/ResizingArrayQueue.java.html
 */
public class CircularQueue<Item> implements Iterable<Item>{
	private Item[] arr;     //array to store elements
	private int size;       //number of elements
	private int head, tail;   //head, tail of the queue
	
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item>{
		private int i = 0;
		
		public boolean hasNext() {
			return i < size;
		}
		
		public void remove() {
			//not supported
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item = arr[(i + head) % arr.length];
			i++;
			return item;
		}
	}
	
	public CircularQueue() {
		arr = (Item[])new Object[2];
		size = 0;
		head = 0;
		tail = 0;
	}
	
	/*
	 * Add item to a queue
	 * @param item item to add
	 * @throws Exception if the queue is full
	 */
	public void enqueue(Item item) throws Exception {
		if(size == arr.length) {
			resize(2 * arr.length);
		}
		if(isFull()) {
			throw new Exception("Queue is full");
		}

		arr[tail] = item;
//		tail = (tail + 1) % arr.length;
		tail++;
		if(tail == arr.length) tail = 0;
		size++;
	}
	
	/*
	 * delete item
	 * @throws Exception if the queue is empty
	 */
	public Item dequeue() throws Exception {
		if(isEmpty()) {
			throw new Exception("Queue is Empty");
		}
		Item item = arr[head];
		arr[head] = null;
//		head = (head + 1) % arr.length;
		head++;
		if(head == arr.length) head = 0;
		size--;
		if(size > 0 && size == arr.length / 4) {
			resize(arr.length / 2);
		}
		return item;
	}
	
	/*
	 * peek the tail
	 * @throws Exception if the queue is empty
	 */
	public Item tail() throws Exception {
		if(isEmpty()) {
			throw new Exception("Queue is Empty");
		}
		Item item = arr[tail];
		System.out.println("tail index: " + tail);
		return item;
	}
	
	/*
	 * peek the head
	 * @throws Exception if the queue is empty
	 */
	public Item head() throws Exception {
		if(isEmpty()) {
			throw new Exception("Queue is Empty");
		}
		System.out.println("head index: " + head);
		Item item = arr[head];
		return item;
	}
	
	/*
	 * Check if the queue is full
	 */
	public boolean isFull() {
		return arr.length == size;
	}
	
	/*
	 * Check if the queue is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * return number of elements in the queue
	 */
	public int size() {
		return size;
	}
	
	/*
	 * Resize the array
	 */
	private void resize(int capacity) {
		assert capacity >= size;
		Item[] newarr = (Item[]) new Object[capacity];
		//copy elements
		for(int i = 0; i < size; i++) {
			newarr[i] = arr[(head + i) % arr.length]; 
		}
		arr = newarr;
		head = 0;
		tail = size;
	}
	
	public static void main(String[] args) throws Exception {
//		CircularQueue<Integer> queue = new CircularQueue<Integer>(4);
//		queue.enqueue(10);
//		queue.enqueue(20);
//		queue.enqueue(30);
//		queue.enqueue(40);
//		System.out.println(queue.dequeue() + " dequeued from queue");
//		System.out.println(queue.dequeue() + " dequeued from queue");
//		System.out.println(queue.dequeue() + " dequeued from queue");
//		System.out.println("head is " + queue.head());
//		System.out.println("tail is " + queue.tail());
//		queue.enqueue(50);
//		queue.enqueue(60);
//		System.out.println("head is " + queue.head());
//		System.out.println("tail is " + queue.tail());
//		for(Integer each:queue) {
//			StdOut.print(each + " ");
//		}
		CircularQueue<String> queue = new CircularQueue<String>();
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if(!item.equals("-")) queue.enqueue(item);
			else if(!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
		}
		StdOut.println("(" + queue.size() + " left on queue)");
	}
}
