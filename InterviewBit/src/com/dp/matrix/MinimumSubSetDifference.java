package com.dp.matrix;

/*
 * Knapsack - > Subset Sum Partition - > MinimumSubsetDifference
 * 
 * Algo: 
 * Check whatever sum possible from 1 to Total Sum: If sum for x is possible then (Total - x) also possible
 * take minimum of all pairs.
 */
public class MinimumSubSetDifference {

	public int solve(int[] A) {
		if (A.length == 0) {
			return 0;
		}
		int range = 0;
		for (int x : A) {
			range += x;
		}

		int k = range;

		boolean[][] dp = new boolean[A.length + 1][k + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = true;
				} else if (i == 0) {
					dp[i][j] = false;
				} else if (j == 0) {
					dp[i][j] = true;
				} else {

					if (A[i - 1] <= j) {
						dp[i][j] = dp[i - 1][j - A[i - 1]] || dp[i - 1][j];
					} else {
						dp[i][j] = dp[i - 1][j];
					}

				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int j = 0; j < dp[0].length; j++) {
			int s1 = 0, s2 = 0;
			if (dp[A.length][j]) {
				s1 = j;
				s2 = Math.abs(range - s1);
				ans = Math.min(ans, Math.abs(s1 - s2));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		MinimumSubSetDifference obj = new MinimumSubSetDifference();
		System.out.println(obj.solve(new int[] { 1, 5 }));
	}
}
