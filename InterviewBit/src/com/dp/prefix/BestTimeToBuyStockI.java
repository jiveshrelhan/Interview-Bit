package com.dp.prefix;

import java.util.List;

/*
 * Super easy algo:
 * If want to sell it today when is the best time to buy the stock ? 
 * when it was on least price before today.
 * 
 * maintain the minimum seen so far today.
 * 
 * O(n) + (1)
 */
public class BestTimeToBuyStockI {
	public int maxProfit(final List<Integer> A) {
		int ans = 0;
		if (A.isEmpty())
			return ans;
		int min = A.get(0);
		for (int i = 1; i < A.size(); i++) {
			int curr = A.get(i);
			if (curr < min) {
				min = curr;
			} else {
				ans = Math.max(curr - min, ans);
			}
		}
		return ans;
	}
}
