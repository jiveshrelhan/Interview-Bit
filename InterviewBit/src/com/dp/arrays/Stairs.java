package com.dp.arrays;

public class Stairs {
	class Recursion {
		private int helper(int current, int A) {
			if (current == A) {
				return 1;
			}
			if (current > A) {
				return 0;
			}
			return helper(current + 1, A) + helper(current + 2, A);
		}

		public int climbStairs(int A) {
			return helper(0, A);
		}
	}

	class DP {
		public int climbStairs(int A) {
			int dp[] = new int[A + 1];
			dp[0] = 1;
			dp[1] = 1;
			for (int i = 2; i < dp.length; i++) {
				dp[i] = dp[i - 1] + dp[i - 2];
			}
			return dp[A];
		}
	}

	public static void main(String[] args) {
		Stairs obj = new Stairs();
		System.out.println(obj.new Recursion().climbStairs(36));
		System.out.println(obj.new DP().climbStairs(36));
	}
}
