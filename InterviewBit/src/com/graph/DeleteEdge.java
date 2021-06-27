package com.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class DeleteEdge {

	long mod = (long) 1e9 + 7;
	int sum = 0;
	long ans = 0;
	ArrayList<Integer> weights;

	public int deleteEdge(ArrayList<Integer> weights, ArrayList<ArrayList<Integer>> pairs) {
		this.weights = weights;
		for (int x : weights) {
			sum += x;
		}
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= weights.size(); i++) {
			graph.add(new ArrayList<>());
		}
		for (ArrayList<Integer> pair : pairs) {
			graph.get(pair.get(0)).add(pair.get(1));
			graph.get(pair.get(1)).add(pair.get(0));

		}
		DFS(graph, 1, new boolean[graph.size()]);
		return (int) (ans % mod);
	}

	private int DFS(ArrayList<ArrayList<Integer>> graph, int src, boolean[] visited) {

		visited[src] = true;
		int total = weights.get(src - 1);
		for (int child : graph.get(src)) {
			if (!visited[child]) {
				int local = DFS(graph, child, visited);
				int remaining = sum - local;
				ans = Math.max(ans, ((local % mod) * (remaining % mod)) % mod);
				total += local;
			}
		}

		return total;
	}

	public static void main(String[] args) {
		DeleteEdge obj = new DeleteEdge();
		ArrayList<ArrayList<Integer>> pairs = new ArrayList<ArrayList<Integer>>();
		pairs.add(new ArrayList<>(Arrays.asList(10, 6)));
		pairs.add(new ArrayList<>(Arrays.asList(3, 2)));
		pairs.add(new ArrayList<>(Arrays.asList(12, 7)));
		pairs.add(new ArrayList<>(Arrays.asList(9, 5)));
		pairs.add(new ArrayList<>(Arrays.asList(2, 1)));
		pairs.add(new ArrayList<>(Arrays.asList(8, 3)));
		pairs.add(new ArrayList<>(Arrays.asList(7, 1)));
		pairs.add(new ArrayList<>(Arrays.asList(4, 2)));
		pairs.add(new ArrayList<>(Arrays.asList(6, 3)));
		pairs.add(new ArrayList<>(Arrays.asList(11, 4)));
		pairs.add(new ArrayList<>(Arrays.asList(5, 3)));
		pairs.add(new ArrayList<>(Arrays.asList(13, 11)));

		ArrayList<Integer> weights = new ArrayList<>(
				Arrays.asList(42, 468, 335, 501, 170, 725, 479, 359, 963, 465, 706, 146, 282));

		System.out.println(obj.deleteEdge(weights, pairs));
	}

}
