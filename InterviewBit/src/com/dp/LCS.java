package com.dp;

import java.util.Arrays;

public class LCS {
	class Memozied {
		int[][] memo;

		private int recursionHelper(String A, String B, int i, int j) {
			/*
			 * Base Case : Smallest valid case. length 0 or empty string is smallest valid
			 * case. In this case index will be -1. therefore if either A or B goes
			 * exhausted return 0 as LCS
			 */
			if (i < 0 || j < 0) {
				return 0;
			}
			if (memo[i][j] != -1) {
				return memo[i][j];
			}
			/*
			 * Case 1: When characters matches : then only one path to follow.
			 */
			/*
			 * Case 2: Else choose the best from both hit and trials
			 */
			if (A.charAt(i) == B.charAt(j)) {
				memo[i][j] = 1 + recursionHelper(A, B, i - 1, j - 1);
			} else {
				memo[i][j] = Math.max(recursionHelper(A, B, i - 1, j), recursionHelper(A, B, i, j - 1));
			}
			return memo[i][j];

		}

		public int solve(String A, String B) {
			if (A.isEmpty() || B.isEmpty()) {
				return 0;
			}
			/*
			 * Always needs to fill default value as non-valid answer of any input. since 0
			 * can be valid answer of any 2 strings therefore filled []s with -1
			 */
			memo = new int[A.length() + 1][B.length() + 1];
			for (int[] x : memo) {
				Arrays.fill(x, -1);
			}
			return recursionHelper(A, B, A.length() - 1, B.length() - 1);
		}
	}

	class DP {

		public int solve(String A, String B) {
			/*
			 * Same base case: If any of string is {}; LCS is 0
			 */
			if (A.isEmpty() || B.isEmpty()) {
				return 0;
			}

			// Same as memo; Always create 1 + Dimensions

			/*
			 * In memo, case it was required to handle AOB exception but here in
			 * Tabulization approach it has business need.
			 */

			/*
			 * Since we are going to build up currentState using the previous states [i-1]
			 * vs [j-1]. We can consider A as {} empty string against B and similarly
			 * another valid case is B is {} against A.
			 */

			/*
			 * Initialize the DP with valid base cases. If A or B is {} then LCS will be 0;
			 * Since int has 0 as default value; No dedicated initlization statement
			 * required.
			 */
			int[][] dp = new int[A.length() + 1][B.length() + 1];

			/*
			 * Start from 1,1 first column and first row is computed using base case.
			 */
			for (int i = 1; i < dp.length; i++) {
				for (int j = 1; j < dp[0].length; j++) {

					if (A.charAt(i - 1) == B.charAt(j - 1)) {
						dp[i][j] = 1 + dp[i - 1][j - 1];
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					}
				}
			}
			/*
			 * Return the bottom right corner of [][].
			 */
			for(int[] r : dp) {
				System.out.println(Arrays.toString(r));
			}
			return dp[A.length()][B.length()];
		}
	}

	/*
	 * This was not straight forward to code. Need to revise this and re-code
	 */
	class SpaceOptimizedDP {

		public int solve(String A, String B) {
			/*
			 * Same base case: If any of string is {}; LCS is 0
			 */
			if (A.isEmpty() || B.isEmpty()) {
				return 0;
			}

			/*
			 * In our normal DP based solution, we were only accessing current and previous
			 * row.
			 * Instead of creating [][] of AxB we can simply use 1D which store previous row.
			 */
			
			/*
			 *Remove any A or B from the DP state matrix here i have used A and remove B. 
			 */
			int[] currentRow = new int[A.length() + 1];

			/*
			 * Very important I traversing B first then in inner loop iterating over A.
			 * We need to compute [] of A size B times.
			 */

			for (int i = 1; i < B.length() + 1; i++) {
				int prev = 0;
				for (int j = 1; j < A.length() + 1; j++) {
					// create notes with diagram.
					int saved = currentRow[j];
					if (A.charAt(j - 1) == B.charAt(i - 1)) {
						currentRow[j] = 1 + prev;
					} else {
						currentRow[j] = Math.max(currentRow[j - 1], currentRow[j]);
					}
					prev = saved;
				}
			}

			return currentRow[currentRow.length - 1];
		}
	}

	public static void main(String[] args) {
		LCS obj = new LCS();
		System.out.println(obj.new Memozied().solve("abbcdgf", "bbadcgf"));
		System.out.println(obj.new DP().solve("rabbbit", "rabbit"));
		System.out
				.println(obj.new SpaceOptimizedDP().solve("bebdeeedaddecebbbbbabebedc", "abaaddaabbedeedeacbcdcaaed"));
	}
}
