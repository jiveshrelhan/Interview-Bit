package com.hashing;

import java.util.HashSet;

public class ColourfulNumber {

	public int colorful(int n) {
		HashSet<Long> products = new HashSet<>();
		String number = String.valueOf(n);
		/*
		 * Generate all subsequence. Algorithm to generate all subsequence: start from
		 * all index 'i' and consider all length possible from this index 'i'
		 */
		for (int startIndex = 0; startIndex < number.length(); startIndex++) {
			long product = 1;
			for (int length = startIndex; length < number.length(); length++) {
				//Optimized here, since we have already computed the product from (startIndex-length-1)
				// If we add another number to previous number then the product will be number*(oldProduct)
				//System.out.println(number.substring(startIndex,startIndex+length));
				int x = number.charAt(length) - '0';
				product *= x;
				if (products.contains(product)) {
					return 0;
				} else {
					products.add(product);
				}
			}
		}
		return 1;
	}

	public int colorfulAttemp1(int n) {
		HashSet<Long> differentProduct = new HashSet<>();
		String number = String.valueOf(n);
		/*
		 * Generate all subsequence. Algorithm to generate all subsequence: start from
		 * all index 'i' and consider all length possible from this index 'i'
		 */
		for (int startIndex = 0; startIndex < number.length(); startIndex++) {

			for (int length = 1; length <= number.length(); length++) {
				if (startIndex + length > number.length())
					break;
				String sequence = number.substring(startIndex, startIndex + length);
				long product = 1;
				// Extract individual digit from subsequence and calculate product.
				for (int i = 0; i < sequence.length(); i++) {
					product *= (int) sequence.charAt(i) - '0';
				}
				if (differentProduct.contains(product)) {
					return 0;
				} else {
					differentProduct.add(product);
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		ColourfulNumber obj = new ColourfulNumber();
		System.out.println(obj.colorful(123));
	}
}
