package com.trees;

public class InvertTree {
	public TreeNode invertTree(TreeNode A) {

		if (A == null)
			return A;

		if (A.left != null) {
			invertTree(A.left);
		}

		if (A.right != null) {
			invertTree(A.right);
		}

		TreeNode temp = A.right;
		A.right = A.left;
		A.left = temp;

		return A;

	}

	public static void main(String[] args) {
		InvertTree obj = new InvertTree();
		TreeNode root = obj.invertTree(TreeNode.getExample1());
		System.out.println(root);
	}
}
