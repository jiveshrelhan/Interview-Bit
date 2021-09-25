package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxSquareSumMatrix {
	class MySolution {
		public int solve(ArrayList<ArrayList<Integer>> A, int B) {
			int n = A.size();
			int m = A.get(0).size();
			int[][] dp = new int[n + 1][m + 1];
			int maxSum = Integer.MIN_VALUE;

			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < m+1; j++) {
					dp[i][j] = A.get(i - 1).get(j - 1) + dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1];
				}
			}

			for (int[] x : dp) {
				System.out.println(Arrays.toString(x));
			}

			for (int i = 1; i < n; i++) {
				for (int j = 1; j < m; j++) {
					int sum = Integer.MIN_VALUE;

					if (i - B >= 0 && j - B >= 0) {
						sum = dp[i][j] - dp[i - B][j] - dp[i][j - B] + dp[i - B][j - B];
						maxSum = Math.max(maxSum, sum);
					}
				}
			}
			return maxSum;
		}
	}

	class AuthorSolution {
		public int solve(ArrayList<ArrayList<Integer>> A, int B) {
			int row = A.size(), col = A.get(0).size();
			int maxSum = Integer.MIN_VALUE;
			int dp[][] = new int[row + 1][col + 1];
			for (int i = 1; i < dp.length; i++) {
				for (int j = 1; j < dp[0].length; j++) {
					dp[i][j] = A.get(i - 1).get(j - 1) + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
				}
			}
			for (int[] x : dp) {
				System.out.println(Arrays.toString(x));
			}
			for (int i = 1; i < dp.length; i++) {
				for (int j = 1; j < dp[0].length; j++) {
					int sum = Integer.MIN_VALUE;
					if (i - B >= 0 && j - B >= 0) {
						sum = dp[i][j] - dp[i - B][j] - dp[i][j - B] + dp[i - B][j - B];
						maxSum = Math.max(maxSum, sum);
					}
				}
			}
			return maxSum;
		}
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
		a.add(new ArrayList<Integer>(Arrays.asList(-67, -31, 42, -56, -84, -19, 98, 22, -49, -79)));
		a.add(new ArrayList<Integer>(Arrays.asList(-1, 57, -24, -8, 89, -25, 12, -100, 10, -97)));
		a.add(new ArrayList<Integer>(Arrays.asList(-31, -39, -12, -99, 89, -45, -77, -98, 85, 82)));
		a.add(new ArrayList<Integer>(Arrays.asList(-15, -12, -74, -83, 57, -68, 32, 69, 54, 21)));
		a.add(new ArrayList<Integer>(Arrays.asList(89, 76, 29, 68, -8, -75, 55, -66, 49, -59)));
		a.add(new ArrayList<Integer>(Arrays.asList(12, 45, -40, 18, 53, 39, -77, -21, 96, -13)));
		a.add(new ArrayList<Integer>(Arrays.asList(29, 49, -63, -34, 49, 93, 95, -3, -84, -14)));
		a.add(new ArrayList<Integer>(Arrays.asList(5, -12, -18, -45, 34, 14, 1, 16, -29, 86)));
		a.add(new ArrayList<Integer>(Arrays.asList(-37, 13, 55, 85, -47, 12, -92, -68, 45, 13)));
		a.add(new ArrayList<Integer>(Arrays.asList(56, 21, 58, -54, 82, -19, 44, 96, -78, 29)));
		
		MaxSquareSumMatrix obj = new MaxSquareSumMatrix();
		obj.new MySolution().solve(a, 2);
		System.out.println("-------");
		obj.new AuthorSolution().solve(a, 2);
	}
}
