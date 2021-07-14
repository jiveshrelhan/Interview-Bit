package com.dp.arrays;

import java.util.Arrays;

/*-
 * This is based on pattern LCS but not covered by Aditya
 * This is completely based on solving sub problems. 
 * These are little different from recursion.
 */
/*-
 * As per one you-tuber: LIS = LCS(A,Sort(A);
 */
public class LongestIncreasingSubsequence {

	class Recursion {

		private int LISHelper(int[] arr, int curr, int prevIndex) {

			if (curr == arr.length) {
				return 0;
			}

			int currElement = arr[curr];

			if (prevIndex == -1 || currElement > arr[prevIndex]) {
				int include = 1 + LISHelper(arr, curr + 1, curr);
				int exclude = LISHelper(arr, curr + 1, prevIndex);
				return Math.max(include, exclude);
			} else {
				int exclude = LISHelper(arr, curr + 1, prevIndex);
				return exclude;
			}
		}

		public int LIS(int[] arr) {
			return LISHelper(arr, 0, -1);
		}
	}

	class Memo {

		Integer memo[][];

		private int LISHelper(int[] arr, int curr, int prevIndex) {

			if (curr == arr.length) {
				return 0;
			}
			if (prevIndex != -1 && memo[curr][prevIndex] != null) {
				return memo[curr][prevIndex];
			}
			int currElement = arr[curr];

			if (prevIndex == -1 || currElement > arr[prevIndex]) {
				int include = 1 + LISHelper(arr, curr + 1, curr);
				int exclude = LISHelper(arr, curr + 1, prevIndex);
				if (prevIndex != -1) {
					memo[curr][prevIndex] = Math.max(include, exclude);
				}
				return Math.max(include, exclude);
			} else {
				int exclude = LISHelper(arr, curr + 1, prevIndex);
				return memo[curr][prevIndex] = exclude;
			}
		}

		public int LIS(int[] arr) {
			memo = new Integer[arr.length][arr.length];
			return LISHelper(arr, 0, -1);
		}
	}

	/*- 
	 * Since exclude is common: Its better to extract that out
	 */
	class LittleBetterRecursion {
		private int LISHelper(int[] arr, int curr, int prevIndex) {

			if (curr == arr.length) {
				return 0;
			}

			int currElement = arr[curr];
			int include = 0;
			int exclude = LISHelper(arr, curr + 1, prevIndex);

			if (prevIndex == -1 || currElement > arr[prevIndex]) {
				include = 1 + LISHelper(arr, curr + 1, curr);
			}

			return Math.max(include, exclude);
		}

		public int LIS(int[] arr) {
			return LISHelper(arr, 0, -1);
		}
	}

	/*
	 * Can't convert directly by Aditya's Method
	 */
	class DP {

		public int LIS(int[] arr) {
			int[][] dp = new int[arr.length + 1][arr.length + 1];
			for (int i = 0; i < arr.length + 1; i++) {
				dp[i][0] = 1;
			}
			dp[0][0] = 0;
			for (int i = 1; i < arr.length + 1; i++) {
				for (int j = 1; j < arr.length + 1; j++) {
					int currElement = arr[i - 1];
					int include = 0;
					int exclude = dp[i - 1][j];

					if (currElement > arr[j - 1]) {
						include = 1 + dp[i - 1][i];
					}

					dp[i][j] = Math.max(include, exclude);
				}
			}
			return dp[arr.length][arr.length];
		}
	}

	/*-
	 * Intuition : This is how i think. 
	 * Can this index will be part of answer or it will create its own answer.
	 * 
	 * Generally these type of approach compute answer from nth index and compute
	 * final ans at 0.
	 * but for some questions this can be done is reverse way as well.
	 * Like we have already calculated best answer till 0,i now for i+1 what 
	 * will be best answer ?
	 * 
	 * This way we get our answer at last index.
	 */

	/*
	 * Any way is possible, main thing is to define the base case. In this question
	 * base cases are: when there are no elements then LIS is 0 And if one element
	 * is there then LIS is 1.
	 */

	/*- Transitions : 
	 *  For index i : 1 to N-1. Index 0 is base case.
	 * 	Can i include arr[i] in the answer ? 
	 * 	I can only include if the arr[i] > arr[i-1] then LIS[i] = 1 + LIS[i-1]
	 *	else LIS[i] = 1; // Answer start from 1;
	 */

	/*- 
	 * One important Observation: We just don't need to check previous index i-1 for i.
	 * but we need to check all previous index 0 to i-1 because best answer can be start 
	 * from any middle element
	 * 
	 * Example : 1 2 10 3 4 5 6 7 : For index 3 : Val 3 LIS = 1 as per our logic 
	 * since it less than 10
	 * therefore it should start its own series but its partially correct : 
	 * this is answer of we have only 
	 * 
	 * 2 elements in [] i.e {10,3} then LIS[3] = 1 but here we have input of {1,2,10,3} 
	 * so best answer can be from using
	 * any previous index.
	 * 
	 * Correct LIS is LIS[3] = 1 + LIS[2]; 
	 */
	class RealDP {

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

			return dp[arr.length - 1];
		}

	}

	public static void main(String[] args) {
		LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
		System.out.println(obj.new DP().LIS(new int[] { 1, 2, 3 }));
		System.out.println(obj.new RealDP().LIS(new int[] { 1, 2, 3 }));
	}
}
