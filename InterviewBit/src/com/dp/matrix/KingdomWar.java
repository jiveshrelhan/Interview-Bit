package com.dp.matrix;

public class KingdomWar {
	/*-
	 * Did it by using pure observation.
	 * But this can be club in one matrix also equation.
	 * 
	 * dp[i][j] = A[i][j] + dp[i+1][j] + dp[i][j+1] - dp[i+1][j+1]
	 * return max of dp[i][j]
	 */
	public int solve(int[][] A) {
		if (A.length == 0) {
			return 0;
		}
		int[][] leftMatrix = new int[A.length][A[0].length];
		int[][] upMatrix = new int[A.length][A[0].length];
		int[][] diagonalMatrix = new int[A.length][A[0].length];
		int maxRegion = Integer.MIN_VALUE;
		for (int i = A.length - 1; i >= 0; i--) {
			for (int j = A[0].length - 1; j >= 0; j--) {
				leftMatrix[i][j] = A[i][j] + (j + 1 <= A[0].length - 1 ? leftMatrix[i][j + 1] : 0);
				upMatrix[i][j] = A[i][j] + (i + 1 <= A.length - 1 ? upMatrix[i + 1][j] : 0);
				diagonalMatrix[i][j] = A[i][j] + (j + 1 <= A[0].length - 1 ? diagonalMatrix[i][j + 1] : 0)
						+ (i + 1 <= A.length - 1 ? diagonalMatrix[i + 1][j] : 0)
						- (i + 1 <= A.length - 1 && j + 1 <= A[0].length - 1 ? diagonalMatrix[i + 1][j + 1] : 0);

				int test = Math.max(leftMatrix[i][j], Math.max(diagonalMatrix[i][j], upMatrix[i][j]));
				maxRegion = Math.max(maxRegion, test);
			}
		}
		return maxRegion;
	}

	public static void main(String[] args) {
		KingdomWar obj = new KingdomWar();
		System.out.println(obj.solve(new int[][] { { -5, -4, -1 }, { -3, 2, 4 }, { 2, 5, 8 } }));
		System.out.println(obj.solve(new int[][] { { -200 } }));
	}
}
