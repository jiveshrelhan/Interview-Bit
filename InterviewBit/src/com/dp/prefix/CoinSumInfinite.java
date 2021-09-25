package com.dp.prefix;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * It can be done by unbounded knapsack
 * but then it required 2D space N * M where N is required sum and M is number of coins
 */
public class CoinSumInfinite {

	class UsingKnapsack {

		long memo[][];
		int mod = (int) 1e6 + 7;

		private long helper(int currentSum, int currentIndex, int targetSum, ArrayList<Integer> A) {
			if (currentSum == targetSum) {
				return 1;
			}
			if (currentSum > targetSum) {
				return 0;
			}
			if (currentIndex == A.size()) {
				return 0;
			}

			if (memo[currentIndex][currentSum] != -1) {
				return memo[currentIndex][currentSum];
			}

			return memo[currentIndex][currentSum] = (helper(currentSum + A.get(currentIndex), currentIndex, targetSum,
					A) % mod + helper(currentSum, currentIndex + 1, targetSum, A) % mod) % mod;
		}

		public int coinchange2(ArrayList<Integer> A, int B) {
			if (A.size() == 0) {
				return 0;
			}
			memo = new long[A.size()][B + 1];
			for (long[] rows : memo) {
				Arrays.fill(rows, -1);
			}
			return (int) (helper(0, 0, B, A) % mod);
		}
	}

	/*
	 * It is accepted but it has N*M space.
	 * 
	 * Since we are using previous row and current row space can be optimized
	 */
	class DP {
		public int coinchange2(ArrayList<Integer> A, int B) {
			if (A.size() == 0)
				return 0;
			int mod = (int) 1e6 + 7;
			int[][] dp = new int[A.size() + 1][B + 1];

			for (int i = 0; i < dp.length; i++) {
				for (int j = 0; j < dp[0].length; j++) {

					if (j == 0) {
						dp[i][j] = 1;
					} else if (i == 0) {
						dp[i][j] = 0;
					} else {

						if (j >= A.get(i - 1)) {
							dp[i][j] = dp[i][j - A.get(i - 1)] + dp[i - 1][j];
							dp[i][j] %= mod;
						} else {
							dp[i][j] = dp[i - 1][j];
						}
					}
				}
			}
			return dp[A.size()][B] % mod;
		}
	}

	class SpaceOptimizedDP {
		public int coinchange2(ArrayList<Integer> A, int B) {
			if (A.size() == 0)
				return 0;
			int mod = (int) 1e6 + 7;
			int[] prevRow = new int[B + 1];
			prevRow[0] = 1;

			for (int i = 1; i < A.size() + 1; i++) {

				int[] currentRow = new int[B + 1];
				for (int j = 0; j < B + 1; j++) {

					if (j == 0) {
						currentRow[j] = 1;
					} else {

						if (j >= A.get(i - 1)) {
							currentRow[j] = currentRow[j - A.get(i - 1)] + prevRow[j];
							currentRow[j] %= mod;
						} else {
							currentRow[j] = prevRow[j];
						}
					}
				}
				prevRow = currentRow;
			}
			return prevRow[B] % mod;
		}

	}

	public static void main(String[] args) {
		CoinSumInfinite obj = new CoinSumInfinite();
		System.out.println(obj.new UsingKnapsack().coinchange2(
				new ArrayList<Integer>(Arrays.asList(18, 24, 23, 10, 16, 19, 2, 9, 5, 12, 17, 3, 28, 29, 4, 13, 15, 8)),
				458));

		System.out.println(obj.new DP().coinchange2(
				new ArrayList<Integer>(Arrays.asList(18, 24, 23, 10, 16, 19, 2, 9, 5, 12, 17, 3, 28, 29, 4, 13, 15, 8)),
				458));

		System.out.println(obj.new DP().coinchange2(new ArrayList<Integer>(Arrays.asList(1, 2, 3)), 4));
		System.out.println(obj.new SpaceOptimizedDP().coinchange2(new ArrayList<Integer>(Arrays.asList(1, 2, 3)), 4));
	}

}
