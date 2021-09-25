package com.dp.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knapsack0_1 {
	int memo[][];

	private int helper(int item, int bagCapacity, List<Integer> weights, List<Integer> cost) {
		if (bagCapacity == 0) {
			return 0;
		}
		if (item == weights.size()) {
			return 0;
		}
		if (memo[item][bagCapacity] != -1)
			return memo[item][bagCapacity];
		int chooseDish = 0, notChooseDish = 0;

		if (weights.get(item) <= bagCapacity) {
			chooseDish = cost.get(item) + helper(item+1, bagCapacity - weights.get(item), weights, cost);
		}
		notChooseDish = helper(item + 1, bagCapacity, weights, cost);
		return memo[item][bagCapacity] = Math.max(chooseDish, notChooseDish);
	}

	public int solve(ArrayList<Integer> value, ArrayList<Integer> weights, int capacity) {
		memo = new int[weights.size()][capacity + 1];
		for (int[] rows : memo) {
			Arrays.fill(rows, -1);
		}

		return helper(0, capacity, weights, value);
	}
}
