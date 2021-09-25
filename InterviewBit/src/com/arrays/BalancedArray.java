package com.arrays;

import java.util.ArrayList;

public class BalancedArray {

	public int solve(ArrayList<Integer> A) {
		int count = 0;
		int n = A.size();
		int[] leftOddPrefix = new int[n];
		int[] leftEvenPrefix = new int[n];
		int[] rightOddSuffix = new int[n];
		int[] rightEvenSuffix = new int[n];

		leftOddPrefix[0] = 0;
		leftEvenPrefix[0] = A.get(0);
		for (int i = 1; i < n; i++) {
			if (i % 2 == 0) {
				leftOddPrefix[i] = leftOddPrefix[i - 1];
				leftEvenPrefix[i] = leftEvenPrefix[i - 1] + A.get(i);
			} else {
				leftEvenPrefix[i] = leftEvenPrefix[i - 1];
				leftOddPrefix[i] = leftOddPrefix[i - 1] + A.get(i);
			}
		}

		return count;
	}
}
