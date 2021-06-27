package com.trees;

import java.util.Arrays;

/*
 * https://www.youtube.com/watch?v=2FShdqn-Oz8&t=2078s
 */
public class MaxRangeQueriesUsingSegmentTree {

	SegmentTree tree;

	class SegmentTree {

		int[] treeData;
		int[] input;
		final int ROOT = 1;

		// 2*N is sufficient
		SegmentTree(int[] arr) {
			input = arr;
			treeData = new int[2 * arr.length];
			buildTree();
		}

		private void buildTree() {
			// Copy input[] nodes at the leaves of tree.
			System.arraycopy(input, 0, treeData, input.length, input.length);

			// Populate rest of them according to the business needs
			for (int i = input.length - 1; i >= 1; i--) {
				int leftChildIndex = 2 * i;
				int rightChildIndex = 2 * i + 1;
				treeData[i] = Math.max(treeData[leftChildIndex], treeData[rightChildIndex]);
			}
		}

		private int query(int treeIndex, int treeIndex_start, int treeIndex_end, int query_start, int query_end) {
			// Check if current node's has complete answer.
			if (query_start <= treeIndex_start && query_end >= treeIndex_end) {
				return treeData[treeIndex];
			}

			// If current node range is dis-joint
			if (query_start > treeIndex_end || query_end < treeIndex_start) {
				return 0;
			}
			int goLeft = 2 * treeIndex, goRight = 2 * treeIndex + 1;

			int mid = treeIndex_start + (treeIndex_end - treeIndex_start) / 2;

			return Math.max(query(goLeft, treeIndex_start, mid, query_start, query_end),
					query(goRight, mid + 1, treeIndex_end, query_start, query_end));
		}

		private void update(int treeIndex, int newValue) {
			treeData[treeIndex] = newValue;
			for (int i = treeIndex / 2; i >= 1; i /= 2) {
				treeData[i] = Math.max(treeData[2 * i], treeData[2 * i + 1]);
			}
		}

	}

	public void update(int index,int newValue) {
		index--;
		tree.update(tree.input.length+index, newValue);
	}
	
	public void processInput(int[] arr) {
		tree = new SegmentTree(arr);
		System.out.println(Arrays.toString(tree.treeData));
	}

	public int getMax(int L, int R) {
		L--;
		R--;
		int n = tree.input.length;
		return tree.query(tree.ROOT, 0, n - 1, L, R);
	}

	public static void main(String[] args) {
		MaxRangeQueriesUsingSegmentTree obj = new MaxRangeQueriesUsingSegmentTree();
		obj.processInput(new int[] { 1, 2, 3, 4 });
		System.out.println(obj.getMax(1, 3));
	}

}
