package com.arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicateInArray {

	public int repeatedNumber(final List<Integer> A) {

		Set<Integer> set = new HashSet<Integer>();

		for (int i : A) {
			if (set.contains(i)) {
				return i;
			} else {
				set.add(i);
			}
		}

		return -1;

	}

}
