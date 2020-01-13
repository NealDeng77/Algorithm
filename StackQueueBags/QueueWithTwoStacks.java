import java.util.EmptyStackException;

/*
 * Algorithm 1, Princeton
 * Interview Question
 * Queue with two stacks. Implement a queue with two stacks so that 
 * each queue operations takes a constant amortized number of stack operations.
 */
public class QueueWithTwoStacks<Item> {
	private Stack<Item> a = new Stack<Item>();
	private Stack<Item> b = new Stack<Item>();
	
	/*
	 * If second stack is not empty, needs to push all elements of second stack
	 * back to the first stack first because we need to maintain the order
	 */
	public void enqueue(Item item) {
		if(!b.isEmpty()) {
			while(!b.isEmpty()) {
				Item temp = b.pop();
				a.push(temp);
			}
		}
		a.push(item);
	}
	
	
	
	public Item dequeue(){
		// if dequeue is called consecutively
		if(!b.isEmpty()) {
			return b.pop();
		}	
		else if(b.isEmpty()) {
			//empty queue
			if(a.isEmpty()) {
				//throw exception
				throw new EmptyStackException();
			}
			//if only one element in the queue
			else if(a.size() == 1) {
				return a.pop();
			}
			//if more than 1 elements, push all elements to stack b, then pop()
			else if(a.size() > 1) {
				while(!a.isEmpty()) {
					Item itemtemp = a.pop();
					b.push(itemtemp);
				}
				return b.pop();
			}
		}
		return null;
	}
}
