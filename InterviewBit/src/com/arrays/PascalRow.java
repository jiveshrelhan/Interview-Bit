package com.arrays;

import java.util.ArrayList;

/*-
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 */
public class PascalRow {
	public ArrayList<Integer> getRow(int K) {

		// Switching to non zero index
		K += 1;

		ArrayList<Integer> ans = new ArrayList<>();

		ans.add(1);

		if (K == 1)
			return ans;

		ans.add(1);

		ArrayList<Integer> prevRow = new ArrayList<Integer>(ans);

		ArrayList<Integer> currentRow = new ArrayList<Integer>();

		int curr = 0;

		while (curr <= K - 2) {

			currentRow.add(1);

			for (int i = 1; i < prevRow.size(); i++) {
				int index = i;
				if (i >= prevRow.size()) {
					index = i - prevRow.size() + 1;
				}
				currentRow.add(prevRow.get(index) + prevRow.get(index - 1));
			}
			curr++;
			System.out.println(currentRow);
			prevRow = currentRow;
			currentRow = new ArrayList<Integer>();
		}
		ans = new ArrayList<>(prevRow);

		if (K % 2 == 1) {

			for (int i = prevRow.size() - 1; i >= 0; i--) {
				ans.add(prevRow.get(i));
			}

		} else {
			for (int i = prevRow.size() - 2; i >= 0; i--) {
				ans.add(prevRow.get(i));
			}
		}

		return ans;

	}

	public ArrayList<Integer> prevRow(int K) {
		K += 1;
		ArrayList<Integer> prevRow = new ArrayList<Integer>();
		prevRow.add(1);

		for (int i = 1; i <= K; i++) {
			ArrayList<Integer> currentRow = new ArrayList<Integer>();

			for (int j = 0; j < i; j++) {
				int x = j >= 0 && j < prevRow.size() ? prevRow.get(j) : 0;
				int y = j - 1 >= 0 && j-1 < prevRow.size() ? prevRow.get(j - 1) : 0;
				currentRow.add(x + y);
			}

			prevRow = currentRow;
		}
		return prevRow;
	}

	public static void main(String[] args) {
		PascalRow obj = new PascalRow();
		ArrayList<Integer> ans = obj.prevRow(4);
		System.out.println(ans);
	}

}
