package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LargestIsland {

	class Pair{
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

	int[] X = { -1, -1, -1, 0, 0, 1, 1, 1 };
	int[] Y = { -1, 0, 1, -1, 1, -1, 0, 1 };

	int n = 0;
	int m = 0;

	private boolean isValid(int i, int j) {
		if (i >= 0 && i < n && j >= 0 && j < m) {
			return true;
		}
		return false;
	}

	private int dfs(ArrayList<ArrayList<Integer>> graph, int i, int j, Set<Pair> visited) {
		if (!isValid(i, j))
			return 0;

		if (graph.get(i).get(j) == 0) {
			return 0;
		}

		// System.out.println("i : " + i + " j " + j);

		if (visited.contains(new Pair(i, j)))
			return 0;
		visited.add(new Pair(i, j));

		int ans = 0;
		if (graph.get(i).get(j) == 1) {
			ans++;
		}
		for (int x = 0; x <= 7; x++) {
			ans += dfs(graph, i + X[x], j + Y[x], visited);
		}
		return ans;
	}

	public int solve(ArrayList<ArrayList<Integer>> graph) {
		int max_island = 0;
		if (graph.isEmpty()) {
			return max_island;
		}

		n = graph.size();
		m = graph.get(0).size();

		Set<Pair> visited = new HashSet<Pair>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int current_island = dfs(graph, i, j, visited);
				// System.out.println("DFS Call @" + i + " : " + j + " ans : " +
				// current_island);
				max_island = Math.max(max_island, current_island);
			}
		}
		return max_island;
	}

	public static void main(String[] args) {
		LargestIsland obj = new LargestIsland();
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		graph.add(new ArrayList<>(Arrays.asList(0, 0, 1, 1, 0)));
		graph.add(new ArrayList<>(Arrays.asList(1, 0, 1, 1, 0)));
		graph.add(new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0)));
		graph.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1)));
		System.out.println(obj.solve(graph));
	}
}
