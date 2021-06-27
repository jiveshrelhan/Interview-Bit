package com.backtracking;

import java.util.ArrayList;

public class GenerateParanthesis {

	private void helper(ArrayList<String> s, String str, int n, int open, int close) {
		if (open == n && close == n) {
			s.add(str);
			return;
		}

		// 2 Options str + '(' OR str + ')'

		if (open < n) {
			String x = str + "(";
			helper(s, x, n, open + 1, close);
		}
		if (close < n && open > close) {
			String x = str + ")";
			helper(s, x, n, open, close + 1);
		}

	}

	public ArrayList<String> generateParenthesis(int A) {
		ArrayList<String> res = new ArrayList<>();
		helper(res, "", A, 0, 0);
		return res;

	}

	public static void main(String[] args) {
		GenerateParanthesis obj = new GenerateParanthesis();
		System.out.println(obj.generateParenthesis(3));
	}
}
