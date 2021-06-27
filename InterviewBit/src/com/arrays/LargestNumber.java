package com.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {

	// DO NOT MODIFY THE LIST. IT IS READ ONLY
	public String largestNumber(final List<Integer> a) {

		String[] arr = new String[a.size()];
		for (int i = 0; i < a.size(); i++) {
			arr[i] = String.valueOf(a.get(i));
		}

		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String a, String b) {
				return (b + a).compareTo(a + b);
			}
		});

		StringBuilder sb = new StringBuilder();
		for (String s : arr) {
			sb.append(s);
		}

		if (sb.charAt(0) == '0') {
			return String.valueOf(0);
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		LargestNumber obj = new LargestNumber();
		System.out.println(obj.largestNumber(Arrays.asList(9, 99, 999, 9999, 9998)));
	}
}
