package com.dp.arrays;

import java.util.Arrays;

/*
 * This is more of Catalan numbers but can be solved without knowing that.
 * https://www.youtube.com/watch?v=zx6_ypjbstk
 */

/*- 
 * Intuition is using concept of smaller problems:
 * Choose any 2 random points: 
 * ans[i]  = is no of ways having non - intersecting of left and right:
 */

/*-
 * ans[i] = ans[left]*ans[right];
 * no of points of left can be 0 to n-2. why n-2 ? because 2 points already used.
 * no of points of right = n-2-left
 */

/*
 * base case: we can't join odd numbers of point without intersecting.
 * ans[odd] = 0;
 */
public class IntersectingChordsInACircle {
	class Recursion {

		int mod = (int) 1e9 + 7;

		private int helper(int k) {
			if (k % 2 == 1)
				return 0;
			if (k <= 2)
				return 1;
			long ans = 0;
			for (int i = 0; i <= k - 2; i++) {
				ans = (ans + helper(i) % mod * helper(k - 2 - i) % mod) % mod;
			}
			return (int) ans % mod;
		}

		public int chordCnt(int A) {
			return (int) helper(2 * A) % mod;
		}
	}

	/*
	 * Integer didn't work needs to switch to long
	 */
	class Memo {
		long[] memo;
		int mod = (int) 1e9 + 7;

		private long helper(int k) {
			if (k % 2 == 1)
				return 0;
			if (k <= 2)
				return 1;
			if (memo[k] != -1) {
				return memo[k];
			}
			long ans = 0;
			for (int i = 0; i <= k - 2; i += 2) {
				ans = (ans + ((helper(i) % mod * helper(k - 2 - i) % mod) % mod)) % mod;
			}
			return memo[k] = ans;
		}

		public int chordCnt(int A) {
			memo = new long[2 * A + 1];
			Arrays.fill(memo, -1);
			return (int) helper(2 * A) % mod;
		}
	}

	class DP {
		int mod = (int) 1e9 + 7;

		public int chordCnt(int A) {
			long[] dp = new long[2 * A + 1];
			dp[0] = 1;
			dp[2] = 1;
			for (int i = 4; i <= 2 * A; i += 2) {
				dp[i] = 0;
				for (int j = 0; j <= i - 2; j += 2) {
					dp[i] = (dp[i] + ((dp[j] % mod * dp[i - j - 2] % mod) % mod)) % mod;
				}
			}
			return (int) dp[2 * A] % mod;
		}
	}

	public static void main(String[] args) {
		IntersectingChordsInACircle obj = new IntersectingChordsInACircle();
		System.out.println(obj.new Recursion().chordCnt(5));
		System.out.println(obj.new Memo().chordCnt(5));
		System.out.println(obj.new DP().chordCnt(5));
	}
}
