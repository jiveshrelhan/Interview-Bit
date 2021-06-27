package com.linkedlist;

import java.util.Arrays;

public class RemoveDuplicate1 {
	public ListNode deleteDuplicates(ListNode head) {
		ListNode prev = new ListNode(-1);
		ListNode globalHead = prev;
		prev.next = head;
		ListNode curr = head;
		while (curr != null) {
			while (curr != null && curr.next != null && curr.val == curr.next.val) {
				prev.next = curr.next;
				curr = curr.next;
			}

			prev = curr;
			curr = curr.next;
		}
		return globalHead.next;
	}
	public static void main(String[] args) {
		RemoveDuplicate1 obj = new RemoveDuplicate1();
		ListNode node  = ListNode.generateListNode(Arrays.asList(1,2,3,3,3,3,4,5,5));
		System.out.println(obj.deleteDuplicates(node));
	}
}
