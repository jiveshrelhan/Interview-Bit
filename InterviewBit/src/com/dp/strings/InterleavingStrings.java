package com.dp.strings;

public class InterleavingStrings {

	class RawRecursion {
		/*-
		 * Intuition: Either take character from A or B
		 * we need to form C.
		 * if required character is x and if only available from A and B take it.
		 * if it available from both : explore both take from A and solve and B and solve.
		 * if character is not matching with A,B return false;
		 */
		public int isInterleave(String A, String B, String C) {
			if (A.length() + B.length() != C.length()) {
				return 0;
			}
			return solve(A, B, C, 0, 0, 0) ? 1 : 0;
		}

		/*-
		 * This is one of correct solution given in solution approach in interview bit.
		 */
		private boolean solve(String A, String B, String C, int i, int j, int k) {

			if (k == C.length()) {
				return true;
			}

			char requirement = C.charAt(k);

			if (i < A.length() && A.charAt(i) == requirement && solve(A, B, C, i + 1, j, k + 1)) {
				return true;
			} else if (j < B.length() && B.charAt(j) == requirement && solve(A, B, C, i, j + 1, k + 1)) {
				return true;
			}
			return false;
		}

	}

	/*-
	 * Think where we need to use only 2 changing params instead of 3.
	 */
	class Recursion {
		public int isInterleave(String A, String B, String C) {
			if (A.length() + B.length() != C.length()) {
				return 0;
			}
			return solve(A, B, C, 0, 0) ? 1 : 0;
		}

		private boolean solve(String A, String B, String C, int i, int j) {

			if (i + j == C.length()) {
				return true;
			}

			char requirement = C.charAt(i + j);

			if (i < A.length() && A.charAt(i) == requirement && solve(A, B, C, i + 1, j)) {
				return true;
			} else if (j < B.length() && B.charAt(j) == requirement && solve(A, B, C, i, j + 1)) {
				return true;
			}
			return i < 0 && j < 0;
		}
	}

	class Memo {
		/*
		 * simple recursion to meme conversion. Todo
		 */
	}

	class DP {
		/*- 
		 * Direct conversion of recursion relation
		 * if(A.charAt(i) == C.charAt(i+j)) -> DP[i][j] = DP[i-1][j];
		 * if(B.charAt(j) == C.charAt(i+j)) -> DP[i][j] = DP[i][j-1];
		 */

		/*- 
		 * Base condition is tricky
		 * Since there is no base condition in recursion relation then its always better to fill
		 * first row and first column 
		 */

		/*-
		 * First Row means : A is Empty() and B character ({},1st char,2nd char) so on.
		 * Check if we can form C string just using B string.
		 * DP[0][0] = 1; since both strings are empty and c is also empty.
		 * DP[0][j] = (B.charAt(j) == C.charAt(j)) ? DP[0][j-1] : false;
		 * it has to be substring not subsequence thats why previous result is carry forward.
		 */

		/*- Similarly First Column means : A increase from {} to char by char and B is empty.
		 * DP[0][0] = 1/true;
		 * DP[i][0] = A.charAt(i) == C.charAt(i) ? DP[i-1][0] : false;
		 */

		/*- <<IMPORTANT>>
		 * Since we are going to create DP of one additional size. Equation index +-1 needs
		 *to be done.
		 */

		public int isInterleave(String A, String B, String C) {
			if (A.length() + B.length() != C.length()) {
				return 0;
			}
			int[][] dp = new int[A.length() + 1][B.length() + 1];

			for (int i = 0; i < A.length() + 1; i++) {
				for (int j = 0; j < B.length() + 1; j++) {

					if (i == 0 && j == 0) {
						dp[i][j] = 1;
					} else if (i == 0) {
						dp[i][j] = B.charAt(j - 1) == C.charAt(i + j - 1) ? dp[i][j - 1] : 0;
					} else if (j == 0) {
						dp[i][j] = A.charAt(i - 1) == C.charAt(i + j - 1) ? dp[i - 1][j] : 0;
					} else {

						/*- 
						 * We always have choice: by any path we get the answer we should proceed 
						 * means OR condition
						 * That why we will only go to caseB is caseA ans is not true.
						 */
						if (A.charAt(i - 1) == C.charAt(i + j - 1)) {
							dp[i][j] = dp[i - 1][j];
						}

						if (dp[i][j] == 0 && B.charAt(j - 1) == C.charAt(i + j - 1)) {
							dp[i][j] = dp[i][j - 1];
						}
						// Otherwise in all other cases default will be 0 only.
					}
				}
			}
			return dp[A.length()][B.length()];
		}

	}

	public static void main(String[] args) {
		InterleavingStrings obj = new InterleavingStrings();
		System.out.println(obj.new RawRecursion().isInterleave("a", "a", "aa"));
		System.out.println(obj.new Recursion().isInterleave("a", "a", "aa"));
		System.out.println(obj.new DP().isInterleave("noUdRp97xFvpifeSXGwOjcVNhHo9N2D", "6iZItw9A3fj86uYx04tvWKLtl9BK", "n6ioUdRpZ97ItwxF9Av3fj86upYxif0eS4XtvWKLtlG9wOBKjcVNhHo9N2D"));
	}
}
