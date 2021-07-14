package com.dp.strings;

import java.util.Arrays;

/*
 * No need to solve DP version of it. No learning one line change.
 */
public class LongestRepeatedSubsequence {
	/*
	 * Tried hint 1 from interview bit but hint 1 explains all the logic.
	 * 
	 */
	/*
	 * Algorithm : LCS(A,A) and add a extra condition i!=j while comparing characters
	 * from the string.
	 */
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
			/* IMPORTANT
			 * Added extra condition i!=j since we need different position subsequence
			 */
			if (i != j && A.charAt(i) == B.charAt(j)) {
				memo[i][j] = 1 + recursionHelper(A, B, i - 1, j - 1);
			} else {
				memo[i][j] = Math.max(recursionHelper(A, B, i - 1, j), recursionHelper(A, B, i, j - 1));
			}
			return memo[i][j];

		}

		public int anytwo(String A) {
			if (A.isEmpty()) {
				return 0;
			}
			/*
			 * Always needs to fill default value as non-valid answer of any input. since 0
			 * can be valid answer of any 2 strings therefore filled []s with -1
			 */
			memo = new int[A.length() + 1][A.length() + 1];
			for (int[] x : memo) {
				Arrays.fill(x, -1);
			}
			int length = recursionHelper(A, A, A.length() - 1, A.length() - 1);
			return length >= 2 ? 1 : 0;
		}
	}

	public static void main(String[] args) {
		LongestRepeatedSubsequence obj = new LongestRepeatedSubsequence();
		System.out.println(obj.new Memozied().anytwo("aa"));
	}
}
