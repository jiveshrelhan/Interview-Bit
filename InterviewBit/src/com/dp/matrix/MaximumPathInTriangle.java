package com.dp.matrix;

public class MaximumPathInTriangle {
	/*-
	 * Pure observation: Notes available in the tablet.
	 */
	/*
	 * Space can be optimized by using 2 rows previous and current
	 */
	public int solve(int[][] A) {
		if (A.length == 0) {
			return 0;
		}
		int[][] dp = new int[A.length][A[0].length];
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j <= i; j++) {
				int case1 = i - 1 >= 0 && j - 1 >= 0 ? dp[i - 1][j - 1] : 0;
				int case2 = i - 1 >= 0 ? dp[i - 1][j] : 0;
				dp[i][j] = Math.max(case1, case2) + A[i][j];
				ans = Math.max(ans, dp[i][j]);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		MaximumPathInTriangle obj = new MaximumPathInTriangle();
		System.out.println(obj.solve(new int[][] { { 8, 0, 0, 0 }, { 4, 4, 0, 0 }, { 2, 2, 6, 0 }, { 1, 1, 1, 1 } }));
		System.out.println(obj.solve(new int[][] { { 3, 0, 0, 0 }, { 7, 4, 0, 0 }, { 2, 4, 6, 0 }, { 8, 5, 9, 3 } }));
	}
}
