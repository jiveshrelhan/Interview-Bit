package com.stackNqueues;

import java.util.Stack;

public class RedundantBraces {
	public int braces(String A) {
		Stack<Character> stack = new Stack<>();
		for (char c : A.toCharArray()) {
			if (c == ')') {
				boolean isReduntant = true;
				while (!stack.isEmpty()) {
					char x = stack.pop();
					if (x == '+' || x == '-' || x == '*' || x == '/') {
						isReduntant = false;
					} else if (x == '(') {
						break;
					}
				}
				if (isReduntant) {
					return 1;
				}
			} else {
				stack.push(c);
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		RedundantBraces obj = new RedundantBraces();
		System.out.println(obj.braces("(a+b)/((b+c)/(d+e))"));
	}
}
