package com.dp.matrix;

import java.util.ArrayList;

public class QueenAttack {

	private void attack(ArrayList<String> A, int[][] dp, int i, int j) {
		int left = j - 1;
		while (left >= 0) {
			dp[i][left] += 1;
			if (A.get(i).charAt(left) == '1') {
				break;
			}
			left--;
		}
		int right = j + 1;
		while (right < dp[0].length) {
			dp[i][right] += 1;
			if (A.get(i).charAt(right) == '1') {
				break;
			}
			right++;
		}
		int up = i - 1;
		while (up >= 0) {
			dp[up][j] += 1;
			if (A.get(up).charAt(j) == '1') {
				break;
			}
			up--;
		}
		int down = i + 1;
		while (down < dp.length) {
			dp[down][j] += 1;
			if (A.get(down).charAt(j) == '1') {
				break;
			}
			down++;
		}

		int leftDiagonalIUp = i - 1;
		int leftDiagonalJUp = j - 1;
		while (leftDiagonalIUp >= 0 && leftDiagonalJUp >= 0) {
			dp[leftDiagonalIUp][leftDiagonalJUp] += 1;
			if (A.get(leftDiagonalIUp).charAt(leftDiagonalJUp) == '1') {
				break;
			}
			leftDiagonalIUp--;
			leftDiagonalJUp--;
		}

		int leftDiagonalIDown = i + 1;
		int leftDiagonalJDown = j + 1;

		while (leftDiagonalIDown < dp.length && leftDiagonalJDown < dp[0].length) {
			dp[leftDiagonalIDown][leftDiagonalJDown] += 1;
			if (A.get(leftDiagonalIDown).charAt(leftDiagonalJDown) == '1') {
				break;
			}
			leftDiagonalIDown++;
			leftDiagonalJDown++;
		}

		int rightDiagonalIUp = i - 1;
		int rightDiagonalJUp = j + 1;
		while (rightDiagonalIUp >= 0 && rightDiagonalJUp < dp[0].length) {
			dp[rightDiagonalIUp][rightDiagonalJUp] += 1;
			if (A.get(rightDiagonalIUp).charAt(rightDiagonalJUp) == '1') {
				break;
			}
			rightDiagonalIUp--;
			rightDiagonalJUp++;
		}

		int rightDiagonalIDown = i + 1;
		int rightDiagonalJDown = j - 1;

		while (rightDiagonalIDown < dp.length && rightDiagonalJDown >= 0) {
			dp[rightDiagonalIDown][rightDiagonalJDown] += 1;
			if (A.get(rightDiagonalIDown).charAt(rightDiagonalJDown) == '1') {
				break;
			}
			rightDiagonalIDown++;
			rightDiagonalJDown--;
		}
	}

	public ArrayList<ArrayList<Integer>> queenAttack(ArrayList<String> A) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		int n = A.size();
		if (n == 0) {
			return ans;
		}
		int m = A.get(0).length();

		int[][] dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				char c = A.get(i).charAt(j);
				if (c == '1') {
					//dp[i][j] += 1;
					attack(A, dp, i, j);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < m; j++) {
				int value = dp[i][j];
				/*
				 * if (A.get(i).charAt(j) == '1') { value--; }
				 */
				row.add(value);
			}
			ans.add(row);
		}
		return ans;
	}

	public static void main(String[] args) {
		QueenAttack obj = new QueenAttack();
		ArrayList<String> ans = new ArrayList<>();
		ans.add("011");
		ans.add("011");
		ans.add("011");
		System.out.println(obj.queenAttack(ans));
	}

}
