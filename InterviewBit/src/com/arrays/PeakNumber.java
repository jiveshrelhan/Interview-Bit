package com.arrays;

import java.util.ArrayList;

public class PeakNumber {
//5, 1, 4, 3, 6, 8, 10, 7, 9
//5	 5, 5, 5, 6, 8, 10, 10,10
//1  1  3  3  6  7   7   7 ,9	

	public int perfectPeak(ArrayList<Integer> A) {
		int n = A.size();
		if (n < 3) {
			return 0;
		}
		int left[] = new int[n];
		int right[] = new int[n];

		left[0] = A.get(0);
		for (int i = 1; i < n; i++) {
			left[i] = Math.max(left[i - 1], A.get(i));
		}

		right[n - 1] = A.get(n - 1);

		for (int i = n - 2; i >= 0; i--) {
			right[i] = Math.min(right[i + 1], A.get(i));
		}

		for (int i = 1; i < n - 1; i++) {
			int number = A.get(i);
			if (number > left[i - 1] && number < right[i + 1]) {
				return 1;
			}
		}
		return 0;
	}

}
