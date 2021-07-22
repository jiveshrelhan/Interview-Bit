package com.dp.tricky;

import java.util.Arrays;

/*-
 * Intuition is to create numbers: 
 * nextPossible number can be from (1 to RequiredSum-currentSum)
 * 
 * At any point no of digits == N and RequiredSum = 0 then return 1;
 * 
 *  Fnc(String currentNumber, int currentSum, int S, int N)
 *  
 *  Need to call Fnc from 1 to (S-currentSum) for each number.
 *  
 */

/*- 
 * Getting feeling we need to cache: sum vs number in hashMap
 * 
 * Say we are at i whose value is X. We will look for S-X sum. did we compute S-X before ?
 * If yes by how many digits ?
 * 
 * 
 */

/*-
 * Standard DP problem not trick involved
 */
public class NDigitNumbersWithDigitSumS {
	class Memo {
		int mod = (int) 1e9 + 7;
		int[][] memo;

		private int helper(int n, int s) {
			if (n == 0) {
				return s == 0 ? 1 : 0;
			}
			if (memo[n][s] != -1) {
				return memo[n][s];
			}
			int ans = 0;
			for (int i = 0; i <= 9; i++) {
				if (s - i >= 0) {
					ans = (ans + helper(n - 1, s - i)) % mod;
				}
			}
			return memo[n][s] = ans % mod;
		}

		public int solve(int n, int s) {
			if (n == 0 || s == 0)
				return 0;
			memo = new int[n + 1][s + 1];
			for (int[] rows : memo) {
				Arrays.fill(rows, -1);
			}
			int ans = 0;
			for (int i = 1; i <= 9; i++) {
				if (s - i >= 0)
					ans = (ans + helper(n - 1, s - i)) % mod;
			}

			return ans % mod;
		}
	}

	public static void main(String[] args) {
		NDigitNumbersWithDigitSumS obj = new NDigitNumbersWithDigitSumS();
		System.out.println(obj.new Memo().solve(75, 22));
	}

}
