public class LinkedQueueOfStrings {
	private Node head, tail;
	private class Node {
		String str;
		Node next;
	}
	
	private boolean isEmpty() {
		return head == null;
	}
	
	public void enqueue(String item) {
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
	
	public String dequeue() {
		String item = head.str;
		head = head.next;
		if(isEmpty()) {
			tail = null;
		}
		return item;
	}
}
