package com.trees;

public class RemoveHalfNodes {

	private TreeNode preOrder(TreeNode n) {
		/*
		 * If leaf node ignore.
		 */
		if (n.left == null && n.right == null) {
			return n;
		}

		/*
		 * If single child, update value their left child and right child pointers.
		 */
		TreeNode singleChild = null;
		if (n.left != null && n.right == null) {
			singleChild = n.left;
		} else if (n.right != null && n.left == null) {
			singleChild = n.right;
		}

		if (singleChild != null) {
			n.val = singleChild.val;
			n.left = singleChild.left;
			n.right = singleChild.right;
			/*
			 * Important to recall on the same node. It could be possible the node we
			 * Propagate above is also faulty.
			 * Example 3 TreeNode
			 */
			preOrder(n);
		}
		if (n.left != null)
			preOrder(n.left);
		if (n.right != null)
			preOrder(n.right);
		return n;
	}

	public TreeNode solve(TreeNode A) {
		return preOrder(A);
	}

	public static void main(String[] args) {
		RemoveHalfNodes obj = new RemoveHalfNodes();
		TreeNode root = TreeNode.getExample3();
		System.out.println(obj.solve(root).val);
	}
}
