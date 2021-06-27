package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class ThreeSumZero {
	public ArrayList<ArrayList<Integer>> threeSum(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		TreeSet<ArrayList<Integer>> tree_set = new TreeSet<ArrayList<Integer>>(new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				if (o1.get(0) < o2.get(0)) {
					return -1;
				} else if (o1.get(0) > o2.get(0)) {
					return 1;
				} else {
					if (o1.get(1) < o2.get(1)) {
						return -1;
					} else if (o1.get(1) > o2.get(1)) {
						return 1;
					} else {
						if (o1.get(2) < o2.get(2)) {
							return -1;
						} else if (o1.get(2) > o2.get(2)) {
							return 1;
						} else {
							return 0;
						}
					}
				}
			}
		});
		int n = A.size();
		Collections.sort(A);
		for (int i = 0; i < n - 2; i++) {
			int j = i + 1;
			int k = n - 1;
			while (j < k) {
				long currentSum = (long)A.get(i) + A.get(j) + A.get(k);
				if (currentSum == 0) {
					tree_set.add(new ArrayList<Integer>(Arrays.asList(A.get(i), A.get(j), A.get(k))));
					j++;
					k--;
				} else if (currentSum < 0) {
					j++;
				} else {
					k--;
				}
			}
		}
		tree_set.stream().forEach(k -> {
			ans.add(k);
		});
		return ans;
	}

	public static void main(String[] args) {
		ThreeSumZero obj = new ThreeSumZero();
		System.out.println(obj.threeSum(new ArrayList<Integer>(
				Arrays.asList(2147483647, -2147483648, -2147483648, 0, 1))));
		System.out.println((long)-2147483648 + -2147483648);
	}
}
