package com.linkedlist;

import java.util.Arrays;

public class RemoveDuplicate2 {
	public ListNode deleteDuplicates(ListNode head) {
		ListNode prev = new ListNode(-1);
		ListNode globalHead = prev;
		prev.next = head;
		ListNode curr = head;
		while(curr!=null) {
			boolean duplicate = false;
			while(curr!=null && curr.next!=null && curr.val == curr.next.val) {
				prev.next = curr.next;
				duplicate = true;
				curr = curr.next;
			}
			
			if(duplicate) {
				prev.next = curr.next;
			}
			else {
				prev=curr;
			}
			curr = curr.next;
		}
		return globalHead.next;
	}
	public static void main(String[] args) {
		RemoveDuplicate2  obj = new RemoveDuplicate2();
		ListNode node  = ListNode.generateListNode(Arrays.asList(1,1,2,3,4,5));
		System.out.println(obj.deleteDuplicates(node));
	}
}
