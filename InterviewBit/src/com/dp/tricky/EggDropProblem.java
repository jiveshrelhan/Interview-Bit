package com.dp.tricky;

import java.util.Arrays;

public class EggDropProblem {

	class DP {

		public int solve(int eggs, int floors) {
			int[][] dp = new int[eggs + 1][floors + 1];

			for (int floor = 0; floor < dp[0].length; floor++) {
				dp[1][floor] = floor;
			}

			for (int egg = 1; egg < dp.length; egg++) {
				dp[egg][1] = 1;
			}

			for (int i = 2; i < dp.length; i++) {
				for (int j = 2; j < dp[0].length; j++) {
					dp[i][j] = Integer.MAX_VALUE;
					for (int k = 1; k <= j; k++) {
						dp[i][j] = Math.min(dp[i][j], Math.max(dp[i][j - k], dp[i - 1][k - 1]) + 1);
					}
				}
			}

			/*
			 * for (int[] rows : dp) { System.out.println(Arrays.toString(rows)); }
			 */
			return dp[eggs][floors];
		}

	}

	class OptimizedDP {
		public int solve(int eggs, int floors) {
			int[][] dp = new int[eggs + 1][floors + 1];

			for (int floor = 0; floor < dp[0].length; floor++) {
				dp[1][floor] = floor;
			}

			for (int egg = 1; egg < dp.length; egg++) {
				dp[egg][1] = 1;
			}
			for (int i = 2; i < dp.length; i++) {
				for (int j = 2; j < dp[0].length; j++) {

					//binary search
					int low = 0;
					int high = j;
					while (low <= high) {
						int k = (low + high) / 2;
						if (dp[i - 1][k - 1] < dp[i][j - k]) {
							low = k + 1;
						} else {
							high = k - 1;
						}
					}
					dp[i][j] = Integer.MAX_VALUE;
					int case1ofBestK = Math.max(dp[i][j - low], dp[i - 1][low - 1]);
					int case2ofBestK = Math.max(dp[i][j - high], dp[i - 1][high - 1]);
					dp[i][j] = Math.min(case1ofBestK, case2ofBestK) + 1;
				}
			}
			return dp[eggs][floors];
		}
	}

	int[][] memo;

	private int helper(int eggs, int floors) {

		if (eggs == 1) {
			return floors;
		}

		if (floors == 0 || floors == 1) {
			return floors;
		}

		if (memo[eggs][floors] != -1) {
			return memo[eggs][floors];
		}

		int temp = Integer.MAX_VALUE;
		for (int k = 1; k <= floors; k++) {
			int case1 = memo[eggs][floors - k] != -1 ? memo[eggs][floors - k] : helper(eggs, floors - k);
			int case2 = memo[eggs - 1][k - 1] != -1 ? memo[eggs - 1][k - 1] : helper(eggs - 1, k - 1);
			temp = Math.min(temp, Math.max(case1, case2) + 1);
		}
		return memo[eggs][floors] = temp;
	}

	public int solve(int eggs, int floors) {
		memo = new int[eggs + 1][floors + 1];
		for (int[] rows : memo) {
			Arrays.fill(rows, -1);
		}
		return helper(eggs, floors);
	}

	public static void main(String[] args) {
		EggDropProblem obj = new EggDropProblem();
		System.out.println(obj.solve(2, 10));
		System.out.println(obj.new DP().solve(2, 10));
		System.out.println(obj.new OptimizedDP().solve(2, 10));
	}
}
