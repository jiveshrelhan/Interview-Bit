package com.dp.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TusharBirthday {
	class Recursion {

		int capacity;
		int[] strength;

		private int helper(int friend, int capacityLeft) {
			if (capacityLeft <= 0) {
				return 0;
			}
			if (friend == strength.length) {
				return 0;
			}
			int case1 = 0;

			int case2 = helper(friend + 1, capacityLeft);

			if (strength[friend] <= capacityLeft) {
				case1 = 1 + helper(friend, capacityLeft - strength[friend]);
			}
			return Math.max(case1, case2);
		}

		public int[] solve(int A, int[] B) {
			this.capacity = A;
			this.strength = B;
			System.out.println(helper(0, capacity));
			return null;
		}
	}

	class ActualRecursion {
		int capacity;
		int[] strength;
		ArrayList<Integer> ans = new ArrayList<>();

		private int helper(int friend, int capacityLeft, ArrayList<Integer> friendsKicked) {

			if (friendsKicked.size() >= ans.size()) {
				ans = new ArrayList<Integer>(friendsKicked);
			}

			if (capacityLeft <= 0) {
				return 0;
			}
			if (friend == strength.length) {
				return 0;
			}

			int case1 = 0;

			int case2 = helper(friend + 1, capacityLeft, friendsKicked);
			if (strength[friend] <= capacityLeft) {
				friendsKicked.add(friend);
				case1 = 1 + helper(friend, capacityLeft - strength[friend], friendsKicked);
				friendsKicked.remove(friendsKicked.size() - 1);
			}

			return Math.max(case1, case2);

		}

		public int[] solve(int A, int[] B) {
			this.capacity = A;
			this.strength = B;
			helper(0, capacity, new ArrayList<Integer>());
			return ans.stream().mapToInt(Integer::intValue).toArray();
		}
	}

	/*
	 * Mind Fucked up. Memo solution not working.
	 */
	class Memo {
		int capacity;
		int[] strength;
		ArrayList<Integer> ans = new ArrayList<>();
		int[][] memo;

		private int helper(int friend, int capacityLeft, ArrayList<Integer> friendsKicked) {

			System.out.println(friendsKicked);

			if (friendsKicked.size() >= ans.size()) {
				ans = new ArrayList<Integer>(friendsKicked);
			}

			if (capacityLeft <= 0) {
				return 0;
			}
			if (friend == strength.length) {
				return 0;
			}

			if (memo[friend][capacityLeft] != -1) {
				return memo[friend][capacityLeft];
			}

			int case1 = 0;

			int case2 = helper(friend + 1, capacityLeft, friendsKicked);
			if (strength[friend] <= capacityLeft) {
				friendsKicked.add(friend);
				case1 = 1 + helper(friend, capacityLeft - strength[friend], friendsKicked);
				friendsKicked.remove(friendsKicked.size() - 1);
			}

			return memo[friend][capacityLeft] = Math.max(case1, case2);

		}

		public int[] solve(int A, int[] B) {
			this.capacity = A;
			this.strength = B;
			memo = new int[B.length + 1][A + 1];
			for (int[] rows : memo) {
				Arrays.fill(rows, -1);
			}
			helper(0, capacity, new ArrayList<Integer>());
			return ans.stream().mapToInt(Integer::intValue).toArray();
		}
	}

	/*-
	 * I attempted a DP version of it. 
	 * Was sure it was a application of unbounded knapsack.
	 */
	
	/*-
	 * HIGHEST DIFFICULTY FACED IN HOW TO PRINT THE SELECTION
	 * THERE IS ONE WAY USING 1D ARRAY BUT THIS ONE WAS LITTLE SIMPLE.
	 */
	/*- 
	 * Algo : Mark the selection. Selection is when we get the better the result.
	 * Then we have to back track. Since interviewer has asked to print the smallest 
	 * lexographical solution.
	 * 
	 * Trick was to reverse the B/cost array. the first answer we get from backtracking will
	 * be actual the smallest one.
	 */
	class DP {
		public int[] solve(int A, int[] B) {
			List<Integer> t = IntStream.of(B).boxed().collect(Collectors.toList());
			Collections.reverse(t);
			B = t.stream().mapToInt(Integer::intValue).toArray();
			int[][] dp = new int[B.length + 1][A + 1];
			boolean[][] selected = new boolean[B.length + 1][A + 1];
			ArrayList<Integer> ans = new ArrayList<>();
			for (int i = 1; i < dp.length; i++) {
				for (int j = 1; j < dp[0].length; j++) {
					dp[i][j] = dp[i - 1][j];
					if (B[i - 1] <= j) {
						int include = 1 + dp[i][j - B[i - 1]];
						if (include >= dp[i][j]) {
							// We get better and similar result mark it.
							selected[i][j] = true;
							dp[i][j] = include;
						}
					}
				}
			}
			for (int[] rows : dp) {
				System.out.println(Arrays.toString(rows));
			}
			System.out.println("-------------------------------------");
			for (boolean[] rows : selected) {
				System.out.println(Arrays.toString(rows));
			}
			// Start from both the corner end.
			int person = B.length;
			int capacity = A;
			while (person != 0 && capacity != 0) {
				// if current person is not marked: go to previous person.
				// update the column by reducing the capacity.
				if (selected[person][capacity]) {
					ans.add(B.length - person);
					capacity = capacity - B[person - 1];
				} else {
					person--;
				}
			}
			return ans.stream().mapToInt(Integer::intValue).toArray();
		}
	}

	public static void main(String[] args) {
		TusharBirthday obj = new TusharBirthday();
		System.out.println(Arrays.toString(obj.new ActualRecursion().solve(14, new int[] { 8, 7, 6, 5, 4 })));
		// System.out.println(Arrays.toString(obj.new Memo().solve(14, new int[] { 8, 7,
		// 6, 5, 4 })));
		System.out.println(Arrays.toString(obj.new DP().solve(14, new int[] { 8, 7, 6, 5, 4 })));
	}
}
