	package com.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KLargestElements {
	public ArrayList<Integer> solve(ArrayList<Integer> A, int B) {
		ArrayList<Integer> ans = new ArrayList<>();
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < A.size(); i++) {
			if (queue.size() > B) {
				queue.poll();
			}
			queue.add(A.get(i));
		}

		while (ans.size() < B) {
			ans.add(queue.poll());
		}
		// We can return in any order otherwise need to reverse the list.
		return ans;
	}

	public ArrayList<Integer> solveNotOptmizied(ArrayList<Integer> A, int B) {
		ArrayList<Integer> ans = new ArrayList<>();
		PriorityQueue<Integer> queue = new PriorityQueue<>(A.size(), new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {

				return o2 - o1;
			}
		});
		for (int i = 0; i < A.size(); i++) {
			queue.add(A.get(i));
		}

		while (ans.size() < B) {
			ans.add(queue.poll());
		}

		return ans;
	}
}
