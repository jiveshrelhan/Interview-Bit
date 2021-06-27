package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class MinimumCostPath {

	class Pair {
		int i, j;
		int cost;

		Pair(int i, int j, int cost) {
			this.i = i;
			this.j = j;
			this.cost = cost;
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
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			return i == other.i && j == other.j;
		}
	}

	int n = 0;
	int m = 0;
	LinkedList<Pair> queue = new LinkedList<>();

	private boolean isValid(int i, int j) {
		if (i >= 0 && i < n && j >= 0 && j < m) {
			return true;
		}
		return false;
	}

	private void addToQueue(Pair p, boolean atFront) {
		if (isValid(p.i, p.j)) {
			if (atFront) {
				queue.addFirst(p);
			} else {
				queue.addLast(p);
			}
		}
	}

	private int bfs(int n, int m, ArrayList<String> directions) {
		Pair src = new Pair(0, 0, 0);
		queue.add(src);
		HashSet<Pair> visited = new HashSet<>();
		while (!queue.isEmpty()) {
			Pair p = queue.poll();

			if (p.i == n && p.j == m) {
				return p.cost;
			}

			if (!visited.contains(p)) {
				char dir = directions.get(p.i).charAt(p.j);
				visited.add(p);
				if (dir == 'U') {
					addToQueue(new Pair(p.i - 1, p.j, p.cost), true);
					addToQueue(new Pair(p.i + 1, p.j, p.cost + 1), false);
					addToQueue(new Pair(p.i, p.j - 1, p.cost + 1), false);
					addToQueue(new Pair(p.i, p.j + 1, p.cost + 1), false);
				}
				if (dir == 'D') {
					addToQueue(new Pair(p.i - 1, p.j, p.cost + 1), false);
					addToQueue(new Pair(p.i + 1, p.j, p.cost), true);
					addToQueue(new Pair(p.i, p.j - 1, p.cost + 1), false);
					addToQueue(new Pair(p.i, p.j + 1, p.cost + 1), false);
				}
				if (dir == 'L') {
					addToQueue(new Pair(p.i - 1, p.j, p.cost + 1), false);
					addToQueue(new Pair(p.i + 1, p.j, p.cost + 1), false);
					addToQueue(new Pair(p.i, p.j - 1, p.cost), true);
					addToQueue(new Pair(p.i, p.j + 1, p.cost + 1), false);
				}
				if (dir == 'R') {
					addToQueue(new Pair(p.i - 1, p.j, p.cost + 1), false);
					addToQueue(new Pair(p.i + 1, p.j, p.cost + 1), false);
					addToQueue(new Pair(p.i, p.j - 1, p.cost + 1), false);
					addToQueue(new Pair(p.i, p.j + 1, p.cost), true);
				}
			}
		}
		return 0;
	}

	public int solve(int n, int m, ArrayList<String> directions) {
		if (n == 0 && m == 0)
			return 0;
		this.n = n;
		this.m = m;
		return bfs(n - 1, m - 1, directions);
	}

	public static void main(String[] args) {
		MinimumCostPath obj = new MinimumCostPath();
		System.out.println(obj.solve(1, 4, new ArrayList<String>(Arrays.asList("LLLL"))));
	}
}
