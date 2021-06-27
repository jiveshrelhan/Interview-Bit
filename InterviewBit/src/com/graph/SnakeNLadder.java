package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SnakeNLadder {

	class Pair {
		int position;
		int rolls;

		Pair(int position, int rolls) {
			this.position = position;
			this.rolls = rolls;
		}
	}

	Map<Integer, Integer> ladderMap = null;
	Map<Integer, Integer> snakesMap = null;

	private Map<Integer, Integer> constructMap(ArrayList<ArrayList<Integer>> input) {
		Map<Integer, Integer> map = new HashMap<>();

		for (ArrayList<Integer> x : input) {
			map.put(x.get(0), x.get(1));
		}

		return map;
	}

	public int BFS(int startPosition) {
		Queue<Pair> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		queue.add(new Pair(1, 0));
		visited.add(startPosition);

		while (!queue.isEmpty()) {
			Pair currentPosition = queue.poll();
			if (currentPosition.position == 100) {
				return currentPosition.rolls;
			}

			for (int dice = 1; dice <= 6; dice++) {
				int nextPossible = currentPosition.position + dice;

				if (!visited.contains(nextPossible)) {
					if (ladderMap.containsKey(nextPossible)) {
						nextPossible = ladderMap.get(nextPossible);
					} else if (snakesMap.containsKey(nextPossible)) {
						nextPossible = snakesMap.get(nextPossible);
					}
					visited.add(nextPossible);
					queue.add(new Pair(nextPossible, currentPosition.rolls + 1));
				}
			}
		}
		return -1;
	}

	public int snakeLadder(ArrayList<ArrayList<Integer>> ladders, ArrayList<ArrayList<Integer>> snakes) {

		ladderMap = constructMap(ladders);
		snakesMap = constructMap(snakes);
		return BFS(1);

	}

	public static void main(String[] args) {
		SnakeNLadder obj = new SnakeNLadder();
		ArrayList<ArrayList<Integer>> ladders = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> snakes = new ArrayList<ArrayList<Integer>>();

		ladders.add(new ArrayList<>(Arrays.asList(32, 62)));
		ladders.add(new ArrayList<>(Arrays.asList(42, 68)));
		ladders.add(new ArrayList<>(Arrays.asList(12, 98)));

		snakes.add(new ArrayList<>(Arrays.asList(95, 13)));
		snakes.add(new ArrayList<>(Arrays.asList(97, 25)));
		snakes.add(new ArrayList<>(Arrays.asList(93, 37)));
		snakes.add(new ArrayList<>(Arrays.asList(79, 27)));
		snakes.add(new ArrayList<>(Arrays.asList(75, 19)));
		snakes.add(new ArrayList<>(Arrays.asList(49, 47)));
		snakes.add(new ArrayList<>(Arrays.asList(67, 17)));

		System.out.println(obj.snakeLadder(ladders, snakes));
	}
}
