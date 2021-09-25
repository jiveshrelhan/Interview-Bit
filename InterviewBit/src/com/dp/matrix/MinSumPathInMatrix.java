package com.dp.matrix;

public class MinSumPathInMatrix {
	public int minPathSum(int[][] A) {
		if (A.length == 0) {
			return 0;
		}
		int n = A.length, m = A[0].length;
		int[][] dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = A[i][j];
				} else if (i == 0) {
					dp[i][j] = A[i][j] + dp[i][j - 1];
				} else if (j == 0) {
					dp[i][j] = A[i][j] + dp[i - 1][j];
				} else {
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + A[i][j];
				}
			}
		}
		return dp[n - 1][m - 1];
	}

	public static void main(String[] args) {
		MinSumPathInMatrix obj = new MinSumPathInMatrix();
		System.out.println(obj.minPathSum(new int[][] { { 1, 3, 2 }, { 4, 3, 1 }, { 5, 6, 1 } }));
		System.out.println(obj.minPathSum(
				new int[][] { { 20, 29, 84, 4, 32, 60, 86, 8, 7, 37 }, { 77, 69, 85, 83, 81, 78, 22, 45, 43, 63 },
						{ 60, 21, 0, 94, 59, 88, 9, 54, 30, 80 }, { 40, 78, 52, 58, 26, 84, 47, 0, 24, 60 },
						{ 40, 17, 69, 5, 38, 5, 75, 59, 35, 26 }, { 64, 41, 85, 22, 44, 25, 3, 63, 33, 13 },
						{ 2, 21, 39, 51, 75, 70, 76, 57, 56, 22 }, { 31, 45, 47, 100, 65, 10, 94, 96, 81, 14 } }));
	}
}
