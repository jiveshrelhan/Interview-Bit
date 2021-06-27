package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PairWithDifference {

	private boolean binarySearch(ArrayList<Integer> A, int x, int key) {
		boolean result = false;
		int start = 0;
		int end = A.size() - 1;

		while (start <= end) {
			int mid = start + (end - start) / 2;
			int midValue = A.get(mid);
			if (midValue == key && mid != x) {
				result = true;
				return result;
			} else if (midValue < key) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return result;
	}

	public int solve(ArrayList<Integer> A, int B) {
		int pairFound = 0;
		int n = A.size();
		if (n == 1) {
			pairFound = A.get(0) == B ? 1 : 0;
			return pairFound;
		}
		Collections.sort(A);

		for (int i = 0; i < n; i++) {
			int x = A.get(i);
			int y = x + B;
			boolean search = binarySearch(A, i, y);
			if (search) {
				pairFound = 1;
				return pairFound;
			}
		}
		return pairFound;
	}

	public static void main(String[] args) {
		ArrayList<Integer> A = new ArrayList<>(
				Arrays.asList(-259, -825, 459, 825, 221, 870, 626, 934, 205, 783, 850, 398));
		Collections.sort(A);
		PairWithDifference obj = new PairWithDifference();
		System.out.println(obj.solve(A, 1650));
	}
}
