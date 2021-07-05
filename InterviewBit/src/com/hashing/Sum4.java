package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Sum4 {

	/**
	 * Important to know, how we are returning all indexes of C and D
	 * 
	 * @param A
	 * @param startIndex
	 * @param target
	 * @return
	 */
	private ArrayList<ArrayList<Integer>> twoSum(ArrayList<Integer> A, int startIndex, int target) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		if (A.isEmpty()) {
			return ans;
		}
		/*
		 * Since input can have duplicates too. We need to provide solution will all
		 * indexes.
		 */
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int i = startIndex; i < A.size(); i++) {
			int a = A.get(i);
			int required = target - a;
			/*
			 * If we get the required target (C) in hash map, then create all solutions
			 * using all indexes of C present in map. here D is i.
			 */
			if (map.containsKey(required)) {
				ArrayList<Integer> possibleStartingIndex = map.get(required);
				// All C's and D
				for (int x : possibleStartingIndex) {
					ans.add(new ArrayList<>(Arrays.asList(x, i)));
				}

			} // If seen first time, create empty list and add first index of C
			else if (!map.containsKey(a)) {
				map.put(a, new ArrayList<>(Arrays.asList(i)));
			} // Else keep updating C's indexes.
			else {
				ArrayList<Integer> existing = map.get(a);
				existing.add(i);
				map.put(a, existing);
			}

		}
		return ans;

	}

	public ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> input, int x) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		// Set is used to return the all the solution in sorted order.
		Set<ArrayList<Integer>> set = new TreeSet<>(new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				if (o1.get(0) < o2.get(0))
					return -1;
				else if (o1.get(0) > o2.get(0))
					return 1;
				else if (o1.get(1) < o2.get(1))
					return -1;
				else {
					if (o1.get(1) > o2.get(1))
						return 1;
					else if (o1.get(2) < o2.get(2))
						return -1;
					else {
						if (o1.get(2) > o2.get(2))
							return 1;
						else if (o1.get(3) < o2.get(3))
							return -1;
						else {
							if (o1.get(3) > o2.get(3))
								return 1;
							else
								return 0;
						}
					}
				}
			}
		});

		// Sort the input
		Collections.sort(input);

		for (int i = 0; i < input.size() - 3; i++) {
			for (int j = i + 1; j < input.size() - 2; j++) {
				int a = input.get(i);
				int b = input.get(j);
				int remaining = x - (a + b);
				// Find C,D using 2 Sum. Remaining = Target - (A+B)
				ArrayList<ArrayList<Integer>> ans = twoSum(input, j + 1, remaining);
				if (!ans.isEmpty()) {
					// Since there can be many indexes of C and D satisfies the condition, need to
					// put all of them in result.
					for (ArrayList<Integer> all : ans) {
						ArrayList<Integer> temp = new ArrayList<Integer>(
								// A,B,C,D
								Arrays.asList(a, b, input.get(all.get(0)), input.get(all.get(1))));
						set.add(temp);
					}

				}
			}
		}
		// Copy all the sorted solution to array list.
		set.forEach(k -> {
			result.add(k);
		});
		return result;
	}

	public static void main(String[] args) {
		Sum4 obj = new Sum4();
		System.out
				.println(
						obj.fourSum(
								new ArrayList<Integer>(Arrays.asList(9, -8, -10, -7, 7, -8, 2, -7, 4, 7, 0, -3, -4, -5,
										-1, -4, 5, 8, 1, 9, -2, 5, 10, -5, -7, -1, -6, 4, 1, -5, 3, 8, -4, -10, -9, -3,
										10, 0, 7, 9, -8, 10, -9, 7, 8, 0, 6, -6, -7, 6, -4, 2, 0, 10, 1, -2, 5, -2)),
								0));
	}
}
