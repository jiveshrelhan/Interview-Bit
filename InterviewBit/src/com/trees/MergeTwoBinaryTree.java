package com.trees;

public class MergeTwoBinaryTree {

	private TreeNode merge(TreeNode A, TreeNode B) {

		if (A == null && B == null) {
			return null;
		}

		if (A == null) {
			return B;
		}

		if (B == null) {
			return A;
		}

		if (A != null && B != null) {
			A.val += B.val;
		}

		A.left = merge(A.left, B.left);
		A.right = merge(A.right, B.right);

		return A;
	}

	public TreeNode solve(TreeNode A, TreeNode B) {
		return merge(A, B);
	}

	public static void main(String[] args) {
		MergeTwoBinaryTree obj = new MergeTwoBinaryTree();
		System.out.println(obj.merge(TreeNode.getExample2(), TreeNode.getExample4()));
	}
}
