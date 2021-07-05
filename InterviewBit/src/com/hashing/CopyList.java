package com.hashing;

import java.util.HashMap;
import java.util.Map;

public class CopyList {

	class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	};
	/*
	 * First create nodes and keep the mapping between original and newly created node in the map.
	 * use map to create to generates the links
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode curr = head;
		RandomListNode newHead = null;
		while (curr != null) {
			RandomListNode blankCopy = new RandomListNode(curr.label);
			if (newHead == null) {
				newHead = blankCopy;
			}
			map.put(curr, blankCopy);
			curr = curr.next;
		}
		curr = head;
		while (curr != null) {
			/*
			 * We can set the next pointer in above while loop also but anyways both are same.
			 */
			RandomListNode blankCopy = map.get(curr);
			if (curr.next != null)
				blankCopy.next = map.get(curr.next);
			if (curr.random != null)
				blankCopy.random = map.get(curr.random);

			curr = curr.next;
		}

		return newHead;
	}
}
