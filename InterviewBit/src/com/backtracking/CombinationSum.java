package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CombinationSum {

	private void helper(int start,ArrayList<Integer> input, int target, ArrayList<Integer> currentSet, int currentSum,
			ArrayList<ArrayList<Integer>> res) {

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
			helper(i,input, target, currentSet, currentSum, res);
			currentSum -= input.get(i);
			currentSet.remove(currentSet.size() - 1);
		}
	}

	public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> input, int target) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		List<Integer> newList = input.stream().sorted()
                .distinct()
                .collect(Collectors.toList());
		
		ArrayList<Integer> sortedDuplicateFree = new ArrayList<>(newList);
		helper(0,sortedDuplicateFree, target, new ArrayList<Integer>(), 0, res);
		return res;
	}

	public static void main(String[] args) {
		CombinationSum obj = new CombinationSum();
		System.out.println(obj.combinationSum(new ArrayList<>(Arrays.asList(3, 3, 1)), 4));
	}
}
