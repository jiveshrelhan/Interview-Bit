package com.trees;

public class KthSmallest {

	int count = 0;
	int ans = -1;
	
	public int kthsmallest(TreeNode node, int k) {
		
		if (node.left != null) {
			kthsmallest(node.left, k);
		}
		
		count++;
		
		if(count == k) {
			ans = node.val;
		}


		if (node.right != null) {
			kthsmallest(node.right, k);
		}

		return ans;
	}

	public static void main(String[] args) {
		KthSmallest obj = new KthSmallest();
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);

		System.out.println(obj.kthsmallest(root, 2));
	}
}
