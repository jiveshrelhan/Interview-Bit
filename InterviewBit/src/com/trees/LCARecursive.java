package com.trees;

public class LCARecursive {

	TreeNode ans = null;

	private TreeNode helper(TreeNode n, int A, int B) {
		if (n == null)
			return null;


		TreeNode leftAns = helper(n.left, A, B);
		TreeNode rightAns = helper(n.right, A, B);

		//if(n.val)
		
		if ((leftAns == null || rightAns == null) && (n.val == A)) {
			if (leftAns == null) {
				leftAns = n;
			} else {
				rightAns = n;
			}
		}

		if (leftAns != null && rightAns != null) {
			ans = n;
			return n;
		}
		if (leftAns == null) {
			return rightAns;
		}
		return leftAns;

	}

	public int lca(TreeNode A, int B, int C) {
		helper(A, B, C);
		return ans == null ? -1 : ans.val;
	}

	public static void main(String[] args) {
		LCARecursive obj = new LCARecursive();
		System.out.println(obj.lca(TreeNode.getExample3(), 3, 5));
	}
}
