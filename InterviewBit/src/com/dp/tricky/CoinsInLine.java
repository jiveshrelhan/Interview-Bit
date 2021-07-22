package com.dp.tricky;

import java.util.Arrays;

public class CoinsInLine {

	class Memo {
		int[][] memo;

		private int helper(int i, int j, int[] A) {
			if (i > j) {
				return 0;
			}
			if (memo[i][j] != -1) {
				return memo[i][j];
			}
			int case1 = A[i] + Math.min(helper(i + 1, j - 1, A), helper(i + 2, j, A));
			int case2 = A[j] + Math.min(helper(i + 1, j - 1, A), helper(i, j - 2, A));
			return memo[i][j] = Math.max(case1, case2);
		}

		public int maxcoin(int[] A) {
			memo = new int[A.length][A.length];
			for (int[] rows : memo) {
				Arrays.fill(rows, -1);
			}
			return helper(0, A.length - 1, A);
		}
	}

	// Need to learn tabular version of it. Didn't understand how table got created.
	class DP {

	}

	public static void main(String[] args) {
		CoinsInLine obj = new CoinsInLine();
		System.out.println(obj.new Memo().maxcoin(new int[] { 1, 2, 3, 4 }));
		System.out.println(obj.new Memo().maxcoin(new int[] { 5, 4, 8, 10 }));
		System.out.println(obj.new Memo().maxcoin(new int[] { 1 }));
	}

}
