package com.string;

import java.util.Arrays;

public class LCSubstring {
	class TLE {
		public String longestCommonPrefix(String[] A) {
			if (A.length == 0) {
				return "";
			}

			String prev = A[0];
			for (int i = 1; i < A.length; i++) {
				StringBuilder newLCP = new StringBuilder();
				int prevChar = 0, currChar = 0;
				String current = A[i];
				while (prevChar < prev.length() && currChar < current.length()) {
					if (prev.charAt(prevChar) == current.charAt(currChar)) {
						newLCP.append(prev.charAt(prevChar));
					}
					prevChar++;
					currChar++;
				}
				prev = newLCP.toString();
			}

			return prev;
		}
	}

	class Optimized {
		public String longestCommonPrefix(String[] A) {

			/* Variable to store the longest common prefix string */
			StringBuilder result = new StringBuilder();

			if (A.length == 0)
				return result.toString();

			/* Sort the strings in ascending order */
			Arrays.sort(A);

			/* Compare only the characters in first and last string of sorted array */
			String first = A[0];
			String last = A[A.length - 1];

			int minlen = Math.min(first.length(), last.length());

			int i = 0;
			while (i < minlen) {

				if (first.charAt(i) == last.charAt(i))
					/* Same characters, append it to result */
					result.append(first.charAt(i));
				else
					/* Distinct characters, no need for checking further characters */
					break;

				i++;
			}

			return result.toString();
		}
	}
	
	public static void main(String[] args) {
		LCSubstring obj = new LCSubstring();
		System.out.println(obj.new Optimized().longestCommonPrefix(new String[] {"abcd","ab","abab"}));
	}
}
