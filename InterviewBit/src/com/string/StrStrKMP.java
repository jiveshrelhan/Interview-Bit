package com.string;

public class StrStrKMP {
	private int[] kmp(String pattern) {
		String s = pattern;
		int[] pie = new int[s.length()];

		int i = 1, j = 0;
		while (i < s.length()) {

			j = pie[i - 1];

			while (j > 0 && s.charAt(i) != s.charAt(j)) {
				j = pie[j - 1];
			}

			// one more than previous match
			if (s.charAt(i) == s.charAt(j)) {
				j++;
			}

			pie[i] = j;
			i++;
		}

		return pie;
	}

	// DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
	public int strStr(final String A, final String B) {
		int n = A.length(), m = B.length();
		if (n == 0 || m == 0 || m > n) {
			return -1;
		}

		int[] pie = kmp(B);

		int j = 0;
		for (int i = 0; i < n; i++) {
			while (j > 0 && A.charAt(i) != B.charAt(j)) {
				j = pie[j-1];
			}
			if (A.charAt(i) == B.charAt(j)) {
				j++;
			}

			if (j == m) {
				return i - m + 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		StrStrKMP obj = new StrStrKMP();
		System.out.println(obj.strStr("abaac", "aa"));
	}
}
