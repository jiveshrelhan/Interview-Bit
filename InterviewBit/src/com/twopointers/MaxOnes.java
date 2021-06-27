package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxOnes {

	/**
	 * See notes on teams
	 */
	public int solve(ArrayList<Integer> A, int B) {
		int max = 0, n = A.size(), start = 0, end = 0;
		// Boundary check for right pointer
		while (end < n) {
			/*
			 * Check only constraint/invalid conditions and continue grow on valid
			 * situations by doing end++
			 */
			if (A.get(end) == 0) {
				B--;
			}
			/*
			 * If runs out of constraints/ permitted values, shrink the left boundary by
			 * doing start++
			 */
			if (B < 0) {
				/*
				 * At any place if you get the same invalid conditions and now you are removing
				 * that from window. Reload the constraints by doing B++;
				 */
				if (A.get(start) == 0) {
					B++;
				}
				start++;
			}
			/*
			 * Keep expanding right boundary and maintain max window size seen so far.
			 */
			end++;
			max = Math.max(max, end - start);
		}

		return max;
	}

	public int solveHighComplexity(ArrayList<Integer> A, int B) {
		int firstZero = -1, start = 0, end = A.size(), maxOne = 0, b = B;
		int localLength = 0;
		while (start < end) {
			if (A.get(start) == 1) {
				localLength++;

			} else if (b > 0) {
				localLength++;
				b--;
				if (firstZero == -1)
					firstZero = start;
			} else {

				while (start < end && A.get(start) == 1) {
					localLength++;
					start++;
				}
				maxOne = Math.max(maxOne, localLength);
				b = B;
				localLength = 0;
				start = firstZero != -1 ? firstZero : start;
				firstZero = -1;
			}
			start++;
			maxOne = Math.max(maxOne, localLength);
		}

		return maxOne;
	}

	public static void main(String[] args) {
		MaxOnes obj = new MaxOnes();
		System.out.println(obj.solve(new ArrayList<Integer>(Arrays.asList(0, 0, 0)), 0));
	}
}
