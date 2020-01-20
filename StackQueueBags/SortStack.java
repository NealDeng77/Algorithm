/*
 * Cracking the coding interview
 * 3.5 sort stack
 * Write a program to sort a stack such that the smallest items are on the top. You can 
 * use an additional temporary stack, but you may not copy the elements into any other 
 * data structure(such as an array). The stack supports the following operation: push
 * pop, peek and isEmpty
 */
public class SortStack {
	public void sort(Stack<Integer> s) {
		Stack<Integer> r = new Stack<Integer>();

		while(!s.isEmpty()) {
			int temp = s.pop();
			while(!r.isEmpty() && r.peek() < temp) {
				s.push(r.pop());
			}
			r.push(temp);
		}
		
		
		while(!r.isEmpty()) {
			s.push(r.pop());
		}
	}
}
