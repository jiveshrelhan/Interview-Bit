package com.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidSuduko {

	/*
	 * Defines global index mapping. Since Sudoku is 3 x 3. First cube is from 0,0
	 * to 2,2 Second cube is from 3,3 to 5,5 Third cube is from 6,6 to 8,8 Forth
	 * cube is from 1,1 to 2,2 and so on.
	 */
	/*
	 * This will help us finding the exact cube on which we are currently working
	 * on. We need to check in the same cube if if the num we are trying to insert
	 * is already present or not.
	 */
	HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();

	private boolean validate(List<String> A, Map<Integer, Set<Character>> rows, Map<Integer, Set<Character>> cols,
			int i, int j, char num) {
		boolean res = true;
		/*
		 * 3 Conditions : Num should not be present in same column, same row and in same
		 * local cube.
		 */

		/*
		 * If present in row return false. Else add to current row set.
		 */
		if (rows.get(i).contains(num)) {
			return false;
		} else {
			rows.get(i).add(num);
		}
		/*
		 * If present in column return false. Else add to current column set.
		 */
		if (cols.get(j).contains(num)) {
			return false;
		} else {
			cols.get(j).add(num);
		}

		/*
		 * Defines the coordinates of current working cube.
		 */
		int rowIndex = i / 3;
		int colIndex = j / 3;

		/*
		 * Check only those element in whole row which falls under current row and
		 * current column of cube. 3 X 3 cube
		 * 
		 */
		for (int p = map.get(rowIndex)[0]; p <= map.get(rowIndex)[1]; p++) {
			for (int q = map.get(colIndex)[0]; q <= map.get(colIndex)[1]; q++) {
				// Not the same coordinate.
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

		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < A.size(); j++) {
				char c = A.get(i).charAt(j);
				if (c == '.')
					continue;
				// Run the algo on all '.' if we are not able to full fill then then return 0
				// else 1.
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
