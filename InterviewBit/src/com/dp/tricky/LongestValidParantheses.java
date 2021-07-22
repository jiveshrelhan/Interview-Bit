package com.dp.tricky;

/*-002 
 * (())
 * 
 * i- length of previous valid 
 * 3 - 2 = 1
 * Adjust -1 since we have to go to 0.
 * if(i-dp[i-1] -1 == '(')
 * 	dp[i - dp[i-1] - 1] = dp[i-1] +2 
  */
public class LongestValidParantheses {

	/*-
	 * https://leetcode.com/problems/longest-valid-parentheses/solution/
	 */
	class DP {
		public int longestValidParentheses(String A) {
			char[] input = A.toCharArray();
			int[] dp = new int[A.length()];
			int maxSoFar = 0;
			for (int i = 1; i < input.length; i++) {
				if (input[i] == '(') {
					dp[i] = 0;
				} else if (input[i] == ')') {
					if (input[i - 1] == '(') {
						dp[i] = ((i - 2) >= 0 ? dp[i - 2] : 0) + 2;
					} else if (i - dp[i - 1] - 1 >= 0 && input[i - dp[i - 1] - 1] == '(') {
						dp[i] = dp[i - 1] + ((i - dp[i - 1] - 2) >= 0 ? dp[i - dp[i - 1] - 2] : 0) + 2;
					}
				}
				maxSoFar = Math.max(maxSoFar, dp[i]);
			}
			return maxSoFar;
		}
	}

	public static void main(String[] args) {
		LongestValidParantheses obj = new LongestValidParantheses();
		System.out.println(obj.new DP().longestValidParentheses(")()())"));
	}
}
