package com.linkedlist;

import java.util.Arrays;

public class KReverseLinkedList {

	@SuppressWarnings("unused")
	private ListNode reverse(ListNode A, int k) {
		if (A == null)
			return null;
		ListNode prev = null;
		ListNode curr = A;
		int i = 1;
		while (curr != null && i <= k) {
			ListNode future = curr.next;
			curr.next = null;
			curr.next = prev;
			prev = curr;
			curr = future;
			i++;
		}
		return prev;
	}

	/*
	 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 2 -> 1 -> 4 -> 3 -> 6 -> 5
	 */
	public ListNode reverseList(ListNode A, int k) {
		if(A==null) {
			return null;
		}
		ListNode prev = null;
		ListNode start = A;
		ListNode curr = A;
		int i = 1;
		while (curr != null && i <= k) {
			ListNode future = curr.next;
			curr.next = null;
			curr.next = prev;
			prev = curr;
			curr = future;
			i++;
		}
		start.next = reverseList(curr,k);

		return prev;
	}

	public static void main(String[] args) {
		KReverseLinkedList obj = new KReverseLinkedList();
		ListNode node = ListNode.generateListNode(Arrays.asList(1, 2, 3, 4, 5, 6));
		System.out.println(obj.reverseList(node, 4));
	}
}
