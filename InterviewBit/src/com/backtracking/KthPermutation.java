package com.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KthPermutation {

	private Map<Integer, Long> getFactorial(int n) {
		Map<Integer, Long> map = new HashMap<>();
		map.put(0, 1L);
		long fact = 1;
		for (int i = 1; i <= n; i++) {
			if (n > 12)
				fact = Integer.MAX_VALUE;
			else
				fact *= i;
			map.put(i, fact);
		}
		return map;
	}

	private int getAndUpdateQueue(ArrayList<Integer> queue, int index) {
		int number = queue.get(index);
		queue.remove(index);
		return number;
	}

	public String getPermutation(int n, int k) {
		StringBuilder ans = new StringBuilder();
		ArrayList<Integer> queue = new ArrayList<>();
		k--;
		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}
		Map<Integer, Long> facts = getFactorial(n);
		int positionFiled = 0;

		while (positionFiled < n) {
			if (k == 0) {
				break;
			}
			long factForSize = facts.get(queue.size() - 1);
			int index = (int) (k / factForSize);
			int number = getAndUpdateQueue(queue, index);
			k %= factForSize;
			ans.append(number);
			positionFiled++;
		}
		queue.forEach(x -> ans.append(x));
		/*
		 * queue.stream().forEach(x -> { ans.append(x); });
		 */

		return ans.toString();
	}

	public static void main(String[] args) {
		KthPermutation obj = new KthPermutation();
		System.out.println(obj.getPermutation(4, 7));
	}

}
