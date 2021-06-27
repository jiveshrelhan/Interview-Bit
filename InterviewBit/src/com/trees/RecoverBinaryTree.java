package com.trees;

import java.util.ArrayList;

public class RecoverBinaryTree {
	ArrayList<Integer> ans = new ArrayList<Integer>();

	private int[] helper(TreeNode n) {

		if (n == null) {
			return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE };
		}

		int[] leftSubtree = helper(n.left);
		int[] rightSubtree = helper(n.right);

		if (n.val < leftSubtree[1]) {
			ans.add(n.val);
			ans.add(leftSubtree[1]);
		} else if (n.val > rightSubtree[0]) {
			ans.add(rightSubtree[0]);
			ans.add(n.val);
		}

		int max = Math.max(n.val, Math.max(leftSubtree[1], rightSubtree[1]));
		int min = Math.min(n.val, Math.min(leftSubtree[0], rightSubtree[0]));

		return new int[] { min, max };
	}

	public ArrayList<Integer> recoverTree(TreeNode A) {
		helper(A);
		return ans.size()> 2 ? new ArrayList<>(ans.subList(ans.size()-2, ans.size())) : ans;
	}

	public static void main(String[] args) {
		RecoverBinaryTree obj = new RecoverBinaryTree();
		TreeNode roo2 = new TreeNode(1);
		roo2.left = new TreeNode(2);
		//TreeNode root = TreeNode.getExample2();
		System.out.println(obj.recoverTree(roo2));
	}
}
