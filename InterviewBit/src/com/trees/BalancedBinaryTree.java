package com.trees;

public class BalancedBinaryTree {

	boolean isBalanced = true;

	private int postOrder(TreeNode n) {
		if (n == null)
			return 0;

		if (!isBalanced)
			return 0;

		int leftHeight = postOrder(n.left);
		int rightHeight = postOrder(n.right);

		int diff = Math.abs(leftHeight-rightHeight);
		if (diff > 2) {
			isBalanced = false;
		}

		int maxHeight = Math.max(leftHeight, rightHeight) + 1;

		return maxHeight;
	}

	public int isBalanced(TreeNode A) {
		postOrder(A);
		return isBalanced ? 1 : 0;
	}
}
