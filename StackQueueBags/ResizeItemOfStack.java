//resizing array implementation of generic stack
//implement iterator
import java.util.Iterator;
public class ResizeItemOfStack<Item> implements Iterable<Item>{
	private Item[] s;
	private int N;
	
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item>{
		private int i = N;
		
		public boolean hasNext() {
			return i > 0;
		}
		
		public void remove() {
			//not supported
		}
		
		public Item next() {
			return s[--i];
		}
	}
	
	public ResizeItemOfStack() {
		s = (Item[])new Object[1];
	}
	
	public boolean isEmpty() {
		return N == 0 ;
	}
	
	public void push(Item str) {
		if((N) == s.length) {
			resize(2 * s.length);
		}
		s[N++] = str;
	}
	
	public Item pop() {
		Item str = s[--N];
		s[N] = null;
		//resize if the capacity is 1/4 of the s.length
		if( (N > 0) && (N == 1/4 * s.length) ) {
			resize(s.length / 2);
		}
		
		return str;		
		//return s[--N]; //this is loitering. because the garbage collection won't clean the memory
		//even when it's empty in the array elements
	}
	
	private void resize(int size) {
		Item[] newS = (Item[])new Object[size];
		for(int i = 0; i < N; i++) {
			newS[i] = s[i]; 
		}
		s = newS;
	}
}
