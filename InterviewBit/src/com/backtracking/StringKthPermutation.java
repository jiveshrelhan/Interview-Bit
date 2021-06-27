package com.backtracking;

public class StringKthPermutation {

	private String answer = "";
	private int k = 0;

	private void helper(StringBuilder selected, StringBuilder available, int n) {

		if (k == 0)
			return;

		if (k == 1 && selected.length() == n) {
			answer = selected.toString();
		}
		if (selected.length() == n) {
			k--;
			return;
		}

		for (int i = 0; i < available.length(); i++) {
			char c = available.charAt(i);
			selected.append(c);
			available.deleteCharAt(i);
			helper(selected, available, n);
			selected.deleteCharAt(selected.length() - 1);
			available.insert(i, c);
		}

	}

	public String getPermutation(int n, int k) {
		if (n > 12)
			return String.valueOf(Integer.MAX_VALUE);
		StringBuilder str = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			str.append(i);
		}
		this.k = k;
		helper(new StringBuilder(), str, n);
		return answer;
	}

	public static void main(String[] args) {
		StringKthPermutation obj = new StringKthPermutation();
		System.out.println(obj.getPermutation(3, 4));
	}

}
