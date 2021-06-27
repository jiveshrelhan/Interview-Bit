package com.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DiagonalTraversal {
	 public ArrayList<Integer> solve(TreeNode A) {
	        ArrayList<Integer> ans = new ArrayList<>();
	        Queue<TreeNode> nextLevel = new LinkedList<>();
	        TreeNode curr = A;
	        
	        while(true){
	            if(curr == null && nextLevel.isEmpty()){
	                break;
	            }
	            
	            if(curr == null){
	                curr = nextLevel.poll();
	            }
	            
	            ans.add(curr.val);
	            
	            if(curr.left!=null){
	                nextLevel.add(curr.left);
	            }
	            
	            curr = curr.right;
	        }
	        
	        return ans;
	    }
}
