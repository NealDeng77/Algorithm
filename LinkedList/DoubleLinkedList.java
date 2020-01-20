
public class DoubleLinkedList {
	private Node head;
	private class Node{
		Node next;
		int value;
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
