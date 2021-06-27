package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Sum2 {

	/*
	 * Algorithm is easy. Only important point is to use putIfAbsent since we need
	 * to give lowest index1 as per the question.
	 */
	public ArrayList<Integer> twoSum(final List<Integer> A, int x) {
		ArrayList<Integer> ans = new ArrayList<>();
		if (A.isEmpty()) {
			return ans;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(A.get(0), 0);
		for (int i = 1; i < A.size(); i++) {
			int a = A.get(i);
			int required = x - a;
			if (map.containsKey(required)) {
				ans.add(map.get(required) + 1);
				ans.add(i + 1);
				return ans;
			}
			map.putIfAbsent(a, i);
		}

		return ans;

	}

	public static void main(String[] args) {
		Sum2 obj = new Sum2();
		System.out.println(obj.twoSum(Arrays.asList(2, 7, 11, 15), 9));
	}
}
