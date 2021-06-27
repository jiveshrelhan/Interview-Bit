package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubArrayWithGivenXOR {
	/*
	 * https://www.youtube.com/watch?v=lO9R5CaGRPY
	 */
	public int solve(ArrayList<Integer> A, int target) {
		int count = 0, currentXOR = 0;
		Map<Integer, Integer> prefixXor = new HashMap<>();
		for (int num : A) {
			currentXOR = currentXOR ^ num;
			// If current whole sub[]'s XOR is target
			if (currentXOR == target) {
				count++;
			}

			// How many times we remaining XOR seen before.
			int remaining = currentXOR ^ target;
			if (prefixXor.containsKey(remaining)) {
				count += prefixXor.get(remaining);
			}
			/*
			 * k points to key, v points to value. Here there is no need of k but it's a
			 * required param. If val == null, means not present then map 1 else increase
			 * the value by 1.
			 */
			prefixXor.compute(currentXOR, (k, v) -> (v == null ? 1 : v + 1));
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(new SubArrayWithGivenXOR().solve(new ArrayList<Integer>(Arrays.asList(5, 6, 7, 8, 9)), 5));
	}
}
