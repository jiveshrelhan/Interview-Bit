package com.trees;

public class SymmetricBinaryTree {

	private boolean helper(TreeNode left, TreeNode right) {

		if (left == null && right == null)
			return true;

		if ((left == null || right == null))
			return false;

		return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);

	}

	public int isSymmetric(TreeNode A) {
		return helper(A.left, A.right) ? 1 : 0;
	}

	public static void main(String[] args) {
		SymmetricBinaryTree obj = new SymmetricBinaryTree();
		System.out.println(obj.isSymmetric(TreeNode.leftSkewTree()));
	}
}
