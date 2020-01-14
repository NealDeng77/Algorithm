import java.util.EmptyStackException;

/*
 * Interview Question in Cracking the Coding Interview
 * Describe how you could use a single array to implement three stacks
 * Idea: Fixed array, 1st stack: arr[0, 1/3n), 2nd stack:[1/3n, 2/3n), 3rd stack[2/3n, n)
 * 
 */
public class ThreeInOne<Item> {
	private Item[] arr;       //array to store elements
	private int capacity;     //array capacity 
	private int numOfElement1;  //number of elements in stack one
	private int numOfElement2;  //number of elements in stack two
	private int numOfElement3;  //number of elements in stack three
	
	/*
	 * Constructor
	 */
	public ThreeInOne(int n) {
		capacity = n;
		arr = (Item[])new Object[n];
		numOfElement1 = 0;
		numOfElement2 = 0;
		numOfElement3 = 0;
	}
	
	/*
	 * Add item to a specific stack
	 * @param item item to add
	 * @param stackNum add item to which stack
	 */
	public void push(Item item, int stackNum) throws Exception {
		if(isFull(stackNum)) {
			throw new Exception("Stack " + stackNum + " is full" );
		}
		if(stackNum == 1) {
			arr[numOfElement1++] = item;
		}
		else if(stackNum == 2) {
			arr[capacity/3 + numOfElement2] = item;
			numOfElement2++;
		}
		else if(stackNum == 3) {
			arr[2*capacity/3 + numOfElement3] = item;
			numOfElement3++;
		}
		
	}
	
	/*
	 * Pop from the stack
	 * @param stackNum which stack to pop
	 */
	public Item pop(int stackNum) throws Exception {
		if(stackNum < 1 || stackNum > 3) {
			throw new Exception("No such stack");
		}
		if(isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		Item temp;
		if(stackNum == 1) {
			temp = arr[--numOfElement1];
			arr[numOfElement1] = null;
			return temp;
		}
		else if(stackNum == 2) {
			temp = arr[capacity/3 + numOfElement2 - 1];
			arr[capacity/3 + numOfElement2 - 1] = null;
			numOfElement2--;
			return temp;
		}
		else{
			temp = arr[2* capacity/3 + numOfElement3 - 1];
			arr[2* capacity/3 + numOfElement3 - 1] = null;
			numOfElement3--;
			return temp;
		}
	}
	
	/*
	 * Peek the top of the stack
	 * @param stackNum which stack to peek
	 */
	public Item peek(int stackNum) throws Exception {
		if(stackNum < 1 || stackNum > 3) {
			throw new Exception("No such stack");
		}
		if(isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		Item temp;
		if(stackNum == 1) {
			temp = arr[numOfElement1 - 1];
			return temp;
		}
		else if(stackNum == 2) {
			temp = arr[capacity/3 + numOfElement2 - 1];
			return temp;
		}
		else{
			temp = arr[2* capacity/3 + numOfElement3 - 1];
			return temp;
		}
	}
	
	/*
	 * Check if the stack is empty
	 */
	private boolean isEmpty(int stackNum) throws Exception {
		if(stackNum < 1 || stackNum > 3) {
			throw new Exception("No such stack");
		}
		if(stackNum == 1 && numOfElement1 == 0) {
			return true;
		}
		else if(stackNum == 2 && numOfElement1 == 0) {
			return true;
		}
		else if(stackNum == 3 && numOfElement1 == 0) {
			return true;
		}
		return false;
	}
	
	
	/*
	 * Check if the stack is full
	 */
	private boolean isFull(int stackNum) throws Exception {
		if(stackNum < 1 || stackNum > 3) {
			throw new Exception("No such stack");
		}
		if(stackNum == 1 && numOfElement1 == capacity/3) {
			return true;
		}
		else if(stackNum == 2 && numOfElement1 == capacity/3) {
			return true;
		}
		else if(stackNum == 3 && numOfElement1 == capacity/3) {
			return true;
		}
		return false;
	}
}
