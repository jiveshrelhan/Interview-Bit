package com.linkedlist.revision;

import java.util.Arrays;

public class EvenReverse {
	public ListNode solve(ListNode A) {
		ListNode oddList = new ListNode(-1);
		ListNode curr = A, evenPtr = null, oddPtr = oddList;
		int i = 1;

		while (curr != null) {
			ListNode future = curr.next;
			curr.next = null;

			if (i % 2 == 1) {
				oddPtr.next = curr;
				oddPtr = curr;
			} else {
				curr.next = evenPtr;
				evenPtr = curr;
			}
			i++;
			curr = future;
		}

		oddList = oddList.next;

		ListNode head = new ListNode(-1);
		curr = head;

		while (oddList != null && evenPtr != null) {
			ListNode oddFuture = oddList.next;
			oddList.next = null;
			ListNode evenFuture = evenPtr.next;
			evenPtr.next = null;

			curr.next = oddList;
			curr = curr.next;
			curr.next = evenPtr;
			curr = curr.next;
			oddList = oddFuture;
			evenPtr = evenFuture;
		}

		if (oddList != null) {
			curr.next = oddList;
		}

		return head.next;
	}

	public static void main(String[] args) {
		EvenReverse obj = new EvenReverse();
		System.out.println(obj.solve(ListNode.generateListNode(Arrays.asList(1, 2, 3, 4))));
	}
}
