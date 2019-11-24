
public class LinkedStackOfStrings {
	private Node head = null;
	private class Node {
		String str;
		Node next;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void push(String strtemp) {
		Node oldNode = head;
		head = new Node();
		head.str = strtemp;
		head.next = oldNode;
	}
	
	public String pop() {
		String popString = head.str;
		head = head.next;
		return popString;
	}
}
