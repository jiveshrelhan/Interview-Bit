package com.trees;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class PersonHeights {

	class SegmentTree {

		int[] treeData;
		int[] input;
		final int ROOT = 1;

		// 4*N is sufficient
		SegmentTree(int[] arr) {
			int n = arr.length;
			n = (int) Math.ceil(Math.log(n) / Math.log(2) + 1);
			int size = 1 << n;
			input = arr;
			treeData = new int[size];
			buildTree();
		}

		int build(int l, int r, int i) {
			if (l > r)
				return 0;
			if (l == r) {
				treeData[i] = 1;
				return 1;
			}
			int mid = (l + r) / 2;
			int L = build(l, mid, 2 * i);
			int R = build(mid + 1, r, 2 * i + 1);
			treeData[i] = L + R;
			return treeData[i];
		}

		private void buildTree() {
			// Copy input[] nodes at the leaves of tree.
			System.arraycopy(input, 0, treeData, input.length, input.length);

			// Populate rest of them according to the business needs
			for (int i = input.length - 1; i >= 1; i--) {
				int leftChildIndex = 2 * i;
				int rightChildIndex = 2 * i + 1;
				treeData[i] = treeData[leftChildIndex] + treeData[rightChildIndex];
			}

			System.out.println(Arrays.toString(treeData));
		}

		private int query(int treeIndex, int treeIndex_start, int treeIndex_end, int spaces_requriment) {
			if (treeIndex_start > treeIndex_end)
				return -1;

			if (treeIndex_start == treeIndex_end) {
				return treeIndex_start;
			}

			int goLeft = 2 * treeIndex, goRight = 2 * treeIndex + 1;
			int mid = treeIndex_start + (treeIndex_end - treeIndex_start) / 2;

			int leftSpaces = treeData[goLeft];
			// int rightSpaces = treeData[goRight];

			if (leftSpaces >= spaces_requriment) {
				return query(goLeft, treeIndex_start, mid, spaces_requriment);
			} else {
				return query(goRight, mid + 1, treeIndex_end, spaces_requriment - leftSpaces);
			}

		}

		private void update(int treeIndex, int newValue) {
			treeData[treeIndex] = newValue;
			for (int i = treeIndex / 2; i >= 1; i /= 2) {
				treeData[i] = treeData[2 * i] + treeData[2 * i + 1];
			}
		}

	}

	public int[] order(int[] heights, int[] inFront) {

		// Sort the person acc to heights
		Map<Integer, Integer> treeMap = new TreeMap<>();
		for (int i = 0; i < heights.length; i++) {
			treeMap.put(heights[i], inFront[i]);
		}
		System.out.println(treeMap);
		// 1 Represents free Space
		int[] freeSpaces = new int[heights.length];
		int[] ans = new int[heights.length];
		Arrays.fill(freeSpaces, 1);
		SegmentTree segmentTree = new SegmentTree(freeSpaces);

		// Iterate over heights. Fix person i to inFront's
		treeMap.forEach((height, spaces_required) -> {
			int positionToFixPerson = segmentTree.query(segmentTree.ROOT, 0, heights.length - 1, spaces_required + 1);
			ans[positionToFixPerson] = height;
			segmentTree.update(positionToFixPerson, 0);
		});

		return ans;

	}

	public static void main(String[] args) {
		PersonHeights obj = new PersonHeights();
		int[] ans = obj.order(new int[] { 5, 3, 2, 6, 1, 4 }, new int[] { 0, 1, 2, 0, 3, 2 });
		System.out.println(Arrays.toString(ans));
	}
}
