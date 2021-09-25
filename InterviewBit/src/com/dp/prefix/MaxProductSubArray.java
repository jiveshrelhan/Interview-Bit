package com.dp.prefix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Cannot solved it using hint as well.
 * 
 * https://www.youtube.com/watch?v=lXVy6YWFcRM
 * 
 * O(n) + O(1) Solution
 */
public class MaxProductSubArray {

	/*-
	 * Algorithm :
	 * Everything is fine, if all numbers are positive.
	 * If [] contains -ive, 
	 * either it can make the overall product -ive
	 * OR it might help to make product positive if the previous product is -ive
	 * 
	 * So we have to maintain maximum product and minimum product at each i.
	 * 
	 * We can use 2 dp[] one to store maximum and minimum but its not required since next value is
	 * dependent upon dp[i-1] therefore 2 variable preMax and preMin are enough.
	 *
	 *We can handle 0 specifically, best thing is to remove the 0. we can do that by making prevMax and PrevMin to 1
	 *in that way next element will think previous element was 1 instead 0 and by multiplying by 1 will also not have
	 *any impact on further products.
	 */

	public int maxProduct(final List<Integer> A) {
		int max = Collections.max(A);
		int prevMax = 1, prevMin = 1;
		for (int x : A) {
			if (x == 0) {
				prevMax = 1;
				prevMin = 1;
			} else {
				int temp = prevMax;
				prevMax = Math.max(prevMax * x, Math.max(prevMin * x, x));
				prevMin = Math.min(temp * x, Math.min(prevMin * x, x));
				/*
				 * we can't simply return DP[n] We need to update our final answer every time
				 * because of 0. the best answer can be before 0 arrived.
				 */
				max = Math.max(max, Math.max(prevMax, prevMin));
			}
		}
		return max;
	}

	public static void main(String[] args) {
		MaxProductSubArray obj = new MaxProductSubArray();
		System.out.println(obj.maxProduct(Arrays.asList()));
	}
}
