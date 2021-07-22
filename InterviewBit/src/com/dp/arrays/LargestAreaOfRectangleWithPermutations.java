package com.dp.arrays;

import java.util.Arrays;

/*
 * https://www.youtube.com/watch?v=9vYCQLYF6ng
 */
/*
 * Couldn't remember how to calculate max area of rectangle
 * Answer is histogram approach using stack.
 * but there is another approach using dynamic programming.
 * Compute length like prefix array but contiguous, 
 * and calculate area for each row. 
 */
/*
 * In this question we just need to sort the rows. so that largest 
 * height arr[]s should come closer.
 */
public class LargestAreaOfRectangleWithPermutations {

	public int solve(int[][] A) {
		int maxArea = 0;

		for (int i = 1; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == 1) {
					A[i][j] += A[i - 1][j];
				}
			}
		}

		for (int i = 0; i < A.length; i++) {
			Arrays.sort(A[i]);
		}
		int area = 0;
		for (int i = 0; i < A.length; i++) {
			int width = 1;
			for (int j = A[0].length - 1; j >= 0; j--) {
				area = A[i][j] * width;
				maxArea = Math.max(area, maxArea);
				width++;
			}
		}

		return maxArea;
	}

}
