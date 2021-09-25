package com.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class DisjointIntervals {
	public int solve(int[][] A) {
		int n = A.length;
		if (n == 1) {
			return 1;
		}
		Arrays.sort(A, Comparator.comparingInt(o -> o[0]));
		int L = 0, R = 1;
		int countToRemove = 0;
		while (R < n) {
			if (A[L][1] < A[R][0]) {
				L = R;
				R++;
			} else if (A[L][1] <= A[R][1]) {
				countToRemove++;
				R++;
			} else if (A[L][1] > A[R][1]) {
				L = R;
				R++;
				countToRemove++;
			}
		}
		return n - countToRemove;
	}

	public static void main(String[] args) {
		DisjointIntervals obj = new DisjointIntervals();
		System.out.println(obj
				.solve(new int[][] { { 3, 13 }, { 7, 7 }, { 3, 10 }, { 4, 8 }, { 7, 8 }, { 9, 12 }, { 3, 6 }, { 7, 12 },
						{ 4, 10 }, { 3, 8 }, { 5, 11 }, { 9, 10 }, { 3, 7 }, { 4, 4 }, { 7, 15 }, { 2, 9 }, }));
	}
}
