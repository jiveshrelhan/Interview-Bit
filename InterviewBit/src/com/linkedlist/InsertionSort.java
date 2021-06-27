package com.linkedlist;

import java.util.Stack;

public class InsertionSort {

	public ListNode insertionSortList(ListNode A) {
		ListNode curr = A, prev = null;
		Stack<ListNode> stack1 = new Stack<>();
		Stack<ListNode> stack2 = new Stack<>();
		while (curr != null) {
			ListNode future = curr.next;
			ListNode pop = null;
			while (!stack1.isEmpty() && stack1.peek().val > curr.val) {
				pop = stack1.pop();
				stack2.push(pop);
			}
			if (pop != null) {
				if (prev != null)
					prev.next = curr.next;
				curr.next = null;
				if (!stack1.isEmpty()) {
					pop = stack1.peek();
					ListNode save = pop.next;
					pop.next = curr;
					curr.next = save;
				} else {
					curr.next = A;
					A = curr;
				}
			} else {
				prev = curr;
			}
			stack1.push(curr);
			curr = future;
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
			stack2 = new Stack<>();
		}
		ListNode head = null;
		while (!stack1.isEmpty()) {
			head = stack1.pop();
		}
		return head;
	}

	public static void main(String[] args) {
		InsertionSort obj = new InsertionSort();
		System.out.println(obj.insertionSortList(ListNode.generateListNode("1->3->2->5->4->7->6")));
	}
}
