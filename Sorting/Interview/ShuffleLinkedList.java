package Interview;

import edu.princeton.cs.algs4.StdRandom;

//reference: https://massivealgorithms.blogspot.com/2019/03/shuffling-linked-list.html
//https://web.stanford.edu/class/cs9/sample_probs/ListShuffling.pdf
public class ShuffleLinkedList {
	private class Node {
		int value;
		Node next;
	}
	
	private Node sort(Node head) {
		if(head == null || head.next == null) return head;
		Node slow = head;
		Node fast = head;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		Node left = head;
		Node right = slow.next;
		slow.next = null;
		left = sort(left);
		right = sort(right);
		return merge(left, right);
	}
	
	private Node merge(Node left, Node right) {
		Node aux = new Node();  //auxiliary list
		Node l = left;
		Node r = right;
		Node current = aux;
		while(l != null && r != null) {
			int rand = StdRandom.uniform(2);
			if(rand == 0) {
				current.next = l;
				current = current.next;
				l = l.next;
			}
			else {
				current.next = r;
				current = current.next;
				r = r.next;
			}
		}
		if(l != null) {
			current.next = l;
		}
		else if(r != null) {
			current.next = r;
		}
		return aux.next;
	}
}  
