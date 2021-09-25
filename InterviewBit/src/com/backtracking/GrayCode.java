package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class GrayCode {
	class AnotherBasicApproach {
		ArrayList<Integer> ans = new ArrayList<Integer>();

		private void helper(int n, String x) {
			if (x.length() > n) {
				return;
			}
			if (x.length() == n) {
				ans.add(Integer.valueOf(x, 2));
				return;
			}

			char lastChar = x.charAt(x.length() - 1);
			if (lastChar == '0')
				helper(n, x + "1");
			else
				helper(n, x + "0");
		}

		public ArrayList<Integer> grayCode(int a) {
			helper(a, "0");
			helper(a,"1");
			return ans;
		}
	}

	private ArrayList<String> generateAllCode(int n) {
		if (n == 0) {
			return new ArrayList<>(Arrays.asList("0"));
		} else if (n == 1) {
			return new ArrayList<>(Arrays.asList("0", "1"));
		}

		ArrayList<String> forOthers = generateAllCode(n - 1);
		ArrayList<String> nextList = new ArrayList<>();
		for (String x : forOthers) {
			nextList.add("0" + x);
			nextList.add("1" + x);
		}
		return nextList;
	}

	public ArrayList<Integer> grayCode(int a) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ArrayList<String> s = generateAllCode(a);
		for (String x : s) {
			ans.add(Integer.valueOf(x, 2));
		}
		return ans;
	}

	public static void main(String[] args) {
		GrayCode obj = new GrayCode();
		System.out.println(obj.grayCode(2));
		System.out.println(obj.new AnotherBasicApproach().grayCode(2));
	}
}
