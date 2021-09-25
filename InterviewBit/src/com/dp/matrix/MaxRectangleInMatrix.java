package com.dp.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * Better Algo to find max area histogram.
 * Aditya verma's also is very complex and required lot of code.
  NGL,PSL and so on..
  
  https://www.youtube.com/watch?v=g8bSdXCG-lA
  https://www.youtube.com/watch?v=g8bSdXCG-lA
  */
public class MaxRectangleInMatrix {

	/*-
	 * Remove current tower and compute area. height will be tower height * width;
	 * width can be calculated by observation: if stack is empty. it means there is
	 * at-least height available in all the towers.
	 * 
	 * if the stack is not empty: There is at least height present till stack second
	 * last from top index. 
	 * Then area will be height * ( currentIndex - secondLastIndex) 
	 * -1 will be added for adjustment as its index based.
	 */
	private int computeArea(ArrayList<Integer> row, Deque<Integer> stack, int i) {

		int top = stack.pollFirst();

		// row.get(top) is height
		if (stack.isEmpty()) {
			return row.get(top) * i;
		} else {
			// height * width
			return row.get(top) * (i - stack.peekFirst() - 1);
		}
	}

	private int computeHistogram(ArrayList<Integer> row) {
		int m = row.size();
		Deque<Integer> stack = new LinkedList<>();
		int maxAreaInCurrentHistogram = 0;
		int i = 0;
		for (i = 0; i < m;) {
			int height = row.get(i);

			if (stack.isEmpty() || row.get(stack.peekFirst()) <= height) {
				stack.offerFirst(i);
				i++;
			} else {
				int area = computeArea(row, stack, i);
				maxAreaInCurrentHistogram = Math.max(maxAreaInCurrentHistogram, area);
			}
		}

		while (!stack.isEmpty()) {
			int area = computeArea(row, stack, i);
			maxAreaInCurrentHistogram = Math.max(maxAreaInCurrentHistogram, area);
		}
		return maxAreaInCurrentHistogram;
	}

	public int maximalRectangle(ArrayList<ArrayList<Integer>> A) {
		int n = A.size();
		if (n == 0)
			return 0;
		int m = A.get(0).size();
		int maxAreaInOverallMatrix = 0;

		// Pre-compute area of first row
		ArrayList<Integer> currentRow = A.get(0);
		maxAreaInOverallMatrix = computeHistogram(currentRow);

		// Merge currentRow with previousRow and recompute the histogram
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int value = A.get(i).get(j);
				// If current value is 0, this can't contribute to create a rectangle.
				if (value == 0) {
					currentRow.set(j, 0);
				} else {
					int oldValueInPreviousRow = currentRow.get(j);
					currentRow.set(j, oldValueInPreviousRow + value);
				}
			}
			maxAreaInOverallMatrix = Math.max(maxAreaInOverallMatrix, computeHistogram(currentRow));
		}

		return maxAreaInOverallMatrix;
	}

	public static void main(String[] args) {
		MaxRectangleInMatrix obj = new MaxRectangleInMatrix();
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		matrix.add(new ArrayList<Integer>(Arrays.asList(1, 1, 1)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(1, 1, 1)));
		System.out.println(obj.maximalRectangle(matrix));
	}
}
