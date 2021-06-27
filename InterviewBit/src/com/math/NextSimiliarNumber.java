package com.math;

import java.util.Arrays;

public class NextSimiliarNumber {

	private int getIndex(char[] number) {
		int i = number.length - 1;
		while (i > 0) {
			if (number[i] > number[i - 1]) {
				break;
			}
			i--;
		}
		return i;
	}

	public String solve(String n) {
		char number[] = n.toCharArray();
		int index = getIndex(number);
		if (index == 0) {
			return "-1";
		}
		int x = number[index - 1];
		int j = index + 1;
		int smallestIndex = index;
		while (j < number.length) {
			if (number[j] > x && number[j] < number[smallestIndex]) {
				smallestIndex = j;
			}
			j++;
		}

		char temp = number[index - 1];
		number[index - 1] = number[smallestIndex];
		number[smallestIndex] = temp;
		Arrays.sort(number, index, number.length);
		return String.valueOf(number);
	}

	public static void main(String[] args) {
		NextSimiliarNumber obj = new NextSimiliarNumber();
		System.out.println(obj.solve("21"));
	}
}
