package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CoutingTriangles {

	@SuppressWarnings("unused")
	private int binarySearch(ArrayList<Integer> A, int x, int key) {
		int start = x, end = A.size() - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int midValue = A.get(mid);
			if (midValue <= key) {
				start = mid + 1;
			} else
				end = mid - 1;
		}
		return end;
	}

	public int nTriang(ArrayList<Integer> A) {
		long count = 0;
		int n = A.size();
		long mod = (long) 1e9 + 7;
		if (n < 3)
			return (int) count;
		Collections.sort(A);
		// i<j<k 3 Sides of Triangle
		for (int k = n - 1; k >= 2; k--) {
			int j = k - 1, i = 0;
			while (i < j) {
				int sum = A.get(i) + A.get(j);
				/**
				 * If Sum is less than A.get(K) then, it won't form triangle. As soon as it
				 * forms triangle. All numbers between [i,j] with k will form triangle.
				 */
				if (sum <= A.get(k)) {
					i++;
				} else {
					count += j - i;
					/**
					 * We got all the answer by fixing k and j with i now. We will try with k,j-1
					 * and i. See diagram on
					 * http://techieme.in/count-triangles-formed-by-the-elements-of-array/
					 */
					j--;
				}
			}
		}

		return (int) (count % mod);
	}

	public static void main(String[] args) {
		CoutingTriangles obj = new CoutingTriangles();
		System.out.println(obj.nTriang(new ArrayList<Integer>(Arrays.asList(4, 6, 13, 16, 20, 3, 1, 12))));
	}
}
