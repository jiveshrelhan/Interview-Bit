package com.dp;

import java.util.Arrays;

/*
 * State : index of both strings 
 * Score : cost
 */
public class EditDistance {
	class Classic {

		private int recursionHelper(String A, String B, int i, int j) {
			if (i == -1 && j == -1) {
				return 0;
			}
			if (i == -1) {
				return j + 1;
			}
			if (j == -1) {
				return i + 1;
			}
			if (A.charAt(i) == B.charAt(j)) {
				return recursionHelper(A, B, i - 1, j - 1);
			}
			int deleteCost = recursionHelper(A, B, i - 1, j);
			int insertCost = recursionHelper(A, B, i, j - 1);
			int replaceCost = recursionHelper(A, B, i - 1, j - 1);
			return 1 + Math.min(deleteCost, Math.min(insertCost, replaceCost));
		}

		public int minDistance(String A, String B) {
			if (A.isEmpty()) {
				return B.length();
			} else if (B.isEmpty()) {
				return A.length();
			}
			return recursionHelper(A, B, A.length() - 1, B.length() - 1);
		}
	}

	/*
	 * Solved this question in more organic way; Was confused with partial memory of
	 * Aaditya's solution where he merge cost of replacement and insertion in one
	 * case. And Added a global condition. If A is always larger then B if not swap
	 * then run the algo.
	 * 
	 */
	class Memoized {
		int[][] memo;

		private int recursionHelper(String A, String B, int i, int j) {
			// When both get emptied. Was confused it should be infinite or 0 but later
			// realized
			// for 2 empty string distance is always 0 since both are same.
			if (i == -1 && j == -1) {
				return 0;
			}
			// if A is empty then insert all remaining characters of B (index+1)
			if (i == -1) {
				return j + 1;
			}
			// Similarly for B.
			if (j == -1) {
				return i + 1;
			}
			if (memo[i][j] != -1) {
				return memo[i][j];
			}
			// General cases if current characters match then no need to add cost and move
			// ahead.
			if (A.charAt(i) == B.charAt(j)) {
				return memo[i][j] = recursionHelper(A, B, i - 1, j - 1);
			}
			// else take the minimum of 3 operations.
			int deleteCost = recursionHelper(A, B, i - 1, j);
			int insertCost = recursionHelper(A, B, i, j - 1);
			int replaceCost = recursionHelper(A, B, i - 1, j - 1);
			return memo[i][j] = 1 + Math.min(deleteCost, Math.min(insertCost, replaceCost));
		}

		public int minDistance(String A, String B) {
			if (A.isEmpty()) {
				return B.length();
			} else if (B.isEmpty()) {
				return A.length();
			}
			memo = new int[A.length() + 1][B.length() + 1];
			for (int[] rows : memo) {
				Arrays.fill(rows, -1);
			}
			return recursionHelper(A, B, A.length() - 1, B.length() - 1);
		}
	}

	/*
	 * solve this by converting the memo solution to array based. didn't reach or
	 * thought this directly. More focus required on Push vs Pull DP by Erricho
	 */
	class DP {
		public int minDistance(String A, String B) {
			if (A.isEmpty()) {
				return B.length();
			} else if (B.isEmpty()) {
				return A.length();
			}
			int n = A.length(), m = B.length();
			int[][] dp = new int[n + 1][m + 1];

			// Initialized matrix by base conditions
			for (int i = 0; i <= n; i++) {
				dp[i][0] = i;
			}

			for (int j = 0; j <= m; j++) {
				dp[0][j] = j;
			}

			// Normal case conversation from memozied solution.
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (A.charAt(i - 1) == B.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						int deleteCost = dp[i - 1][j];
						int insertCost = dp[i][j - 1];
						int replaceCost = dp[i - 1][j - 1];
						dp[i][j] = 1 + Math.min(deleteCost, Math.min(insertCost, replaceCost));
					}
				}
			}

			return dp[n][m];
		}
	}

	public static void main(String[] args) {
		EditDistance obj = new EditDistance();
		System.out.println(obj.new Classic().minDistance("aac", "abac"));
		System.out.println(obj.new Classic().minDistance("Anshuman", ""));
		System.out.println(obj.new Memoized().minDistance("aac", "abac"));
		System.out.println(obj.new Memoized().minDistance("abac", "aac"));
		System.out.println(obj.new Memoized().minDistance("aaa", "aaaa"));
		System.out.println(obj.new DP().minDistance("abac", "aac"));
		System.out.println(obj.new DP().minDistance("aaa", ""));
	}
}
