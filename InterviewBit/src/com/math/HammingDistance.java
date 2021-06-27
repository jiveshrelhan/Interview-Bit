package com.math;

public class HammingDistance {
	long mod = (long) 1e9 + 7;

	public int getHammingDistance(int a, int b) {
		if (a == b) {
			return 0;
		}
		int xor = a ^ b;
		int count = 0;
		while (xor != 0) {
			xor = xor & (xor - 1);
			count++;
		}
		return count;
	}

	public int hammingDistance(final int[] input) {
		long ans = 0;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length; j++) {
				ans += getHammingDistance(input[i], input[j]) % mod;
			}
		}
		return (int) ans;
	}

	public static void main(String[] args) {
		HammingDistance obj = new HammingDistance();
		System.out.println(obj.hammingDistance(new int[] {1}));
	}
}
