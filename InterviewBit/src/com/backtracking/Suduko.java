package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Suduko {

	HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();

	private boolean isSafe(ArrayList<ArrayList<Character>> n, int row, int col, int num) {
		char c = (char) (num + '0');
		for (int i = 0; i < n.size(); i++) {
			if (n.get(i).get(col) == c) {
				return false;
			}
		}

		for (int i = 0; i < n.size(); i++) {
			if (n.get(row).get(i) == c) {
				return false;
			}
		}

		for (int i = 0; i < n.size(); i++) {
			if (n.get(row).get(i) == c) {
				return false;
			}
		}
		int rowIndex = row / 3;
		int colIndex = col / 3;

		for (int i = map.get(rowIndex)[0]; i <= map.get(rowIndex)[1]; i++) {
			for (int j = map.get(colIndex)[0]; j <= map.get(colIndex)[1]; j++) {
				if (n.get(i).get(j) == c) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean helper(ArrayList<ArrayList<Character>> n) {
		int row = -1;
		int col = -1;
		boolean isEmpty = false;
		for (int i = 0; i < n.size() && !isEmpty; i++) {
			for (int j = 0; j < n.size() && !isEmpty; j++) {
				char c = n.get(i).get(j);
				if (c == '.') {
					isEmpty = true;
					row = i;
					col = j;
				}
			}
		}

		if (!isEmpty) {
			return true;
		}

		for (int i = 1; i <= 9; i++) {
			if (isSafe(n, row, col, i)) {
				n.get(row).set(col, (char)(i + '0'));
				if (helper(n)) {
					return true;
				}
				n.get(row).set(col, '.');
			}
		}
		return false;
	}

	public void solveSudoku(ArrayList<ArrayList<Character>> a) {
		map.put(0, new int[] { 0, 2 });
		map.put(1, new int[] { 3, 5 });
		map.put(2, new int[] { 6, 8 });
		helper(a);
	}

	public static void main(String[] args) {
		Suduko obj = new Suduko();
		ArrayList<ArrayList<Character>> a = new ArrayList<ArrayList<Character>>();
		a.add(new ArrayList<Character>(Arrays.asList('5', '3', '.', '.', '7', '.', '.', '.', '.')));
		a.add(new ArrayList<Character>(Arrays.asList('6', '.', '.', '1', '9', '5', '.', '.', '.')));
		a.add(new ArrayList<Character>(Arrays.asList('.', '9', '8', '.', '.', '.', '.', '6', '.')));
		a.add(new ArrayList<Character>(Arrays.asList('8', '.', '.', '.', '6', '.', '.', '.', '3')));
		a.add(new ArrayList<Character>(Arrays.asList('4', '.', '.', '8', '.', '3', '.', '.', '1')));
		a.add(new ArrayList<Character>(Arrays.asList('7', '.', '.', '.', '2', '.', '.', '.', '6')));
		a.add(new ArrayList<Character>(Arrays.asList('.', '6', '.', '.', '.', '.', '2', '8', '.')));
		a.add(new ArrayList<Character>(Arrays.asList('.', '.', '.', '4', '1', '9', '.', '.', '5')));
		a.add(new ArrayList<Character>(Arrays.asList('.', '.', '.', '.', '8', '.', '.', '7', '9')));
		obj.solveSudoku(a);
		System.out.println(a);
	}
}
