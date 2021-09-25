package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class CombinationSum2 {

	class Optimized {

		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		private void helper(ArrayList<Integer> input, int index, int target, ArrayList<Integer> currentSet) {
			if (target == 0) {
				res.add(new ArrayList<Integer>(currentSet));
				return;
			}

			if (target < 0) {
				return;
			}
			Integer prev = null;
			for (int i = index; i < input.size(); i++) {
				if (prev!= null && prev == input.get(i))
					continue;
				currentSet.add(input.get(i));
				helper(input, i + 1, target - input.get(i), currentSet);
				currentSet.remove(currentSet.size() - 1);
				prev = input.get(i);
			}
		}

		public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> input, int target) {
			Collections.sort(input);
			helper(input, 0, target, new ArrayList<Integer>());
			return res;
		}
	}

	private void helper(int start, ArrayList<Integer> input, int target, ArrayList<Integer> currentSet, int currentSum,
			TreeSet<ArrayList<Integer>> res) {

		int n = input.size();
		if (currentSum == target) {
			res.add(new ArrayList<>(currentSet));
		}
		if (currentSum > target) {
			return;
		}

		for (int i = start; i < n; i++) {
			currentSum += input.get(i);
			currentSet.add(input.get(i));
			helper(i + 1, input, target, currentSet, currentSum, res);
			currentSum -= input.get(i);
			currentSet.remove(currentSet.size() - 1);
		}
	}

	public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> input, int target) {
		TreeSet<ArrayList<Integer>> res = new TreeSet<>(new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				int i = 0, j = 0;
				if (o1.size() < o2.size()) {
					return -1;
				} else if (o2.size() < o1.size()) {
					return 1;
				}
				while (i < o1.size() && j < o2.size()) {
					if (o1.get(i) < o2.get(j)) {
						return -1;
					} else if (o1.get(i) > o2.get(j)) {
						return 1;
					}
					i++;
					j++;
				}
				if (i < o1.size()) {
					return 1;
				} else if (i >= o1.size() && j < o2.size()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		Collections.sort(input);
		helper(0, input, target, new ArrayList<Integer>(), 0, res);
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		res.stream().forEach(k -> {
			ans.add(k);
		});
		return ans;
	}

	public static void main(String[] args) {
		CombinationSum2 obj = new CombinationSum2();
		System.out.println(obj.combinationSum(new ArrayList<>(Arrays.asList(10, 1, 2, 7, 6, 1, 5)), 8));
		System.out.println(obj.new Optimized().combinationSum(new ArrayList<>(Arrays.asList(10, 1, 2, 7, 6, 1, 5)), 8));
	}
}
