package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;

public class SortByColors {

	public void sortColors(ArrayList<Integer> a) {
		int low = 0, mid = 0, high = a.size()-1;

		while (mid <= high) {
			int val = a.get(mid);
			if (val == 0) {
				swap(a, low, mid);
				low++;
				mid++;
			} else if (val == 1) {
				mid++;
			} else {
				swap(a, mid, high);
				high--;
			}
		}
	}

	private void swap(ArrayList<Integer> a, int x, int y) {
		int temp = a.get(x);
		a.set(x, a.get(y));
		a.set(y, temp);
	}

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 2, 0));
		SortByColors obj = new SortByColors();
		obj.sortColors(a);
		System.out.println(a);
	}
}
