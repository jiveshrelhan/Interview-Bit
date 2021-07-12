package com.dp;

import java.util.Arrays;

public class DistinctSubsequences {
	class Recursion {

		public int recursionHelper(String A, String B, int i, int j) {

			// When t is empty
			if (j < 0) {
				return 1;
			}

			if (i < 0) {
				return 0;
			}

			// If character matches we have to 2 choices either consider the current or skip
			// it
			// else just skip it
			if (A.charAt(i) == B.charAt(j)) {
				int include = recursionHelper(A, B, i - 1, j - 1);
				int exclude = recursionHelper(A, B, i - 1, j);
				return include + exclude;
			} else {
				int exclude = recursionHelper(A, B, i - 1, j);
				return exclude;
			}
		}

		public int numDistinct(String A, String B) {
			if (A.isEmpty() || B.isEmpty()) {
				return 0;
			}
			return recursionHelper(A, B, A.length() - 1, B.length() - 1);
		}
	}

	/*
	 * Ignore it
	 */
	class NewRecursion {
		public int recursionHelper(String A, String B, int i, int j) {

			if (i < 0 || j < 0) {
				return 0;
			}
			int a = 0, b = 0, c = 0;
			if (A.charAt(i) == B.charAt(j)) {
				a = 1 + recursionHelper(A, B, i - 1, j - 1);
			}
			b = recursionHelper(A, B, i - 1, j);
			c = recursionHelper(A, B, i, j - 1);
			System.out.println("A : " + a + " B	" + b + " C " + c);
			return Math.max(a, Math.max(b, c));
		}

		public int numDistinct(String A, String B) {
			if (A.isEmpty() || B.isEmpty()) {
				return 0;
			}
			return recursionHelper(A, B, A.length() - 1, B.length() - 1);
		}
	}

	/*
	 * This also worked but gave TLE
	 */
	class MemoziedTLE {
		int[][][] memo;

		public int recursionHelper(String A, String B, int i, int j, int k, int target) {

			if (i < 0 || j < 0) {
				if (k == target)
					return 1;
				return 0;
			}
			if (memo[i][j][k] != -1) {
				return memo[i][j][k];
			}
			int a = 0, b = 0, c = 0;
			if (A.charAt(i) == B.charAt(j)) {
				a = recursionHelper(A, B, i - 1, j - 1, k + 1, target);
			}
			b = recursionHelper(A, B, i - 1, j, k, target);
			c = recursionHelper(A, B, i, j - 1, k, target);
			return memo[i][j][k] = a + b + c;
		}

		public int numDistinct(String A, String B) {
			if (A.isEmpty() || B.isEmpty()) {
				return 0;
			}

			int target = B.length();

			memo = new int[A.length()][B.length()][target];

			for (int[][] x : memo) {
				for (int[] y : x) {
					Arrays.fill(y, -1);
				}
			}

			int ans = recursionHelper(A, B, A.length() - 1, B.length() - 1, 0, target);
			return ans;
		}
	}

	class ClassicRecursionByNikhil {
		public int count = 0;

		public void solve(String A, String B, int i, int j, int k, int target) {

			if (i < 0 || j < 0) {
				if (k == target)
					count++;
				return;
			}
			if (A.charAt(i) == B.charAt(j)) {
				solve(A, B, i - 1, j - 1, k + 1, target);
			}
			solve(A, B, i - 1, j, k, target);
			solve(A, B, i, j - 1, k, target);
		}

	}

	class Memoized {
		int memo[][];

		public int recursionHelper(String A, String B, int i, int j) {

			// When both strings are empty
			if (j < 0) {
				return 1;
			}

			if (i < 0) {
				return 0;
			}

			if (memo[i][j] != -1) {
				return memo[i][j];
			}
			// If character matches we have to 2 choices either consider the current or skip
			// else just skip it
			if (A.charAt(i) == B.charAt(j)) {
				int include = recursionHelper(A, B, i - 1, j - 1);
				int exclude = recursionHelper(A, B, i - 1, j);
				return memo[i][j] = include + exclude;
			} else {
				int exclude = recursionHelper(A, B, i - 1, j);
				return memo[i][j] = exclude;
			}
		}

		public int numDistinct(String A, String B) {
			if (A.isEmpty() || B.isEmpty()) {
				return 0;
			}
			memo = new int[A.length()][B.length()];
			for (int[] rows : memo) {
				Arrays.fill(rows, -1);
			}
			return recursionHelper(A, B, A.length() - 1, B.length() - 1);
		}
	}

	class DP {
		public int numDistinct(String A, String B) {
			if (A.isEmpty() || B.isEmpty()) {
				return 0;
			}
			int[][] dp = new int[A.length() + 1][B.length() + 1];

			//When t is empty
			for(int i=0;i<A.length()+1;i++) {
				dp[i][0]=1;
			}
			
			for (int i = 1; i < A.length() + 1; i++) {
				for (int j = 1; j < B.length() + 1; j++) {
					if (A.charAt(i - 1) == B.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
			
			/*
			for (int[] r : dp) {
				System.out.println(Arrays.toString(r));
			}
			*/

			return dp[A.length()][B.length()];

		}
	}

	public static void main(String[] args) {
		DistinctSubsequences obj = new DistinctSubsequences();
		String A = "abb";
		String B = "bb";
		// System.out.println(obj.new NewRecursion().numDistinct(A, B));
		System.out.println(obj.new Recursion().numDistinct(A, B));
		System.out.println(obj.new Memoized().numDistinct(A, B));
		System.out.println(obj.new DP().numDistinct(A, B));
	}
}
