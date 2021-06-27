package com.trees;

import java.util.ArrayList;
import java.util.LinkedList;

public class ZigZag {
	private ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		LinkedList<TreeNode> currLevel = new LinkedList<>();
		LinkedList<TreeNode> nextLevel = new LinkedList<>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		currLevel.add(root);
		while (!currLevel.isEmpty()) {
			while (!currLevel.isEmpty()) {

				TreeNode poll = currLevel.pollLast();
				temp.add(poll.val);
				if (ans.size() % 2 == 0) {
					if (poll.left != null) {
						nextLevel.add(poll.left);
					}
					if (poll.right != null) {
						nextLevel.add(poll.right);
					}
				} else {
					if (poll.right != null) {
						nextLevel.add(poll.right);
					}
					if (poll.left != null) {
						nextLevel.add(poll.left);
					}
				}

			}
			ans.add(temp);
			currLevel = nextLevel;
			nextLevel = new LinkedList<>();
			temp=new ArrayList<>();
		}

		return ans;
	}

	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {

		return levelOrder(A);
	}

	public static void main(String[] args) {
		ZigZag obj = new ZigZag();
		System.out.println(obj.zigzagLevelOrder(TreeNode.getExample1()));
	}
}
