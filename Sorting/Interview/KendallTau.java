package Interview;
/*
 * find the kendaltau distance between two permutations in linearithmic time
 */
public class KendallTau {
	public static long findDistance(int[] a, int[] b) {
		if(a.length != b.length) {
			throw new IllegalArgumentException("Two arrays have different sizes");
		}
		//use a new array to store the position of each number in array a
		int[] aPosition = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			aPosition[a[i]] = i;
		}
		
		//use another array to store the corresponding position in array b
		int[] bPosition = new int[b.length];
		for(int j = 0; j < b.length; j++) {
			bPosition[j] = aPosition[b[j]];	
		}
		return CountInversion.count(bPosition);
		
	}
	
}
