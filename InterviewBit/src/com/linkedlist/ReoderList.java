package com.linkedlist;

import java.util.Arrays;

public class ReoderList {

	public ListNode reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode secondHalf = slow.next;
		slow.next = null;
		ListNode prev = null;
		ListNode curr = secondHalf;

		while (curr != null) {
			ListNode future = curr.next;
			curr.next = prev;
			prev = curr;
			curr = future;
		}

		secondHalf = prev;

		curr = head;

		while (curr != null && secondHalf != null) {
			ListNode future = curr.next;
			ListNode secondHalfFuture = secondHalf.next;
			secondHalf.next = null;
			curr.next = secondHalf;
			secondHalf.next = future;
			curr = future;
			secondHalf = secondHalfFuture;
		}

		return head;

	}
	
	public static void main(String[] args) {
		ReoderList obj = new ReoderList();
		ListNode node = ListNode.generateListNode(Arrays.asList(1,2));
		System.out.println(obj.reorderList(node));
	}

}
