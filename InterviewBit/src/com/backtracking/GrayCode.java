package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class GrayCode {

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
		System.out.println(obj.grayCode(10));
	}
}
