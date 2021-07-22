package com.dp.arrays;

import java.util.Arrays;

/*-
 * Standard straight forward solution.
 *
 * I was computing . i * j * k * z
 * where as order is k*i*j*z
 * 
 * We have to take two copy of matrix. one copy will be not work
 * since we might have updated the same value before in current matrix which will cause
 * wrong value update for upcoming cell updates.
 */
public class KthManhattanDistance {

	private boolean isValid(int i, int j, int n, int m) {
		if (i < n && i >= 0 && j < m && j >= 0) {
			return true;
		}
		return false;
	}

	public int[][] solve(int K, int[][] B) {
		int n = B.length;
		int m = B[0].length;
		int[][] dist = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dist[i][j] = B[i][j];
			}
		}
		int X[] = { 0, -1, 0, 1 };
		int Y[] = { -1, 0, 1, 0 };
		for (int k = 1; k <= K; k++) {
			int[][] updatedDist = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					int max = dist[i][j];
					for (int z = 0; z <= 3; z++) {
						int newX = i + X[z];
						int newY = j + Y[z];
						if (isValid(newX, newY, n, m)) {
							max = Math.max(max, dist[newX][newY]);
						}
					}
					updatedDist[i][j] = max;
				}
			}
			dist = updatedDist;
		}

		return dist;

	}

	public static void main(String[] args) {
		KthManhattanDistance obj = new KthManhattanDistance();
		int[][] dist = obj.solve(2, new int[][] { { 1, 2, 4 }, { 4, 5, 8 } });
		for (int[] rows : dist) {
			System.out.println(Arrays.toString(rows));
		}
	}
}
