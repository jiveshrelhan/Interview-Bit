package com.backtracking;

import java.util.ArrayList;

public class NQueen {

	ArrayList<ArrayList<String>> allAns = new ArrayList<ArrayList<String>>();

	private boolean isSafe(ArrayList<ArrayList<String>> res, int n, int row, int col) {

		for (int i = 0; i < n; i++) {
			if (res.get(row).get(i) == "Q") {
				return false;
			}
		}

		for (int i = 0; i < n; i++) {
			if (res.get(i).get(col) == "Q") {
				return false;
			}
		}
		int i = row, j = col;
		while (i >= 0 && j >= 0) {
			if (res.get(i).get(j) == "Q") {
				return false;
			}
			i--;
			j--;
		}

		i = row;
		j = col;
		while (i < n && j >= 0) {
			if (res.get(i).get(j) == "Q") {
				return false;
			}
			i++;
			j--;
		}

		return true;
	}

	private void addToAllAns(ArrayList<ArrayList<String>> res) {
		ArrayList<String> thisAns = new ArrayList<String>();
		for (int i = 0; i < res.size(); i++) {
			StringBuilder str = new StringBuilder();
			for (int j = 0; j < res.size(); j++) {
				str.append(res.get(i).get(j));
			}
			thisAns.add(str.toString());
		}
		this.allAns.add(thisAns);
	}

	private void helper(ArrayList<ArrayList<String>> res, int n, int col) {
		if (col >= n) {
			addToAllAns(res);
		}

		for (int i = 0; i < n; i++) {
			if (isSafe(res, n, i, col)) {
				res.get(i).set(col, "Q");
				helper(res, n, col + 1);
				res.get(i).set(col, ".");
			}
		}
	}

	public ArrayList<ArrayList<String>> solveNQueens(int n) {
		ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < n; i++) {
			ArrayList<String> row = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				row.add(".");
			}
			ans.add(row);
		}
		helper(ans,n,0);
		return this.allAns;
	}

	public static void main(String[] args) {
		NQueen n = new NQueen();
		System.out.println(n.solveNQueens(4));
	}
}
