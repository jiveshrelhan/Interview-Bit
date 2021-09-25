package com.dp.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * Average depends upon: Sum and number of elements in the set.
 * 
 * We can compute all sum possible along with the numbers used.
 * 
 *  s1 can be calculated by sum/numbers.
 *  
 *  s2 can be calculated by (total_sum - sum)/(total_numbers - numbers)
 *  
 *  if s1 and s2 are equals we found are solution.
 *  
 *  How do we provide the what element we have selected for sum
 *  
 *  May be can we store: Array of index used in the state  
 */
/*
 * Learning how to store chosedItems 
 */
public class EqualAveragePartition {

	Boolean memo[][][];

	private boolean isSolution(int currentSum, int size, int elementIndex, ArrayList<Integer> input,
			ArrayList<Integer> chosedItem) {
		if (currentSum == 0 && size == 0) {
			return true;
		}
		if (size == 0 || elementIndex == input.size()) {
			return false;
		}

		if (memo[currentSum][size][elementIndex] != null) {
			return memo[currentSum][size][elementIndex];
		}

		boolean caseChooseNumber = false, caseNotChooseNumber = false;

		if (input.get(elementIndex) <= currentSum) {
			chosedItem.add(elementIndex);
			caseChooseNumber = isSolution(currentSum - input.get(elementIndex), size - 1, elementIndex + 1, input,
					chosedItem);
			if (caseChooseNumber) {
				return true;
			}
			chosedItem.remove(chosedItem.size() - 1);
		}

		caseNotChooseNumber = isSolution(currentSum, size, elementIndex + 1, input, chosedItem);
		if (caseNotChooseNumber) {
			return true;
		}
		return memo[currentSum][size][elementIndex] = false;
	}

	private ArrayList<ArrayList<Integer>> getS1AndS2(ArrayList<Integer> input, ArrayList<Integer> s1) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> s1WithValues = new ArrayList<Integer>();
		ArrayList<Integer> s2WithValues = new ArrayList<Integer>();
		int s1Index = 0;
		for (int i = 0; i < input.size(); i++) {
			if (s1Index < s1.size() && s1.get(s1Index) == i) {
				s1WithValues.add(input.get(i));
				s1Index++;
			} else {
				s2WithValues.add(input.get(i));
			}
		}

		ans.add(s1WithValues);
		ans.add(s2WithValues);

		return ans;
	}

	public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> input) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		Collections.sort(input);
		int n = input.size();
		int sum = 0;

		for (int x : input) {
			sum += x;
		}
		memo = new Boolean[sum+1][n][n];
		
		for (int size = 1; size < n - 1; size++) {
			if ((sum * size) % n == 0) {
				int required = (sum * size) / n;
				ArrayList<Integer> s1 = new ArrayList<Integer>();
				if (isSolution(required, size, 0, input, s1)) {
					return getS1AndS2(input, s1);
				}

			}
		}
		return ans;
	}

	public static void main(String[] args) {
		EqualAveragePartition obj = new EqualAveragePartition();
		System.out.println(obj.avgset(new ArrayList<Integer>(Arrays.asList(13, 45, 17, 0, 34, 10, 38, 33, 32))));
	}

}
