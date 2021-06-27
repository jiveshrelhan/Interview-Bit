package com.linkedlist;

public class PairSwap {

	public ListNode swapPairs(ListNode head) {
		ListNode start = head;
		if (start == null || start.next == null) {
			return start;
		}

		ListNode end = start.next;
		ListNode future = end.next;
		end.next = start;
		start.next = swapPairs(future);

		return end;
	}

	public static void main(String[] args) {
		ListNode node = ListNode.generateListNode("1 -> 4 -> 6 -> 4 -> 10->11");
		System.out.println(new PairSwap().swapPairs(node));
	}
}
