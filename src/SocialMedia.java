
public class SocialMedia {
	private int[] id;
	private int count;        //count set
	private int[] sz;
	public SocialMedia(int n) {
		count = n;
		id = new int[n];
		for(int i = 0; i < n;i++) {
			id[i] = i;
		}
		sz = new int[n];
		for(int i = 0; i < n;i++) {
			sz[i] = 1;
		}
	}
	
	private void validate(int i) {
		int length = id.length;
		if(i < 0 || i >= length) {
			throw new IllegalArgumentException("index" + i + "is not between 0 and " + (length - 1));
		}
	}
	
	private boolean connected(int i, int j) {
		return find(i) == find(j);
	}
	
	private int find(int i ) {
		validate(i);
		while(i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	private void union(int i, int j) {
		int x = find(i);
		int y = find(j);
		if(x == y) return;
		if(sz[x] < sz[y]) {
			id[x] = y;
			sz[y] = sz[y] + sz[x];
		}else {
			id[y] = x;
			sz[x] = sz[x] + sz[y];
		}
		count--;
	}
	
	public int count() {
		return count;
	}
	
	
	
	//when count == 1, means fully connected
	public static void main(String[] args) {
		int n = StdIn.readInt();
		SocialMedia sm = new SocialMedia(n);
		int m = 0;
		boolean connect = false;
		while(connect != true) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(sm.connected(p, q)){
				m++;
				continue;
			}
			sm.union(p, q);
			m++;
			if(sm.count() == 1) {
				connect = true;
			}
			StdOut.println(p + " " + q);
		}
		StdOut.println("After timestamp " + m + ", all connected");
		
	}
}
