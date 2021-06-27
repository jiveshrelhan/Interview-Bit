package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Subset {

	private void helper(ArrayList<Integer> input, ArrayList<Integer> currentSet, int startIndex,
			ArrayList<ArrayList<Integer>> res) {
		int n = input.size();

		res.add(new ArrayList<>(currentSet));
		for (int j = startIndex; j < n; j++) {
			currentSet.add(input.get(j));
			helper(input, currentSet, j + 1, res);
			currentSet.remove(currentSet.size() - 1);
		}
	}

	public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> input) {
		Collections.sort(input);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> currentSet = new ArrayList<>();
		helper(input, currentSet, 0, res);
		return res;
	}

	public static void main(String[] args) {
		Subset obj = new Subset();
		ArrayList<Integer> input = new ArrayList<>(Arrays.asList(3, 2, 1));
		System.out.println(obj.subsets(input));
	}

}
