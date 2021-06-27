package com.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Diffk2 {
	/*
	 * Couldn't solve. Corner case by example 
	 * when k ==53 and arr [53 0]
	 * for i = 0, remaining is 53 map is empty. continue and add to map
	 * map contains 53
	 * for i=1, remaining 0-53 = -53 map contains 53 not -53. So we have to compute for 0 + 53 
	 * 
	 *   Algo becomes current - remaining and current + remaining
	 */
	public int diffPossible(final List<Integer> A, int target) {

		HashSet<Integer> set = new HashSet<>();
		for (int x : A) {
			int remaining1 = x - target;
			int remaining2 = x + target;
			if (set.contains(remaining1) || set.contains(remaining2)) {
				return 1;
			} else {
				set.add(x);
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		Diffk2 obj = new Diffk2();
		System.out.println(obj.diffPossible(Arrays.asList(77, 28, 19, 21, 67, 15, 53, 25, 82, 52, 8, 94, 50, 30, 37, 39,
				9, 43, 35, 48, 82, 53, 16, 20, 13, 95, 18, 67, 77, 12, 93, 0), 53));
	}
}
