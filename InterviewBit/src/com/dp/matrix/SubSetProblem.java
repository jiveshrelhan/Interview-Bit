package com.dp.matrix;

public class SubSetProblem {

	class Recursion {

		private boolean helper(int current, int target, int[] A) {
			if (target == 0) {
				return true;
			}
			if (target < 0) {
				return false;
			}

			if (current >= A.length) {
				return false;
			}
			return helper(current + 1, target - A[current], A) || helper(current + 1, target, A);
		}

		public boolean solve(int[] A, int k) {
			return helper(0, k, A);
		}

	}

	/*
	 * Is simple
	 */
	class Memo {
	}

	/*
	 * https://www.youtube.com/watch?v=tRpkluGqINc Pep-coding
	 * 
	 * Batting logic : If current player score x run then left (required - x) run
	 * needs to be scored by remaining players.
	 * 
	 * DP[NewPlayer][Required] = DP[OtherPlayers][Required - Player[capability]
	 * 
	 * else
	 * 
	 * DP[NewPlayer][Required] = NoRunScored + DP[OtherPlayers][Required]; since
	 * situation is still same just one more bats-man on 0.
	 */
	class DP {

		public boolean solve(int[] A, int k) {
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
			return dp[A.length][k];
		}
	}

	public static void main(String[] args) {
		SubSetProblem obj = new SubSetProblem();
		System.out.println(obj.new Recursion().solve(new int[] { 1, 5, 2 }, 4));
		System.out.println(obj.new DP().solve(new int[] { 1, 5, 2 }, 4));

	}

}
