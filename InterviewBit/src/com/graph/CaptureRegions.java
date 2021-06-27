package com.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class CaptureRegions {

	private boolean isValid(int i, int j, int n, int m) {
		if (i >= 0 && i < n && j >= 0 && j < m) {
			return true;
		}
		return false;
	}

	int X[] = { 0, -1, 0, 1 };
	int Y[] = { -1, 0, 1, 0 };

	public void solve(ArrayList<ArrayList<Character>> matrix) {
		if(matrix.isEmpty() ) {
			return;
		}
		int n = matrix.size();
		int m = matrix.get(0).size();
		boolean visited[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
					if (matrix.get(i).get(j) == 'O') {
						DFS(matrix, i, j, n, m, visited);
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix.get(i).get(j) == 'O') {
					matrix.get(i).set(j, 'X');
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix.get(i).get(j) == '1') {
					matrix.get(i).set(j, 'O');
				}
			}
		}

	}

	private void DFS(ArrayList<ArrayList<Character>> matrix, int i, int j, int n, int m, boolean[][] visited) {

		visited[i][j] = true;
		matrix.get(i).set(j, '1');
		for (int p = 0; p < 4; p++) {
			int newI = i + X[p];
			int newJ = j + Y[p];
			if (isValid(newI, newJ, n, m) && matrix.get(newI).get(newJ) == 'O' && visited[newI][newJ] == false) {
				DFS(matrix, newI, newJ, n, m, visited);
			}
		}
	}
	
	public static void main(String[] args) {
		CaptureRegions obj = new CaptureRegions();
		ArrayList<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();
		
		  matrix.add(new ArrayList<Character>(Arrays.asList('X', 'X', 'X')));
		  matrix.add(new ArrayList<Character>(Arrays.asList('X', 'O', 'X')));
		  matrix.add(new ArrayList<Character>(Arrays.asList('X', 'X', 'X')));
		 
		
		obj.solve(matrix);
		System.out.println(matrix);
	}

}
