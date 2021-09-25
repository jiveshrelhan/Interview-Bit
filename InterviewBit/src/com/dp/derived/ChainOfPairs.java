package com.dp.derived;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Same as Longest Increasing Subsequence : 1 Liner change.
 */
public class ChainOfPairs {
	
	public int solve(ArrayList<ArrayList<Integer>> A) {
		if(A.isEmpty()) {
			return 0;
		}
		int dp[] = new int[A.size()];
		Arrays.fill(dp, 1);

		for (int current = 1; current < A.size(); current++) {
			for (int previous = 0; previous < current; previous++) {
				if (A.get(current).get(0) > A.get(previous).get(1)) {
					dp[current] = Math.max(dp[current], 1 + dp[previous]);
				}
			}
		}

		int max = 1;
		for (int i = 0; i < dp.length; i++) {
			max = Math.max(max, dp[i]);
		}
		return max;

	}
	
	public static void main(String[] args) {
		ChainOfPairs obj = new ChainOfPairs();
		ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();
		A.add(new ArrayList<>(Arrays.asList(5,24)));
		A.add(new ArrayList<>(Arrays.asList(39,60)));
		A.add(new ArrayList<>(Arrays.asList(15,28)));
		A.add(new ArrayList<>(Arrays.asList(27,40)));
		A.add(new ArrayList<>(Arrays.asList(50,90)));
		System.out.println(obj.solve(A));
	}
}
