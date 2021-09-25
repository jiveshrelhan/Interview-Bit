package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepeatedNFirstMissingNumber {

	class TLE {
		public ArrayList<Integer> repeatedNumber(final List<Integer> A) {

			ArrayList<Integer> ans = new ArrayList<Integer>();

			int N = A.size();

			if (N == 0)
				return ans;

			long natural_sum = N * (N + 1) / 2;

			long naturalSquareSum = (N * (2 * N + 1) * (N + 1)) / 6;

			long actual_sum = 0;
			long actualSquareSum = 0;

			for (int i = 0; i < N; i++) {
				long number = (long) A.get(i);
				actual_sum += number;
				actualSquareSum += number * number;
			}
			long X = (((actualSquareSum - naturalSquareSum) / (actual_sum - natural_sum)) + natural_sum - actual_sum)
					/ 2;
			long B = actual_sum - natural_sum + X;
			ans.add((int) B);
			ans.add((int) X);

			return ans;

		}
	}

	/*
	 * Similar to first missing number. try to put index + 1 at index. This approach
	 * also didn't work because list is final But why eclipse is not throwing error.
	 */
	class SwapSort {
		public ArrayList<Integer> repeatedNumber(final List<Integer> A) {

			ArrayList<Integer> ans = new ArrayList<Integer>();

			int i = 0;

			while (i < A.size()) {
				int element = A.get(i);
				if (A.get(element - 1) == element || A.get(i) == i + 1) {
					i++;
				} else {
					A.set(i, A.get(element - 1));
					A.set(element - 1, element);
				}
			}

			for (i = 0; i < A.size(); i++) {
				if (A.get(i) != i + 1) {
					ans.add(A.get(i));
					ans.add(i + 1);
					return ans;
				}
			}

			return ans;
		}
	}

	public static void main(String[] args) {
		RepeatedNFirstMissingNumber obj = new RepeatedNFirstMissingNumber();
		System.out.println(obj.new SwapSort().repeatedNumber(Arrays.asList(3, 1, 2, 5, 3)));
	}
}
