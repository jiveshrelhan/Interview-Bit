package com.backtracking;

public class MaximalString {
	
	private int helper(StringBuilder str, int k, Integer max) {
		if (Integer.valueOf(str.toString()) > max) {
			max = Integer.valueOf(str.toString());
		}
		if (k == 0)
			return max;
		
		for (int i = 0; i < str.length() - 1; i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(j) > str.charAt(i)) {
					char c = str.charAt(j);
					str.setCharAt(j, str.charAt(i));
					str.setCharAt(i, c);
					max = helper(str, k - 1, max);
					c = str.charAt(j);
					str.setCharAt(j, str.charAt(i));
					str.setCharAt(i, c);
				}
			}
		}
		return max;
	}

	public String solve(String A, int k) {
		Integer max = Integer.valueOf(A);
		StringBuilder currentString = new StringBuilder(A);
		max = helper(currentString, k, max);
		return String.valueOf(max);
	}
	
	public static void main(String[] args) {
		MaximalString obj = new MaximalString();
		System.out.println(obj.solve("254", 2));
	}
}
