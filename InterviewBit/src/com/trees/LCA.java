package com.trees;

import java.util.LinkedList;

public class LCA {

	private boolean populateRoots(TreeNode n, int target, LinkedList<TreeNode> queue) {

		if (n == null)
			return false;
		
		queue.add(n);
		
		if (n.val == target) {
			return true;
		}

		if (n.left != null && populateRoots(n.left, target, queue)) {
			return true;
		}

		if (n.right != null && populateRoots(n.right, target, queue)) {
			return true;
		}

		queue.removeLast();

		return false;

	}

	private Integer compareRoots(LinkedList<TreeNode> queue1, LinkedList<TreeNode> queue2) {
		Integer possibleAns = null;
		while (!queue1.isEmpty() && !queue2.isEmpty() && queue1.peekFirst() == queue2.peekFirst()) {
			possibleAns = queue1.peekFirst().val;
			queue1.pollFirst();
			queue2.pollFirst();
		}
		return possibleAns;
	}

	public int lca(TreeNode A, int B, int C) {
		LinkedList<TreeNode> queue1 = new LinkedList<>();
		LinkedList<TreeNode> queue2 = new LinkedList<>();
		populateRoots(A, B, queue1);
		populateRoots(A, C, queue2);
		Integer possible = compareRoots(queue1, queue2);
		return possible != null ? possible.intValue() : -1;

	}

	public static void main(String[] args) {
		LCA obj = new LCA();

		System.out.println(obj.lca(TreeNode.getExample5(), 17, 22));
	}
}
