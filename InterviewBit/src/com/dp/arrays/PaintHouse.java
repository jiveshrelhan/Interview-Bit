package com.dp.arrays;

import java.util.Arrays;

/*- 
 *  T(N) = Minimum(cost[N][color] + cost[N-1][!color])
 */
public class PaintHouse {

	/*- 
	 * Approach : current house cost will be : 
	 * A[house][current][color] + Minimum of (A[house-1][different_colors]);
	 */
	class DP {
		public int solve(int[][] A) {
			if (A.length == 0) {
				return 0;
			}
			int[][] costMatrix = new int[A.length + 1][3];

			for (int house = 1; house < A.length + 1; house++) {
				for (int paint = 0; paint < 3; paint++) {
					int cost = Integer.MAX_VALUE;
					for (int previousRow = 0; previousRow < 3; previousRow++) {
						if (paint == previousRow) {
							continue;
						}
						cost = Math.min(costMatrix[house - 1][previousRow], cost);
					}
					costMatrix[house][paint] = cost + A[house - 1][paint];
				}
			}
			/*
			 * for (int[] row : costMatrix) { System.out.println(Arrays.toString(row)); }
			 */
			int minimum = Integer.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				minimum = Math.min(minimum, costMatrix[A.length][i]);
			}
			return minimum;
		}

	}

	class Recursion {

		private int helper(int houses, int color, int[][] A) {
			if (houses == A.length) {
				return 0;
			}
			int case1 = Integer.MAX_VALUE, case2 = Integer.MAX_VALUE, case3 = Integer.MAX_VALUE;
			if (color == 0) {
				case1 = Math.min(helper(houses + 1, 1, A), helper(houses + 1, 2, A)) + A[houses][color];
			} else if (color == 1) {
				case2 = Math.min(helper(houses + 1, 0, A), helper(houses + 1, 2, A)) + A[houses][color];
			} else if (color == 2) {
				case2 = Math.min(helper(houses + 1, 1, A), helper(houses + 1, 2, A)) + A[houses][color];
			}
			return Math.min(case1, Math.min(case2, case3));
		}

		public int solve(int[][] A) {
			if (A.length == 0) {
				return 0;
			}
			return Math.min(helper(0, 0, A), Math.min(helper(0, 1, A), helper(0, 2, A)));
		}

	}

	class Memo {
		int[][] memo;

		private int helper(int houses, int color, int[][] A) {
			if (houses == A.length) {
				return 0;
			}
			if (memo[houses][color] != -1) {
				return memo[houses][color];
			}
			int case1 = Integer.MAX_VALUE, case2 = Integer.MAX_VALUE, case3 = Integer.MAX_VALUE;
			if (color == 0) {
				case1 = Math.min(helper(houses + 1, 1, A), helper(houses + 1, 2, A)) + A[houses][color];
			} else if (color == 1) {
				case2 = Math.min(helper(houses + 1, 0, A), helper(houses + 1, 2, A)) + A[houses][color];
			} else if (color == 2) {
				case3 = Math.min(helper(houses + 1, 0, A), helper(houses + 1, 1, A)) + A[houses][color];
			}
			return memo[houses][color] = Math.min(case1, Math.min(case2, case3));
		}

		public int solve(int[][] A) {
			if (A.length == 0) {
				return 0;
			}
			memo = new int[A.length][3];
			for (int[] rows : memo) {
				Arrays.fill(rows, -1);
			}
			return Math.min(helper(0, 0, A), Math.min(helper(0, 1, A), helper(0, 2, A)));
		}
	}

	public static void main(String[] args) {
		PaintHouse obj = new PaintHouse();
		int[][] A = { { 1, 2, 3 }, { 10, 11, 12 } };
		System.out.println(obj.new DP().solve(A));
		System.out.println(obj.new Recursion().solve(A));
	}
}
