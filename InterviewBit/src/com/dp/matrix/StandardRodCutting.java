package com.dp.matrix;

import java.util.Arrays;

/*-
 * Recurrence Relation : 
 * If I make a cut of Length i : Function (i,L) = > Profit[i] + Function (i,L-i) , 
 * else if don't make a cut : Function (i,L) = Function (i+1,L).
 * 
 * Base cases : 
 * I can only make a cut If length of i <= Required (L) 
 * and also i should always be less than size()
 */

/*-	
 * THIS PROBLEM IS DIFFERENT FROM INTERVIEW BIT ROT CUTTING
 * 
 * THIS PROBLEM SAYS, GIVEN A LENGHT N, FORM A ROD OF LENGTH N BY PIECES LEN (1,X)
 * EVERY PIECE HAS DIFFERNT COST. RETURN WHICH HAS MAXIMUM COST.
 *
 * Ex : 10, {1,5,5} Requirement  is 10 Length : Max is 25 : 5 (Length 2 of Remaining 8) 
 *  + 5 (Length 2 of Remaining 6) + 5 (Remaining 4) + 5 (Remaining 2) + 5(Remaining = 0) Total cost 25
 *  
 */
public class StandardRodCutting {

	class Memo {
		int[][] memo;

		private int helper(int currentIndex, int currentLength, int[] profit) {
			if (currentIndex == profit.length || currentLength <= 0 || currentIndex + 1 > currentLength) {
				return 0;
			}

			if (memo[currentIndex][currentLength] != -1) {
				return memo[currentIndex][currentLength];
			}
			int caseMakeACut = profit[currentIndex] + helper(currentIndex, currentLength - currentIndex - 1, profit);
			int caseNotToMakeACut = helper(currentIndex + 1, currentLength, profit);

			return memo[currentIndex][currentLength] = Math.max(caseMakeACut, caseNotToMakeACut);
		}

		public int solve(int n, int[] A) {
			memo = new int[A.length][n + 1];
			for (int rows[] : memo) {
				Arrays.fill(rows, -1);
			}
			return helper(0, n, A);
		}
	}

	public static void main(String[] args) {
		StandardRodCutting obj = new StandardRodCutting();
		System.out.println(obj.new Memo().solve(10, new int[] { 1, 5, 2 }));
	}

}
