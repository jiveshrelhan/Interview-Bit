package com.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReverseLevelOrder {
	private Stack<List<TreeNode>> levelOrder(TreeNode root) {
		List<TreeNode> currLevel = new ArrayList<>();
		List<TreeNode> nextLevel = new ArrayList<>();
		List<TreeNode> temp = new ArrayList<>();
		Stack<List<TreeNode>> allLevels = new Stack<>();
		currLevel.add(root);
		while (!currLevel.isEmpty()) {
			while (!currLevel.isEmpty()) {
				TreeNode poll = currLevel.remove(0);
				temp.add(poll);

				if (poll.left != null) {
					nextLevel.add(poll.left);
				}
				if (poll.right != null) {
					nextLevel.add(poll.right);
				}

			}
			allLevels.add(temp);
			temp = new ArrayList<>();
			currLevel = nextLevel;
			nextLevel = new ArrayList<>();
		}

		return allLevels;
	}

	public ArrayList<Integer> solve(TreeNode A) {
		ArrayList<Integer> ans = new ArrayList<>();
		Stack<List<TreeNode>> levels = levelOrder(A);
		while (!levels.isEmpty()) {
			List<TreeNode> level = levels.pop();
			for (TreeNode n : level) {
				ans.add(n.val);
			}
		}
		ans.add(A.val);
		return ans;
	}

	public static void main(String[] args) {
		ReverseLevelOrder obj = new ReverseLevelOrder();
		System.out.println(obj.solve(TreeNode.getExample1()));
	}
}
