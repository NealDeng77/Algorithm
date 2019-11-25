
public class ResizeStringOfStack {
	private String[] s;
	private int N;
	
	public ResizeStringOfStack() {
		s = new String[1];
	}
	
	public boolean isEmpty() {
		return N == 0 ;
	}
	
	public void push(String str) {
		if((N) == s.length) {
			resize(2 * s.length);
		}
		s[N++] = str;
	}
	
	public String pop() {
		String str = s[--N];
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
		String[] newS = new String[size];
		for(int i = 0; i < N; i++) {
			newS[i] = s[i]; 
		}
		s = newS;
	}
}
