package com.backtracking;

public class KthPermutationBrute {

	private String answer = "";
	private int k = 0;
	private String digits = "";

	private void helper(StringBuilder selected, int[] available, int n) {

		if (k == 0)
			return;

		if (k == 1 && selected.length() == n) {
			answer = selected.toString();
		}
		if (selected.length() == n) {
			k--;
			return;
		}

		for (int i = 0; i < available.length; i++) {
			int number = available[i];
			if (number < 0)
				continue;
			selected.append(number);
			available[i] *= -1;
			helper(selected, available, n);
			if (number > 9) {
				selected.delete(selected.length() - 2, selected.length());
			} else {
				selected.delete(selected.length() - 1, selected.length());
			}
			available[i] *= -1;
		}

	}

	public String getPermutation(int n, int k) {
		if (n > 12)
			return String.valueOf(Integer.MAX_VALUE);
		int[] nums = new int[n];
		for (int i = 1; i <= n; i++) {
			nums[i - 1] = i;
			digits += i;
		}

		this.k = k;
		helper(new StringBuilder(), nums, this.digits.length());
		return answer;
	}

	public static void main(String[] args) {
		KthPermutationBrute obj = new KthPermutationBrute();
		System.out.println(obj.getPermutation(2, 2));
	}

}
