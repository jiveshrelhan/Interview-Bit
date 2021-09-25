package com.dp.knapsack;

import java.util.Arrays;
import java.util.List;

public class TusharBirthday {
	class ClassicKnapsackApproach {
		int memo[][];

		private int helper(int item, int bagCapacity, List<Integer> weights, List<Integer> cost) {
			if (bagCapacity == 0) {
				return 0;
			}
			if (item == weights.size()) {
				return 1000000;
			}
			if (memo[item][bagCapacity] != -1)
				return memo[item][bagCapacity];
			int chooseDish = Integer.MAX_VALUE, notChooseDish = Integer.MAX_VALUE;

			if (weights.get(item) <= bagCapacity) {
				chooseDish = cost.get(item) + helper(item, bagCapacity - weights.get(item), weights, cost);
			}
			notChooseDish = helper(item + 1, bagCapacity, weights, cost);
			return memo[item][bagCapacity] = Math.min(chooseDish, notChooseDish);
		}

		public int solve(final List<Integer> capacity, final List<Integer> weight, final List<Integer> value) {

			int totalcost = 0;
			for (int cap : capacity) {
				memo = new int[weight.size()][cap + 1];
				for (int[] rows : memo) {
					Arrays.fill(rows, -1);
				}
				totalcost += helper(0, cap, weight, value);
			}

			return totalcost;
		}

	}

	class OptimizedKnapsackApproach {
		int memo[][];

		private int helper(int item, int bagCapacity, List<Integer> weights, List<Integer> cost) {
			if (bagCapacity == 0) {
				return 0;
			}
			if (item == weights.size()) {
				return 1000000;
			}
			if (memo[item][bagCapacity] != -1)
				return memo[item][bagCapacity];
			int chooseDish = Integer.MAX_VALUE, notChooseDish = Integer.MAX_VALUE;

			if (weights.get(item) <= bagCapacity) {
				chooseDish = cost.get(item) + helper(item, bagCapacity - weights.get(item), weights, cost);
			}
			notChooseDish = helper(item + 1, bagCapacity, weights, cost);
			return memo[item][bagCapacity] = Math.min(chooseDish, notChooseDish);
		}

		public int solve(final List<Integer> capacity, final List<Integer> weight, final List<Integer> value) {
			int maxCapacity = 0;
			for (int cap : capacity) {
				maxCapacity = Math.max(maxCapacity, cap);
			}
			memo = new int[weight.size()][maxCapacity + 1];
			for (int[] rows : memo) {
				Arrays.fill(rows, -1);
			}
			int totalcost = 0;
			for (int cap : capacity) {
				totalcost += helper(0, cap, weight, value);
			}

			return totalcost;
		}

	}

	public static void main(String[] args) {
		TusharBirthday obj = new TusharBirthday();
		System.out.println(obj.new ClassicKnapsackApproach().solve(Arrays.asList(4, 6), Arrays.asList(1, 3), Arrays.asList(5, 3)));
		System.out.println(obj.new OptimizedKnapsackApproach().solve(Arrays.asList(4, 6), Arrays.asList(1, 3), Arrays.asList(5, 3)));
	}
}
