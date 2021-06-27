package com.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;

public class SmallerElements {
	public int solve(ArrayList<Integer> A, int B) {
		int start = 0;
		int end = A.size() - 1;
		int result = 0;
		if (A.isEmpty() || A.get(0) > B) {
			return result;
		}
		if (A.get(end) < B) {
			return A.size();
		}
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (A.get(mid) <= B) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}
	public static void main(String[] args) {
		SmallerElements obj = new SmallerElements();
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(1, 2, 5, 5)), 6));
	}
}
