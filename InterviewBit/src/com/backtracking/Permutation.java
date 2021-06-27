package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class Permutation {
	private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> selected, ArrayList<Integer> available) {
		if (selected.size() == available.size()) {
			res.add(new ArrayList<>(selected));
			return;
		}
		for (int i = 0; i < available.size(); i++) {
			int number = available.get(i);
			if (number < 0) {
				continue;
			}
			available.set(i, -1 * available.get(i));
			selected.add(number);
			helper(res, selected, available);
			selected.remove(selected.size()-1);
			available.set(i, -1 * available.get(i));
		}

	}

	public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		helper(res, new ArrayList<>(), A);
		return res;
	}
	
	public static void main(String[] args) {
		Permutation obj = new Permutation();
		System.out.println(obj.permute(new ArrayList<>(Arrays.asList(1,2,3))));
	}
}
