package com.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstLastOccurance {
	private int binarySearch(List<Integer> A, int B, int x, int y) {
		int start = x, end = y;

		while (start <= end) {
			int mid = start + (end - start) / 2;

			int midValue = A.get(mid);

			if (midValue == B) {
				return mid;
			} else if (midValue < B) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	public ArrayList<Integer> searchRange(final List<Integer> A, int B) {

		ArrayList<Integer> ans = new ArrayList<>();

		int firstOccurance = binarySearch(A, B, 0, A.size() - 1);

		if (firstOccurance == -1) {
			ans.add(-1);
			ans.add(-1);
			return ans;
		}

		ans.add(firstOccurance);
		ans.add(firstOccurance);

		int tryLeft = binarySearch(A, B, 0, firstOccurance - 1);

		while (tryLeft != -1) {
			ans.set(0, tryLeft);
			tryLeft = binarySearch(A, B, 0, tryLeft - 1);
		}

		int tryRight = binarySearch(A, B, firstOccurance + 1, A.size() - 1);

		while (tryRight != -1) {
			ans.set(1, tryRight);
			tryRight = binarySearch(A, B, tryRight + 1, A.size() - 1);
		}

		return ans;
	}

	public static void main(String[] args) {
		FirstLastOccurance obj = new FirstLastOccurance();
		System.out.println(obj.searchRange(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5,
				5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
				7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10, 10, 10, 10, 10, 10, 10), 10));
	}
}
