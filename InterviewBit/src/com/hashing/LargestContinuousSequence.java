package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LargestContinuousSequence {
	/*
	 * Algorithm: Using hint1 from interview bit Is Sum[0,i] = x and Sum[0,j] = x
	 * where j>i then this implies from i+1 to j there is no gain in sum in other
	 * words there sum is zero.
	 * 
	 * Algo: Maintain hashmap which stores sum and there end index since startIndex
	 * is always 0. Ex Sum(0,j) sum function always starts from 0.
	 * 
	 * Compute perfix sum say x, if you see this x before this (look into hashmap)
	 * ans is previous x position +1 till current index
	 * 
	 * Keep maintain the largest sub array by checking there length
	 * endIndex-startIndex + 1;
	 * 
	 * CORNERCASE : we need to put 0,-1 in hash map. Since if whole[] is 0 or First
	 * N digit is 0 we need another previous reference point where we saw 0. The
	 * real place where we saw 0 when nothing exists i.e -1. so as per the logic
	 * startIndex -1 + 1 = 0 which is start of []
	 * 
	 */
	public ArrayList<Integer> lszero(ArrayList<Integer> A) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<>();
		/*
		 * Corner case: 1,-1,1,-1 whole [] is 0. or First n is 0. So we have to imagine
		 * 0 exists before actual array too.
		 */
		map.put(0, -1);
		int[] index = new int[2];
		int currentSum = 0, maxSofar = 0;
		for (int currentIndex = 0; currentIndex < A.size(); currentIndex++) {
			currentSum += A.get(currentIndex);
			if (map.containsKey(currentSum)) {
				int startIndex = map.get(currentSum) + 1;
				int endIndex = currentIndex;
				int length = endIndex - startIndex + 1;
				if (length > maxSofar) {
					maxSofar = length;
					index[0] = startIndex;
					index[1] = endIndex;
				}
			} else {
				map.put(currentSum, currentIndex);
			}
		}
		if(maxSofar == 0) {
			return ans;
		}
		for (int i = index[0]; i <= index[1]; i++) {
			ans.add(A.get(i));
		}
		return ans;
	}

	public ArrayList<Integer> lszeroBruteForce(ArrayList<Integer> A) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int[] index = new int[2];
		int currentLength = 0;
		int currentSum = 0;
		boolean solutionFound = false;
		int currentIndex = 0;
		int startIndex = 0;

		for (int i = 0; i < A.size(); i++) {
			currentIndex = i;
			currentSum = 0;
			startIndex = i;
			while (currentIndex < A.size()) {
				currentSum += A.get(currentIndex);
				if (currentSum == 0) {
					if (currentIndex - startIndex + 1 > currentLength) {
						currentLength = currentIndex - startIndex + 1;
						index[0] = startIndex;
						index[1] = currentIndex;
						solutionFound = true;
					}
				}
				currentIndex++;
			}
		}

		for (int i = index[0]; i <= index[1] && solutionFound; i++) {
			ans.add(A.get(i));
		}
		return ans;
	}

	public static void main(String[] args) {
		LargestContinuousSequence obj = new LargestContinuousSequence();
		System.out.println(obj.lszero(new ArrayList<Integer>(Arrays.asList(0,0,0))));
	}
}
