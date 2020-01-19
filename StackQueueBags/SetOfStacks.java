import java.util.ArrayList;
import java.util.EmptyStackException;

/*
 * Cracking the Coding Interview
 * 3.3Stack of Plates solution
 * Assumption: 
 * It's like a "rollover" system. If we pop an element from stack 1, we need to remove
 * the bottom of stack 2 and push it onto stack 1. We then need to rollover from stack
 * 3 to stack 2, stack 4 to stack 3, etc.
 */
public class SetOfStacks {
	private class Node {
		private Node above, below;
		private int value;
		private Node(int value) {
			this.value = value;
		}
	}
	
	ArrayList<Stack> stacks = new ArrayList<Stack>();
	public int capacity;
	public SetOfStacks(int capacity) {
		this.capacity = capacity;
	}
	
	/*
	 * Get the last stack
	 */
	public Stack getLastStack() {
		if(stacks.size() == 0) {
			return null;
		}
		return stacks.get(stacks.size() - 1);
	}
	
	public void push(int value) throws Exception {
		Stack last = getLastStack();
		//push to the last stack
		if(last != null && !last.isFull()) {
			last.push(value);
		} else {
			//create new stack
			Stack stack = new Stack(capacity);
			stack.push(value);
			stacks.add(stack);
		}
	}
	
	/*
	 * Pop from a specific sub-stack
	 * It's like a "rollover" system. If we pop an element from stack 1, we need to remove
	 * the bottom of stack 2 and push it onto stack 1. We then need to rollover from stack
	 * 3 to stack 2, stack 4 to stack 3, etc.
	 */
	public int popAt(int index) throws Exception {
		return leftShift(index, true);
	}
	
	private  int leftShift(int index, boolean removeTop) throws Exception {
		Stack stack = stacks.get(index);
		int removed_item;
		if(removeTop) removed_item = stack.pop();
		else {
			removed_item = stack.removeBottom();
		}
		if(stack.isEmpty()) {
			stacks.remove(index);
		}else if(stacks.size() > index + 1) {
			int v = leftShift(index + 1, false);
			stack.push(v);
		}
		return removed_item;

	}
	
	private class Stack{
		int capacity;
		private Node top, bottom;
		private int size = 0;
		
		public Stack(int capacity) {
			this.capacity = capacity;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public boolean isFull() {
			return size == capacity;
		}
		
		public void join(Node above, Node below) {
			if(below != null) below.above = above;
			if(above != null) above.below = below;
		}
		
		
		public boolean push(int value) throws Exception {
			if(size >= capacity) {
				throw new Exception("Stack is full");
			}
			size++;
			Node n = new Node(value);
			if(size == 1) {
				bottom = n;
			}
			join(n, top);
			top = n;
			return true;
		}
		
		public int pop() {
			if(size == 0) {
				throw new EmptyStackException();
			}
			Node t = top;
			top = top.below;
			size--;
			return t.value;
		}
		
		public int removeBottom() {
			Node b = bottom;
			bottom = bottom.above;
			if(bottom != null) {
				bottom.below = null;
			}
			size--;
			return b.value;
		}
	}
}

