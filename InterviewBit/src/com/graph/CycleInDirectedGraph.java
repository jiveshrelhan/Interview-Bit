package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CycleInDirectedGraph {

	public int solve(int A, ArrayList<ArrayList<Integer>> pairs) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= A; i++) {
			graph.add(new ArrayList<>());
		}

		for (ArrayList<Integer> pair : pairs) {
			graph.get(pair.get(0)).add(pair.get(1));
		}
		return khanAlgo(graph) ? 0 : 1;
	}

	private boolean khanAlgo(ArrayList<ArrayList<Integer>> graph) {

		int size_of_graph = graph.size();
		int[] indegree = new int[size_of_graph];
		ArrayList<Integer> topoOrder = new ArrayList<>();
		for (int i = 0; i < size_of_graph; i++) {
			for (int destination : graph.get(i)) {
				indegree[destination] = indegree[destination] + 1;
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < size_of_graph; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int vertexToRemove = queue.poll();
			topoOrder.add(vertexToRemove);
			for (int updateChildIndegree : graph.get(vertexToRemove)) {
				indegree[updateChildIndegree] = indegree[updateChildIndegree] - 1;
				if (indegree[updateChildIndegree] == 0) {
					queue.add(updateChildIndegree);
				}
			}
		}
		if (topoOrder.size() != size_of_graph) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		CycleInDirectedGraph obj = new CycleInDirectedGraph();
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		graph.add(new ArrayList<>(Arrays.asList(1, 3)));
		graph.add(new ArrayList<>(Arrays.asList(2, 3)));
		graph.add(new ArrayList<>(Arrays.asList(3, 2)));

		System.out.println(obj.solve(5, graph));
	}
}
