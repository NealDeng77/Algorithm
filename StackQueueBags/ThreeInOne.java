import java.util.EmptyStackException;

/*
 * Interview Question in Cracking the Coding Interview
 * Describe how you could use a single array to implement three stacks
 * Idea: Fixed array, 1st stack: arr[0, 1/3n), 2nd stack:[1/3n, 2/3n), 3rd stack[2/3n, n)
 * Author: Qiaofang Deng
 * StackNum: 0, 1, 2
 */
public class ThreeInOne<Item> {
	private static final int NUMOFSTACK = 3;
	private int numOfStack;   //number of stack
	private Item[] arr;       //array to store elements
	private int capacity;     //stack capacity 
	private int[] size; //to store the number of elements of each stack
	
	/*
	 * Constructor
	 */
	public ThreeInOne(int stackSize) {
		numOfStack = NUMOFSTACK;
		capacity = stackSize;
		arr = (Item[])new Object[stackSize * numOfStack];
		size = new int[numOfStack];
	}
	
	/*
	 * Add item to a specific stack
	 * @param item item to add
	 * @param stackNum add item to which stack
	 * @throws IllegalArgumentException if stackNum < 0 || > 2
	 * @throws Exception if the stack is full
	 */
	public void push(Item item, int stackNum) throws Exception {
		validation(stackNum);
		if(isFull(stackNum)) {
			throw new Exception("Stack " + stackNum + " is full" );
		}
		//push item
		arr[indexOfTop(stackNum)] = item;
		//increment stack size accordingly
		size[stackNum]++;
		
	}
	
	/*
	 * Pop from the stack
	 * @param stackNum which stack to pop
	 * @throws IllegalArgumentException if stackNum < 0 || > 2
	 * @throws Exception if the stack is empty
	 */
	public Item pop(int stackNum) throws Exception {
		validation(stackNum);
		if(isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		Item temp;
		temp = arr[indexOfTop(stackNum)];
		//avoid loitering
		arr[indexOfTop(stackNum)] = null;
		size[stackNum]--;
		return temp;
	}
	
	/*
	 * Peek the top of the stack
	 * @param stackNum which stack to peek
	 * @throws IllegalArgumentException if stackNum < 0 || > 2
	 * @throws Exception if the stack is empty
	 */
	public Item peek(int stackNum) throws Exception {
		validation(stackNum);
		if(isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		return arr[indexOfTop(stackNum) - 1];
	}
	
	/*
	 * Check if the stack is empty
	 * @param stackNum which stack
	 */
	private boolean isEmpty(int stackNum){
		return size[stackNum] == 0;
	}
	
	
	/*
	 * Check if the stack is full
	 * @param stackNum which stack
	 */
	private boolean isFull(int stackNum) {
		return size[stackNum] == capacity;
	}
	
	/*
	 * return the index of the top of the stack
	 * @param stackNum which stack
	 */
	private int indexOfTop(int stackNum) {
		int offset = stackNum * capacity;
		return offset + size[stackNum] - 1;
	}
	
	/*
	 * validate argument
	 * @param stackNum which stack
	 * @throws IllegalArgumentException if stackNum < 0 || > 2
	 */
	private void validation(int stackNum) {
		if(stackNum < 0 || stackNum > 2) {
			throw new IllegalArgumentException();
		}
	}
}
