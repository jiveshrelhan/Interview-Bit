package com.heaps;

public class WayToFormMaxHeapAuthor {
	public int solve(int A) {
		long[][] combs = getCombs(A);
		return (int) (helper(A, combs) % 1000000007);
	}

	private long helper(int A, long[][] combs) {
		if (A <= 1) {
			return 1;
		}
		int n = A;
		int h = 0;
		while (A > 1) {
			h++;
			A /= 2;
		}
		int m = (int) Math.pow(2, h);
		int p = n - (m - 1);
		// L = 2h - 1 if p >= m/2
		// = 2h - 1 - (m/2 - p) if p<(m/2)
		int L = 0;
		if (p >= m / 2) {
			L = m - 1;
		} else {
			L = m - 1 - (m / 2 - p);
		}
		System.out.println(L);
		// System.out.println("m: " + m + " p: " + p + " L: " + L);
		return (((combs[n - 1][L] * helper(L, combs)) % 1000000007 * helper(n - 1 - L, combs) % 1000000007));
	}

	private long[][] getCombs(int A) {
		long[][] combs = new long[A + 1][A + 1];
		for (int n = 1; n <= A; n++) {
			for (int r = 0; r <= n; r++) {
				if (n == r || r == 0) {
					combs[n][r] = 1;
				} else {
					combs[n][r] = (combs[n - 1][r] + combs[n - 1][r - 1]) % 1000000007;
				}
			}
		}
		return combs;
	}
	
	public static void main(String[] args) {
		WayToFormMaxHeapAuthor obj = new WayToFormMaxHeapAuthor();
		System.out.println(obj.solve(100));
	}
}
