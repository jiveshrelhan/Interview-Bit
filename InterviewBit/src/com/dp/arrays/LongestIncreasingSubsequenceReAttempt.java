package com.dp.arrays;

import java.util.Arrays;

/*
 * DP code is wrong check re-attempt
 */
public class LongestIncreasingSubsequenceReAttempt {

	class Recursion {

		private int helper(int currentIndex, int previousSelected, int length, int[] A) {
			if (currentIndex == A.length) {
				return length;
			}
			int max = Integer.MIN_VALUE;
			for (int i = currentIndex; i < A.length; i++) {
				if (previousSelected == -1 || A[i] > A[previousSelected]) {
					max = Math.max(max, helper(i, i, length + 1, A));
				}
			}
			max = Math.max(max, helper(currentIndex + 1, previousSelected, length, A));
			return max;
		}

		public int lis(final int[] A) {
			return helper(0, -1, 0, A);
		}

	}

	class CorrectDP {
		public int LIS(int[] arr) {
			/*
			 * Since we don't need {empty} input to create base case: we are not declaring
			 * len + 1 [].
			 */
			int dp[] = new int[arr.length];
			Arrays.fill(dp, 1);

			for (int current = 1; current < arr.length; current++) {
				for (int previous = 0; previous < current; previous++) {
					if (arr[current] > arr[previous]) {
						dp[current] = Math.max(dp[current], 1 + dp[previous]);
					}
				}
			}

			int max = 1;
			for (int i = 0; i < dp.length; i++) {
				max = Math.max(max, dp[i]);
			}
			return max;
		}
	}

	public static void main(String[] args) {
		LongestIncreasingSubsequenceReAttempt obj = new LongestIncreasingSubsequenceReAttempt();
		System.out.println(obj.new Recursion().lis(new int[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 }));
	}

}
