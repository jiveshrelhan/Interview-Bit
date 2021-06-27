package com.binarysearch;

public class Power {

	public int pow(int x, int n, int d) {

		if (x == 0)
			return 1 % d;
		long result = 1;
		long a = x;
		while (n > 0) {
			if (n % 2 == 1) {
				result = ((result % d) * (a % d)) % d;
			}

			a = ((a % d) * (a % d)) % d;

			n /= 2;
		}

		return result > 0 ? (int) result % d : (int) ((d + result) % d);

	}

	public static void main(String[] args) {
		Power obj = new Power();
		System.out.println(obj.pow(71045970, 41535484, 64735492));
	}
}
