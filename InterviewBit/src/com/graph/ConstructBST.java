package com.graph;

import java.util.ArrayList;

import com.linkedlist.ListNode;
import com.trees.TreeNode;

public class ConstructBST {
	private TreeNode divideNConqour(int[] A,int start, int end){
        if(start > end){
            return null;
        }
        int mid = start + (end-start)/2;
        TreeNode newNode = new TreeNode(A[mid]);
        newNode.left = divideNConqour(A,start,mid-1);
        newNode.right = divideNConqour(A,mid+1,end);
        return newNode;
    }
	public TreeNode sortedListToBST(ListNode a) {
	    ArrayList<Integer> arr = new ArrayList<>();
	    while(a!=null){
	        arr.add(a.val);
	        a = a.next;
	    }
	    int[] A = arr.stream().mapToInt(Integer::intValue).toArray();
	    return divideNConqour(A, 0, A.length-1);
	}
}
