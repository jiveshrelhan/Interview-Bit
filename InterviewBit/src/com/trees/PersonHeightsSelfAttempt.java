package com.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * https://www.youtube.com/watch?v=2FShdqn-Oz8&t=2078s
 */
public class PersonHeightsSelfAttempt {

	SegmentTree tree;

	class SegmentTree {

		int[] treeData;
		int[] input;
		final int root = 1;

		// 2*N is sufficient
		SegmentTree(int[] arr) {
			int n = arr.length;
			n = (int) Math.ceil(Math.log(n) / Math.log(2) + 1);
			int size = 1 << n;
			input = arr;
			treeData = new int[size];
			buildTree(root, 0, arr.length - 1);
		}

		private void buildTree(int treeIndex, int start, int end) {
			if (start > end) {
				return;
			}
			if (start == end) {
				treeData[treeIndex] = input[start];
				return;
			}

			int mid = start + (end - start) / 2;

			buildTree(left(treeIndex), start, mid);
			buildTree(right(treeIndex), mid + 1, end);

			treeData[treeIndex] = treeData[left(treeIndex)] + treeData[right(treeIndex)];

		}

		private int queryForNoOfSpaces(int treeIndex, int treeIndex_start, int treeIndex_end, int user_start,
				int user_end) {
			if (treeIndex_start > treeIndex_end)
				return 0;

			if (treeIndex_start == user_start && treeIndex_end == user_end) {
				return treeData[treeIndex];
			}

			int mid = treeIndex_start + (treeIndex_end - treeIndex_start) / 2;
			return queryForNoOfSpaces(left(treeIndex), treeIndex_start, mid, user_start, user_end)
					+ queryForNoOfSpaces(right(treeIndex), mid + 1, treeIndex_end, user_start, user_end);
		}

		private int queryForKthSpace(int treeIndex, int treeIndex_start, int treeIndex_end, int spaces) {
			if (spaces > treeData[treeIndex])
				return -1;

			if (treeIndex_start == treeIndex_end) {
				return treeIndex_start;
			}

			int mid = treeIndex_start + (treeIndex_end - treeIndex_start) / 2;

			int leftChildValue = treeData[left(treeIndex)];

			if (leftChildValue >= spaces) {
				return queryForKthSpace(left(treeIndex), treeIndex_start, mid, spaces);
			} else {
				return queryForKthSpace(right(treeIndex), mid + 1, treeIndex_end, spaces - leftChildValue);
			}

		}

		private void update(int treeIndex, int input_pos, int treeIndex_start, int treeIndex_end, int newValue) {

			if (treeIndex_start > treeIndex_end)
				return;

			if (treeIndex_start == treeIndex_end) {
				treeData[treeIndex] = newValue;
				return;
			}

			int mid = treeIndex_start + (treeIndex_end - treeIndex_start) / 2;

			if (input_pos <= mid) {
				update(left(treeIndex), input_pos, treeIndex_start, mid, newValue);
			} else {
				update(right(treeIndex), input_pos, mid + 1, treeIndex_end, newValue);
			}
			treeData[treeIndex] = treeData[left(treeIndex)] + treeData[right(treeIndex)];
		}

		private int left(int index) {
			return 2 * index;
		}

		private int right(int index) {
			return 2 * index + 1;
		}

	}

	public void update(int index, int newValue) {
		tree.update(tree.root, index, 0, tree.input.length - 1, newValue);
	}

	public void processInput(int[] arr) {
		tree = new SegmentTree(arr);
		//System.out.println(Arrays.toString(tree.treeData));
	}

	public int getKthSpace(int k) {
		return tree.queryForKthSpace(tree.root, 0, tree.input.length - 1, k);
	}

	public int noOfSpaces(int l, int r) {
		return tree.queryForNoOfSpaces(tree.root, 0, tree.input.length - 1, l, r);
	}

	public ArrayList<Integer> order(ArrayList<Integer> heights, ArrayList<Integer> inFronts) {
		// Sort the person acc to heights
		int[] heightArr = heights.stream().mapToInt(Integer::intValue).toArray();
		int[] inFrontsArr = inFronts.stream().mapToInt(Integer::intValue).toArray();
		int[] freeSpaces = new int[heightArr.length];
		Arrays.fill(freeSpaces, 1);
		processInput(freeSpaces);
		Map<Integer, Integer> treeMap = new TreeMap<>();
		for (int i = 0; i < heights.size(); i++) {
			treeMap.put(heightArr[i], inFrontsArr[i]);
		}
		// 1 Represents free Space
		int[] ans = new int[heights.size()];

		// Iterate over heights. Fix person i to inFront's
		treeMap.forEach((height, spaces_required) -> {
			int index = getKthSpace(spaces_required + 1);
			if (index != -1) {
				ans[index]= height;
				update(index, 0);
			}
		});
		return new ArrayList<>(IntStream.of(ans).boxed().collect(Collectors.toList()));
		
	}

	public static void main(String[] args) {
		PersonHeightsSelfAttempt obj = new PersonHeightsSelfAttempt();
		// System.out.println(obj.noOfSpaces(0, 5));
		ArrayList<Integer> ans = obj.order(new ArrayList<>(Arrays.asList(86, 77)),
				new ArrayList<>(Arrays.asList(0, 1)));
		System.out.println(ans);
	}

}
