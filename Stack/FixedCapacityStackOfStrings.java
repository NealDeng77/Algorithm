
public class FixedCapacityStackOfStrings {
	private String[] s;
	private int N;
	
	public FixedCapacityStackOfStrings(int capacity) {
		s = new String[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0 ;
	}
	
	public void push(String str) {
		s[N++] = str;
	}
	
	public String pop() {
		String str = s[N - 1];
		s[N - 1] = null;
		N--;
		return str;
		
		
		
		//return s[--N]; //this is loitering. because the garbage collection won't clean the memory
		//even when it's empty in the array elements
	}
}
