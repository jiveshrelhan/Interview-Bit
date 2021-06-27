package com.stackNqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class EvalExpression {
	public int evalRPN(ArrayList<String> A) {
		int result = -1;
		Stack<Integer> stack = new Stack<>();
		for (String x : A) {
			if (x.equals("+")) {
				int a = stack.pop();
				int b = stack.pop();
				result = b + a;
				stack.push(result);
			} else if (x.equals("-")) {
				int a = stack.pop();
				int b = stack.pop();
				result = b - a;
				stack.push(result);
			} else if (x.equals("*")) {
				int a = stack.pop();
				int b = stack.pop();
				result = b * a;
				stack.push(result);
			} else if (x.equals("/")) {
				int a = stack.pop();
				int b = stack.pop();
				result = b / a;
				stack.push(result);
			} else {
				stack.push(Integer.valueOf(x));
			}
		}
		return stack.isEmpty() ? 0 : stack.pop();
	}

	public static void main(String[] args) {
		EvalExpression obj = new EvalExpression();
		System.out.println(obj.evalRPN(new ArrayList<>(Arrays.asList("5", "1", "2", "+", "4", "*", "+", "3", "-"))));
	}
}
