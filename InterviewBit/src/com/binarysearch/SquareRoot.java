package com.binarysearch;

public class SquareRoot {

	public int sqrt(int A) {
		long low = 1;
		long high = A;
		long mid = low;

		while (low <= high) {
			mid = low + (high - low) / 2;
			long ans = mid * mid;
			if (ans == A) {
				return (int) mid;
			} else if (ans > A) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return (int) high;

	}

	public static void main(String[] args) {
		SquareRoot obj = new SquareRoot();
		System.out.println(obj.sqrt(2147483647));
	}

}
