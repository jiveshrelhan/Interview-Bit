package com.trees;

import java.util.Arrays;
import java.util.Stack;

public class CartesianTree {

	private TreeNode[] constructTree(int[] parent, int[] left, int[] right, int[] input) {
		TreeNode[] trees = new TreeNode[parent.length];
		for (int i = 0; i < input.length; i++) {
			TreeNode newNode = new TreeNode(input[i]);
			trees[i] = newNode;
		}
		for (int i = 0; i < input.length; i++) {
			if (left[i] != -1) {
				int parentToLeft = parent[left[i]];
				trees[parentToLeft].left = trees[left[i]];
			}
			if (right[i] != -1) {
				int parentToRight = parent[right[i]];
				trees[parentToRight].right = trees[right[i]];
			}
		}
		return trees;
	}

	public TreeNode buildTree(int[] arr) {
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		int head = 0;
		int[] parent = new int[arr.length];
		int[] leftPointer = new int[arr.length];
		int[] rightPointer = new int[arr.length];
		Arrays.fill(parent, -1);
		Arrays.fill(leftPointer, -1);
		Arrays.fill(rightPointer, -1);
		for (int i = 1; i < arr.length; i++) {
			int valueToInsert = arr[i];

			/*
			 * best case : when new element is smaller than chain of right pointers in
			 * stack. No changes in pointers is required.
			 */
			if (valueToInsert < arr[stack.peek()]) {
				rightPointer[stack.peek()] = i;
				parent[i] = stack.peek();
			}
			/*
			 * Adjustment is required. whole previously added tree must be at left.
			 * 
			 * If this is not fresh root of tree then make right pointer to newNode, else
			 * update the head pointer.
			 */
			else {
				int indexWhereAdjustmentRequired = -1;
				while (!stack.isEmpty() && valueToInsert > arr[stack.peek()]) {
					indexWhereAdjustmentRequired = stack.pop();
				}
				leftPointer[i] = indexWhereAdjustmentRequired;
				parent[indexWhereAdjustmentRequired] = i;

				if (!stack.isEmpty()) {
					rightPointer[stack.peek()] = i;
					parent[i] = stack.peek();
				} else {
					head = i;
				}
			}
			stack.push(i);
		}
		TreeNode[] trees = constructTree(parent, leftPointer, rightPointer, arr);
		return trees[head];
	}

	public static void main(String[] args) {
		CartesianTree obj = new CartesianTree();
		System.out.println(obj.buildTree(new int[] { 3, 1, 2,4 }));
	}

}
