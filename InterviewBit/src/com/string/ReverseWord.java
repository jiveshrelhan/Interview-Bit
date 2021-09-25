package com.string;

public class ReverseWord {

	public String solve(String A) {
		int start = -1, end = -1, curr = A.length() - 1;
		StringBuilder str = new StringBuilder();
		while (curr >= 0) {
			while (curr >= 0 && A.charAt(curr) == ' ') {
				curr--;
			}
			end = curr;
			if (end == -1) {
				return str.toString();
			}

			while (curr >= 0) {
				char c = A.charAt(curr);
				if (c == ' ') {
					break;
				}
				start = curr;
				curr--;
			}
			String word = A.substring(start, end + 1);
			if (str.length() == 0)
				str.append(word);
			else
				str.append(" " + word);
		}
		return str.toString();
	}

	public static void main(String[] args) {
		ReverseWord obj = new ReverseWord();
		System.out.println(obj.solve("is   the "));
	}
}
