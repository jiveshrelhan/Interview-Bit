package com.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidSuduko {

	HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();

	private boolean validate(List<String> A, Map<Integer, Set<Character>> rows, Map<Integer, Set<Character>> cols,
			int i, int j, char num) {
		boolean res = true;
		if (rows.get(i).contains(num)) {
			return false;
		} else {
			rows.get(i).add(num);
		}
		if (cols.get(j).contains(num)) {
			return false;
		} else {
			cols.get(j).add(num);
		}

		int rowIndex = i / 3;
		int colIndex = j / 3;

		for (int p = map.get(rowIndex)[0]; p <= map.get(rowIndex)[1]; p++) {
			for (int q = map.get(colIndex)[0]; q <= map.get(colIndex)[1]; q++) {
				if (p == i && q == j) {
					continue;
				}
				if (A.get(p).charAt(q) == num) {
					return false;
				}
			}
		}
		return res;
	}

	/*
	 * Check row and col
	 */
	public int isValidSudoku(final List<String> A) {

		Map<Integer, Set<Character>> rows = new HashMap<>();
		Map<Integer, Set<Character>> cols = new HashMap<>();
		map.put(0, new int[] { 0, 2 });
		map.put(1, new int[] { 3, 5 });
		map.put(2, new int[] { 6, 8 });
		for (int i = 0; i < A.size(); i++) {
			rows.put(i, new HashSet<>());
			cols.put(i, new HashSet<>());
		}

		/*
		 * for (int i = 0; i < A.size(); i++) { for (int j = 0; j < A.size(); j++) {
		 * char x = A.get(i).charAt(j); if (rows.containsKey(i)) { Set<Character>
		 * currentRow = rows.get(i); currentRow.add(x); rows.put(i, currentRow); } else
		 * { Set<Character> newRow = new HashSet<>(); newRow.add(x); rows.put(i,
		 * newRow); }
		 * 
		 * if (cols.containsKey(j)) { Set<Character> currentCol = cols.get(j);
		 * currentCol.add(x); cols.put(j, currentCol); } else { Set<Character> newCol =
		 * new HashSet<>(); newCol.add(x); cols.put(j, newCol); } } }
		 */

		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < A.size(); j++) {
				char c = A.get(i).charAt(j);
				if (c == '.')
					continue;
				if (!validate(A, rows, cols, i, j, c)) {
					return 0;
				}
			}
		}

		return 1;
	}

	public static void main(String[] args) {
		ValidSuduko obj = new ValidSuduko();
		System.out.println(obj.isValidSudoku(Arrays.asList("1........", ".........", ".........", ".........",
				".........", ".........", ".........", ".........", ".........")));
	}
}
