package com.dp.tricky;

import java.util.Arrays;

public class EvaluateExpression {

	private int computeFunction(int leftT, int leftF, int rightT, int rightF, char operator, boolean isTrue) {

		if (operator == '|') {
			if (isTrue) {
				return leftT * rightT + leftT * rightF + leftF * rightT;
			} else {
				return leftF * rightF;
			}
		} else if (operator == '&') {
			if (isTrue) {
				return leftT * rightT;
			} else {
				return leftF * rightF + leftF * rightT + leftT * rightF;
			}

		} else {
			if (isTrue) {
				return leftT * rightF + leftF * rightT;
			} else {
				return leftT * rightT + leftF * rightF;
			}
		}
	}

	int[][][] dp;

	int mod = (int) 1e3 + 3;

	private int helper(int start, int end, char[] input, boolean isTrue) {

		if (start > end) {
			return 0;
		}
		if (start == end) {
			if (isTrue) {
				return input[start] == 'T' ? 1 : 0;
			} else {
				return input[start] == 'F' ? 1 : 0;
			}
		}
		int temp = 0;
		for (int k = start + 1; k < end; k += 2) {
			int leftTrue = dp[start][k - 1][1] != -1 ? dp[start][k - 1][1] : helper(start, k - 1, input, true);
			int leftFalse = dp[start][k - 1][0] != -1 ? dp[start][k - 1][0] : helper(start, k - 1, input, false);
			int rightTrue = dp[k + 1][end][1] != -1 ? dp[k + 1][end][1] : helper(k + 1, end, input, true);
			int rightFalse = dp[k + 1][end][0] != -1 ? dp[k + 1][end][0] : helper(k + 1, end, input, false);
			temp += computeFunction(leftTrue, leftFalse, rightTrue, rightFalse, input[k], isTrue);
			temp %= mod;
		}
		int is_true = isTrue ? 1 : 0;
		return dp[start][end][is_true] = temp;

	}

	public int cnttrue(String A) {
		if (A.length() == 1) {
			return A.charAt(0) == 'T' ? 1 : 0;
		}
		char[] input = A.toCharArray();
		dp = new int[A.length()][A.length()][2];
		for (int[][] rows : dp) {
			for (int[] x : rows) {
				Arrays.fill(x, -1);
			}
		}
		return helper(0, input.length - 1, input, true);
	}

	public static void main(String[] args) {
		EvaluateExpression obj = new EvaluateExpression();
		System.out.println(obj.cnttrue("T^T^T^F|F&F^F|T^F^T"));
	}
}
