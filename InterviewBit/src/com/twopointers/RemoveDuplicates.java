package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveDuplicates {
	public int removeDuplicates(ArrayList<Integer> A) {
		if (A.size() <= 1) {
			return A.size();
		}
		int nonDuplicateElementIndex = 0;
		int j = 1, n = A.size();
		while (j < n) {
			if (A.get(j).equals(A.get(j - 1))) {
				j++;
			}
			else{
				A.set(++nonDuplicateElementIndex, A.get(j));
				j++;
			}
		}
		
		A.subList(nonDuplicateElementIndex, A.size()-1).clear();
	
		return nonDuplicateElementIndex + 1;
	}

	public int removeDuplicatesBruteForce(ArrayList<Integer> A) {
		int i = 1, n = A.size();
		while (i < A.size()) {
			if (A.get(i).equals(A.get(i - 1))) {
				A.remove(i);
				n--;
			} else {
				i++;
			}
		}
		return n;
	}

	public static void main(String[] args) {
		RemoveDuplicates obj = new RemoveDuplicates();
		System.out.println(obj.removeDuplicates(new ArrayList<>(Arrays.asList(1, 1,1,2,2,2,2))));
	}
}
