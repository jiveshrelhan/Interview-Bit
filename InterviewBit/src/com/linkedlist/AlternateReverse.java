package com.linkedlist;

public class AlternateReverse {
	private ListNode solveHelper(ListNode head, int k, boolean reverse) {
		if (head == null) {
			return head;
		}
		ListNode curr = head;
		ListNode start = head;
		ListNode prev = null;
		int i = 1;
		while (curr != null && i <= k) {
			ListNode future = curr.next;
			if (reverse) {
				curr.next = prev;
			}
			prev = curr;
			curr = future;
			i++;
		}
		if (reverse) {
			start.next = solveHelper(curr, k, !reverse);
			return prev;
		} else {
			prev.next = solveHelper(curr, k, !reverse);
			return start;
		}
	}

	public ListNode solve(ListNode head,int k) {
		return solveHelper(head,k,true);
	}
	public static void main(String[] args) {
		ListNode node = ListNode.generateListNode("1 -> 4 -> 6 -> 6 -> 4 -> 10");
		System.out.println(new AlternateReverse().solve(node, 7));
	}
}
