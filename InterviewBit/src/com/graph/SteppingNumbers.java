package com.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SteppingNumbers {

	class Pair {
		String str;
		int digit;

		Pair(String str, int digit) {
			this.str = str;
			this.digit = digit;
		}
	}

	ArrayList<Integer> ans = new ArrayList<Integer>();

	public ArrayList<Integer> stepnum(int start, int end) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 10; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				graph.get(i).add(i + 1);
			} else if (i == 9) {
				graph.get(i).add(i - 1);
			} else {
				graph.get(i).add(i - 1);
				graph.get(i).add(i + 1);
			}
		}
		if(start == 0) {
			ans.add(0);
			start++;
		}
		for (int vertex = 1; vertex <= 9; vertex++) {
			BFS(graph, vertex, "", start, end);
		}
		Collections.sort(ans);
		return ans;
	}

	private void BFS(ArrayList<ArrayList<Integer>> graph, int currentVertex, String str, int start, int end) {
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(str, currentVertex));

		while (!queue.isEmpty()) {
			Pair node = queue.poll();
			Integer temp = Integer.parseInt(node.str + node.digit);
			if (temp > end) {
				continue;
			}
			if (temp <= end && temp >= start) {
				ans.add(temp);
			}
			for (int child : graph.get(node.digit)) {
				queue.add(new Pair(temp.toString(), child));
			}
		}
	}

	public static void main(String[] args) {
		SteppingNumbers obj = new SteppingNumbers();
		System.out.println(obj.stepnum(0,100));
	}
}
