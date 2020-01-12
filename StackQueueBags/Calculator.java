/*
 * Use stack to implement Arithmetic expression evaluation
 * 
 * Steps:
 * 1. Use two stacks to store operator and vals
 * 2. When operator is "(", ignore; When operator is +, *, - , /,  push to the stack
 *    When operator is ), pop two values and one operator, push the result to
 *    the vals stack
 */
public class Calculator {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();    //store operators
		Stack<Double> vals = new Stack<Double>();   //store values
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(s.equals("(")) continue;
			else if(s.equals("+")) ops.push(s);
			else if(s.equals("*")) ops.push(s);
			else if(s.equals("-")) ops.push(s);
			else if(s.equals("/")) ops.push(s);
			else if(s.equals(")")) {
				String op = ops.pop();
				if(op.equals("+")) {
					double result = vals.pop() + vals.pop();
					vals.push(result);
				}else if(op.equals("*")) {
					double result = vals.pop() * vals.pop();
					vals.push(result);
				}else if(op.equals("-")) {
					double operand1 = vals.pop();
					double operand2 = vals.pop();	
					double result = operand2 - operand1;
					vals.push(result);
				}else if(op.equals("/")) {
					double operand1 = vals.pop();
					double operand2 = vals.pop();	
					double result = operand2 / operand1;
					vals.push(result);
				}
			}
			else {
				vals.push(Double.parseDouble(s));
			}
		}
		StdOut.println(vals.pop());
	}
}
