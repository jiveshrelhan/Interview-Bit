package com.dp.matrix;

public class IncreasingPathInMatrix {

	/*-
	 * This is better soln the author.
	 * This take dp at i,j if we can reach to bottom cell from i, j or not.
	 *
	 * if we are not able to reach 0,0 then return -1 else length will be always n+m-1
	 */
	public int solve(int[][] A) {
		if (A.length == 0) {
			return 0;
		}
		int n = A.length, m = A[0].length;
		boolean[][] dp = new boolean[n][m];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (i == n - 1 && j == m - 1) {
					dp[i][j] = true;
					continue;
				}
				dp[i][j] = i + 1 < n && A[i][j] < A[i + 1][j] ? dp[i + 1][j] : false;

				if (!dp[i][j]) {
					dp[i][j] = j + 1 < m && A[i][j] < A[i][j + 1] ? dp[i][j + 1] : false;
				}

			}
		}
		return dp[0][0] == false ? -1 : n + m - 1;
	}

	public static void main(String[] args) {
		IncreasingPathInMatrix obj = new IncreasingPathInMatrix();
		System.out.println(obj.solve(new int[][] { { 63, 56, 11, 60, 25, 38, 49, 84, 96, 42, 3, 51 } }));
		System.out.println(obj.solve(new int[][] { { 1, 2, 3, 4 }, { 2, 2, 3, 4 }, { 3, 2, 3, 4 }, { 4, 5, 6, 7 } }));

		System.out.println(obj.solve(new int[][] { { 92, 5, 3, 54, 93, 83, 22, 17, 19, 96, 48, 27, 72, 39, 70, 13, 68 },
				{ 100, 36, 95, 4, 12, 23, 34, 74, 65, 42, 12, 54, 69, 48, 45, 63, 58 },
				{ 38, 60, 24, 42, 30, 79, 17, 36, 91, 43, 89, 7, 41, 43, 65, 49, 47 },
				{ 6, 91, 30, 71, 51, 7, 2, 94, 49, 30, 24, 85, 55, 57, 41, 67, 77 },
				{ 32, 9, 45, 40, 27, 24, 38, 39, 19, 83, 30, 42, 34, 16, 40, 59, 5 },
				{ 31, 78, 7, 74, 87, 22, 46, 25, 73, 71, 30, 78, 74, 98, 13, 87, 91 },
				{ 62, 37, 56, 68, 56, 75, 32, 53, 51, 51, 42, 25, 67, 31, 8, 92, 8 },
				{ 38, 58, 88, 54, 84, 46, 10, 10, 59, 22, 89, 23, 47, 7, 31, 14, 69 } }));

	}
}
