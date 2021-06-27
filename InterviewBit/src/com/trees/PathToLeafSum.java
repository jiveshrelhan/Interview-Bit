package com.trees;

public class PathToLeafSum {

	int currentSum = 0;
	int MOD = 1003;

	private void helper(TreeNode A, int prevVal) {

		if (A == null)
			return;
		int val = (prevVal * 10 + A.val) % MOD;

		if (A.left == null && A.right == null) {
			currentSum += val;
			return;
		}

		helper(A.left, val);
		helper(A.right, val);
	}

	public int sumNumbers(TreeNode A) {
		helper(A, 0);
		return currentSum % MOD;
	}

	public static void main(String[] args) {
		PathToLeafSum obj = new PathToLeafSum();
		System.out.println(obj.sumNumbers(TreeNode.leftSkewTree()));
	}
}
