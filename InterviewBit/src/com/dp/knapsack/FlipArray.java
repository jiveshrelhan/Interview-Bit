package com.dp.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://discuss.interviewbit.com/t/the-best-solution-ever-very-very-simple/70147
 * 
 * I figured it out it a 0/1 knapsack but don't how to define state in recurrence relation.
 * since here we wanted to minimize the sum and minimum number of flips simultaneously.
 */
public class FlipArray {

	public int solve(final List<Integer> A) {
		int sum = 0;
		for (int x : A) {
			sum += x;
		}
		// Now aim to find the sum close to sum/2 with using minimum number of flips;
		// Since we need to capture, if the sum j is possible or not using current set
		// of elements
		// If yes, with many number of flips.
		// Therefore initialize dp[i][j] = Infinite. If sum is not possible then value
		// will be infinite otherwise
		// keep minimizing the value of dp[i][j] which indicates minimum number of flips
		int n = A.size();
		int target = sum / 2;
		int[][] dp = new int[n + 1][target + 1];

		for (int[] rows : dp) {
			Arrays.fill(rows, Integer.MAX_VALUE);
		}

		for (int i = 0; i <= n; i++)
			dp[i][0] = 0; // With requirement of sum 0 having cell required 0 flips. Empty Set.

		//Why we are seeing in previous row is important: Because its 0/1. If you choose current element 
		//remaining stuff needs to be done by previous players.
		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < target + 1; j++) {
				if (j >= A.get(i - 1)) {
					if (dp[i-1][j - A.get(i - 1)] != Integer.MAX_VALUE) {
						dp[i][j] = Math.min(dp[i-1][j], 1 + dp[i-1][j - A.get(i - 1)]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		for (int j = target; j >= 0; j--) {
			if (dp[n][j] != Integer.MAX_VALUE) {
				return dp[n][j];
			}
		}
		return n - 1;
	}
	
	public static void main(String[] args) {
		FlipArray obj = new FlipArray();
		System.out.println(obj.solve(new ArrayList<Integer>(Arrays.asList(8, 4, 5, 7, 6, 2 ))));
	}
}
