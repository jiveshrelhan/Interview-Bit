package com.trees;

public class ConstructFromPreOrderInOrder {
	int preOrderIndex = 0;

	private int findIndex(int[] preOrder, int[] inOrder, int start, int end) {
		int value = preOrder[preOrderIndex++];
		while (start <= end) {
			if (inOrder[start] == value) {
				break;
			}
			start++;
		}
		return start;
	}

	private TreeNode driver(int[] preOrder, int[] inOrder, int start, int end) {
		if (start > end) {
			return null;
		}
		int splitFromHere = findIndex(preOrder, inOrder, start, end);
		TreeNode root = new TreeNode(inOrder[splitFromHere]);
		TreeNode leftNode = driver(preOrder, inOrder, start, splitFromHere - 1);
		TreeNode rightNode = driver(preOrder, inOrder, splitFromHere + 1, end);
		root.left = leftNode;
		root.right = rightNode;
		return root;
	}

	public TreeNode buildTree(int[] preOrder, int[] inOrder) {
		return driver(preOrder, inOrder, 0, inOrder.length - 1);
	}

	public static void main(String[] args) {
		ConstructFromPreOrderInOrder obj = new ConstructFromPreOrderInOrder();
		TreeNode root = obj.buildTree(new int[] { 1, 2, 4, 5, 3, 6, 7 }, new int[] { 4, 2, 5, 1, 6, 3, 7 });
		InOrderTraversal obj1 = new InOrderTraversal();
		System.out.println(obj1.inorderTraversal(root));
		System.out.println(new PreOrderTraversal().preorderTraversal(root));
	}
}
