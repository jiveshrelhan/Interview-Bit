package com.dp.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * This is super ugly numbers question
 */
public class SmallestSequenceWithPrimeFactors {

	/*
	 * Thought we need to handle duplicates: like : 2*2*2 = 6 and 3*2 = 6 but no
	 * author want unique sequence
	 */
	class WrongPerception {
		public int[] solve(int A, int B, int C, int D) {
			int[] ans = new int[D];

			TreeMap<Integer, Integer> sortedFreqMap = new TreeMap<>();
			sortedFreqMap.compute(A, (k, v) -> (v == null ? 1 : v + 1));
			sortedFreqMap.compute(B, (k, v) -> (v == null ? 1 : v + 1));
			sortedFreqMap.compute(C, (k, v) -> (v == null ? 1 : v + 1));

			int numbersFound = 0;

			while (numbersFound < D) {
				Entry<Integer, Integer> minimum = sortedFreqMap.firstEntry();
				if (minimum.getValue() > 1) {
					sortedFreqMap.put(minimum.getKey(), minimum.getValue() - 1);
				} else {
					sortedFreqMap.remove(minimum.getKey());
				}
				ans[numbersFound] = minimum.getKey();
				sortedFreqMap.compute(A * minimum.getKey(), (k, v) -> (v == null ? 1 : v + 1));
				sortedFreqMap.compute(B * minimum.getKey(), (k, v) -> (v == null ? 1 : v + 1));
				sortedFreqMap.compute(C * minimum.getKey(), (k, v) -> (v == null ? 1 : v + 1));
				numbersFound++;
			}

			return ans;
		}
	}

	class QuickSolutionWithoutDP {
		public int[] solve(int A, int B, int C, int D) {
			int[] ans = new int[D];

			TreeSet<Integer> set = new TreeSet<>();
			set.add(A);
			set.add(B);
			set.add(C);

			int numbersFound = 0;

			while (numbersFound < D) {
				int minimum = set.pollFirst();

				ans[numbersFound] = minimum;
				set.add(A * minimum);
				set.add(B * minimum);
				set.add(C * minimum);
				numbersFound++;
			}

			return ans;
		}
	}

	/*-
	 * Transitions : DP[i] = Minimum of (DP[i-1]*A , DP[i-1]*B,DP[i-1]*C,B,C);
	 */
	/*- EXTREME HARD QUESTION : More of trick. Need to cram.
	 * 
	 */
	class DP {
		private int getMinimumIndex(List<Integer> list) {
			int minIndex = 0;
			for (int i = 1; i < list.size(); i++) {
				if (list.get(i) < list.get(minIndex)) {
					minIndex = i;
				}
			}
			return minIndex;
		}

		public int[] solve(int A, int B, int C, int D) {
			int[] dp = new int[D];
			List<Integer> numbers = new ArrayList<>();
			numbers.add(A);
			numbers.add(B);
			numbers.add(C);
			int index[] = { 0, 0, 0 };
			int input[] = { A, B, C };
			int numberFound = 0;
			while (numberFound < D) {
				int minimumIndex = getMinimumIndex(numbers);
				if (numberFound == 0 || dp[numberFound - 1] != numbers.get(minimumIndex)) {
					dp[numberFound] = numbers.get(minimumIndex);
					numberFound++;
				}
				numbers.set(minimumIndex, dp[index[minimumIndex]] * input[minimumIndex]);
				index[minimumIndex] += 1;
			}
			return dp;
		}
	}

	public static void main(String[] args) {
		SmallestSequenceWithPrimeFactors obj = new SmallestSequenceWithPrimeFactors();
		System.out.println(Arrays.toString(obj.new QuickSolutionWithoutDP().solve(3, 11, 7, 50)));
		System.out.println(Arrays.toString(obj.new DP().solve(2, 5, 7, 50)));
	}
}
