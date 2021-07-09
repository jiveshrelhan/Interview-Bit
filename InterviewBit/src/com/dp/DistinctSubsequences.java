package com.dp;

import java.util.Arrays;

public class DistinctSubsequences {
//	class Memozied {
//		int[][] memo;
//
//		private int recursionHelper(String A, String B, int i, int j, int k) {
//			/*
//			 * Base Case : Smallest valid case. length 0 or empty string is smallest valid
//			 * case. In this case index will be -1. therefore if either A or B goes
//			 * exhausted return 0 as LCS
//			 */
//			if (i < 0 || j < 0) {
//				return 0;
//			}
//			if (memo[i][j] != -1) {
//				return memo[i][j];
//			}
//			/*
//			 * Case 1: When characters matches : then only one path to follow.
//			 */
//			/*
//			 * Case 2: Else choose the best from both hit and trials
//			 */
//			if (A.charAt(i) == B.charAt(j)) {
//				memo[i][j] = 1 + recursionHelper(A, B, i - 1, j - 1);
//			}
//			else{
//				memo[i][j] = Math.max(recursionHelper(A, B, i - 1, j), recursionHelper(A, B, i, j - 1));
//			}
//			int case2 = recursionHelper(A, B, i - 1, j);
//			int case3 = recursionHelper(A, B, i, j-1);
//			if(memo[i][j] == 6 || case2 == 6 || case3 == 6) {
//				System.out.println("Hi");
//			}
//			return memo[i][j];
//
//		}
//
//		public int solve(String A, String B) {
//			if (A.isEmpty() || B.isEmpty()) {
//				return 0;
//			}
//			/*
//			 * Always needs to fill default value as non-valid answer of any input. since 0
//			 * can be valid answer of any 2 strings therefore filled []s with -1
//			 */
//			memo = new int[A.length()][B.length()];
//			for (int[] x : memo) {
//				Arrays.fill(x, -1);
//			}
//			int ans = recursionHelper(A, B, A.length() - 1, B.length() - 1);
//			for (int[] x : memo) {
//				System.out.println(Arrays.toString(x));
//			}
//			return ans;
//		}
//	}
	static class DP{
		public int count = 0;
		public void solve(String A, String B, int i, int j, int k, int target) {

			if(i<0 || j<0) {
				if(k==target)
					count++;
				return;
			}
			if(A.charAt(i) == B.charAt(j)) {
				solve(A,B, i-1, j-1, k+1, target);
			}
			solve(A,B,i-1, j, k, target);
			solve(A,B,i,j-1,k,target);
				
		}
	}
	public static void main(String[] args) {
//		DistinctSubsequences obj = new DistinctSubsequences();
//		System.out.println(obj.new Memozied().solve("rabbbit", "rabbit"));
		String A = "rabbit";
		String B = "rabbbitit";
		int target = (A.length()<B.length())?A.length():B.length();
		DP obj = new DP();
		obj.solve(A, B, A.length()-1, B.length()-1, 0, target);
		System.out.println(obj.count);
	}
}
