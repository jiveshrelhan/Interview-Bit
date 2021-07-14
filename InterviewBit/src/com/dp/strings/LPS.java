package com.dp.strings;

public class LPS {
	class DP {
		/*
		 * Same as LPS = LCS(A,reverse(A))
		 */
		public int solve(String A) {
			String B = new StringBuilder(A).reverse().toString();
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
			return dp[A.length()][B.length()];
		}
	}
	public static void main(String[] args) {
		LPS obj = new LPS();
		System.out.println(obj.new DP().solve("bebeeed"));
	}
}
