package com.dp.prefix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubMatrixWithSumZero {
	public int solve(ArrayList<ArrayList<Integer>> A) {
		int n = A.size();
		if (n == 0) {
			return 0;
		}
		int m = A.get(0).size();
		int count = 0;
		int[][] dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j] = A.get(i).get(j) + (j > 0 ? dp[i][j - 1] : 0);
			}
		}

		for (int col_left_fix = 0; col_left_fix < m; col_left_fix++) {
			for (int col_right_fix = col_left_fix; col_right_fix < m; col_right_fix++) {
				Map<Integer, Integer> map = new HashMap<>();
				map.put(0, 1);
				int sum = 0;
				for (int row = 0; row < n; row++) {
					sum += dp[row][col_right_fix] - (col_left_fix > 0 ? dp[row][col_left_fix - 1] : 0);
					count += map.getOrDefault(sum, 0);
					map.put(sum, map.getOrDefault(sum, 0) + 1);
				}

			}
		}
		return count;
	}

	public static void main(String[] args) {
		SubMatrixWithSumZero obj = new SubMatrixWithSumZero();
		ArrayList<ArrayList<Integer>> A = new ArrayList<>();
		A.add(new ArrayList<Integer>(Arrays.asList(-8,5,7)));
		A.add(new ArrayList<Integer>(Arrays.asList(3,7,-8)));
		A.add(new ArrayList<Integer>(Arrays.asList(5,-8,9)));
		System.out.println(obj.solve(A));
	}
}
