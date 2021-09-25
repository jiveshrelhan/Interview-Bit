package com.greedy;

public class Seats {
	public int seats(String A) {
		int MOD = 10000003;
		long jumps = Integer.MAX_VALUE;
		char[] chars = A.toCharArray();
		int n = chars.length;
		if (n <= 1) {
			return 0;
		}
		int[] prefix = new int[n], suffix = new int[n];
		prefix[0] = 0;

		int numberOfX = 0;
		numberOfX = chars[0] == 'x' ? 1 : 0;

		for (int i = 1; i < n; i++) {
			char c = chars[i];
			if (c == '.') {
				prefix[i] = prefix[i - 1] + numberOfX * 1;
			} else {
				prefix[i] = prefix[i - 1];
				numberOfX++;
			}
			prefix[i] %= MOD;
		}

		suffix[n - 1] = 0;
		numberOfX = chars[n - 1] == 'x' ? 1 : 0;

		for (int i = n - 2; i >= 0; i--) {
			char c = chars[i];
			if (c == '.') {
				suffix[i] = suffix[i + 1] + numberOfX * 1;
			} else {
				suffix[i] = suffix[i + 1];
				numberOfX++;
			}
			suffix[i] %= MOD;
		}

		for (int i = 0; i < n - 1; i++) {
			jumps = Math.min(jumps, (prefix[i] % MOD + suffix[i + 1] % MOD) % MOD);
		}

		return (int) jumps % MOD;
	}

	public static void main(String[] args) {
		Seats obj = new Seats();
		System.out.println(obj.seats("xxx."));
	}
}
