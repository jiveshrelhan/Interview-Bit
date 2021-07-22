package com.dp.arrays;

import java.util.Arrays;

/*
 * Few corner cases which i didn't handled:
 * 0 is also available in the string. 0 is invalid.
 * so we have reject all single digit 0 and '0x' strings.
 */

/*-
 * It similar to frog jump game. Is it possible to reach to end ?
 */
public class WaysToDecode {

	/*
	 * Recursion has some bugs see Memo
	 */
	class Recursion {
		int mod = (int) 1e9 + 7;

		private int helper(int currentIndex, char[] chars) {
			if (currentIndex == chars.length) {
				return 1;
			}
			int case1 = 0, case2 = 0;
			if (currentIndex < chars.length - 1) {
				String extractNext = chars[currentIndex] + "" + chars[currentIndex + 1];
				if (Integer.valueOf(extractNext) <= 26) {
					case1 = helper(currentIndex + 2, chars);
				}
			}
			case2 = helper(currentIndex + 1, chars);
			return (case1 + case2) % mod;
		}

		public int numDecodings(String A) {
			return helper(0, A.toCharArray());
		}

	}

	class Memo {
		int memo[];
		int mod = (int) 1e9 + 7;

		private int helper(int currentIndex, char[] chars) {
			if (currentIndex == chars.length) {
				return 1;
			}
			if (memo[currentIndex] != -1) {
				return memo[currentIndex];
			}
			// case1 is choose 2 characters
			// case2 is choose current/single characters.
			int case1 = 0, case2 = 0;

			if (chars[currentIndex] != '0') {
				case2 = helper(currentIndex + 1, chars);
				if (currentIndex < chars.length - 1) {
					String extractNext = chars[currentIndex] + "" + chars[currentIndex + 1];
					int intValue = Integer.valueOf(extractNext);
					if (intValue > 0 && intValue <= 26) {
						case1 = helper(currentIndex + 2, chars);
					}
				}
			}
			return memo[currentIndex] = (case1 + case2) % mod;
		}

		public int numDecodings(String A) {
			memo = new int[A.length()];
			Arrays.fill(memo, -1);
			return helper(0, A.toCharArray());
		}

	}

	/*-
	 * Was not able to convert directly from memorized solution. 
	 * But was able to think in subproblems : 
	 * no of ways to decode if the input is till 'i'
	 */

	/*-
	 * WAS NOT SURE HOW BASE CASE DP[0] = 1; 
	 * IF THERE IS NO STRING THEN NO OF WAYS TO DECODE SHOULD ALSO BE 0.
	 */
	class DP {
		int mod = (int) 1e9 + 7;

		public int numDecodings(String A) {
			if (A.isEmpty()) {
				return 0;
			}
			int[] dp = new int[A.length() + 1];
			dp[0] = 1;
			dp[1] = A.charAt(0) != '0' ? 1 : 0;
			for (int i = 2; i < dp.length; i++) {
				char currentChar = A.charAt(i - 1);
				if (currentChar != '0') {
					dp[i] += dp[i - 1] % mod;
				}
				String prevChars = A.charAt(i - 2) + "" + A.charAt(i - 1);
				int intValue = Integer.valueOf(prevChars);
				// Bug : intValue >=10 not > 0
				if (intValue >= 10 && intValue <= 26) {
					dp[i] += dp[i - 2] % mod;
				}
			}
			/*
			 * Another test case failed: forget to add mod at return statement.
			 */
			return dp[A.length()] % mod;
		}
	}

	public static void main(String[] args) {
		WaysToDecode obj = new WaysToDecode();
		System.out.println(obj.new Recursion().numDecodings("121221"));
		System.out.println(obj.new Memo().numDecodings("2611055971756562"));
		System.out.println(obj.new DP().numDecodings(
				"32925665678138257423442343752148360796465852883409126159293254159974370974059818198867156827877059067081419274873853679038"));
	}
}
