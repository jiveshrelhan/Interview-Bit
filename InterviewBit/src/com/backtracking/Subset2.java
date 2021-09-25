package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Subset2 {

	class Optimized {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		private void helper(ArrayList<Integer> input, int index, ArrayList<Integer> currentSet) {
			if (index > input.size())
				return;

			res.add(new ArrayList<Integer>(currentSet));
			Integer prev = null;
			for (int i = index; i < input.size(); i++) {
				if (prev != null && prev == input.get(i))
					continue;
				currentSet.add(input.get(i));
				helper(input, i + 1, currentSet);
				currentSet.remove(currentSet.size() - 1);
				prev = input.get(i);
			}
		}

		public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> input) {
			Collections.sort(input);
			helper(input, 0, new ArrayList<Integer>());
			return res;
		}

	}

	private void checkDuplicate(ArrayList<ArrayList<Integer>> res, String string, Set<String> set) {
		if (!set.contains(string)) {
			set.add(string);
			ArrayList<Integer> currentSet = new ArrayList<Integer>();
			String[] str = string.split("#");
			for (String x : str) {
				if (!x.isEmpty())
					currentSet.add(Integer.valueOf(x));
			}
			res.add(currentSet);
		}
	}

	private void helper(ArrayList<ArrayList<Integer>> res, int start, String str, Set<String> set,
			ArrayList<Integer> input) {
		checkDuplicate(res, str, set);

		for (int i = start; i < input.size(); i++) {
			helper(res, i + 1, str + "#" + String.valueOf(input.get(i)), set, input);
			helper(res, i + 1, str, set, input);
		}
	}

	public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> input) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		Collections.sort(input);
		Set<String> set = new HashSet<>();
		helper(res, 0, "", set, input);
		return res;

	}

	public static void main(String[] args) {
		Subset2 obj = new Subset2();
		System.out.println(obj.subsetsWithDup(new ArrayList<>(Arrays.asList(1, 2, 2, 3))));
		System.out.println(obj.new Optimized().subsetsWithDup(new ArrayList<>(Arrays.asList(1, 2, 2, 3))));
	}

}
