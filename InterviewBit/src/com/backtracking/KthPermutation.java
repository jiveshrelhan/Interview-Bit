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
		
		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}
        Map<Integer, Long> facts = getFactorial(n);

		int positionFiled = 1;

		while (positionFiled <= n) {
			long factForSize = facts.get(n-positionFiled);
			int index = (int) ((k-1) / factForSize);
			int number = getAndUpdateQueue(queue, index);
			k = (int)(k-(index*factForSize));
			ans.append(number);
			positionFiled++;
		}
		return ans.toString();
	}

	public static void main(String[] args) {
		KthPermutation obj = new KthPermutation();
		System.out.println(obj.getPermutation(3, 3));
	}

}
