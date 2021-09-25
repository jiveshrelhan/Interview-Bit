package com.string;

public class RemoveConsecutiveCharacters {

	public String solve(String A, int B) {
		char[] chars = A.toCharArray();
		int start = 0, end = 1;
		int freeIndex = 0;
		while (end < chars.length) {
			char current = chars[start];
			if (end < start) {
				end = start + 1;
			}
			while (end < chars.length && chars[end] == current) {
				end++;
			}
			if (end - start != B) {
				while (start < end) {
					chars[freeIndex++] = chars[start++];
				}
			}

			start = end;
		}
		A = String.valueOf(chars);
		return A.substring(0, freeIndex);
	}

	public static void main(String[] args) {
		System.out.println(new RemoveConsecutiveCharacters().solve("aaaa", 2));
	}
}
