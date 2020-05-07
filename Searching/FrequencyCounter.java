/*
 * A client program to test ST implementation
 */
public class FrequencyCounter {
	public static void main(String[] args) {
		int minlen = Integer.parseInt(args[0]);
		ST<Integer, Integer> st = new ST<Integer, Integer>();
		while(!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if(word.length() < minlen) continue;
			if(!st.contains(word)) st.put(word, 1);
			else st.put(word, st.get(word) + 1);
		}
		
		String max = "";
		st.put(max, 0);
		for(String word : st) {
			if(st.get(word) > st.get(max)) {
				max = word;
			}
		}
		StdOut.println(max + " " + st.get(max));
	}
	
}
