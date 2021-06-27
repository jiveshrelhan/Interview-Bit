package com.math;

import java.util.Arrays;

public class FizzBuzz {

	public String[] fizzBuzz(int A) {
		String[] str = new String[A];
		for (int i = 0; i < A; i++) {
			int n = i + 1;
			if (n % 15 == 0) {
				str[i] = "FizzBuzz";
			} else if (n % 5 == 0) {
				str[i] = "Buzz";
			} else if (n % 3 == 0) {
				str[i] = "Fizz";
			} else {
				str[i] = String.valueOf((i + 1));
			}
		}
		return str;
	}
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new FizzBuzz().fizzBuzz(5)));
	}
}
