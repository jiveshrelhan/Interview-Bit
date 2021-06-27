package com.linkedlist;

public class Palindrome {

	private ListNode reverse(ListNode head) {
		if (head == null)
			return null;

		int size = 1;
		ListNode tail = head;
		while (tail.next != null) {
			size++;
			tail = tail.next;
		}

		ListNode curr = head;
		int i = 1;
		while (i < size) {
			ListNode future = curr.next;
			ListNode temp = tail.next;
			tail.next = curr;
			curr.next = temp;
			curr = future;
			i++;
		}
		return tail;
	}

	private boolean isSame(ListNode node1, ListNode node2) {
		boolean res = true;
		while (node1 != null && node2 != null) {
			if (node1.val != node2.val) {
				return false;
			}
			node1 = node1.next;
			node2 = node2.next;
		}
		return res;
	}

	public int lPalin(ListNode A) {
		if (A == null) {
			return 1;
		}
		int size = 0;
		ListNode curr = A;
		while (curr != null) {
			curr = curr.next;
			size++;
		}
		boolean isOdd = size % 2 == 0 ? false : true;

		int i = 1;
		int target = isOdd ? (size + 1) / 2 : size / 2;
		curr = A;
		while (i < target) {
			curr = curr.next;
			i++;
		}
		ListNode future = curr.next;
		curr.next = null;
		ListNode reverseList = reverse(future);
		if (isSame(A, reverseList)) {
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) {
		Palindrome obj = new Palindrome();
		System.out.println(obj.lPalin(ListNode.generateListNode("1->2->2->3->2->0->1")));
	}
}
