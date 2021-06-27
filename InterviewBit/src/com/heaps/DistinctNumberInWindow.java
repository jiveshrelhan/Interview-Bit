package com.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DistinctNumberInWindow {
	public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
		ArrayList<Integer> ans = new ArrayList<Integer>();

		// Preparation
		HashMap<Integer, Integer> freqMap = new HashMap<>();
		for (int i = 0; i < B; i++) {
			int x = A.get(i);
			freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
		}

		ans.add(freqMap.size());

		for (int i = B; i < A.size(); i++) {

			int toAdd = A.get(i);

			freqMap.put(toAdd, freqMap.getOrDefault(toAdd, 0) + 1);

			int toRemove = A.get(i - B);

			if (freqMap.get(toRemove) == 1) {
				freqMap.remove(toRemove);
			} else {
				freqMap.put(toRemove, freqMap.get(toRemove) - 1);
			}

			ans.add(freqMap.size());

		}

		return ans;
	}
	
	public static void main(String[] args) {
		DistinctNumberInWindow obj = new DistinctNumberInWindow();
		System.out.println(obj.dNums(new ArrayList<>(Arrays.asList(1, 1, 2, 2)), 1));
	}
}
