package com.backtracking;

import java.util.ArrayList;

public class Combination {

	private void helper(ArrayList<ArrayList<Integer>> res, int currentNumber, int n, int k,
			ArrayList<Integer> currentSet) {

		if (currentSet.size() == k) {
			res.add(new ArrayList<>(currentSet));
			return;
		}

		for (int i = currentNumber; i <= n; i++) {
			currentSet.add(i);
			helper(res, i + 1, n, k, currentSet);
			currentSet.remove(currentSet.size() - 1);
		}
	}

	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> currentSet = new ArrayList<>();
		helper(res,1,n,k,currentSet);
		return res;
	}
	public static void main(String[] args) {
		Combination obj =new Combination();
		System.out.println(obj.combine(4, 2));
	}
}
