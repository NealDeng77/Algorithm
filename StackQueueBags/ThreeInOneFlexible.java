import java.util.EmptyStackException;

/*
 * Interview Question in Cracking the Coding Interview
 * Describe how you could use a single array to implement three stacks
 * Idea: Allow the stack blocks to be flexible in size. When one stack exceeds its initial capacity, grow the 
 * allowable capacity and shift elements as necessary.
 * The array is circular, such that the final stack may start at the end of the array and wrap around to the beginning.
 * The solution is offered by the book: Cracking the coding interview
 */
public class ThreeInOneFlexible {
	/*
	 * Use a private class to store stack information
	 */
	private class StackInfo {
		private int start;              //start index of the stack
		private int size;               //number of elements in the stack
		private int capacity;           //the capacity of the stack
		/*
		 * Constructor
		 */
		public StackInfo(int start, int capacity) {
			this.start = start;
			this.capacity = capacity;	
		}
		
		/*
		 * Return the last index of the stack
		 */
		public int lastCapactiyIndex() {
			return adjustIndex(start + capacity - 1);
		}
		
		/*
		 * Return the last element of the stack
		 */
		public int lastElementIndex() {
			return adjustIndex(start + size - 1);
		}
		
		/*
		 * Check if an index on the full array is within the stack boundaries. The stack
		 * can wrap around to the start of the array
		 */
		public boolean isWithinStackCapacity(int index) {
			//check if the index is outside the array boundary
			if(index < 0 || index > values.length) {
				return false;
			}
			
			//if index wraps around, adjust it
			//why?
			int contiguousIndex = index < start ? index + values.length : index;
			int end = start + capacity;
			return start <= contiguousIndex && contiguousIndex < end;
		}
		
		/*
		 * Check if the stack is full
		 */
		public boolean isFull() {
			return size == capacity;
		}
		
		/*
		 * Check if the stack is empty
		 */
		public boolean isEmpty() {
			return size == 0;
		}
	}
	
	private StackInfo[] info;     //store stack info
	private int[] values;         //array 
	
	/*
	 * Constructor
	 * Three stack in one array
	 * Each stack has the same size by default
	 */
	public ThreeInOneFlexible(int numOfStack, int stackSize) {
		info = new StackInfo[numOfStack];
		for(int i = 0; i < numOfStack; i++) {
			info[i] = new StackInfo(i * stackSize, stackSize);   //each stack has the same size by default
		}
		values = new int[numOfStack * stackSize];
	}
	
	/*
	 * Push one value to the top of the stack
	 * If the stack is full, expand it
	 * @param stackNum push the value to which stack
	 * @param value the value to push
	 * @throw exception when all stacks are full 
	 */
	public void push(int stackNum, int value) throws Exception {
		//Check if all stacks are full
		if(allStackIsFull()) {
			throw new Exception("All stacks are full");
		}
		//Check if the specific stack is full
		StackInfo stack = info[stackNum];
		//expand the stack when it's full
		if(stack.isFull()) {
			expand(stackNum);
		}
		
		stack.size++;
		values[stack.lastElementIndex()] = value;
	}
	
	
	/*
	 * pop from the stack
	 * @param stackNum which stack to pop
	 * @throw EmptyStackException if the stack is empty
	 */
	public int pop(int stackNum) {
		StackInfo stack = info[stackNum];
		if(stack.isEmpty()) {
			throw new EmptyStackException();
		}
		int value = values[stack.lastElementIndex()];
		values[stack.lastElementIndex()] = 0;
		stack.size--;
		return value;
	}
	
	/*
	 * return the top element of the stack
	 * @param stackNum which stack to pop
	 * @throw EmptyStackException if the stack is empty
	 */
	private int peek(int stackNum) {
		StackInfo stack = info[stackNum];
		if(stack.isEmpty()) {
			throw new EmptyStackException();
		}
		int value = values[stack.lastElementIndex()];
		return value;
	}
	
	/*
	 * Expand the stack by shifting over other stacks
	 * @param stackNum which stack to pop
	 */
	private void expand(int stackNum) {
		info[stackNum].capacity++;
		shift((stackNum + 1) % info.length);
	}
	
	/*
	 * Shift all elements of one stack over by one element. If the stack has available 
	 * capacity, then shrink the capacity by one. If the stack doesn't have enough 
	 * capacity, shift the next stack over.
	 */
	private void shift(int stackNum) {
		StackInfo stack = info[stackNum];
		//if the stack is full, shift next stack
		if(stack.size >= stack.capacity) {
			int nextStack = (stackNum + 1) % info.length;
			shift(nextStack);
			stack.capacity++;
		}
		
		//shift all elements over
		int index = stack.lastCapactiyIndex();
		while(stack.isWithinStackCapacity(index)) {
			values[index] = values[previousIndex(index)];
			index = previousIndex(index);
		}
		
		//adjust stack data
		values[stack.start] = 0;           //avoid loitering
		stack.start = nextIndex(stack.start);
		stack.capacity--;      //shrink stack
		
	}
	
	/*
	 * Adjust index to be within the range of 0, length - 1
	 * Java's mod operator(%) can return negative values. For example, (-11 % 5) will 
	 * return -1, cause (5 * -2) + (-1) = -11, but it needs to return 4. 
	 */
	private int adjustIndex(int index) {
		int max = values.length;
		return ((index % max) + max) % max;
	}
	
	/*
	 * Return number of elements in all stacks
	 */
	private int numOfElements() {
		int numOfElements = 0;
		for(StackInfo each:info) {
			numOfElements += each.size;
		}
		return numOfElements;
	}
	
	/*
	 * Check if all stacks are full
	 */
	private boolean allStackIsFull() {
		return numOfElements() == values.length;
	}
	
	/*
	 * Return the next index, use adjustIndex to wrap around
	 */
	private int nextIndex(int index) {
		return adjustIndex(index + 1);
	}
	
	/*
	 * Return the previous index, use adjustIndex to wrap around 
	 */
	private int previousIndex(int index) {
		return adjustIndex(index - 1);
	}
}
