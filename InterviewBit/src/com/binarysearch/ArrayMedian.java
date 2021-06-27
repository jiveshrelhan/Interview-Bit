package com.binarysearch;

import java.util.Arrays;
import java.util.List;

public class ArrayMedian {

	private double findNumber(final List<Integer> a, final List<Integer> b) {
		int totalElements = a.size() + b.size();
		int nby2Target = totalElements / 2;
		int start = 0;
		int end = a.size();

		while (start <= end) {

			int mid = start + (end - start) / 2;
			int posInA = mid;
			int posInB = nby2Target - (posInA);

			int leftFromA = posInA == 0 ? Integer.MIN_VALUE : a.get(posInA - 1);
			int leftFromB = posInB == 0 ? Integer.MIN_VALUE : b.get(posInB - 1);
			int rightFromA = posInA == a.size() ? Integer.MAX_VALUE : a.get(posInA);
			int rightFromB = posInB == b.size() ? Integer.MAX_VALUE : b.get(posInB);

			if (leftFromA <= rightFromB && leftFromB <= rightFromA) {
				boolean odd = totalElements % 2 == 1 ? true : false;

				if (odd) {
					return Math.min(rightFromA, rightFromB);
				}
				return (Math.min(rightFromA, rightFromB) + Math.max(leftFromA, leftFromB)) / 2.0;
			} else if (leftFromA > rightFromB) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}

		}
		return -1;
	}

	public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
		int totalElements = a.size() + b.size();
		boolean odd = totalElements % 2 == 1 ? true : false;
		int nby2Target = totalElements / 2;

		if (b.size() < a.size()) {
			return findMedianSortedArrays(b, a);
		}

		List<Integer> non_empty = a.isEmpty() || b.isEmpty() ? a.isEmpty() ? b : a : null;

		if (non_empty != null) {
			if (odd) {
				return non_empty.get(nby2Target);
			}
			return (non_empty.get(nby2Target - 1) + non_empty.get(nby2Target)) / 2.0;
		}

		return findNumber(a, b);

	}

	public static void main(String[] args) {
		ArrayMedian obj = new ArrayMedian();
		System.out.println(
				obj.findMedianSortedArrays(Arrays.asList(-47, 5, 8, 18, 22, 25 ), Arrays.asList()));
	}
}
