/*
 * Algorithm 1, Princeton
 * Interview Question
 * Stack with max. Create a data structure that efficiently supports the stack 
 * operations (push and pop) and also a return-the-maximum operation. Assume 
 * the elements are real numbers so that you can compare them.
 * Reference: https://www.geeksforgeeks.org/tracking-current-maximum-element-in-a-stack/
 * 
 * Idea:
 * 1. Use another stack to check the max.
 * 2. push the item to the stack
 * 3. Now from the second element, push the element to the main stack. 
 *    Compare the element with the top element of the track stack, if the current element is greater
 *    than top of trackStack then push the current element to trackStack otherwise push the top element
 *    of trackStack again into it.
 * 4. If we pop an element from the main stack, then pop an element from the trackStack as well.
 * 5. Now to compute the maximum of the main stack at any point, we can simply print the top element of Track stack.
 */
public class StackWithMax {
	private Stack<Double> stack = new Stack<Double>();
	private Stack<Double> maxstack = new Stack<Double>();
	
	public void push(double item) {
		
		stack.push(item);
		if(!maxstack.isEmpty()) {
			//if the top of the maxstack is larger, push that element again
			if(item <= maxstack.peek()) {
				maxstack.push(maxstack.peek());
			}else {
				//if the item is the max, push to the top of the stack
				maxstack.push(item);
			}
		}
		//if the maxstack is empty
		else {
			maxstack.push(item);
		}
	}
	
	public double getMax() {
		return maxstack.peek();
	}
	
	public double pop() {
		maxstack.pop();
		return stack.pop();
	}
}
