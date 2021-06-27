package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * We need find all connected components. This can be done by using DFS.
 * Challenge is to identify shapes of component. Component is size is not enough
 * to differentiate.
 * 
 * 0XX 0X
 * 
 * AND
 * 
 * 0X 0XX are two different shapes.
 * 
 * @author JiveshR1
 *
 */
public class BlackShapes {

	int X[] = { 0, -1, 0, 1 };
	int Y[] = { -1, 0, 1, 0 };

	private boolean isValid(int i, int j, int n, int m) {
		if (i >= 0 && i < n && j >= 0 && j < m) {
			return true;
		}
		return false;
	}

	public ArrayList<ArrayList<ArrayList<Integer>>> computeCC(ArrayList<String> input) {
		ArrayList<ArrayList<ArrayList<Integer>>> cc = new ArrayList<ArrayList<ArrayList<Integer>>>();

		int n = input.size(), m = input.get(0).length();
		int[][] visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				char c = input.get(i).charAt(j);
				if (c == 'X' && visited[i][j] == 0) {
					ArrayList<ArrayList<Integer>> component = new ArrayList<ArrayList<Integer>>();
					DFS(i, j, input, component, visited, n, m);
					cc.add(component);
				}
			}
		}
		return cc;
	}

	
	@SuppressWarnings("unused")
	private int processCC(ArrayList<ArrayList<ArrayList<Integer>>> cc) {
		HashSet<ArrayList<ArrayList<Integer>>> set = new HashSet<>();
		for (int i = 0; i < cc.size(); i++) {
			ArrayList<ArrayList<Integer>> component = cc.get(i);
			int minI = Integer.MAX_VALUE, minJ = Integer.MAX_VALUE;
			for (ArrayList<Integer> coordindate : component) {
				minI = Math.min(coordindate.get(0), minI);
				minJ = Math.min(coordindate.get(1), minJ);
			}
			component = reviseComponents(component, minI, minJ);
			set.add(component);
		}

		return set.size();
	}

	private ArrayList<ArrayList<Integer>> reviseComponents(ArrayList<ArrayList<Integer>> component, int minI,
			int minJ) {
		for (ArrayList<Integer> coordindate : component) {
			coordindate.set(0, coordindate.get(0) - minI);
			coordindate.set(1, coordindate.get(1) - minJ);
		}
		return component;
	}

	private void DFS(int i, int j, ArrayList<String> input, ArrayList<ArrayList<Integer>> component, int[][] visited,
			int n, int m) {
		visited[i][j] = 1;
		component.add(new ArrayList<>(Arrays.asList(i, j)));
		for (int p = 0; p < 4; p++) {
			int newI = i + X[p];
			int newJ = j + Y[p];
			if (isValid(newI, newJ, n, m)) {
				char c = input.get(newI).charAt(newJ);
				if (c == 'X' && visited[newI][newJ] == 0) {
					DFS(newI, newJ, input, component, visited, n, m);
				}

			}
		}
	}

	public int black(ArrayList<String> A) {
		return computeCC(A).size();
	}

	public static void main(String[] args) {
		BlackShapes obj = new BlackShapes();
		ArrayList<String> input = new ArrayList<String>(Arrays.asList("X0X0", "00XX"));
		System.out.println(obj.black(input));
	}

}
