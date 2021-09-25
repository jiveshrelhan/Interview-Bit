package com.dp.matrix;

/*
 * Either you come from up or left. If you can come up from both 
 * add them since it will act as two different path.
 * if current cell is block set all paths to 0
 */
public class UniquePathInMatrix {
	public int uniquePathsWithObstacles(int[][] A) {
		if (A.length == 0) {
			return 0;
		}
		int n = A.length, m = A[0].length;
		
		if (A[0][0] == 1) {
			return 0;
		}

		int[][] dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = 1;
				} else if (A[i][j] == 1) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = i - 1 >= 0 ? dp[i - 1][j] : 0;
					dp[i][j] += j - 1 >= 0 ? dp[i][j - 1] : 0;
				}
			}
		}
		return dp[n - 1][m - 1];
	}

	public static void main(String[] args) {
		UniquePathInMatrix obj = new UniquePathInMatrix();
		System.out.println(obj.uniquePathsWithObstacles(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }));
		System.out.println(obj.uniquePathsWithObstacles(new int[][] {}));
	}
}
