package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author JiveshR1
 *
 */
public class ValidPath {

	class Pair {
		int i, j;

		Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Objects.hash(i, j);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			Pair other = (Pair) obj;
			return i == other.i && j == other.j;
		}

	}

	private boolean isSafeDistance(int x_center, int y_center, int radius, int curr_x, int curr_y) {

		return Math.sqrt((Math.pow(x_center - curr_x, 2) + Math.pow(y_center-curr_y, 2))) > radius ? true : false;
	}

	private boolean checkForEachCircle(int curr_x, int curr_y) {
		for (int i = 0; i < No_of_Circles; i++) {
			if (!isSafeDistance(x[i], y[i], radius, curr_x, curr_y)) {
				return false;
			}
		}

		return true;
	}

	private boolean isValid(int i, int j) {
		if (i >= 0 && i <= n && j >= 0 && j <= m) {
			return true;
		}
		return false;
	}

	int[] X = { -1, -1, -1, 0, 0, 1, 1, 1 };
	int[] Y = { -1, 0, 1, -1, 1, -1, 0, 1 };

	int n = 0;
	int m = 0;

	Queue<Pair> queue = new LinkedList<>();
	int[] x, y;
	int No_of_Circles;
	int radius;
	int[][] visited;

	public String solve(int n, int m, int length, int radius, ArrayList<Integer> circle_x,
			ArrayList<Integer> circle_y) {
		this.n = n;
		this.m = m;
		this.visited = new int[n + 1][m + 1];
		this.x = circle_x.stream().mapToInt(Integer::intValue).toArray();
		this.y = circle_y.stream().mapToInt(Integer::intValue).toArray();
		this.radius = radius;
		this.No_of_Circles = length;
		int src_x = 0;
		int src_y = 0;

		/*
		 * for (int i = 0; i < No_of_Circles; i++) { int x_center = x[i], y_center =
		 * y[i]; for (int p = 0; p <= 7; p++) { for (int r = 0; r <= radius; r++) { int
		 * newX = x_center + (X[p] * r); int newY = y_center + (Y[p] * r); if
		 * (isValid(newX, newY)) { visited[newX][newY] = 1; } }
		 * 
		 * } }
		 */

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (!checkForEachCircle(i, j)) {
					visited[i][j] = 1;
				}
			}
		}

		// GraphUtility.printMatrix(visited);

		queue.add(new Pair(src_x, src_y));
		visited[src_x][src_y] = 1;

		while (!queue.isEmpty()) {
			Pair position = queue.poll();
			if (position.i == n && position.j == m) {
				return "YES";
			}

			for (int p = 0; p <= 7; p++) {
				int newX = position.i + X[p];
				int newY = position.j + Y[p];
				Pair newPair = new Pair(newX, newY);
				if (isValid(newX, newY) && visited[newX][newY] != 1) {
					visited[newX][newY] = 1;
					queue.add(newPair);
				}
			}

		}

		return "NO";
	}

	public static void main(String[] args) {
		ValidPath obj = new ValidPath();

		/*
		 * System.out.println(obj.solve(58, 91, 6, 8, new ArrayList<>(Arrays.asList(40,
		 * 6, 36, 38, 23, 54)), new ArrayList<>(Arrays.asList(88, 14, 50, 10, 15, 5))));
		 */
		System.out.println(obj.solve(58, 91, 6, 8, new ArrayList<>(Arrays.asList(40,6,36,38,23,54)),
				new ArrayList<>(Arrays.asList(88,14,50,10,15,5))));

		// System.out.println(obj.solve(2, 3, 1, 1, new ArrayList<>(Arrays.asList(2)),
		// new ArrayList<>(Arrays.asList(3))));
	}

}
