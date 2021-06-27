package com.trees;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class PersonHeightsAuthor {

	class SegmentTree {
		int n;
		int[] tree;

		SegmentTree(int nn) {
			n = nn;
			tree = new int[16];
			build();
		}

		void build() {
			// Copy input[] nodes at the leaves of tree.
			Arrays.fill(tree, n - 1, n + n, 1);

			// Populate rest of them according to the business needs
			for (int i = n - 1; i >= 1; i--) {
				tree[i] = tree[left(i)] + tree[right(i)];
			}

		}

		void update(int index, int newValue) {
			tree[index] = newValue;
			for (int i = index / 2; i >= 1; i /= 2) {
				tree[i] = tree[2 * i] + tree[2 * i + 1];
			}
		}

		int query(int k) {
			int i = 0, s = n >> 1;
			for (int p = 2; p < 2 * n; p <<= 1, s >>= 1) {
				if (k < tree[p])
					continue;
				k -= tree[p++];
				i += s;
			}
			return i;
		}

		int left(int i) {
			return 2 * i;
		}

		int right(int i) {
			return 2 * i + 1;
		}
	}

	public int[] order(int[] heights, int[] inFront) {
		int n = heights.length;
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
		SegmentTree segmentTree = new SegmentTree(n);
		System.out.println(Arrays.toString(segmentTree.tree));
		// Iterate over heights. Fix person i to inFront's
		treeMap.forEach((height, spaces_required) -> {
			int index = segmentTree.query(spaces_required + 1);
			System.out.println("Query index : " + index + " for space " + (spaces_required + 1));
			ans[index] = height;
			segmentTree.update(index, 0);
			System.out.println(Arrays.toString(segmentTree.tree));
		});

		return ans;

	}

	public static void main(String[] args) {
		PersonHeightsAuthor obj = new PersonHeightsAuthor();
		int[] ans = obj.order(new int[] { 5, 3, 2, 6, 1, 4 }, new int[] { 0, 1, 2, 0, 3, 2 });
		System.out.println(Arrays.toString(ans));
	}
}
