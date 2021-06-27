package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubArrayWithBNumbers {
	public int solve(ArrayList<Integer> A, int B) {
		int count = 0;
		int[] preFix = new int[A.size()];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < A.size(); i++) {
			int num = A.get(i);
			if (i == 0) {
				preFix[i] = num % 2 == 1 ? 1 : 0;
			} else if (num % 2 == 1) {
				preFix[i] = preFix[i - 1] + 1;
			} else {
				preFix[i] = preFix[i - 1];
			}
			int x = preFix[i];
			int required = x - B;
			if (x == B) {
				count++;
			}
			if (required >= 0 && map.containsKey(required)) {
				int startIndex = map.get(required);
				int endIndex = map.getOrDefault((required + 1),i);
				int diff = endIndex - startIndex;
				count += diff;
			}
			map.putIfAbsent(preFix[i], i);
		}

		/*
		 * for (int i = 0; i < preFix.length; i++) { int x = preFix[i]; int required = x
		 * - B; if (x == B) { count++; } if (required >= 0 && map.containsKey(required))
		 * { int startIndex = map.get(required); int endIndex =
		 * map.getOrDefault((required + 1), startIndex); int diff = endIndex -
		 * startIndex; count += diff; } }
		 */

		return count;
	}

	public static void main(String[] args) {
		SubArrayWithBNumbers obj = new SubArrayWithBNumbers();
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(4, 3, 2, 3, 4)), 2));
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(1, 3, 4, 5, 7, 8)), 2));
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9)), 3));
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(44,7,32,44, 35, 73,88)), 0));

	}
}
