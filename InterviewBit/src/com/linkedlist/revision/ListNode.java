package com.linkedlist.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
		next = null;
	}

	public static ListNode generateListNode(String input) {
		List<Integer> list = new ArrayList<Integer>();
		Arrays.stream(input.split("->")).forEach(k -> {
			k=k.trim();
			list.add(Integer.valueOf(k));
		});
		return generateListNode(list);
	}

	public static ListNode generateListNode(List<Integer> list) {
		ListNode head = null;
		ListNode prev = null;
		for (int x : list) {
			ListNode curr = new ListNode(x);
			if (head == null) {
				head = curr;
				prev = curr;
			} else if (prev != curr) {
				prev.next = curr;
				prev = curr;
			}
		}
		return head;
	}

	public static String printList(ListNode head) {
		ListNode curr = head;
		StringBuilder str = new StringBuilder();
		while (curr != null) {
			String temp = str.length() == 0 ? "" + curr.val : "->" + curr.val;
			str.append(temp);
			curr = curr.next;
		}
		System.out.println(str.toString());
		return str.toString();
	}

	@Override
	public String toString() {
		return " Val: " + this.val + " Next: " + this.next;
	}

}
