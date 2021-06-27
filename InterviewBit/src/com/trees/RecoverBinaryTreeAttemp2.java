package com.trees;

import java.util.ArrayList;
import java.util.Collections;

public class RecoverBinaryTreeAttemp2 {
	ArrayList<Integer> ans = new ArrayList<Integer>();

	TreeNode prev = null;

	private void helper(TreeNode n) {

		if (n == null)
			return;

		helper(n.left);

		if (prev != null && prev.val > n.val) {
			if (ans.size() == 0) {
				ans.add(prev.val);
				ans.add(n.val);
			} else {
				ans.set(1, n.val);
			}
		}

		prev = n;
		helper(n.right);
	}

	public ArrayList<Integer> recoverTree(TreeNode A) {
		helper(A);
		Collections.sort(ans, Collections.reverseOrder());
		return ans;
	}

	public static void main(String[] args) {
		RecoverBinaryTreeAttemp2 obj = new RecoverBinaryTreeAttemp2();
		TreeNode roo2 = new TreeNode(1);
		roo2.left = new TreeNode(2);
		TreeNode root = TreeNode.getExample2();
		System.out.println(obj.recoverTree(root));
	}
}
