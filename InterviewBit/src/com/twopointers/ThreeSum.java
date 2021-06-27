package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ThreeSum {
	/*
	 * private int binarySearch(ArrayList<Integer> A, int B, int k) { int start = k,
	 * end = A.size() - 1;
	 * 
	 * while (start <= end) { int mid = start + (end - start) / 2; int midValue =
	 * A.get(mid); if (midValue == B) { return mid; } else if (midValue < B) {
	 * start++; } else { end--; } } return start; }
	 * 
	 * public int threeSumClosest(ArrayList<Integer> A, int B) { int n = A.size();
	 * int sum = -1; int minimum = Integer.MAX_VALUE; Collections.sort(A); for (int
	 * i = 0; i < n; i++) { for (int j = i + 1; j < n; j++) { int remaining = B -
	 * (A.get(i) + A.get(j)); int index = binarySearch(A, B, j + 1); if (index < n -
	 * 1 && A.get(index) == remaining) { return B; } else { if (index < n && minimum
	 * > Math.abs(B - (A.get(i) + A.get(j) + A.get(index)))) { minimum = Math.abs(B
	 * - (A.get(i) + A.get(j) + A.get(index))); sum = A.get(i) + A.get(j) +
	 * A.get(index); }
	 * 
	 * if (index >= 0 && minimum > Math.abs(B - (A.get(i) + A.get(j) + A.get(index -
	 * 1)))) { minimum = Math.abs(B - (A.get(i) + A.get(j) + A.get(index - 1))); sum
	 * = A.get(i) + A.get(j) + A.get(index - 1); } } } } return sum; }
	 */

	public int threeSumClosest(ArrayList<Integer> A, int B) {
		int n = A.size();
		int sum = A.get(0) + A.get(1) + A.get(n - 1);
		Collections.sort(A);
		for (int i = 0; i < n - 2; i++) {
			int j = i + 1;
			int k = n - 1;
			while (j < k) {
				int currentSum = A.get(i) + A.get(j) + A.get(k);
				if (currentSum == B) {
					return B;
				} else if (currentSum < B) {
					j++;
				} else {
					k--;
				}
				if (Math.abs(B - sum) > Math.abs(B - currentSum)) {
					sum = currentSum;
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		ThreeSum obj = new ThreeSum();
		System.out.println(
				obj.threeSumClosest(new ArrayList<Integer>(Arrays.asList(2, 1, -9, -7, -8, 2, -8, 2, 3, -8)), -1));
	}
}
