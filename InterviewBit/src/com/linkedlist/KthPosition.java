package com.linkedlist;

import java.util.Arrays;

public class KthPosition {

	public int solve(ListNode head, int k) {
		ListNode slow = head, fast = head, ans = head;
		int i = 1;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			i++;
		}
		int j = i - k;
		if (j <= 0) {
			return -1;
		}
		while (j > 1) {
			ans = ans.next;
			j--;
		}
		return ans.val;
	}

	public static void main(String[] args) {
		KthPosition obj = new KthPosition();
		ListNode node = ListNode.generateListNode(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		System.out.println(obj.solve(node, 2));
		ListNode node1 = ListNode.generateListNode("1 -> 14 -> 6 -> 16 -> 4 -> 10");
		System.out.println(obj.solve(node1, 10));
	}
}
