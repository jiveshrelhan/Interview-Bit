package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CoursesComplition {
	ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {

		for (int i = 0; i <= A; i++) {
			graph.add(new ArrayList<>());
		}

		for (int edge = 0; edge < B.size(); edge++) {
			graph.get(B.get(edge)).add(C.get(edge));
		}

		return khanAlgo(graph) ? 1 : 0;
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
		CoursesComplition obj = new CoursesComplition();
		System.out.println(
				obj.solve(5, new ArrayList<>(Arrays.asList(1, 3, 4, 5)), new ArrayList<>(Arrays.asList(2, 1, 5, 3))));
	}
}
