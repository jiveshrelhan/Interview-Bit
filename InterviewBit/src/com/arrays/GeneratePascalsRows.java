package com.arrays;

import java.util.ArrayList;

public class GeneratePascalsRows {
	public ArrayList<ArrayList<Integer>> solve(int K) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> prevRow = new ArrayList<Integer>();
		prevRow.add(1);
		for (int i = 1; i <= K; i++) {
			ArrayList<Integer> currentRow = new ArrayList<Integer>();
			for (int j = 0; j < i; j++) {
				int x = j >= 0 && j < prevRow.size() ? prevRow.get(j) : 0;
				int y = j - 1 >= 0 && j - 1 < prevRow.size() ? prevRow.get(j - 1) : 0;
				currentRow.add(x + y);
			}
			ans.add(currentRow);
			prevRow = currentRow;
		}
		return ans;
	}
	public static void main(String[] args) {
		GeneratePascalsRows obj = new GeneratePascalsRows();
		System.out.println(obj.solve(1));
	}
}
