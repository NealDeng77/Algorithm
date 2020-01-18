import java.util.ArrayList;
import java.util.EmptyStackException;

/*
 * Cracking the coding interview. 3.3 stack of Plates
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
 * threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
 * composed of several stacks and should create a new stack once the previous one exceeds capacity,
 * SetOfStacks. push() and SetOfStacks.popO should behave identically to a single stack
 * (that is, pop() should return the same values as it would if there were just a single stack).
 * FOLLOW UP
*  Implement a function popAt ( i n t index) which performs a pop operation on a specific substack.
 */
public class StackOfPlates {
	private int currentStack;
	private ArrayList<FixedCapacityStack> stacks = new ArrayList<FixedCapacityStack>();  //store the stacks
	private final static int CAPACITY = 10;
	
	/*
	 * constructor
	 */
	public StackOfPlates(){
		FixedCapacityStack<Integer> stack = new FixedCapacityStack<Integer>(CAPACITY);
		stacks.add(stack);
	}

	/*
	 * Push item to the stack
	 */
	public void push(int item) {
		int numOfStacks = stacks.size();
		FixedCapacityStack<Integer> stack = (FixedCapacityStack<Integer>)stacks.get(numOfStacks - 1);
		if(!stack.isFull() && stack != null) {
			stack.push(item);
		}else {
			FixedCapacityStack<Integer> newStack = new FixedCapacityStack<Integer>(CAPACITY);
			newStack.push(item);
			stacks.add(newStack);
		}
	}
	
	/*
	 * Pop from the stacks
	 * @throw EmptyStackException if all stacks are empty
	 */
	public int pop() {
		if(stacks.size() == 0) {
			throw new EmptyStackException();
		}
		int numOfStacks = stacks.size();
		FixedCapacityStack<Integer> stack = (FixedCapacityStack<Integer>)stacks.get(numOfStacks - 1);
		//pop from the last stack
		int tempItem = stack.pop();
		//if the stack is empty, remove that stack
		if(stack.isEmpty()) {
			stacks.remove(numOfStacks - 1);
		}
		return tempItem;
	}
	
//	/*
//	 * Pop from a specific sub-stack
//	 * We assume some stacks can be not at full capacity. In this situation, the popAt() operation
//   * has less time complexity
//	 */
//	public int popAt(int index) {
//		FixedCapacityStack<Integer> stack = (FixedCapacityStack<Integer>)stacks.get(index - 1);
//		if(stack.isEmpty() || stack == null) {
//			throw new EmptyStackException();
//		}
//		int tempItem = stack.pop();
//		//if the stack is empty, remove that stack
//		if(stack.isEmpty()) {
//			stacks.remove(index - 1);
//		}
//		return tempItem;
//	}
	
	/*
	 * Pop from a specific sub-stack
	 * It's like a "rollover" system. If we pop an element from stack 1, we need to remove
	 * the bottom of stack 2 and push it onto stack 1. We then need to rollover from stack
	 * 3 to stack 2, stack 4 to stack 3, etc.
	 */
	public int popAt(int index) {
		
	}
}
