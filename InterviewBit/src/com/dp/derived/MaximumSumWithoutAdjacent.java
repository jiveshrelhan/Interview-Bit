package com.dp.derived;

import java.util.ArrayList;
import java.util.Arrays;

/* 	You can choose any number located at i,j
 *  Recurrence Relation: 
 *  If you choose i,j then next number you can choose is A.get(i,j) + (0,j+2) || (1,j+2)
 *  else choose from (O,j+1) or (1,j+1);
 */
public class MaximumSumWithoutAdjacent {

	class Recursion {
		Integer memo[][];

		private int helper(int i, int j, ArrayList<ArrayList<Integer>> A) {
			if (j >= A.get(0).size()) {
				return 0;
			}
			if (memo[i][j] != null)
				return memo[i][j];
			int caseChooseForSum = 0, caseNotToChooseForSum = 0;

			caseChooseForSum = A.get(i).get(j) + Math.max(helper(0, j + 2, A), helper(1, j + 2, A));

			caseNotToChooseForSum = Math.max(helper(0, j + 1, A), helper(1, j + 1, A));

			return memo[i][j] = Math.max(caseChooseForSum, caseNotToChooseForSum);

		}

		public int adjacent(ArrayList<ArrayList<Integer>> A) {
			if (A.isEmpty()) {
				return 0;
			}
			memo = new Integer[2][A.get(0).size()];
			return Math.max(helper(0, 0, A), helper(1, 0, A));
		}
	}

	public static void main(String[] args) {
		MaximumSumWithoutAdjacent obj = new MaximumSumWithoutAdjacent();
		ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();
		A.add(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
		A.add(new ArrayList<>(Arrays.asList(2, 3, 4, 5)));
		System.out.println(obj.new Recursion().adjacent(A));
	}

}
