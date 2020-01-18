import java.util.EmptyStackException;

/*
 * Algorithm 1, Princeton
 * Interview Question
 * Stack with max. Create a data structure that efficiently supports the stack 
 * operations (push and pop) and also a return-the-maximum operation. Assume 
 * the elements are real numbers so that you can compare them.
 * Reference: https://www.geeksforgeeks.org/tracking-current-maximum-element-in-a-stack/
 * Cracking the coding interview
 * Minstack has the same idea.
 * 
 * Idea:
 * 1. Use another stack to check the max.
 * 2. push the item to the stack
 * 3. Now from the second element, push the element to the main stack. 
 *    Compare the element with the top element of the track stack, if the current element is greater or 
 *    equal to the top of trackStack then push the current element to trackStack.
 * 4. If we pop an element from the main stack, then compare it to the top of the maxstack
 * if it's the same, pop from the maxstack as well
 * 5. Now to compute the maximum of the main stack at any point, we can simply print the top element of Track stack.
 * 
 * Performance: 1. use extra stack, but not much space because only when a larger element is pushed to the
 * mainstack we push that to the maxstack as well   
 * 2.push, pop, getMax O(1)
 *
 */
public class StackWithMax {
	private Stack<Double> stack = new Stack<Double>();
	private Stack<Double> maxstack = new Stack<Double>();
	
	/*
	 * push item to the stack
	 */
	public void push(double item) {
		
		stack.push(item);
		if(!maxstack.isEmpty()) {
			//if the top of the maxstack is larger, push that element again
			if(item >= maxstack.peek()) {
				maxstack.push(item);
			}
		}
		//if the maxstack is empty
		else {
			maxstack.push(item);
		}
	}
	
	/*
	 * return the max element of the stack
	 */
	public double getMax() {
		if(maxstack.isEmpty()) {
			throw new EmptyStackException();
		}
		return maxstack.peek();
	}
	
	/*
	 * pop from the stack
	 */
	public double pop() {
		if(stack.isEmpty()) {
			throw new EmptyStackException();
		}
		double item = stack.pop(); 
		if(maxstack.peek() == item) maxstack.pop();
		return item;
	}
}
