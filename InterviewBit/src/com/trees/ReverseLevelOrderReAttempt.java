package com.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseLevelOrderReAttempt {
	private ArrayList<Integer> levelOrder(TreeNode root) {
		ArrayList<Integer> ans = new ArrayList<>();
		List<TreeNode> currLevel = new ArrayList<>();
		currLevel.add(root);
		while (!currLevel.isEmpty()) {
			TreeNode poll = currLevel.remove(0);
			ans.add(poll.val);

			if (poll.right != null) {
				currLevel.add(poll.right);
			}
			if (poll.left != null) {
				currLevel.add(poll.left);
			}

		}
		Collections.reverse(ans);
		return ans;
	}

	public ArrayList<Integer> solve(TreeNode A) {
		return levelOrder(A);
	}

	public static void main(String[] args) {
		ReverseLevelOrderReAttempt obj = new ReverseLevelOrderReAttempt();
		System.out.println(obj.solve(TreeNode.getExample1()));
	}
}
