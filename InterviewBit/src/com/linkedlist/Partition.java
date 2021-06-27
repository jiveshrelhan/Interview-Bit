package com.linkedlist;

public class Partition {
	/*
	 * Question is little different from GeeksForGeeks.
	 * Interview bit has asked to filter only greater element than x if it came before x.
	 * i.e we need to find and create list of smaller element from the original list and merge them at the end.
	 */
	public ListNode partition(ListNode A, int x) {
		//To void checking null check of head.
		ListNode less = new ListNode(-1),equal = new ListNode(-1);
		equal.next = A;

		/*Less pointer keep track of end of smaller element list. Since we are going only at the end we need tail pointer.
		* less points to head and lessPointer points to tail.
		*/
		ListNode lessPointer = less;
		
		/*
		 * curr is equal to i. Prev is required to remove the smaller element from current list.
		 */
		ListNode curr = A, prev = equal;

		while (curr != null) {
			ListNode future = curr.next;
			if (curr.val < x) {
				/*
				 * Isolate curr node by making previous.next = future and curr.next = null;
				 */
				prev.next = future;
				curr.next = null;
				
				/*
				 * Join smaller element list at tail.
				 */
				lessPointer.next = curr;
				lessPointer = curr;
			} else {
				/*
				 * In case element is >= update the prev Pointer. 
				 */
				prev = curr;
			}
			curr = future;
		}
		/*
		 * Merge lessPointer tail to current pointer head.
		 * lessPointer.next = equal.next will remove the additional -1 added to equal list.
		 */
		lessPointer.next = equal.next;
		
		/*
		 * less.next will remove the additional -1 added to smaller list.
		 */
		return less.next;
	}

	public static void main(String[] args) {
		Partition obj = new Partition();
		ListNode list = ListNode.generateListNode(
				"558 -> 162 -> 89 -> 253 -> 153 -> 478 -> 423 -> 976 -> 945 -> 267 -> 781 -> 964 -> 117 -> 403 -> 815 -> 358 -> 15 -> 581 -> 338 -> 417 -> 890 -> 989 -> 754 -> 957 -> 243 -> 310 -> 911 -> 724 -> 462 -> 7 -> 437 -> 101 -> 812 -> 483 -> 450 -> 547 -> 316 -> 929 -> 583 -> 1 -> 132 -> 167 -> 266 -> 590 -> 328 -> 23 -> 591 -> 904 -> 274 -> 534 -> 677 -> 574 -> 184 -> 606 -> 926 -> 863 -> 832 -> 626 -> 972 -> 307 -> 174 -> 940 -> 952 -> 703 -> 699 -> 404 -> 150 -> 636 -> 782 -> 442 -> 560 -> 613 -> 158 -> 743 -> 970 -> 917 -> 322 -> 220 -> 578 -> 829 -> 33 -> 439 -> 428 -> 551 -> 376 -> 985 ");
		list = obj.partition(list, 1);
		ListNode.printList(list);
	}
}
