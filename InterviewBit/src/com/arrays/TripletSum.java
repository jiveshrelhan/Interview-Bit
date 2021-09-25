package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TripletSum {

	public int solve(ArrayList<Integer> A) {
		int n = A.size();
		int max[] = new int[n + 1];
		max[n] = 0;
		for (int i = n - 1; i >= 0; i--) {
			max[i] = Math.max(max[i + 1], A.get(i));
		}
		TreeSet<Integer> set = new TreeSet<>();
		set.add(Integer.MIN_VALUE);
		int ans = 0;
		for (int i = 0; i < n; i++) {
			int number = A.get(i);
			if (number < max[i + 1]) {
				ans = Math.max(ans, set.lower(number) + number + max[i + 1]);
			}
			set.add(number);
		}
		return ans;

	}

	public static void main(String[] args) {
		TripletSum obj = new TripletSum();
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(2, 5, 3, 1, 4, 9))));
	}

}
