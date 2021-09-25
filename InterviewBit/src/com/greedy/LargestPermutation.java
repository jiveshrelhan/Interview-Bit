package com.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class LargestPermutation {
	class BruteForce {
		public int[] solve(int[] A, int B) {
			int i = 0, k = 0;
			while (i < B && k < A.length) {
				int max = A[k];
				int index = k;
				for (int j = k; j < A.length; j++) {
					if (A[j] > max) {
						max = A[j];
						index = j;
					}
				}
				if (index != k) {
					int temp = A[k];
					A[k] = A[index];
					A[index] = temp;
					i++;
				}
				k++;
			}
			return A;
		}
	}

	static class Pair {
		int index;
		int value;

		Pair(int index, int value) {
			this.index = index;
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	class VeryNearSolution {

		public int[] solve(int[] A, int B) {
			int n = A.length;
			Pair[] pairs = new Pair[n];
			for (int i = 0; i < n; i++) {
				pairs[i] = new Pair(i, A[i]);
			}
			Comparator<Pair> comp = Comparator.comparing(Pair::getValue, Comparator.reverseOrder());
			Arrays.sort(pairs, comp);
			for (int i = 0; i < n && B > 0; i++) {
				Pair p = pairs[i];
				if (p.value != A[i]) {
					int temp = A[i];
					A[i] = A[p.index];
					A[p.index] = temp;
					B--;
				}
			}
			return A;
		}
	}

	class AuthorSolution {
		public int[] solve(int[] A, int B) {
			int n = A.length;
			int valueToIndex[] = new int[n + 1];

			for (int i = 0; i < n; i++) {
				valueToIndex[A[i]] = i;
			}

			for (int i = 0, k = 0; i < n && k < B; i++) {

				if (A[i] != n - i) {
					int originalIndexOfMax = valueToIndex[n - i];

					valueToIndex[n - i] = i;
					valueToIndex[A[i]] = originalIndexOfMax;

					int maxValue = A[originalIndexOfMax];
					A[originalIndexOfMax] = A[i];
					A[i] = maxValue;
					k++;
				}
			}
			return A;
		}
	}

	public static void main(String[] args) {
		LargestPermutation obj = new LargestPermutation();
		System.out.println(Arrays.toString(obj.new BruteForce().solve(new int[] { 10, 9, 8, 7, 6, 4, 5, 2, 1, 3 }, 2)));
		System.out.println(Arrays.toString(obj.new VeryNearSolution().solve(new int[] { 3, 2, 4, 1, 5 }, 5)));
		System.out.println(Arrays.toString(obj.new AuthorSolution().solve(new int[] { 3, 2, 4, 1, 5 }, 5)));
	}
}
