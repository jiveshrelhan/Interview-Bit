package com.dp.matrix;

/*
 * https://www.youtube.com/watch?v=4uUGxZXoR5o
 */
public class DungeonPrincess {

	public int calculateMinimumHP(int[][] A) {
		if(A.length == 0) {
			return 0;
		}
		int n = A.length, m = A[0].length;
		int[][] dp = new int[n][m];

		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (i == n - 1 && j == m - 1) {
					dp[i][j] = Math.min(0, A[i][j]);
				} else if (i == n - 1) {
					dp[i][j] = Math.min(0, A[i][j] + dp[i][j + 1]);
				} else if (j == m - 1) {
					dp[i][j] = Math.min(0, A[i][j] + dp[i + 1][j]);
				} else {
					dp[i][j] = Math.min(0, A[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j]));
				}
			}
		}

		return Math.abs(dp[0][0]) + 1;
	}

	public static void main(String[] args) {
		DungeonPrincess obj = new DungeonPrincess();
		System.out.println(obj.calculateMinimumHP(new int[][] { { -2, -3, 3 }, { -5, 10, 1 }, { 10, 30, -5 } }));
	}
}
