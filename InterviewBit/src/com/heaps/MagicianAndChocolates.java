package com.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MagicianAndChocolates {
	public int nchoc(int B, ArrayList<Integer> A) {
		long mod = (long) 1e9 + 7;
		Collections.sort(A, Collections.reverseOrder());
		PriorityQueue<Long> queue = new PriorityQueue<Long>(Collections.reverseOrder());
		long totalCost = 0, accomodated = 0;

		for (int a : A)
			queue.add((long) a);

		while (accomodated < B && !queue.isEmpty()) {
			long cost = queue.poll();
			totalCost += cost;
			queue.add((long) Math.floor(cost / 2));
			accomodated++;
		}

		return (int) (totalCost % mod);
	}

	public static void main(String[] args) {
		MagicianAndChocolates obj = new MagicianAndChocolates();
		System.out.println(obj.nchoc(10, new ArrayList<Integer>(Arrays.asList(2147483647, 2000000014, 2147483647))));
	}
}
