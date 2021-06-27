package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PairsWithGivenXOR {
	/*
	 * Question is all about to property:
	 * A XOR B = C === A XOR C = B
	 * Also, Since we need to solve this in O(n), then there should be direct relation 
	 * between the target and current number to check the answer directly in hashing.
	 * Otherwise the complexity is going to be O(n2) to check all pairs XOR.
	 */
	public int solve(ArrayList<Integer> A, int k) {
		int pairs = 0;
		Set<Integer> set = new HashSet<Integer>();
		for (int x : A) {
			int toFind = x ^ k;
			if (set.contains(toFind)) {
				//System.out.println("A ^ B " + x + " " + toFind);
				pairs++;
			}
			set.add(x);
		}

		return pairs;
	}

	public static void main(String[] args) {
		PairsWithGivenXOR obj = new PairsWithGivenXOR();
		System.out.println(obj.solve(new ArrayList<Integer>(Arrays.asList(3, 6, 8, 10, 15, 50)), 5));
	}
}
