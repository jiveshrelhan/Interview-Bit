package com.dp.strings;

import java.util.HashMap;

public class ScrammbleString {

	/*
	 * I also thought, we might need to break in every position and check if current
	 * set of splits strings are scrambled. And also thought, for every string
	 * having length >=2 have 2 choice either swap them or not.
	 * 
	 */
	/*
	 * Challenges faced: Do i need to form string and compare ? Didn't figured out
	 * transitions diagrams
	 */
	/*
	 * Aditya's verma algorithm. Recursively split the string from length 1 to n-1
	 * (since no empty strings are allowed). Then 2 choices : say we split at len 2,
	 * then compare First(2,A) with last(B) and Last(A) with First of(2,B) and other
	 * choice is not swap:compare first substring of A with first substring of B and
	 * remaining substring of A with remaining substring of B.
	 */
	class RawMCM {
		private boolean mcm(String A, String B) {
			if (A.length() != B.length()) {
				return false;
			}
			/*
			 * Very important base case: I was thinking to check on single character base
			 * case. but this is good if the current whole string is equal then no need to
			 * check.
			 */
			if (A.equals(B)) {
				return true;
			}
			/*
			 * We don't need to handle case when both are empty. It is handled by equals
			 * method.
			 */

			/*
			 * If any string is non empty return false;
			 */
			if (A.isEmpty() || B.isEmpty()) {
				return false;
			}

			for (int i = 1; i <= A.length() - 1; i++) {
				// First with Last and Last with First
				boolean case1 = mcm(A.substring(0, i), B.substring(B.length() - i))
						&& mcm(A.substring(i), B.substring(0, B.length() - i));

				// First with First and Last with Last
				boolean case2 = mcm(A.substring(0, i), B.substring(0, i)) && mcm(A.substring(i), B.substring(i));

				// If any is true break don't need to explore other's i.
				if (case1 || case2) {
					return true;
				}
			}
			return false;
		}

		public int isScramble(final String A, final String B) {
			/*
			 * Global base cases.
			 */
			if (A.isEmpty() && B.isEmpty()) {
				return 1;
			}
			if (A.length() != B.length()) {
				return 0;
			}
			return mcm(A, B) ? 1 : 0;
		}
	}

	class Memo {
		HashMap<String, Boolean> memo = new HashMap<>();

		private boolean mcm(String A, String B) {
			if (A.length() != B.length()) {
				return false;
			}
			if (A.equals(B)) {
				return true;
			}
			if (A.isEmpty() || B.isEmpty()) {
				return false;
			}
			String key = A.concat(" ").concat(B);
			if (memo.containsKey(key)) {
				return memo.get(key);
			}
			for (int i = 1; i <= A.length() - 1; i++) {
				boolean case1 = mcm(A.substring(0, i), B.substring(B.length() - i))
						&& mcm(A.substring(i), B.substring(0, B.length() - i));

				boolean case2 = mcm(A.substring(0, i), B.substring(0, i)) && mcm(A.substring(i), B.substring(i));

				if (case1 || case2) {
					return true;
				}
				memo.put(key, false);
			}
			return false;
		}

		public int isScramble(final String A, final String B) {
			if (A.isEmpty() && B.isEmpty()) {
				return 1;
			}
			if (A.length() != B.length()) {
				return 0;
			}
			return mcm(A, B) ? 1 : 0;
		}
	}

	public static void main(String[] args) {
		ScrammbleString obj = new ScrammbleString();
		// System.out.println(obj.new RawMCM().isScramble("knxhpkpcogzwrwdyfksw",
		// "fpsprgdywowkckhzxnkw"));
		System.out.println(obj.new Memo().isScramble("knxhpkpcogzwrwdyfksw", "fpsprgdywowkckhzxnkw"));
	}
}
