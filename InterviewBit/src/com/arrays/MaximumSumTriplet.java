package com.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumSumTriplet {

	private PriorityQueue<Integer> buildPQ(List<Integer> input) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		for (int i = 0; i < input.size(); i++) {
			queue.add(input.get(i));
		}

		return queue;
	}

	public int solve(List<Integer> A) {

		PriorityQueue<Integer> queue = buildPQ(A);

		int max = 0;

		for (int i = 0; i < A.size()-2; i++) {
			int curr = A.get(i);
			queue.remove(curr);
			int nextLarger = queue.poll();
			int nextToNext = queue.peek();
			if (nextLarger <= curr || nextLarger == curr + 1 || nextToNext <= curr ) {
				queue.add(nextLarger);
				continue;
			}


			int sum = curr + nextLarger + nextToNext;

			max = Math.max(sum, max);

			queue.add(nextLarger);

		}

		return max;

	}
	
	public static void main(String[] args) {
		MaximumSumTriplet obj = new MaximumSumTriplet();
		int ans = obj.solve(Arrays.asList(3,2));
		System.out.println(ans);
	}
}
