package com.linkedlist.revision;

import java.util.Arrays;

public class SortBinaryList {
	public ListNode solve(ListNode A) {
		ListNode zeroHead = null, oneHead = null, zerocurr = null;
		ListNode curr = A;

		while (curr != null) {
			ListNode future = curr.next;
			curr.next = null;

			if (curr.val == 0) {
				if (zeroHead == null) {
					zeroHead = curr;
					zerocurr = curr;
				} else {
					zerocurr.next = curr;
					zerocurr = curr;
				}
			} else {
				if (oneHead == null) {
					oneHead = curr;
				} else {
					curr.next = oneHead;
					oneHead = curr;
				}
			}
			curr = future;
		}

		if (zeroHead == null) {
			return oneHead;
		} else {
			zerocurr.next = oneHead;
			return zeroHead;
		}

	}

	public static void main(String[] args) {
		SortBinaryList obj = new SortBinaryList();
		System.out.println(obj.solve(ListNode.generateListNode(Arrays.asList())));
	}
}
