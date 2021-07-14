package com.dp.strings;

public class RegularExpression2 {
	class Recursion {

		/*-
		 * Transitions 
		 * if ch == . then continue i+1,j+1
		 * if ch == * the if previous character from pattern matches with input character or with '.'
		 * then 3 choices empty,one time, more time which  is equivalent to i,j+1 || i+1,j+1 || i+1,j 
		 * 			else character doesn't match then ignore the * consider it as e empty one then i,j+1
		 * 
		 * else for all other character: pattern character and input character should match else return false;
		 */

		/*-
		 * Base Conditions:
		 * if reached to end of i and j then return true;
		 * if reach to end of i and check all other characters except '*' such as (a-z & .) should proceed with '*'
		 * 		if yes then true and else false;
		 * if reach to end of j then false;
		 */
		public int isMatch(final String A, final String B) {
			if (!B.isEmpty() && B.charAt(0) == '*') {
				return 0;
			}
			return solve(A, B, 0, 0) ? 1 : 0;
		}

		private boolean solve(String A, String B, int i, int j) {

			if (i == A.length() && j == B.length()) {
				return true;
			}

			if (i == A.length()) {
				int k = j;
				if ((B.length() - k +1) % 2 == 1) {
					return false;
				}
				while (k + 1 < B.length()) {
					if (B.charAt(k + 1) != '*') {
						return false;
					}
				}
				return true;
			}

			if (j >= B.length()) {
				return false;
			}

			char patternChar = B.charAt(j);

			if (j+1 < B.length() && B.charAt(j + 1) == '*') {

				if (patternChar == A.charAt(i) || patternChar == '.') {
					return solve(A, B, i, j + 2) || solve(A, B, i + 1, j + 2) || solve(A, B, i + 1, j);
				} else {
					return solve(A, B, i, j + 2);
				}
			} else if (patternChar == '.') {
				return solve(A, B, i + 1, j + 1);
			} 
			else {
				if (patternChar == A.charAt(i)) {
					return solve(A, B, i + 1, j + 1);
				} else {
					return false;
				}
			}
		}
	}

	//Todo should be simple
	class DP{
		
	}
	public static void main(String[] args) {
		RegularExpression2 obj = new RegularExpression2();
		System.out.println(obj.new Recursion().isMatch("ab", ".*"));
	}
}
