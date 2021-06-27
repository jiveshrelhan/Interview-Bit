package com.heaps;

import java.util.HashMap;

public class WaysToFormMaxHeap {

	long mod = (long) 1e9 + 7;
	long combs[][];
	HashMap<Integer, Long> functionMap = new HashMap<>();

	private int getLeftChildren(int A) {
		int h = 0, n = A;
		while (A > 1) {
			h++;
			A /= 2;
		}
		int m = (int) Math.pow(2, h);
		int p = n - (m - 1);

		return p >= (m / 2) ? m - 1 : m - 1 - ((m / 2) - p);
	}

	// F(1) = 1;
	// F(N) = (N-1)C(left) * F(left) * F(right)
	private long recursionHelper(int A) {

		if (A <= 1) {
			return 1;
		}
		if (functionMap.containsKey(A)) {
			return functionMap.get(A);
		}
		int left = getLeftChildren(A);
		int right = A - left - 1;
		functionMap.put(A,
				(((binomialDiff(A - 1, left) % mod * recursionHelper(left) % mod) % mod) * recursionHelper(right) % mod)
						% mod);

		return functionMap.get(A);
	}

	private long binomialDiff(int n, int r) {
		return combs[n][r];
	}

	private long[][] getCombs(int A) {
		long[][] combs = new long[A + 1][A + 1];
		for (int n = 1; n <= A; n++) {
			for (int r = 0; r <= n; r++) {
				if (n == r || r == 0) {
					combs[n][r] = 1;
				} else {
					combs[n][r] = (combs[n - 1][r] + combs[n - 1][r - 1]) % mod;
				}
			}
		}
		return combs;
	}

	@SuppressWarnings("unused")
	private long binomial(int n, int r) {
		long C[][] = new long[n + 1][r + 1];
		int i, j;

		// Calculate value of Binomial
		// Coefficient in bottom up manner
		for (i = 0; i <= n; i++) {
			for (j = 0; j <= Math.min(i, r); j++) {
				// Base Cases
				if (j == 0 || j == i)
					C[i][j] = 1;

				// Calculate value using
				// previously stored values
				else
					C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
			}
		}

		return C[n][r];
	}

	public int solve(int A) {
		combs = getCombs(A);
		return (int) (recursionHelper(A) % mod);
	}

	public static void main(String[] args) {
		WaysToFormMaxHeap obj = new WaysToFormMaxHeap();
		System.out.println(obj.solve(100));
	}
}
