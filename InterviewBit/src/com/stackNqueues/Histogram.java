package com.stackNqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Histogram {
	public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
		ArrayList<Integer> result = new ArrayList<>();
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] { Integer.MIN_VALUE, -1 });

		for (int i = 0; i < A.size(); i++) {
			int value = A.get(i);
			while (!stack.isEmpty() && stack.peek()[0] >= value) {
				stack.pop();
			}
			result.add(stack.peek()[1]);
			stack.push(new int[] { value, i });
		}
		return result;
	}

	public ArrayList<Integer> nextSmaller(ArrayList<Integer> A) {
		ArrayList<Integer> result = new ArrayList<>();
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] { Integer.MIN_VALUE, A.size() });

		for (int i = A.size()-1; i >= 0; i--) {
			int value = A.get(i);
			while (!stack.isEmpty() && stack.peek()[0] >= value) {
				stack.pop();
			}
			result.add(stack.peek()[1]);
			stack.push(new int[] { value, i });
		}
		Collections.reverse(result);
		return result;
	}

	public int largestRectangleArea(ArrayList<Integer> A) {
		int n = A.size();
		ArrayList<Integer> nextSmaller = nextSmaller(A);
		ArrayList<Integer> prevSmaller = prevSmaller(A);
		System.out.println(nextSmaller);
		System.out.println(prevSmaller);
		int maxArea = 0;
		for (int i = 0; i < n; i++) {
			int width = nextSmaller.get(i) - prevSmaller.get(i) - 1;
			int area = A.get(i) * width;
			maxArea = Math.max(maxArea, area);
		}
		return maxArea;
	}

	public static void main(String[] args) {
		Histogram obj = new Histogram();
		System.out.println(obj
				.largestRectangleArea(new ArrayList<Integer>(Arrays.asList(90, 58, 69, 70, 82, 100, 13, 57, 47, 18))));
	}
}
