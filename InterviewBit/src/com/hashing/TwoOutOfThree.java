package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class TwoOutOfThree {
	/*
	 * I have indexed all inputs[] in the map. More of frequencies[] array
	 * Use to tree set to give ans in sorted order was clever.
	 */
	public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		Map<Integer, Set<Integer>> map = new TreeMap<>();
		for (int a : A) {
			Set<Integer> set = map.getOrDefault(a, new HashSet<>());
			set.add(0);
			map.put(a, set);
		}
		for (int b : B) {
			Set<Integer> set = map.getOrDefault(b, new HashSet<>());
			set.add(1);
			map.put(b, set);
		}
		for (int c : C) {
			Set<Integer> set = map.getOrDefault(c, new HashSet<>());
			set.add(2);
			map.put(c, set);
		}

		for (Entry<Integer, Set<Integer>> e : map.entrySet()) {
			if (e.getValue().size() >= 2) {
				ans.add(e.getKey());
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		TwoOutOfThree obj = new TwoOutOfThree();
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(56, 56, 34, 34, 72, 71)),
				new ArrayList<>(Arrays.asList(56, 56, 34, 34, 72, 71)),
				new ArrayList<>(Arrays.asList(56, 56, 34, 34, 72, 71))));
	}
}
