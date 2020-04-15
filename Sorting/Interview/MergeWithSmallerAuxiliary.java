package Interview;

//reference : https://www.algoqueue.com/algoqueue/default/view/983040/merging-two-sorted-arrays-with-smaller-auxiliary-array
//https://github.com/guibin/Knowledge/blob/master/libs/lib.algorithm/src/main/java/guibin/zhang/onlinecourse/MergeWithSmallAux.java
//https://massivealgorithms.blogspot.com/2019/03/merging-with-smaller-auxiliary-array.html
//https://www.geeksforgeeks.org/merge-two-sorted-arrays-o1-extra-space/
public class MergeWithSmallerAuxiliary {
	public static void merge(Comparable[] arr) {
		int n = arr.length / 2;
		Comparable[] aux = new Comparable[n];
		for(int i = 0 ; i < n; i++) {
			aux[i] = arr[i];
		}
		int i = 0, j = n;
		for(int k = 0; k < arr.length; k++) {
			if(i >= n) {
				arr[k] = arr[j++];
			}
			else if(j >= arr.length) {
				arr[k] = aux[i++];
			}
			else if(arr[i].compareTo(arr[j]) < 0) {
				arr[k] = aux[i++];
			}
			else {
				arr[k] = aux[j++];
			}
		}
	}
	
	
}
