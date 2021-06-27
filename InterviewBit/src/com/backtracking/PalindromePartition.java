package com.backtracking;

import java.util.ArrayList;

public class PalindromePartition {

	private boolean palindrome(String s) {
		StringBuilder str = new StringBuilder(s);
		str.reverse();
		return str.toString().equals(s);
	}

	private void helper(ArrayList<ArrayList<String>> res, String a, int index, ArrayList<String> currentSet) {
		if (index >= a.length()) {
			res.add(new ArrayList<String>(currentSet));
			return;
		}

		for (int size = 1; size <= a.length(); size++) {
			if (index + size > a.length())
				break;
			String chosed = a.substring(index, index + size);
			if (palindrome(chosed)) {
				currentSet.add(chosed);
				helper(res, a, index + size, currentSet);
				currentSet.remove(currentSet.size() - 1);
			}
		}

	}

	public ArrayList<ArrayList<String>> partition(String a) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		helper(res, a, 0, new ArrayList<String>());
		return res;
	}

	public static void main(String[] args) {
		PalindromePartition obj = new PalindromePartition();
		System.out.println(obj.partition("aaa"));
	}
}
