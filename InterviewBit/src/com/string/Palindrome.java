package com.string;

public class Palindrome {

	private boolean isAlphaNumeric(char c) {
		return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z');
	}

	public int isPalindrome(String a) {
		a = a.toLowerCase();
		int n = a.length();
		if (n <= 1)
			return 1;
		int left = 0, right = n - 1;
		int isPalindrome = 1;
		while (left < n && right >= 0 && left <= right) {
			char charAtLeft = a.charAt(left);
			char charAtRight = a.charAt(right);
			if (!(isAlphaNumeric(charAtLeft))) {
				left++;
			} else if (!(isAlphaNumeric(charAtRight))) {
				right--;
			} else {
				if (charAtLeft != charAtRight) {
					return isPalindrome = 0;
				}
				left++;
				right--;
			}

		}
		return isPalindrome;
	}

	public static void main(String[] args) {
		Palindrome obj = new Palindrome();
		System.out.println(obj.isPalindrome("1a1"));
	}
}
