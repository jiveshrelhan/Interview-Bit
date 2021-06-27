package com.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestDistance {

	int ans = Integer.MIN_VALUE;

	public int solve(ArrayList<Integer> A) {

		if (A.size() <= 1) {
			return 0;
		}
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < A.size(); i++) {
			graph.add(new ArrayList<>());
		}
		Integer root = 0;
		for (int i = 0; i < A.size(); i++) {
			int parent = A.get(i);
			if (parent == -1) {
				root = i;
			} else {
				graph.get(parent).add(i);
			}
		}
		helper(graph, root.intValue());
		return ans - 1;
	}

	private int helper(ArrayList<ArrayList<Integer>> graph, int currentVertex) {

		if (graph.get(currentVertex).size() == 0) {
			return 1;
		}

		int max = 0;
		int prev_max = 0;
		for (int child : graph.get(currentVertex)) {
			int ans = helper(graph, child);
			if (ans >= max) {
				prev_max = max;
				max = ans;
			} else if (ans > prev_max) {
				prev_max = ans;
			}
		}
		ans = Math.max(prev_max + max + 1, ans);
		return max + 1;
	}

	public static void main(String[] args) {
		LongestDistance obj = new LongestDistance();
		int ans = obj.solve(new ArrayList<Integer>(Arrays.asList(-1,0)));
		System.out.println(ans);
	}
}