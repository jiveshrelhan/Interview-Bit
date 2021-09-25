package com.string;

import java.util.ArrayList;
import java.util.Arrays;

public class ValidIPAddress {

	ArrayList<String> res = new ArrayList<String>();

	private boolean isValid(String str) {
		int n = str.length();
		if (n == 0 || n > 3) {
			return false;
		}
		if (n == 1) {
			return true;
		}

		if (str.charAt(0) == '0') {
			return false;
		}

		int number = Integer.valueOf(str);

		if (number >= 0 && number <= 255) {
			return true;
		}

		return false;
	}

	private void helper(String input, int index, int pos, String current) {
		if (index == input.length() && pos == 4) {
			res.add(new String(current));
			return;
		}
		if (index >= input.length() || (pos == 4 && index < input.length())) {
			return;
		}

		for (int i = index; i < index + 3; i++) {
			if (i >= input.length())
				break;
			String word = input.substring(index, i + 1);
			if (isValid(word)) {
				String newCurrent = null;
				if (current == "")
					newCurrent = word;
				else
					newCurrent = current + "." + word;
				helper(input, i + 1, pos + 1, newCurrent);
			}
		}
	}

	public String[] restoreIpAddresses(String A) {
		helper(A, 0, 0, "");
		String[] s = new String[res.size()];
		for (int i = 0; i < res.size(); i++) {
			s[i] = res.get(i);
		}
		return s;
	}

	public static void main(String[] args) {
		ValidIPAddress obj = new ValidIPAddress();
		System.out.println(Arrays.toString(obj.restoreIpAddresses("11111")));
	}
}
