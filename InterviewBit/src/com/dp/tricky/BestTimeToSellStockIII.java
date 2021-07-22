package com.dp.tricky;

import java.util.Arrays;

/*
 * Pep-coding Video
 */
public class BestTimeToSellStockIII {
	public int maxProfit(final int[] A) {
		if (A.length < 2) {
			return 0;
		}
		int[] prefixDP = new int[A.length];
		int[] suffixDP = new int[A.length];
		int smallestBuyPriceFromLeft = A[0];
		int largestSellPriceFromRight = A[A.length - 1];

		for (int i = 1; i < A.length; i++) {
			if (smallestBuyPriceFromLeft > A[i]) {
				smallestBuyPriceFromLeft = A[i];
			}
			int profit = A[i] - smallestBuyPriceFromLeft;
			if (prefixDP[i - 1] < profit) {
				prefixDP[i] = profit;
			} else {
				prefixDP[i] = prefixDP[i - 1];
			}
		}
		System.out.println(Arrays.toString(prefixDP));
		for (int i = A.length - 2; i >= 0; i--) {
			if (largestSellPriceFromRight < A[i]) {
				largestSellPriceFromRight = A[i];
			}
			int profit = largestSellPriceFromRight - A[i];
			if (suffixDP[i + 1] < profit) {
				suffixDP[i] = profit;
			} else {
				suffixDP[i] = suffixDP[i + 1];
			}

		}
		System.out.println(Arrays.toString(suffixDP));

		int ans = 0;
		for (int i = 0; i < A.length; i++) {
			ans = Math.max(ans, prefixDP[i] + suffixDP[i]);
		}
		return ans;

	}

	public static void main(String[] args) {
		BestTimeToSellStockIII obj = new BestTimeToSellStockIII();
		System.out.println(obj.maxProfit(new int[] { 1, 2, 1, 2 }));
		System.out.println(obj.maxProfit(new int[] { 7, 2, 4, 8, 7 }));
	}
}
