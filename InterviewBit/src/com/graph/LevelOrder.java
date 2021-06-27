package com.graph;

import java.util.ArrayList;

import com.trees.TreeNode;

public class LevelOrder {

	int minimum_height = -1;
	ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

	private void helper(TreeNode node, int level) {
		if (node == null)
			return;

		if (level > minimum_height) {
			ans.add(new ArrayList<>());
			minimum_height = level;
		}

		helper(node.left, level + 1);
		ans.get(level).add(node.val);
		helper(node.right, level + 1);

	}

	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode node) {
		helper(node, 0);
		return ans;
	}

	public static void main(String[] args) {
		LevelOrder obj = new LevelOrder();
		System.out.println(obj.levelOrder(TreeNode.getExample1()));
	}
	
}
