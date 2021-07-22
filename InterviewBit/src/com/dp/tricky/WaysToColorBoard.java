package com.dp.tricky;

import java.util.ArrayList;
import java.util.List;

public class WaysToColorBoard {

	class Pair {
		int x, y, z;

		Pair(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	List<Pair> baseCombinations = new ArrayList<>();

	private void generateBaseCombinations() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if (i != j & j != k) {
						baseCombinations.add(new Pair(i, j, k));
					}
				}
			}
		}
	}

	private boolean isValid(Pair p1, Pair p2) {
		if(p1.x == p2.x || p1.y == p2.y || p1.z == p2.z) {
			return false;
		}
		return true;
	}

	public int solve(int n) {
		if (n == 0)
			return -1;
		int mod = (int) 1e9 + 7;
		generateBaseCombinations();
		int[][][][] dp = new int[4][4][4][n + 1];

		for (int column = 1; column <= n; column++) {
			for (int currentColumnCombination = 0; currentColumnCombination < baseCombinations
					.size(); currentColumnCombination++) {
				if (column == 1) {
					Pair p = baseCombinations.get(currentColumnCombination);
					dp[p.x][p.y][p.z][column] = 1;
				} else {
					int noOfPossibleCombinations = 0;
					for (int previousColumnCombination = 0; previousColumnCombination < baseCombinations
							.size(); previousColumnCombination++) {
						Pair p = baseCombinations.get(currentColumnCombination);
						Pair q = baseCombinations.get(previousColumnCombination);
						if (isValid(p, q)) {
							noOfPossibleCombinations += dp[q.x][q.y][q.z][column - 1];
							noOfPossibleCombinations %= mod;
						}
					}
					Pair p = baseCombinations.get(currentColumnCombination);
					dp[p.x][p.y][p.z][column] = noOfPossibleCombinations;
				}
			}
		}

		long ans = 0;
		for (int i = 0; i < baseCombinations.size(); i++) {
			Pair p = baseCombinations.get(i);
			ans += dp[p.x][p.y][p.z][n];
			ans %= mod;
		}

		return (int) ans % mod;
	}
	public static void main(String[] args) {
		System.out.println(new WaysToColorBoard().solve(2));
	}
}
