package com.linkedlist;

public class ReverseLinkedList2 {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode beforeStart = null, curr = head;
		int start = 1;
		while (start < m) {
			beforeStart = curr;
			curr = curr.next;
			start++;
		}
		if (beforeStart != null)
			beforeStart.next = null;
		ListNode prevHelper = null;
		ListNode reverseStartNode = curr;
		ListNode future = null;
		// curr points to m node;
		int diff = 0;
		while (curr != null && diff <= n - m) {
			future = curr.next;
			curr.next = prevHelper;
			prevHelper = curr;
			curr = future;
			diff++;
		}

		if (beforeStart != null)
			beforeStart.next = prevHelper;
		else {
			head = prevHelper;
		}
		reverseStartNode.next = future;
		return head;
	}

	public static void main(String[] args) {
		ListNode node = ListNode.generateListNode("1->2->3->4->5->");
		System.out.println(new ReverseLinkedList2().reverseBetween(node, 1, 2));
	}
}
