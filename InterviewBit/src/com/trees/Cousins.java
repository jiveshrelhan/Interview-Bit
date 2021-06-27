package com.trees;

import java.util.ArrayList;

public class Cousins {
	TreeNode parentOfB = null;
	int heightOfB = -1;
	ArrayList<Integer> ans = new ArrayList<>();

	private void helper(TreeNode a, int B, int previousHeight, TreeNode parent) {
		if (a == null)
			return;

		int newHeight = previousHeight + 1;
		if (a.val == B) {
			heightOfB = newHeight;
			parentOfB = parent;
		}

		if (newHeight == heightOfB && parent != parentOfB) {
			ans.add(a.val);
		}

		helper(a.left, B, newHeight, a);
		helper(a.right, B, newHeight, a);

	}

	public ArrayList<Integer> solve(TreeNode A, int B) {
		helper(A, B, 0, null);
		return ans;
	}
	
	public static void main(String[] args) {
		Cousins obj = new Cousins();
		System.out.println(obj.solve(TreeNode.getExample3(), 3));
	}
}
