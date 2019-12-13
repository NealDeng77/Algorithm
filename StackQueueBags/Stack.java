//generic 
public class Stack<Item> {
	private Node head = null;
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
	}
	
	public Item pop() {
		Item popString = head.str;
		head = head.next;
		return popString;
	}
}
