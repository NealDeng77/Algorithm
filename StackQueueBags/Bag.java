import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * This class represents a bag of generic items. It supports insertion and iterating over items in arbitrary order
 * Uses a singly linked list with a static nested class Node.
 */
public class Bag<Item> implements Iterable<Item> {
	private Node<Item> first;     //head of the bag
	private int n;                //number of items in the bag
	
	//Node in the linked list
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	
	//Constructor
	public Bag() {
		first = null;
		n = 0;
	}
	
	/*
	 * Returns true if the bag is empty
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/*
	 * Returns the number of items in the bag
	 */
	public int size() {
		return n;
	}
	
	/*
	 * Add new item to the bag
	 * @param newItem the item to add to this bag
	 */
	public void add(Item newItem) {
		Node<Item> oldHead = first;
		first = new Node<Item>();
		first.item = newItem;
		first.next = oldHead;
		n++;
	}
	
	/*
	 * Return an iterator that iterates over the items in this bag in arbitrary order
	 */
	public Iterator<Item> iterator() {
		return new ListIterator(first);
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	
	/*
	 * Unit tests, read from command-line arguments
	 */
	public static void main(String[] args) {
		Bag<String> bag = new Bag<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			bag.add(item);
		}
		
		StdOut.println("size of bag = " + bag.size());
		for(String s: bag) {
			StdOut.println(s);
		}
	}
	
}
