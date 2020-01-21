
public class DoubleLinkedList {
	private Node head;
	private class Node{
		Node next;
		int data;
		Node prev;
		public Node(int d){
			this.data = d;
		}
	}
	
	/*
	 * Add a node at the front of the list
	 */
	public void push(int data) {
		Node oldhead = head;
		head = new Node(data);
		head.next = oldhead;
		head.prev = null;
		oldhead.prev = head;
	}
	
	/*
	 * Add a node after a given node
	 */
	public void insertAfter(Node prevNode, int data) {
		if(prevNode == null) {
			System.out.println("The given previous node can't be null");
		}
		//allocate node
		Node newNode = new Node(data);
		prevNode.next.prev = newNode;
		newNode.prev = prevNode;
		newNode.next = prevNode.next;
		prevNode.next = newNode;
	}
	
	/*
	 * Append a node at the end of the list
	 */
	public void append(int data) {
		Node newNode = new Node(data);
	}
	
	
	public Node deleteNode(Node head, int data) {
		if(head == null) {
			return null;
		}
		//remove head
		if(head.data == data) {
			return head.next;
		}
		Node x = head;
		while(x.next != null) {
			if(x.next.data == data) {
				x.next = x.next.next;
				return head;
			}
			x = x.next;
		}
		return head;
	}
}
