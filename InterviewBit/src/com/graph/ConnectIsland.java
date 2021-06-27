package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ConnectIsland {

	class Pair {
		int destination, cost;

		Pair(int destination, int cost) {
			this.destination = destination;
			this.cost = cost;
		}
	}

	int n = 0;
	List<Pair>[] graph = null;

	private void addAdjacentsEdges(List<Pair>[] graph, PriorityQueue<Pair> queue, int node, int[] visited) {
		for (Pair neighbour : graph[node]) {
			if (visited[neighbour.destination] == 0) {
				queue.add(neighbour);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public int solve(int A, ArrayList<ArrayList<Integer>> B) {
		n = A + 1;
		graph = new ArrayList[n];
		int[] visited = new int[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Pair>();
		}
		for (ArrayList<Integer> entry : B) {
			int src = entry.get(0), dest = entry.get(1), cost = entry.get(2);
			Pair newPair = new Pair(dest, cost);
			graph[src].add(newPair);
			graph[dest].add(new Pair(src,cost));
		}
		PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				return Integer.valueOf(o1.cost).compareTo(Integer.valueOf(o2.cost));
			}
		});
		int sum = 0;
		addAdjacentsEdges(graph, queue, 1, visited);

		visited[1] = 1;
		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			if (visited[p.destination] == 1) {
				continue;
			}
			sum += p.cost;
			visited[p.destination] = 1;
			addAdjacentsEdges(graph, queue, p.destination, visited);
		}

		return sum;
	}

	public static void main(String[] args) {
		ConnectIsland obj = new ConnectIsland();
		ArrayList<ArrayList<Integer>> B = new ArrayList<>();
		B.add(new ArrayList<>(Arrays.asList(1, 2, 10)));
		B.add(new ArrayList<>(Arrays.asList(2, 3, 5)));
		B.add(new ArrayList<>(Arrays.asList(1, 3, 9)));
		System.out.println(obj.solve(3, B));
	}
}
