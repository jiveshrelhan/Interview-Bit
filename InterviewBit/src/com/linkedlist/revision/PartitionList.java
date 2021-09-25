package com.linkedlist.revision;

import java.util.Arrays;

import com.linkedlist.ListNode;

public class PartitionList {
	public ListNode partition(ListNode A, int x) {
		ListNode lessHead = new ListNode(-1);
		ListNode equalOrMoreHead = new ListNode(-1);
		ListNode lessPtr = lessHead, equalOrMorePtr = equalOrMoreHead;
		ListNode curr = A;

		while (curr != null) {
			ListNode future = curr.next;
			curr.next = null;

			if (curr.val < x) {
				lessPtr.next = curr;
				lessPtr = curr;
			} else {
				equalOrMorePtr.next = curr;
				equalOrMorePtr = curr;
			}

			curr = future;
		}

		lessPtr.next = equalOrMoreHead.next;

		return lessHead.next;
	}

	public static void main(String[] args) {
		PartitionList obj = new PartitionList();
		System.out.println(obj.partition(ListNode.generateListNode(Arrays.asList(1, 4, 3, 2, 5, 2)), 3));
	}
}
