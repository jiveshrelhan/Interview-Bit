package com.linkedlist.revision;

import java.util.Arrays;
import java.util.Stack;

import com.linkedlist.ListNode;

public class InsertionSort {
	public ListNode insertionSortList(ListNode A) {
		ListNode curr = A;
		Stack<ListNode> stack1 = new Stack<>();
		Stack<ListNode> stack2 = new Stack<>();

		while (curr != null) {
			ListNode future = curr.next;
			curr.next = null;
			while (!stack1.isEmpty() && stack1.peek().val > curr.val) {
				stack2.push(stack1.pop());
			}
			stack1.push(curr);
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
			curr = future;
		}

		ListNode temp = null, pop = null;

		while (!stack1.isEmpty()) {
			pop = stack1.pop();
			if (temp == null) {
				temp = pop;
			} else {
				pop.next = temp;
				temp = pop;
			}
		}
		return pop;
	}

	public static void main(String[] args) {
		InsertionSort obj = new InsertionSort();
		System.out.println(obj.insertionSortList(ListNode.generateListNode(Arrays.asList(2, 5, 1, 3))));
	}
}
