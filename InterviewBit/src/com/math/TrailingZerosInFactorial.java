package com.math;

public class TrailingZerosInFactorial {

	/*
	 * Author : 
	 * Simple basic maths.
	 * Factorial is product of number from 1 to N.
	 * No. of trailing zeroes depends upon how many 5's and 2's are present in 1 to N
	 * since 5 * 2 = 10.
	 * To find how many 5s are there simply do: N / 5;
	 * There is one catch in above formula: Suppose N is 25
	 * 25/5 = 5 which are 5, 10 (5*2) , 15 (5*3) , 20 (5*4)  ,25 (5(5)
	 * but in last 25(5*5) there is extra 5 we need to count this as well.
	 * so answer is N/5  +  N/25 = 6.
	 * 
	 * General equation is to calculate for N(power of 5) till power is smaller than 5. 
	 * like for N =25, we don't need to calculate N/125 anyways it is going to give 0.
	 * 
	 * Anther observation, we don't need to calculate Number of 2's since no of 2's 
	 * will be always greater then no's of 5.
	 * Therefore calculating no of 5 is enough to know no. of trailing zeros. 
	 */
	public int trailingZeroes(int a) {
		int p = 1, curentPow = 1, ans = 0;
		while (a > curentPow) {
			curentPow = (int) Math.pow(5, p++);
			ans += a / curentPow;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		TrailingZerosInFactorial obj = new TrailingZerosInFactorial();
		System.out.println(obj.trailingZeroes(26));
	}
}
