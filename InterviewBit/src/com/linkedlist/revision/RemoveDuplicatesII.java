package com.linkedlist.revision;

import java.util.Arrays;

public class RemoveDuplicatesII {
	public ListNode deleteDuplicates(ListNode head) {
        ListNode global = new ListNode(-1);
        ListNode prev = global, curr = head;

        while(curr!=null){
            boolean duplicate = false;
            while(curr!=null && curr.next!=null && curr.val == curr.next.val){
                duplicate = true;
                curr.next = curr.next.next;
            }

            if(duplicate){
                prev.next = curr.next;
            }
            else{
                prev.next = curr;
                prev = curr;
            }
            curr = curr.next;
        }
        return global.next;
    }
	
	public static void main(String[] args) {
		RemoveDuplicatesII obj = new RemoveDuplicatesII();
		System.out.println(obj.deleteDuplicates(ListNode.generateListNode(Arrays.asList(1,1,2))));
	}
}
