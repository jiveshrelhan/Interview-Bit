package com.string;

public class CountAndSay {
	private String generateNextSequence(String prev) {
		StringBuilder str = new StringBuilder();
		int start = 0, end = 0, count = 0;
		while (start < prev.length()) {

			count = 0;

			char s = prev.charAt(start);
			while (end < prev.length() && s == prev.charAt(end)) {
				count++;
				end++;
			}
			str.append(count);
			str.append(s);
			start = end;
		}
		return str.toString();
	}

	public String countAndSay(int n) {
		String prev = "1";
		while (n > 1) {
			prev = generateNextSequence(prev);
			n--;
		}

		return prev;
	}
	public static void main(String[] args) {
		CountAndSay oj = new CountAndSay();
		System.out.println(oj.countAndSay(10));
	}
}
