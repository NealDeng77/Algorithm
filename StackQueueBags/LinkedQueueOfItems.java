import java.util.NoSuchElementException;

public class LinkedQueueOfItems<Item> {
	private Node head, tail;
	private class Node {
		Item str;
		Node next;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void enqueue(Item item) {
		Node oldLast = tail;
		tail = new Node();
		tail.str = item;
		tail.next = null;
		//special cases for empty queue
		if(isEmpty()) {
			head = tail;
		}else {
			oldLast.next = tail;
		}
	}
	
	public Item dequeue() {
		if(head == null) {
			throw new NoSuchElementException();
		}
		Item item = head.str;
		head = head.next;
		if(isEmpty()) {
			tail = null;
		}
		return item;
	}
	
	public Item peek() {
		if(head == null) {
			throw new NoSuchElementException();
		}
		return head.str;
	}
}
