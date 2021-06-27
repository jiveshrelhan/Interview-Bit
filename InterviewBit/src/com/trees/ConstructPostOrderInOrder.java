package com.trees;

public class ConstructPostOrderInOrder {
	int postOrderIndex = 0;
	private int findIndex(int[] postOrder, int[] inOrder, int start, int end) {
		int value = postOrder[postOrderIndex];
		while (start <= end) {
			if (inOrder[start] == value) {
				break;
			}
			start++;
		}
		return start;
	}

	private TreeNode driver(int[] postOrder, int[] inOrder, int start, int end) {
		if (start > end) {
			return null;
		}

		int splitFromHere = findIndex(postOrder, inOrder, start, end);
		TreeNode root = new TreeNode(postOrder[postOrderIndex--]);
		TreeNode rightNode = driver(postOrder, inOrder, splitFromHere + 1, end);
		TreeNode leftNode = driver(postOrder, inOrder, start, splitFromHere - 1);
		root.left = leftNode;
		root.right = rightNode;
		return root;
	}

	public TreeNode buildTree(int[] inOrder, int[] postOrder) {
		postOrderIndex = inOrder.length-1;
		return driver(postOrder, inOrder, 0, inOrder.length - 1);
	}

	public static void main(String[] args) {
		ConstructPostOrderInOrder obj = new ConstructPostOrderInOrder();
		TreeNode root = obj.buildTree(new int[] { 2,1,3 }, new int[] { 2,3,1 });
		InOrderTraversal obj1 = new InOrderTraversal();
		System.out.println(obj1.inorderTraversal(root));
		System.out.println(new PostOrderTraversal().postorderTraversal((root)));
	}
}
