package com.linkedlist;

import java.util.Arrays;

public class SortList {

	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode slowPrev = head, slow = head, fast = head;

		while (fast != null && fast.next != null) {
			slowPrev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		slowPrev.next = null;

		ListNode firstList = sortList(head);

		ListNode secondList = sortList(slow);

		return merge(firstList, secondList);

	}

	private ListNode merge(ListNode first, ListNode second) {
		ListNode head = new ListNode(-1);
		ListNode curr = head;
		ListNode l1 = first, l2 = second;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}
			curr = curr.next;
		}

		if (l1 != null) {
			curr.next = l1;
		} else {
			curr.next = l2;
		}

		return head.next;
	}

	public static void main(String[] args) {
		ListNode node = ListNode.generateListNode(Arrays.asList(1, 3, 2, 5, 4, 7, 6));
		SortList obj = new SortList();
		System.out.println(obj.sortList(node));
	}

}
