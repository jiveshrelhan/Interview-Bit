package com.trees;

public class PopulateNextPointer {

	class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int val) {
			this.val = val;
		}
	}

	private int getNoOfChildren(TreeLinkNode node) {
		int children = 0;
		if (node == null) {
			return children;
		}
		if (node.left != null) {
			children++;
		}
		if (node.right != null) {
			children++;
		}
		return children;
	}

	private void populateNextPointer(TreeLinkNode node) {
		if (node == null || (getNoOfChildren(node) == 0)) {
			return;
		}

		if (getNoOfChildren(node) == 2) {
			node.left.next = node.right;
			if (node.right != null && node.next != null) {
				node.right.next = getNextAvailableNode(node.next);
			}
		} else if (getNoOfChildren(node) == 1 && node.next != null) {
			if (node.left != null) {
				node.left.next = getNextAvailableNode(node.next);
			} else {
				node.right.next = getNextAvailableNode(node.next);
			}
		}

		populateNextPointer(node.right);
		populateNextPointer(node.left);
	}

	private TreeLinkNode getNextAvailableNode(TreeLinkNode next) {
		TreeLinkNode nextAvailableNode = next;

		while (next != null && getNoOfChildren(next) == 0) {
			next = next.next;
		}

		if (nextAvailableNode != null) {
			if (nextAvailableNode.left != null) {
				return nextAvailableNode.left;
			}
			return nextAvailableNode.right;
		}

		// Possibly null here
		return nextAvailableNode;
	}

	public void connect(TreeLinkNode root) {
		populateNextPointer(root);
	}
}
