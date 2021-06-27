package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveDuplicates2 {
	private int getWindowSize(ArrayList<Integer> a, int start) {
		if (start >= a.size())
			return a.size();
		int i = start + 1;
		int value = a.get(start);

		while (i < a.size() && a.get(i).equals(value)) {
			i++;
		}

		return i;
	}

	public int removeDuplicates(ArrayList<Integer> a) {
		if(a.size() < 2) {
			return a.size();
		}
		int freePointer = 0, start = 0, end = 0, n = a.size();
		while (end < n) {
			end = getWindowSize(a, start);
			int size = end - start;
			if (size > 2)
				size = 2;
			for (int i = 0; i < size; i++) {
				a.set(freePointer, a.get(start));
				freePointer++;
			}
			start = end;
		}
		a.subList(freePointer, a.size()).clear();
		System.out.println(a);
		return a.size();

	}

	public static void main(String[] args) {
		RemoveDuplicates2 obj = new RemoveDuplicates2();
		System.out.println(obj.removeDuplicates(new ArrayList<>(Arrays.asList(1,1,1))));
	}
}
