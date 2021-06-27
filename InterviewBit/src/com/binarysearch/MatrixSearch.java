package com.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;

public class MatrixSearch {

	private int[] binarySearch(ArrayList<ArrayList<Integer>> A, int B, int row, boolean col) {
		int start = 0;
		int found = 0;
		int end = col == true ? A.size() - 1 : A.get(row).size()-1;
		int no = -1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			no = col == true ? A.get(mid).get(0) : A.get(row).get(mid);
			if (B == no) {
				found = 1;
				return new int[] { found, mid };
			} else if (B < no) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		return new int[] { found, start - 1 };
	}

	public int searchMatrix(ArrayList<ArrayList<Integer>> A, int B) {

		int result = 0;
		int[] ans = binarySearch(A, B, -1, true);
		if (ans[0] == 1 || (ans[1] >= 0 && binarySearch(A, B, ans[1], false)[0] == 1)) {
			result = 1;
		}
		return result;
	}

	public static void main(String[] args) {
		MatrixSearch obj = new MatrixSearch();
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
		matrix.add(new ArrayList<Integer>(Arrays.asList(3)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(29)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(36)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(63)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(67)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(72)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(74)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(85)));

		System.out.println(obj.searchMatrix(matrix, 41));

	}
}
