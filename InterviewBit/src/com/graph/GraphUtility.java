package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GraphUtility {

	public static void BFS(ArrayList<ArrayList<Integer>> graph, int src) {

		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		queue.add(src);
		visited.add(src);

		while (!queue.isEmpty()) {
			int fromVertex = queue.poll();
			for (int toVertex : graph.get(fromVertex)) {
				if (!visited.add(toVertex)) {
					visited.add(toVertex);
					queue.add(toVertex);
				}
			}
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}

}
