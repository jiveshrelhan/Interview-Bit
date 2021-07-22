package com.dp.tricky;

import java.util.Arrays;
import java.util.HashMap;

/*-
 * 9, 4, 7, 2, 10
 * Its a subsequence. Can skip elements.
 * In AP : a and d are important where a is first element and
 *  d is difference between any 2 points in AP.
 */

/*-
 * One way is to consider every element as a and consider every d possible.
 * so DP is 3D [][][] i,a,d.
 * 
 * Other way might be if we freeze a and d we can compute 
 * what should be next number and using hash map we can find that if that element 
 * exists or not.
 *
 * or may be :
 * recursion(int current, int d, int[] input)
 * 
 * How to select initial d value ??
 * 
 * *BruteForce way is consider every a and d and compute LIS and return the max.
 */
public class LongestAirthmeticProgression {

	class MemoTLE {
		int memo[][];

		private int helper(int last, int current, int d, int[] A) {
			if (current >= A.length) {
				return 0;
			}
			if (memo[last][current] != -1) {
				return memo[last][current];
			}
			int include = 0;
			int exclude = helper(last, current + 1, d, A);
			if (A[current] - A[last] == d) {
				include = 1 + helper(current, current + 1, d, A);
			}
			return memo[last][current] = Math.max(include, exclude);
		}

		public int solve(final int[] A) {
			if (A.length == 0) {
				return 0;
			}
			int max = 1;
			for (int i = 0; i < A.length; i++) {
				for (int j = i + 1; j < A.length; j++) {
					memo = new int[A.length][A.length];
					for (int[] rows : memo) {
						Arrays.fill(rows, -1);
					}
					int lis = helper(i, j, A[j] - A[i], A) + 1;
					max = Math.max(max, lis);
				}
			}
			return max;
		}

	}

	/*
	 * https://www.youtube.com/watch?v=Y3sZ2bsresI
	 */
	class DPOfHashMap {
		public int solve(int[] A) {
			if (A.length == 0) {
				return 0;
			}
			@SuppressWarnings("unchecked")
			HashMap<Integer, Integer>[] dicts = new HashMap[A.length];
			for (int i = 0; i < A.length; i++) {
				dicts[i] = new HashMap<Integer, Integer>();
			}
			int maxAns = 0;

			for (int i = 1; i < A.length; i++) {
				for (int j = 0; j < i; j++) {
					int diff = A[i] - A[j];
					int newValue = dicts[j].getOrDefault(diff, 0) + 1;
					dicts[i].put(diff, newValue);
					maxAns = Math.max(maxAns, newValue);
				}
			}

			return maxAns + 1;
		}
	}

	public static void main(String[] args) {
		LongestAirthmeticProgression obj = new LongestAirthmeticProgression();
		System.out.println(obj.new MemoTLE().solve(new int[] { 3, 6, 9, 12 }));
		System.out.println(obj.new DPOfHashMap().solve(new int[] { 3 }));
	}

}
