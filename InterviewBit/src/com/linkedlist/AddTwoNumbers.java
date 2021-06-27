package com.linkedlist;

import java.util.Arrays;

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode A, ListNode B) {
		ListNode temp = new ListNode(-1);
		ListNode ansCounter = temp;

		/*
		 * ListNode x = A, y = B; int sizeA = 0, sizeB = 0;
		 * 
		 * while (x != null) { sizeA++; x = x.next; } while (y != null) { sizeB++; y =
		 * y.next; }
		 * 
		 * if (sizeA > sizeB) { int diff = sizeA - sizeB; y = B; int i = 0; while (i <
		 * diff) { ListNode zero = new ListNode(0); zero.next = y; y = zero; i++; B=y; }
		 * } else if (sizeB > sizeA) { int diff = sizeB - sizeA; x = B; int i = 0; while
		 * (i < diff) { ListNode zero = new ListNode(0); zero.next = x; x = zero; i++;
		 * A=x; } }
		 */
		int carry = 0;
		while (A != null || B != null) {
			int aValue = A == null ? 0 : A.val;
			int bValue = B == null ? 0 : B.val;
			int newValue = aValue + bValue + carry;
			if (newValue > 9) {
				newValue = newValue - 10;
				carry = 1;
			} else {
				carry = 0;
			}
			ListNode newNode = new ListNode(newValue);
			ansCounter.next = newNode;
			ansCounter = newNode;
			A = A != null ? A.next : null;
			B = B != null ? B.next : null;
		}
		if (carry == 1) {
			ListNode newNode = new ListNode(1);
			ansCounter.next = newNode;
		}
		return temp.next;
	}

	public static void main(String[] args) {
		AddTwoNumbers obj = new AddTwoNumbers();
		ListNode A = ListNode.generateListNode(Arrays.asList(9));
		ListNode B = ListNode.generateListNode(Arrays.asList(1));
		System.out.println(obj.addTwoNumbers(A, B));

	}
}
