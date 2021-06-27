package com.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CousinsReAttempt {

	HashMap<Integer, List<TreeNode>> levelMap = new HashMap<>();
	int levelOfB = -1;
	TreeNode parentOfB = null;

	private void helper(TreeNode a, int B, int level, TreeNode parent) {
		if (a == null)
			return;

		int newLevel = level + 1;
		List<TreeNode> list = levelMap.getOrDefault(newLevel, new ArrayList<>());
		list.add(a);
		if (a.val == B) {
			list.remove(list.size() - 1);
			levelOfB = newLevel;
			parentOfB = parent;
		}

		levelMap.put(newLevel, list);

		helper(a.left, B, newLevel, a);
		helper(a.right, B, newLevel, a);

	}

	public ArrayList<Integer> solve(TreeNode A, int B) {
		ArrayList<Integer> ans = new ArrayList<>();
		helper(A, B, -1, null);
		if (levelOfB != -1) {
			List<TreeNode> list = levelMap.get(levelOfB);
			for (TreeNode n : list) {
				if ((parentOfB.left != null && parentOfB.left.val == n.val)
						|| (parentOfB.right != null && parentOfB.right.val == n.val)) {
					continue;
				}
				ans.add(n.val);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		CousinsReAttempt obj = new CousinsReAttempt();
		System.out.println(obj.solve(TreeNode.getExample1(), 1));
	}
}
