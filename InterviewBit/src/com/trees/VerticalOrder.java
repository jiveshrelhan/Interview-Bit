package com.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class VerticalOrder {

	class Pair {
		TreeNode node;
		int verticalLevel;

		Pair(TreeNode n, int x) {
			this.node = n;
			this.verticalLevel = x;
		}
	}

	/**
	 * Need to maintain horizontal Axis and Vertical Axis.
	 */
	TreeMap<Integer, List<Integer>> verticalAxisMap = new TreeMap<>();

	/*
	 * private void helper(TreeNode n, int horizontalLevel) { if (n == null) return;
	 * 
	 * List<Integer> verticalAxis = verticalAxisMap.getOrDefault(horizontalLevel,
	 * new ArrayList<>()); verticalAxis.add(n.val);
	 * verticalAxisMap.put(horizontalLevel, verticalAxis); helper(n.left,
	 * horizontalLevel - 1); helper(n.right, horizontalLevel + 1); }
	 * 
	 * public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
	 * helper(A, 0); ArrayList<ArrayList<Integer>> ans = new
	 * ArrayList<ArrayList<Integer>>(); verticalAxisMap.forEach((k, v) -> {
	 * ans.add(new ArrayList<Integer>(v)); }); return ans; }
	 */
	public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		if (A == null)
			return ans;
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(A, 0));

		while (!queue.isEmpty()) {
			Pair parent = queue.poll();
			List<Integer> list = verticalAxisMap.getOrDefault(parent.verticalLevel, new ArrayList<>());
			list.add(parent.node.val);
			verticalAxisMap.put(parent.verticalLevel, list);
			if (parent.node.left != null) {
				queue.add(new Pair(parent.node.left, parent.verticalLevel - 1));
			}
			if (parent.node.right != null) {
				queue.add(new Pair(parent.node.right, parent.verticalLevel + 1));
			}
		}

		verticalAxisMap.forEach((k, v) -> {
			ans.add(new ArrayList<Integer>(v));
		});
		return ans;

	}

	public static void main(String[] args) {
		VerticalOrder obj = new VerticalOrder();
		System.out.println(obj.verticalOrderTraversal(TreeNode.parser(
				"43 460 3871 4698 8399 504 4421 7515 -1 4167 5727 -1 -1 3096 434 7389 2667 5661 1969 7815 4292 3006 9750 6693 -1 6906 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 ")));
	}
}
