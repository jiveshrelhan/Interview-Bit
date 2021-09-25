package com.linkedlist.revision;

import java.util.Arrays;

public class Reverse {

	public ListNode reverse(ListNode head) {
		ListNode prev = null, curr = head;

		while (curr != null) {
			ListNode future = curr.next;
			curr.next = prev;
			prev = curr;
			curr = future;
		}

		return prev;
	}

	public static void main(String[] args) {
		Reverse obj = new Reverse();
		System.out.println(obj.reverse(ListNode.generateListNode(Arrays.asList(1))));
	}

}
