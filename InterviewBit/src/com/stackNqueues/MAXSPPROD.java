package com.stackNqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class MAXSPPROD {

	private int[] computeNextGreaterElement(ArrayList<Integer> A) {
		int n = A.size();
		int[] res = new int[n];
		Stack<int[]> stack = new Stack<>();
		for (int i = A.size() - 1; i >= 0; i--) {
			int value = A.get(i);
			while (!stack.isEmpty() && stack.peek()[0] <= value) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				res[i] = -1;
			} else {
				res[i] = stack.peek()[1];
			}
			stack.push(new int[] { value, i });
		}

		return res;
	}

	private int[] computePreviousGreaterElement(ArrayList<Integer> A) {
		int[] res = new int[A.size()];
		Stack<int[]> stack = new Stack<>();
		for (int i = 0; i < A.size(); i++) {
			int value = A.get(i);

			while (!stack.isEmpty() && stack.peek()[0] <= value) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				res[i] = -1;
			} else {
				res[i] = stack.peek()[1];
			}

			stack.push(new int[] { value, i });
		}
		return res;
	}

	/*
	 * LeftSpecialValue = PreviousGreaterElement RightSpecialValue =
	 * NextGreaterElement
	 */
	public int maxSpecialProduct(ArrayList<Integer> A) {
		long maxProduct = 0, n = A.size();
		if (n <= 2)
			return 0;
		long mod = (long) 1e9 + 7;
		int[] pge = computePreviousGreaterElement(A);
		int[] nge = computeNextGreaterElement(A);
		for (int i = 0; i < n; i++) {
			if (pge[i] < 0 || pge[i] == n || nge[i] < 0 || nge[i] == n) {
				continue;
			}
			maxProduct = Math.max(maxProduct, (long) pge[i] * (long) nge[i]);
		}
		return (int) (maxProduct % mod);
	}

	public static void main(String[] args) {
		MAXSPPROD obj = new MAXSPPROD();
		System.out.println(obj.maxSpecialProduct(new ArrayList<>(Arrays.asList(6, 5, 4, 9, 9, 6, 5, 4, 5, 9, 9))));
	}
}
