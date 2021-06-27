package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FootballTeams {
	public int solve(int A, ArrayList<ArrayList<Integer>> pairs) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= A; i++) {
			graph.add(new ArrayList<>());
		}

		for (ArrayList<Integer> pair : pairs) {
			graph.get(pair.get(0)).add(pair.get(1));
			graph.get(pair.get(1)).add(pair.get(0));
		}
		return isBipartite(graph) ? 1 : 0;
	}

	private boolean isBipartite(ArrayList<ArrayList<Integer>> graph) {
		int[] color = new int[graph.size()];
		Arrays.fill(color, -1);
		for (int vertex = 1; vertex < graph.size(); vertex++) {
			if (color[vertex] == -1 && !BFS(graph, vertex, color)) {
				return false;
			}
		}
		return true;
	}

	private boolean BFS(ArrayList<ArrayList<Integer>> graph, int src, int[] color) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		color[src] = 0;
		while (!queue.isEmpty()) {

			Integer parent = queue.poll();

			for (int adj : graph.get(parent)) {
				if (color[adj] == -1) {
					color[adj] = 1 - color[parent];
					queue.add(adj);
				} else if (color[adj] == color[parent]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		FootballTeams obj = new FootballTeams();
		ArrayList<ArrayList<Integer>> pairs = new ArrayList<ArrayList<Integer>>();
		pairs.add(new ArrayList<Integer>(Arrays.asList(1, 4)));
		pairs.add(new ArrayList<Integer>(Arrays.asList(3, 1)));
		pairs.add(new ArrayList<Integer>(Arrays.asList(4, 3)));
		pairs.add(new ArrayList<Integer>(Arrays.asList(2, 1)));
		System.out.println(obj.solve(5, pairs));
	}
}
