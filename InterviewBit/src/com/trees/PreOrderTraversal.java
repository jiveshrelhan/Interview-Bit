package com.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PreOrderTraversal {
	public ArrayList<Integer> preorderTraversal(TreeNode A) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		if (A == null)
			return ans;
		HashMap<TreeNode, Integer> stateMachine = new HashMap<>();
		Stack<TreeNode> stack = new Stack<>();
		stateMachine.put(A, 0);
		stack.push(A);

		while (!stack.isEmpty()) {
			TreeNode poppedElement = stack.peek();
			int poppedState = stateMachine.get(poppedElement);

			if (poppedState == 0) {
				ans.add(poppedElement.val);
			} else if (poppedState == 1) {
				if (poppedElement.left != null) {
					stack.push(poppedElement.left);
					stateMachine.put(poppedElement.left, 0);
				}
			} else if (poppedState == 2) {
				if (poppedElement.right != null) {
					stack.push(poppedElement.right);
					stateMachine.put(poppedElement.right, 0);
				}
			} else {
				stack.pop();
			}
			stateMachine.put(poppedElement, poppedState + 1);
		}
		return ans;
	}
	public static void main(String[] args) {
		PreOrderTraversal obj = new PreOrderTraversal();
		System.out.println(obj.preorderTraversal(TreeNode.getExample1()));
	}
}
