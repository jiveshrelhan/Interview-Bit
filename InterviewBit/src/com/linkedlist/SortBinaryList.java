package com.linkedlist;

import java.util.Arrays;

public class SortBinaryList {
	public ListNode solve(ListNode A) {
		/*
		 * Temp and ones node are added to avoid additional check for head.
		 * if head is not set set head. 
		 * this is more convenient way to code.
		 */
		ListNode temp = new ListNode(-1);
		temp.next = A;
		ListNode prev = temp;
		ListNode curr = temp.next; // A;
		ListNode ones = new ListNode(-1);
		while (curr != null) {
			if (curr.val == 1) {
				/*
				 *  curr node is 1, break it from current list.
				 *	A -> 1 -> B
				 *  prev -> curr -> next
				 *  prev.next = curr.next and curr.next = null;
				 */
				prev.next = curr.next;
				curr.next = null;
				
				/*
				 * Join in 1's list.
				 * ones point to head 
				 *  -1 (head/ones) -> P(1) -> Q(1)......
				 *   curr.next = ones.next;
				 *   ones.next = curr;
				 */
				curr.next = ones.next;
				ones.next = curr;
				
				/*
				 * Reset pointer to B from 0 list
				 */
				curr = prev.next;
			} else {
				/* 
				 * if curr node is 0. continue moving curr = curr.next 
				 * and maintain the previous pointer.
				 */
				prev = curr;
				curr = curr.next;
			}
		}
		prev.next = ones.next;
		return temp.next;
	}

	public static void main(String[] args) {
		SortBinaryList obj = new SortBinaryList();
		ListNode list = ListNode.generateListNode(Arrays.asList(0, 1, 0, 0, 1));
		list = obj.solve(list);
		ListNode.printList(list);
	}
}
