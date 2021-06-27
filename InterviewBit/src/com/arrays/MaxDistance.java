package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxDistance {
	static class Pair {
		int index;
		int value;

		Pair(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	// DO NOT MODIFY THE LIST. IT IS READ ONLY
	public int maximumGap(final List<Integer> A) {

		int n = A.size();

		List<Pair> sortedList = new ArrayList<>();
		int[] maxIndex = new int[n];

		for (int i = 0; i < n; i++) {
			sortedList.add(new Pair(i, A.get(i)));
		}

		Collections.sort(sortedList, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {

				if (o1.value == o2.value) {
					if (o1.index < o2.index) {
						return -1;
					} else if (o1.index > o2.index) {
						return 1;
					} else {
						return 0;
					}

				} else if (o1.value < o2.value) {
					return -1;
				} else {
					return 1;
				}

			}

		});

		int maxAns = 0;

		for (int i = n - 1; i >= 0; i--) {
			Pair pair = sortedList.get(i);
			if (i < n - 1) {
				maxIndex[i] = Math.max(maxIndex[i + 1], pair.index);
			} else
				maxIndex[i] = pair.index;
		}

		for (int i = 0; i < n; i++) {
			int ans = maxIndex[i] - sortedList.get(i).index;
			maxAns = Math.max(ans, maxAns);
		}

		return maxAns;
	}

	public static void main(String[] args) {
		MaxDistance obj = new MaxDistance();
		int ans = obj.maximumGap(Arrays.asList(1,1,1,1));
		System.out.println(ans);
	}
}
