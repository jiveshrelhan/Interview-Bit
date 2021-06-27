package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class UsefulExtraEdges_Dijkstra {

	class Edge {
		int to;
		int cost;

		Edge(int to, int cost) {
			this.cost = cost;
			this.to = to;
		}
	}

	class Node {
		int id;
		Integer cost;

		Node(int dest, Integer cost) {
			this.id = dest;
			this.cost = cost;
		}
	}

	int n;
	ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
	int[] distance;
	boolean[] visited;
	PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.cost.compareTo(o2.cost);
		}
	});

	public int solve(int n, ArrayList<ArrayList<Integer>> directed_pairs, int src, int dest,
			ArrayList<ArrayList<Integer>> undirecteed_useful_pairs) {
		this.n = n;
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (ArrayList<Integer> pairs : directed_pairs) {
			graph.get(pairs.get(0)).add(new Edge(pairs.get(1), pairs.get(2)));
		}
		distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[dest] = computeShortestPath(undirecteed_useful_pairs, src, dest);
		if (distance[dest] == Integer.MAX_VALUE) {
			return -1;
		}
		return distance[dest];
	}

	private int computeShortestPath(ArrayList<ArrayList<Integer>> undirecteed_useful_pairs, int src, int dest) {
		int min_ans = Integer.MAX_VALUE;
		for (ArrayList<Integer> pairs_E : undirecteed_useful_pairs) {

			if (pairs_E.get(0) > n || pairs_E.get(1) > n) {
				continue;
			}

			Edge u_to_v = new Edge(pairs_E.get(1), pairs_E.get(2));
			Edge v_to_u = new Edge(pairs_E.get(0), pairs_E.get(2));

			graph.get(pairs_E.get(0)).add(u_to_v);
			graph.get(pairs_E.get(1)).add(v_to_u);

			distance = new int[n + 1];
			visited = new boolean[n + 1];
			Arrays.fill(distance, Integer.MAX_VALUE);

			queue.add(new Node(src, 0));
			distance[src] = 0;
			visited[src] = true;

			while (!queue.isEmpty()) {
				Node parent = queue.poll();

				for (Edge child : graph.get(parent.id)) {
					if (visited[child.to])
						continue;
					int old_distance = distance[child.to];
					int new_distance = distance[parent.id] + child.cost;
					if (new_distance < old_distance) {
						distance[child.to] = new_distance;
						queue.add(new Node(child.to, distance[child.to]));
					}
				}
			}
			graph.get(pairs_E.get(0)).remove(u_to_v);
			graph.get(pairs_E.get(1)).remove(v_to_u);
			min_ans = Math.min(distance[dest], min_ans);
		}
		return distance[dest] = min_ans;
	}

	public static void main(String[] args) {
		UsefulExtraEdges_Dijkstra obj = new UsefulExtraEdges_Dijkstra();
		ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
		B.add(new ArrayList<>(Arrays.asList(1, 5, 5)));
		ArrayList<ArrayList<Integer>> E = new ArrayList<ArrayList<Integer>>();
		E.add(new ArrayList<>(Arrays.asList(6, 7, 4)));
		System.out.println(obj.solve(6, B, 2, 4, E));
	}
}
