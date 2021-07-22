package com.dp.tricky;

import java.util.Arrays;

/*
 * Intuition: Find the longest Common Substring between pairs: i and i+1
 * and remove the common part from the string and merge them.
 * repeat for all indexes
 */

/*
 * How to find LongestCommonSubstring
 */
public class ShortestCommonSuperString {
	int memo[][];
	int maxSeenSoFar = 0;
	String commonString = "";

	private int LCSubstring(int i, int j, String A, String B, String common, int count) {

		if (common.length() > maxSeenSoFar) {
			maxSeenSoFar = common.length();
			commonString = common;
		}

		if (i == A.length() || j == B.length()) {
			return count;
		}

		if (memo[i][j] != -1) {
			return memo[i][j];
		}

		int include = 0;
		if (A.charAt(i) == B.charAt(j)) {
			include = LCSubstring(i + 1, j + 1, A, B, common + A.charAt(i), count + 1);
		}
		int exclude = Math.max(LCSubstring(i + 1, j, A, B, "", 0), LCSubstring(i, j + 1, A, B, "", 0));

		return memo[i][j] = Math.max(include, exclude);
	}

	public int solve(String[] A) {
		if (A.length == 1) {
			return A[0].length();
		}
		String currentString = A[0];
		for (int i = 1; i < A.length; i++) {
			maxSeenSoFar = -1;
			memo = new int[currentString.length() + 1][A[i].length() + 1];
			for (int[] rows : memo) {
				Arrays.fill(rows, -1);
			}
			LCSubstring(0, 0, currentString, A[i], "", 0);
			String commString = commonString;
			// System.out.println("Common : " + commString);
			int index = A[i].indexOf(commString);
			String start = A[i].substring(0, index);
			String end = A[i].substring(index + commString.length());
			currentString += start;
			currentString += end;
		}
		// System.out.println(currentString);
		return currentString.length();
	}

	public static void main(String[] args) {
		ShortestCommonSuperString obj = new ShortestCommonSuperString();
		System.out.println(obj.solve(new String[] { "abcd", "cdef", "fgh", "de" }));
		System.out.println(obj.solve(new String[] { "aaaa", "aa" }));
		System.out.println(obj.solve(new String[] { "aaaa", "bbbb" }));
		System.out.println(obj.solve(new String[] { "cpsklryvmcp", "nbpbwllsrehfmx", "kecwitrsglre", "vtjmxypu" }));
	}
}
