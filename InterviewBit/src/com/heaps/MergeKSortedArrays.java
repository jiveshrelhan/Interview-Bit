package com.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import com.linkedlist.ListNode;

public class MergeKSortedArrays {
	
	public ListNode mergeKLists(ArrayList<ListNode> a) {
		ListNode head = new ListNode(-1);
		ListNode curr = head;
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});
		for (ListNode headOfRow : a) {
			minHeap.add(headOfRow);
		}

		while (!minHeap.isEmpty()) {
			ListNode minAmoungAll = minHeap.poll();
			ListNode future = minAmoungAll.next;
			minAmoungAll.next = null;
			curr.next = minAmoungAll;
			curr = minAmoungAll;
			if (future != null) {
				minHeap.add(future);
			}
		}

		return head.next;
	}
}
