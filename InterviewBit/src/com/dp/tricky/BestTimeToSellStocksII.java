package com.dp.tricky;

/*-
 * We can perform B transactions which mean buy and sell can be done at most B times.
 * 
 * Rule is buy on smaller price and sell on higher price. If there is no days left which satisfy cond.
 * don't execute transaction.
 *
 * Since there can be many pairs where buy amount is lesser than selling price. How to choose the best among those?
 * 
 * If only 1 transaction is allowed. check where max profit is there. Since here B transactions are allowed
 * Greedy will not work. Use DP to try all different sets and return the best. 
 */
public class BestTimeToSellStocksII {

	// GreedyMethod to get max profit when one transaction is allowed.
	@SuppressWarnings("unused")
	private int[] helper(int buyId, int[] price) {
		int maxProfit = 0;
		int sellDay = -1;
		for (int i = buyId + 1; i < price.length; i++) {
			int currentProfit = price[i] - price[buyId];
			if (currentProfit >= 0 && currentProfit >= maxProfit) {
				maxProfit = currentProfit;
				sellDay = i;
			}
		}

		return new int[] { maxProfit, sellDay };
	}

	/*- 
	 * There can be slight optimization in the dp version of it.
	 * O(n3) can be reduced to O(n2)
	 * https://www.youtube.com/watch?v=3YILP-PdEJA
	 * Pepcoding
	 */
	class DP {

		private int solveWithInfinteTransactions(int[] price) {
			int profit = 0;
			for (int i = 1; i < price.length; i++) {
				if (price[i] > price[i - 1]) {
					profit += price[i] - price[i - 1];
				}
			}
			return profit;
		}

		public int solve(int[] price, int k) {
			if (k == 0 || price.length == 0) {
				return 0;
			}
			if (k >= price.length / 2) {
				return solveWithInfinteTransactions(price);
			}
			int[][] dp = new int[k + 1][price.length];
			for (int tran = 1; tran < dp.length; tran++) {
				for (int stock = 1; stock < dp[0].length; stock++) {
					int max = dp[tran][stock - 1];
					for (int prev_day = 0; prev_day < stock; prev_day++) {
						max = Math.max(dp[tran - 1][prev_day] + price[stock] - price[prev_day], max);
					}
					dp[tran][stock] = max;
				}
			}
			return dp[k][price.length - 1];
		}
	}

}
