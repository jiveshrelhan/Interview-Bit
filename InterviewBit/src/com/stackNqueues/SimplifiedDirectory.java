package com.stackNqueues;

import java.util.Stack;

public class SimplifiedDirectory {
	public String simplifyPath(String A) {
		int n = A.length();
		int i = 0;
		String dirName = "";
		Stack<String> stack = new Stack<>();
		while (i < n) {
			char c = A.charAt(i);
			if (c == '.') {
				if (i + 1 < n && A.charAt(i + 1) == '.' && !stack.isEmpty()) {
					stack.pop();
					i++;
				}
			} else if (c == '/') {

				if (dirName.length() > 0)
					stack.push(dirName);
				dirName = "";

			} else {
				dirName += c;
			}
			i++;
		}
		if (!dirName.isEmpty()) {
			stack.push(dirName);
		}
		StringBuilder result = new StringBuilder();
		while (!stack.isEmpty()) {
			result.insert(0, "/" + stack.pop());
		}
		return result.length() > 0 ? result.toString() : "/";
	}

	public static void main(String[] args) {
		SimplifiedDirectory obj = new SimplifiedDirectory();
		System.out.println(obj.simplifyPath("/./.././ykt/xhp/nka/eyo/"));
	}
}
