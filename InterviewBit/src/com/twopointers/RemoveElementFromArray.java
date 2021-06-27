package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveElementFromArray {
	private int findNotElementX(ArrayList<Integer> A, int x, int end) {
		int i = end;
		while (i < A.size()) {
			if (!A.get(i).equals(x)) {
				return i;
			}
			i++;
		}
		return A.size();
	}

	private int findElementX(ArrayList<Integer> A, int x, int start) {
		int i = start;
		while (i < A.size()) {
			if (A.get(i).equals(x)) {
				return i;
			}
			i++;
		}
		return A.size();
	}

	public int removeElement(ArrayList<Integer> A, int x) {
		int n = A.size();
		if (n == 1 && A.get(0).equals(x)) {
			A.remove(n - 1);
			return 0;
		}
		int start = 0, end = 1;
		while (start < n) {
			start = findElementX(A, x, start);
			if(end<start) {
				end = start+1;
			}
			end = findNotElementX(A, x, end);
			if (start == n || end == n) {
				break;
			}
			int temp = A.get(start);
			A.set(start, A.get(end));
			A.set(end, temp);
			start++;
		}
		if (start < n && A.get(start).equals(x)) {
			A.subList(start, n).clear();
		}
		System.out.println(A);
		return A.size();
	}

	public static void main(String[] args) {
		RemoveElementFromArray obj = new RemoveElementFromArray();
		System.out.println(obj.removeElement(new ArrayList<>(Arrays.asList(3, 3, 0, 2, 1, 2, 1, 0, 0)), 0));
	}
}
