package com.dp.arrays;

import java.util.Arrays;

public class LengthOfLongestBiotonicSubsequence {
	/*
	 * Trivial case passed but failed for {1,1,1,1,1} : output 2 and expected 1:
	 */
	/*- 
	 * Approach : Compute LIS from 0,N-1 and LDS (N-1,0)
	 * return Max of (LIS[i] + LDS[i+1]) from 0 to N-2.
	 */
	class MyApproach {
		private int[] LIS(int[] arr) {
			int dp[] = new int[arr.length];
			Arrays.fill(dp, 1);

			for (int current = 1; current < arr.length; current++) {
				for (int previous = 0; previous < current; previous++) {
					if (arr[current] > arr[previous]) {
						dp[current] = Math.max(dp[current], 1 + dp[previous]);
					}
				}
			}
			return dp;
		}

		private int[] LDS(int[] arr) {
			int dp[] = new int[arr.length];
			Arrays.fill(dp, 1);

			for (int current = arr.length - 2; current >= 0; current--) {
				for (int previous = current + 1; previous < arr.length; previous++) {
					if (arr[current] > arr[previous]) {
						dp[current] = Math.max(dp[current], 1 + dp[previous]);
					}
				}
			}
			return dp;
		}

		public int longestSubsequenceLength(final int[] A) {
			if (A.length < 2) {
				return A.length;
			}
			int[] LIS = LIS(A), LDS = LDS(A);

			int ans = 0;
			for (int i = 0; i < A.length - 1; i++) {
				ans = Math.max(ans, LIS[i] + LDS[i + 1]);
			}
			return ans;

		}
	}

	/*-
	 *  Equation LIS from 0, N-1 and LDS from N-1,0
	 *  return Max of (LIS[i] + LDS[i] - 1 ) from 0 to N-1 // For all i's
	 */
	class Author {
		private int[] LIS(int[] arr) {
			int dp[] = new int[arr.length];
			Arrays.fill(dp, 1);

			for (int current = 1; current < arr.length; current++) {
				for (int previous = 0; previous < current; previous++) {
					if (arr[current] > arr[previous]) {
						dp[current] = Math.max(dp[current], 1 + dp[previous]);
					}
				}
			}
			System.out.println("LIS : " + Arrays.toString(dp));
			return dp;
		}

		private int[] LDS(int[] arr) {
			int dp[] = new int[arr.length];
			Arrays.fill(dp, 1);

			for (int current = arr.length - 2; current >= 0; current--) {
				for (int previous = current + 1; previous < arr.length; previous++) {
					if (arr[current] > arr[previous]) {
						dp[current] = Math.max(dp[current], 1 + dp[previous]);
					}
				}
			}
			System.out.println("LDS : " + Arrays.toString(dp));
			return dp;
		}

		public int longestSubsequenceLength(final int[] A) {
			if (A.length < 2) {
				return A.length;
			}
			int[] LIS = LIS(A), LDS = LDS(A);

			int ans = 0;
			for (int i = 0; i < A.length; i++) {
				ans = Math.max(ans, LIS[i] + LDS[i] - 1);
			}
			return ans;

		}
	}

	public static void main(String[] args) {
		LengthOfLongestBiotonicSubsequence obj = new LengthOfLongestBiotonicSubsequence();
		System.out.println(obj.new MyApproach().longestSubsequenceLength(new int[] { 1, 11, 2, 10, 4, 5, 2, 1 }));
		System.out.println(obj.new MyApproach().longestSubsequenceLength(new int[] { 1, 3, 5, 6, 4, 8, 4, 3, 2, 1 }));
		System.out.println(obj.new Author().longestSubsequenceLength(new int[] { 1, 3, 5, 6, 4, 8, 4, 3, 2, 1 }));
		System.out.println(obj.new Author().longestSubsequenceLength(new int[] { 1, 2, 3, 4, 5 }));
	}
}
