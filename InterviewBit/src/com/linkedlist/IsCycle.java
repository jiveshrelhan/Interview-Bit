package com.linkedlist;

public class IsCycle {

	public ListNode detectCycle(ListNode head) {
		ListNode slow = head, fast = head;
		/*
		 * while (fast != null && fast.next != null) { if (fast.next == slow) { return
		 * true; } slow = slow.next; fast = fast.next.next; }
		 */

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				break;
			}
		}

		if (slow != fast)
			return null;

		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	public static void main(String[] args) {
		IsCycle obj = new IsCycle();
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = b;
		System.out.println(obj.detectCycle(a).val);
	}
}
