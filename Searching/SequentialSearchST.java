import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/*
 * Use linked list to implement symbol table
 */
public class SequentialSearchST<Key, Value> {
	private Node head;
	private int n;
	
	private class Node {
		private Key key;
		private Value value;
		private Node next;
		
		public Node (Key key, Value value, Node node) {
			this.key = key;
			this.value = value;
			this.next = node;
		}
	}
	
	public SequentialSearchST() {
		
	}
	
	
	public void put(Key key, Value value) throws Exception {
		if(key == null) throw new IllegalArgumentException("argument to put() is null");
		//value is null means it's a lazy deletion
		if(value == null) {
			delete(key);
			return;
		}
		
		
		for(Node i = head; i != null; i = i.next) {
			if(i.key.equals(key)) {
				i.value = value;
				return;
			}
		}
		//node not found, add a new node at the head
		head = new Node(key, value, this.head);
		n++;
	}
	
	
	public void delete(Key key) throws Exception {
		if(key == null) throw new IllegalArgumentException("argument to put() is null");
 		if(head == null) throw new Exception("empty symbol table");
		if(this.head.key.equals(key)) {
		    head = head.next;
		    System.out.println("inside the delete head function");
		    n--;
		    return;
		}
		
		for(Node i = head; i.next != null; i = i.next) {
			if(i.next.key.equals(key) && i.next.next != null) {
				i.next = i.next.next;
				n--;
				return;
			} 
			else if(i.next.key.equals(key) && i.next.next == null) {
			    i.next = null;
			    n--;
			    return;
			}
		}
	}

	
	//recursive call to delete the node
	//warning: function call stack too large if table is large
	
//	public void delete(Key key) {
//		if(key == null) throw new IllegalArgumentException("argument to put() is null");
//		head = delete(head, key);
//	}
//	
	//delete key in linked list begining at Node node	
//	private Node delete(Node node, Key key) {
//		if(node == null) return null;
//		if(node.key.equals(key)) {
//			n--;
//			return node.next;
//		}
//		node.next = delete(node.next, key);
//		return node;
//	}
	
	
	public Value get(Key key) {
		if(n == 0) {
			throw new NoSuchElementException("symbol table is empty");
		}
		if(key == null) throw new IllegalArgumentException("argument to get() is null");
		for(Node i = head; i != null; i = i.next) {
			if(key.equals(i.key)){
				return i.value;
			}
		}
		return null;
	}
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<Key>();
		for(Node i = head; i != null; i = i.next) {
			queue.add(i.key);
		}
		return queue;
	}
	
	public static void main(String[] args) throws Exception {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		st.put("I", 0);
		st.put("I", 1);
		st.put("I", 2);
		st.put("am", 3);
		st.put("Daniel", 6);
//		st.put("Daniel", null);
         st.delete("Daniel");
         st.delete("I");
		for(String s : st.keys()) {
			System.out.println(s + ": " + st.get(s));
		}
	}
}

