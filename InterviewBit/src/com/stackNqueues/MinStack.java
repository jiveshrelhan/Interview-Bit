package com.stackNqueues;

import java.util.LinkedList;

public class MinStack {
	LinkedList<int[]> stack = new LinkedList<>();
	int currentSize = 0;

	public void push(int x) {
		int min = currentSize == 0 ? x : Math.min(x, stack.getFirst()[1]);
		stack.addFirst(new int[] { x, min });
		currentSize++;
	}

	public void pop() {
		if (currentSize > 0) {
			stack.removeFirst();
			currentSize--;
		}

	}

	public int top() {
		if (currentSize > 0) {
			return stack.peekFirst()[0];
		}
		return -1;
	}

	public int getMin() {
		if (currentSize > 0) {
			return stack.peekFirst()[1];
		}
		return -1;
	}
}
