package com.dp.strings;

public class RegularExpression1 {

	class Recursion {
		/*-
		 *  Case : 					abcd
		 *  Regular Expression :  	a*e
		 */
		/*- Transitions
		 *  if char is ? : then skip and continue : i+1,j+1 
		 *  if char is * : then 2 choices consider * for current character then i+1,j
		 *  			   done with *, then i+1,j+1
		 *  else : match exact A.charAt(i) == B.charAt(j) if yes then i+1,j+1 else return false;
		 */
		/*
		 * Base case: when we exhaust string A and String B or only * is remaining in
		 * String B
		 */
		public int isMatch(final String A, final String B) {
			return solve(A, B, 0, 0) ? 1 : 0;
		}

		/*-
		 * MISSED Transition: * can be used for empty as well.
		 * case 3 , i,j+1. skip * 
		 */
		private boolean solve(String A, String B, int i, int j) {

			if (i == A.length() && j == B.length()) {
				return true;
			}
			if (j == B.length())
				return false;

			if (i == A.length()) {
				int k = j;
				while (k < B.length() && B.charAt(k) == '*') {
					k++;
				}
				return k == B.length() ? true : false;
			}

			int regChar = B.charAt(j);

			if (regChar == '?') {
				return solve(A, B, i + 1, j + 1);
			} else if (regChar == '*') {
				return solve(A, B, i + 1, j) || solve(A, B, i + 1, j + 1) || solve(A, B, i, j + 1);
			} else {
				if (regChar == A.charAt(i)) {
					return solve(A, B, i + 1, j + 1);
				} else {
					return false;
				}
			}
		}

	}

	/*
	 * Memo throws Out of Heap Exception: Need to code in Bottom-Up Only
	 */
	class Memo {
		Boolean[][] memo;

		public int isMatch(final String A, final String B) {
			memo = new Boolean[A.length()][B.length()];
			return solve(A, B, 0, 0) ? 1 : 0;
		}

		private boolean solve(String A, String B, int i, int j) {

			if (i == A.length() && j == B.length()) {
				return true;
			}
			if (j == B.length())
				return false;

			if (i == A.length()) {
				int k = j;
				while (k < B.length() && B.charAt(k) == '*') {
					k++;
				}
				return k == B.length() ? true : false;
			}

			if (memo[i][j] != null) {
				return memo[i][j];
			}

			int regChar = B.charAt(j);

			if (regChar == '?') {
				memo[i][j] = solve(A, B, i + 1, j + 1);
			} else if (regChar == '*') {
				memo[i][j] = (solve(A, B, i + 1, j) || solve(A, B, i + 1, j + 1) || solve(A, B, i, j + 1));
			} else {
				if (regChar == A.charAt(i)) {
					memo[i][j] = solve(A, B, i + 1, j + 1);
				} else {
					memo[i][j] = false;
				}
			}
			return memo[i][j];
		}

	}

	class DP {

		public int isMatch(final String A, final String B) {
			boolean[][] dp = new boolean[A.length() + 1][B.length() + 1];

			for (int i = 0; i < A.length() + 1; i++) {
				for (int j = 0; j < B.length() + 1; j++) {
					if (i == 0 && j == 0) {
						dp[i][j] = true;
					} else if (j == 0) {
						dp[i][0] = false;
						// Stress Point: why previous dp[0][j-1]
						// because it has to be continuous
					} else if (i == 0) {
						if (B.charAt(j - 1) == '*') {
							dp[0][j] = dp[0][j - 1];
						} else {
							dp[0][j] = false;
						}
					} else {
						if (B.charAt(j - 1) == '?') {
							dp[i][j] = dp[i - 1][j - 1];
						} else if (B.charAt(j - 1) == '*') {
							dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 1] || dp[i][j - 1];
						} else {
							if (A.charAt(i - 1) == B.charAt(j - 1)) {
								dp[i][j] = dp[i - 1][j - 1];
							} else {
								dp[i][j] = false;
							}
						}
					}
				}
			}
			return dp[A.length()][B.length()] ? 1 : 0;
		}
	}

	public static void main(String[] args) {
		RegularExpression1 obj = new RegularExpression1();
		System.out.println(obj.new Recursion().isMatch("abcd", "a*e"));
		System.out.println(obj.new Recursion().isMatch("aa", "a"));
		System.out.println(obj.new Recursion().isMatch("aa", "aa"));
		System.out.println(obj.new Recursion().isMatch("aaa", "aa"));
		System.out.println(obj.new Recursion().isMatch("aa", "*"));
		System.out.println(obj.new Recursion().isMatch("aa", "aa*"));
		System.out.println(obj.new Recursion().isMatch("ab", "?*"));
		System.out.println(obj.new Recursion().isMatch("aab", "c*a*b"));
		System.out.println("-------------");
		System.out.println(obj.new Memo().isMatch("abcd", "a*e"));
		System.out.println(obj.new Memo().isMatch("aa", "a"));
		System.out.println(obj.new Memo().isMatch("aa", "aa"));
		System.out.println(obj.new Memo().isMatch("aaa", "aa"));
		System.out.println(obj.new Memo().isMatch("aa", "*"));
		System.out.println(obj.new Memo().isMatch("aa", "aa*"));
		System.out.println(obj.new Memo().isMatch("ab", "?*"));
		System.out.println(obj.new Memo().isMatch("aab", "c*a*b"));
		System.out.println("-------------");
		System.out.println(obj.new DP().isMatch("abcd", "a*e"));
		System.out.println(obj.new DP().isMatch("aa", "a"));
		System.out.println(obj.new DP().isMatch("aa", "aa"));
		System.out.println(obj.new DP().isMatch("aaa", "aa"));
		System.out.println(obj.new DP().isMatch("aa", "*"));
		System.out.println(obj.new DP().isMatch("aa", "aa*"));
		System.out.println(obj.new DP().isMatch("ab", "?*"));
		System.out.println(obj.new DP().isMatch("aab", "c*a*b"));
	}
}
