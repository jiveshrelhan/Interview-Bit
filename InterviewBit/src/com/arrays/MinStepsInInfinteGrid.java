package com.arrays;

import java.util.Arrays;
import java.util.List;

public class MinStepsInInfinteGrid {

	private int findDistance(int x1, int y1, int x2, int y2) {

		/*
		 * Since we need to calculate only distance, We don't need to care either x1 is
		 * greater or x2. We can simply calculate the net distance.
		 */
		int netX = Math.abs(x1 - x2);
		int netY = Math.abs(y1 - y2);

		int count = 0;

		/*
		 * If We haven't reached to target continue...
		 */
		while (netX != 0 || netY != 0) {

			/*
			 * If we need to travel both in x's and y's direction and we will move
			 * diagonally Else in the direction in which we need to travel.
			 */
			if (netX > 0 && netY > 0) {
				netX--;
				netY--;
			} else if (netX > 0) {
				netX--;
			} else if (netY > 0) {
				netY--;
			}

			count++;
		}
		return count;
	}

	/**
	 * First I was thinking to use Euclidean distance but that approach will not
	 * work. ED calculates the exact distance we need only steps.
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public int coverPoints(List<Integer> A, List<Integer> B) {

		int totalSteps = 0;
		/*
		 * Driver caller function. It calculates distance between each points given in
		 * the input and add to totalSteps
		 */
		for (int i = 1; i < A.size(); i++) {
			totalSteps += findDistance(A.get(i - 1), B.get(i - 1), A.get(i), B.get(i));
		}

		return totalSteps;
	}

	public static void main(String[] args) {
		MinStepsInInfinteGrid obj = new MinStepsInInfinteGrid();
		int ans = obj.coverPoints(Arrays.asList(0, 1, 1), Arrays.asList(0, 1, 2));
		System.out.println(ans);
	}

}
