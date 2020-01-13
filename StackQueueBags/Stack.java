//linked-list implementation of generic stack
//implement iterator
import java.util.Iterator;
public class Stack<Item> implements Iterable<Item>{
	private Node head = null;
	private int n = 0;
	private class Node {
		Item str;
		Node next;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void push(Item strtemp) {
		Node oldNode = head;
		head = new Node();
		head.str = strtemp;
		head.next = oldNode;
		n++;
	}
	
	public Item pop() {
		Item popString = head.str;
		head = head.next;
		n--;
		return popString;
	}
	
	public int size() {
		return n;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = head;
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			//Not supported
		}
		
		public Item next() {
			Item item = current.str;
			current = current.next;
			return item;
		}
	}
}
