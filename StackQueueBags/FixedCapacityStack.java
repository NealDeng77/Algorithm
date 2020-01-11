
public class FixedCapacityStack<Item> {
	private Item[] s;
	private int N = 0;
	
	public FixedCapacityStack(int capacity) {
		//s = new Item[capacity];       //java doesn't allow generic array creation
		//so we need to use cast here
		s = (Item[]) new Object[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0 ;
	}
	
	public void push(Item str) {
		s[N++] = str;
	}
	
	public Item pop() {
		Item str = s[N - 1];
		s[N - 1] = null;
		N--;
		return str;
		
		
		
		//return s[--N]; //this is loitering. because the garbage collection won't clean the memory
		//even when it's empty in the array elements
	}
}
