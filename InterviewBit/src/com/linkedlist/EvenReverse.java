package com.linkedlist;

import java.util.Arrays;

public class EvenReverse {
	public ListNode solve(ListNode head) {
		ListNode curr = head, prev = null;
		ListNode evenList = new ListNode(-1);
		int i = 1;
		while (curr != null) {
			ListNode future = curr.next;

			if (i % 2 == 0) {
				ListNode evenFuture = evenList.next;
				prev.next = future;
				curr.next = null;
				evenList.next = curr;
				curr.next = evenFuture;
			} else {
				prev = curr;
			}
			curr = future;
			i++;
		}

		ListNode oddList = head;
		evenList = evenList.next;
		//Merge
		while (oddList != null && evenList != null) {
			ListNode future = oddList.next;
			ListNode evenListFuture = evenList.next;
			oddList.next = evenList;
			evenList.next = null;
			evenList.next = future;
			evenList = evenListFuture;
			oddList = future;
		}

		return head;
	}

	public static void main(String[] args) {
		ListNode node = ListNode.generateListNode(Arrays.asList(1, 2));
		System.out.println(new EvenReverse().solve(node));
	}
}
