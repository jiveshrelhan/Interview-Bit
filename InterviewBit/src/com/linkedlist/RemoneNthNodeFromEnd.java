package com.linkedlist;

import java.util.Arrays;

public class RemoneNthNodeFromEnd {
	public ListNode removeNthFromEnd(ListNode head, int x) {
		ListNode prev = new ListNode(-1);
		ListNode globalHead = prev;
		prev.next = head;

		ListNode curr = head, advance = head;

		int steps = 1;

		while (steps < x && advance != null) {
			advance = advance.next;
			steps++;
		}

		if (advance == null) {
			prev.next = head.next;
			return globalHead.next;
		}

		while (advance.next != null) {
			prev = curr;
			curr = curr.next;
			advance = advance.next;
		}

		prev.next = curr.next;
		return globalHead.next;
	}

	public static void main(String[] args) {
		RemoneNthNodeFromEnd obj = new RemoneNthNodeFromEnd();
		ListNode node = ListNode.generateListNode(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(obj.removeNthFromEnd(node, 10));
	}
}
