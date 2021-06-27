package com.stackNqueues;

import java.util.Stack;

public class Parantheses {
	public int solve(String A) {
		int n = A.length();
		if (n % 2 == 1) {
			return 0;
		}
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			char c = A.charAt(i);
			if (c == '(') {
				stack.push(c);
			} else if (!stack.isEmpty() && stack.peek() == '(') {
				stack.pop();
			} else {
				return 0;
			}
		}

		return stack.isEmpty() ? 1 : 0;
	}

	public static void main(String[] args) {
		Parantheses obj = new Parantheses();
		System.out.println(obj.solve("()"));
	}
}
