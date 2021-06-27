package com.trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

	public int val;
	public TreeNode left, right;

	public TreeNode(int x) {
		this.val = x;
		this.left = this.right = null;
	}

	/*
	 * 1 ..2......3... ...4....5 ..6...7
	 */
	public static TreeNode getExample1() {
		TreeNode root = new TreeNode(1);
		TreeNode a = new TreeNode(2);
		TreeNode b = new TreeNode(4);
		TreeNode c = new TreeNode(5);
		TreeNode d = new TreeNode(3);
		TreeNode e = new TreeNode(6);
		TreeNode f = new TreeNode(7);

		root.left = a;
		root.right = d;
		a.left = b;
		a.right = c;
		d.left = e;
		d.right = f;

		return root;
	}

	public static TreeNode getExample2() {
		TreeNode root = new TreeNode(2);
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(4);
		TreeNode c = new TreeNode(5);

		root.left = a;
		root.right = b;
		a.left = c;

		return root;
	}

	public static TreeNode getExample4() {
		TreeNode root = new TreeNode(3);
		TreeNode a = new TreeNode(6);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(1);
		TreeNode d = new TreeNode(7);

		root.left = a;
		root.right = c;
		a.right = b;
		c.right = d;

		return root;
	}

	public static TreeNode leftSkewTree() {
		TreeNode root = new TreeNode(5);
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		root.left = a;
		a.left = b;
		return root;
	}

	public static TreeNode getExample5() {
		TreeNode root = new TreeNode(5);
		TreeNode a = new TreeNode(6);
		TreeNode b = new TreeNode(8);
		TreeNode c = new TreeNode(11);
		TreeNode d = new TreeNode(13);
		TreeNode e = new TreeNode(4);
		TreeNode f = new TreeNode(7);
		TreeNode g = new TreeNode(2);
		TreeNode h = new TreeNode(5);
		TreeNode i = new TreeNode(1);

		root.left = a;
		root.right = b;
		a.left = c;
		c.left = f;
		c.left = g;
		b.left = d;
		b.right = e;
		e.left = h;
		e.right = i;

		return root;
	}

	public static TreeNode getExample3() {
		TreeNode root = new TreeNode(1);
		TreeNode a = new TreeNode(2);
		TreeNode b = new TreeNode(3);
		TreeNode c = new TreeNode(4);
		TreeNode d = new TreeNode(5);

		root.left = a;
		root.right = b;
		b.left = c;
		b.right = d;

		return root;
	}

	public static TreeNode parser(String input) {
		String[] nodes = input.split(" ");
		int noOfNode = Integer.parseInt(nodes[0]);
		if (noOfNode <= 0)
			return null;
		TreeNode head = null;
		head = new TreeNode(Integer.parseInt(nodes[1]));
		Queue<TreeNode> pendingChildNodes = new LinkedList<>();
		pendingChildNodes.add(head);
		for (int i = 2; i < noOfNode - 1;) {
			int leftVal = Integer.parseInt(nodes[i]);
			int rightVal = Integer.parseInt(nodes[i + 1]);
			TreeNode parent = pendingChildNodes.poll();
			TreeNode leftNode = null, rightNode = null;
			if (leftVal != -1) {
				leftNode = new TreeNode(leftVal);
			}

			if (rightVal != -1) {
				rightNode = new TreeNode(rightVal);
			}

			parent.left = leftNode;
			parent.right = rightNode;
			if (leftNode != null)
				pendingChildNodes.add(leftNode);
			if (rightNode != null)
				pendingChildNodes.add(rightNode);
			i += 2;
		}

		return head;
	}

	@Override
	public String toString() {
		return "TreeNode [val=" + val + "]";
	}

}
