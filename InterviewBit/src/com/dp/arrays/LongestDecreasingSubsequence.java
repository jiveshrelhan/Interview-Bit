package com.dp.arrays;

import java.util.Arrays;

public class LongestDecreasingSubsequence {
	/*
	 * Same as LIS one liner change
	 */
	class DP {

		public int LDS(int[] arr) {
	
			int dp[] = new int[arr.length];
			Arrays.fill(dp, 1);

			for (int current = 1; current < arr.length; current++) {
				for (int previous = 0; previous < current; previous++) {
					// Instead of greater check smaller.
					if (arr[current] < arr[previous]) {
						dp[current] = Math.max(dp[current], 1 + dp[previous]);
					}
				}
			}

			return dp[arr.length - 1];
		}

	}
}
