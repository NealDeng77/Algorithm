package Interview;
//reference: https://www.geeksforgeeks.org/merge-two-sorted-arrays-o1-extra-space/?ref=rp
public class MergeWithOneSpace {
	public void merge(int[] arr1, int[] arr2) {
		int pointer1 = arr1.length - 1;
		int pointer2 = arr2.length - 1;
		int last  = arr1[pointer1];
		int j;
		for(int i = pointer2; i >= 0; i--) {
			for(j = pointer1 - 1; j > 0 && arr1[j] > arr2[i]; j--) {
				arr1[j + 1] = arr1[j];
				
			}
			
			//if there was a greater element
			if(j < pointer1 - 1) {
				arr1[j + 1] = arr2[i];
				arr2[i] = last;
			}
		}
	}
}
