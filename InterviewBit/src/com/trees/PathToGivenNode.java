package com.trees;

import java.util.ArrayList;

public class PathToGivenNode {
	ArrayList<Integer> ans = new ArrayList<>();
	boolean found = false;

	private void helper(TreeNode n, int B) {

		if (found) {
			return;
		}

		if (n == null) {
			ans.add(-1);
			return;
		}

		ans.add(n.val);

		if (n.val == B) {
			found = true;
			return;
		}

		helper(n.left, B);

		if (!found)
			ans.remove(ans.size() - 1);

		helper(n.right, B);

		if (!found)
			ans.remove(ans.size() - 1);

	}

	public ArrayList<Integer> solve(TreeNode A, int B) {
		helper(A, B);
		return ans;
	}

	public static void main(String[] args) {
		PathToGivenNode obj = new PathToGivenNode();
		System.out.println(obj.solve(TreeNode.getExample1(), 1));
	}
}
