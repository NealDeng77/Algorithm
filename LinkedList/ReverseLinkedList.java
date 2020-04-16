
public class ReverseLinkedList {
	private class Node {
		int data;
		Node next;
		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	public Node reverse(Node head) {
		Node prev = null, curr = head, next;
		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		//change the head of the list
		head = prev;
		return head;
	}
}
