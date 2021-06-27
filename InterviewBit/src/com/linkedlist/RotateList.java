package com.linkedlist;

import java.util.Arrays;

public class RotateList {
		public ListNode rotateRight(ListNode head, int k) {
			if (head == null)
				return head;
			int size = 0;
			ListNode curr = head, prev = null;
			while (curr != null) {
				prev = curr;
				curr = curr.next;
				size++;
			}
			ListNode end = prev;
			curr = head;
			prev = null;
			k = k % size;
			if (k == 0)
				return head;
			int target = size - k;
			int i = 1;
	
			while (i <= target) {
				prev = curr;
				curr = curr.next;
				i++;
			}
	
			prev.next = null;
			end.next = head;
			return curr;
	
		}

	public static void main(String[] args) {
		RotateList obj = new RotateList();
		ListNode node = ListNode.generateListNode(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(obj.rotateRight(node, 0));
	}
}
