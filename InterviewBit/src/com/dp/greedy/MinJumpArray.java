package com.dp.greedy;

import java.util.Arrays;

public class MinJumpArray {
	class DP_TLE {
		public int jump(int[] A) {
			int[] dp = new int[A.length];
			Arrays.fill(dp, A.length + 1);
			dp[0] = 0;
			for (int i = 0; i < A.length; i++) {
				for (int power = 1; power <= A[i]; power++) {
					if (power + i < A.length) {
						dp[power + i] = Math.min(dp[power + i], 1 + dp[i]);
					}
				}
			}
			// System.out.println(Arrays.toString(dp));
			return dp[A.length - 1] == A.length + 1 ? -1 : dp[A.length - 1];
		}

	}

	/*
	 * There is one more method in comments using greedy by prakash 
	 * That one is good and more intuitive
	 */
	class Greedy {
		public int jump(int[] A) {
			if(A.length == 1) {
				return 0;
			}
			if(A[0] == 0) {
				return -1;
			}
			int maxReach = A[0], steps = A[0], jumps = 1;
			for (int i = 1; i < A.length; i++) {
				if (i == A.length - 1)
					return jumps;
				maxReach = Math.max(maxReach, i + A[i]);
				steps--;
				if (steps == 0) {
					jumps++;
					if (i >= maxReach) {
						return -1;
					}
					steps = maxReach - i;
				}
			}
			return maxReach >= A.length - 1 ? jumps : -1;
		}
	}

	public static void main(String[] args) {
		MinJumpArray obj = new MinJumpArray();
		System.out.println(obj.new DP_TLE().jump(new int[] { 5, 4, 3, 2, 1 }));
	}

}
