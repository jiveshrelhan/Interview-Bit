package com.dp.matrix;

/*
 * Pep-coding : https://www.youtube.com/watch?v=UagRoA3C5VQ
 */
public class MaximumSubSquareMatrix {
	public int solve(int[][] A) {
		if (A.length == 0) {
			return 0;
		}
		int[][] dp = new int[A.length][A[0].length];
		int ans = 0;
		for (int i = A.length - 1; i >= 0; i--) {
			for (int j = A[0].length - 1; j >= 0; j--) {
				if (A[i][j] == 0) {
					dp[i][j] = 0;
				} else if (i == A.length - 1 || j == A[0].length - 1) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = 1 + Math.min(dp[i + 1][j + 1], Math.min(dp[i + 1][j], dp[i][j + 1]));
				}
				ans = Math.max(ans, dp[i][j]);
			}
		}
		return ans * ans;
	}
}
