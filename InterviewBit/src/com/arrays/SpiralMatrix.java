package com.arrays;

import java.util.ArrayList;

public class SpiralMatrix {

	ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
	int n = 0;
	static int number = 0;

	private void fillRight(int iteration) {

		int i = iteration, j = iteration;
		while (j < n - iteration) {
			matrix.get(i).set(j, ++number);
			j++;
		}
	}

	private void fillDown(int iteration) {

		int i = iteration + 1, j = n - 1 - iteration;
		while (i < n - iteration) {
			matrix.get(i).set(j, ++number);
			i++;
		}
	}
	
	private void fillLeft(int iteration) {

		int i = n-1-iteration, j = n - 2 - iteration;
		while (j >= iteration) {
			matrix.get(i).set(j, ++number);
			j--;
		}
	}

	
	private void fillUp(int iteration) {

		int i = n-iteration-2, j = iteration;
		while (i > iteration) {
			matrix.get(i).set(j, ++number);
			i--;
		}
	}

	public ArrayList<ArrayList<Integer>> generateMatrix(int A) {
		number =0;
		for (int i = 0; i < A; i++) {
			matrix.add(new ArrayList<>());
			for (int j = 0; j < A; j++) {
				matrix.get(i).add(0);
			}
		}
		n = A;

		for (int i = 0; i <= A / 2; i++) {
			fillRight(i);
			fillDown(i);
			fillLeft(i);
			fillUp(i);
		}

		return matrix;
	}

	public static void main(String[] args) {

		SpiralMatrix obj = new SpiralMatrix();
		obj.generateMatrix(58);
		for (ArrayList<Integer> m : obj.matrix) {
			System.out.println(m);
		}
	}
}
