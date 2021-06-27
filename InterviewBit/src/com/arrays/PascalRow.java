package com.arrays;

import java.util.ArrayList;

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
					index = i -  prevRow.size() + 1;
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

	public static void main(String[] args) {
		PascalRow obj = new PascalRow();
		ArrayList<Integer> ans = obj.getRow(3);
		System.out.println(ans);
	}

}
