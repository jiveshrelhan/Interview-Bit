package com.dp.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * Algorithm is straight forward: Keep adding previous value to nextRow.
 * If same slot is shared the take the minimum.
 * 
 * I believe coding can be little better. Check i = A-2 solution from the comments that is more 
 * elegant
 */
public class MinPathSumTriangle {
	public int minimumTotal(ArrayList<ArrayList<Integer>> A) {
		if (A.isEmpty()) {
			return 0;
		}
		int[] prevRow = new int[A.size()];
		prevRow[0] = A.get(0).get(0);
		for (int i = 1; i < A.size(); i++) {
			int[] currentRow = new int[A.size()];
			for (int j = 0; j < A.get(i).size(); j++) {
				int case1 = Integer.MAX_VALUE, case2 = Integer.MAX_VALUE;
				if (j - 1 >= 0) {
					case1 = A.get(i).get(j) + prevRow[j - 1];
				}
				if (j < A.get(i).size() - 1) {
					case2 = A.get(i).get(j) + prevRow[j];
				}
				currentRow[j] = Math.min(case1, case2);
			}
			prevRow = currentRow;
		}
		return IntStream.of(prevRow).min().getAsInt();
	}

	public static void main(String[] args) {
		MinPathSumTriangle obj = new MinPathSumTriangle();
		ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();
		A.add(new ArrayList<>(Arrays.asList(2)));
		A.add(new ArrayList<>(Arrays.asList(3, 4)));
		A.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
		A.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
		System.out.println(obj.minimumTotal(A));
	}

}
