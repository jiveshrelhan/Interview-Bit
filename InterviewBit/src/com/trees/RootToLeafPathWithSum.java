package com.trees;

import java.util.ArrayList;

public class RootToLeafPathWithSum {
	ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

	public void helper(TreeNode A, int previousSum, ArrayList<Integer> currentPath, int B) {
		if (A == null)
			return;

		int val = previousSum + A.val;
		currentPath.add(A.val);
		if (A.left == null && A.right == null && val == B) {
			ans.add(new ArrayList<>(currentPath));
		}
		
		if (A.left != null) {
			helper(A.left, val, currentPath, B);
		}
		if (A.right != null) {
			helper(A.right, val, currentPath, B);
		}
		currentPath.remove(currentPath.size() - 1);

	}

	public ArrayList<ArrayList<Integer>> pathSum(TreeNode A, int B) {
		helper(A, 0, new ArrayList<Integer>(), B);
		return ans;
	}

	public static void main(String[] args) {
		RootToLeafPathWithSum obj = new RootToLeafPathWithSum();
		obj.pathSum(TreeNode.getExample5(), 22);
		System.out.println(obj.ans);
	}
}
