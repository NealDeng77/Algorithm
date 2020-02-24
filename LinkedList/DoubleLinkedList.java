/*
 * Reference: https://www.geeksforgeeks.org/doubly-linked-list/
 */
public class DoubleLinkedList {
	private Node head;   //head of the list
	private class Node{
		Node next;
		int value;
		Node prev;
		
		//Constructor to create a new node
		//next and prev is initialized as null by default
		public Node(int d) {
			this.value = d;
		}
	}
	
	/*
	 * Insert at the front of the list
	 * @param data data of the new Node
	 */
	public void insertFront(int data) {
		if(head != null) {
			Node oldHead = head;
			Node head = new Node(data);
			head.next = oldHead;
			oldHead.prev = head;
		}else {
			head = new Node(data);
		}
	}
	
	/*
	 * Insert after a node
	 * @param data data of the new Node
	 * @param prevNode the Node to insert after
	 * @throw Exception when insert after a null node
	 */
	public void insertAfter(int data, Node prevNode) throws Exception{
		if(prevNode == null) {
			throw new Exception("Can't insert after a null node");
		}
		Node insertNode = new Node(data);
		insertNode.next = prevNode.next;
		if(prevNode.next != null) {
			prevNode.next.prev = insertNode;
		}
		prevNode.next = insertNode;
		insertNode.prev = prevNode;
		
	}
	
	/*
	 * Insert at the end of the list
	 * @param data data of the new Node
	 */
	public void insertEnd(int data) {
		if(head == null) {
			head = new Node(data);
		}else {
			Node tail = head;
			while(tail.next != null) {
				tail = tail.next;
			}
			Node oldTail = tail;
			tail = new Node(data);
			tail.prev = oldTail;
			oldTail.next = tail;		
		}
	}
	
	/*
	 * Insert before a node
	 * @param data data of the new Node
	 * @param nextNode the node to insert before
	 */
	public void insertBefore(int data, Node nextNode) throws Exception {
		if(nextNode == null) {
			throw new Exception("next node can't be null");
		}
		Node newNode = new Node(data);
		newNode.prev = nextNode.prev;
		nextNode.prev = newNode;
		newNode.next = nextNode;
		if(newNode.prev != null) {
			//if newNode prev is not null, change its next point 
			newNode.prev.next = newNode;
		}else {
			//newNode is the head
			head = newNode;
		}
	}
	
	public Node deleteNode(Node head, int value) {
		if(head == null) {
			return null;
		}
		//remove head
		if(head.value == value) {
			return head.next;
		}
		Node x = head;
		while(x.next != null) {
			if(x.next.value == value) {
				x.next = x.next.next;
				return head;
			}
			x = x.next;
		}
		return head;
	}
}
