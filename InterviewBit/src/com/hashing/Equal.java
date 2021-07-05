package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Equal {
		public ArrayList<Integer> twoSum(ArrayList<Integer> A, int start,int b, int x) {
			ArrayList<Integer> ans = new ArrayList<>();
			// Requirement of question, need to return the solution having smallest B1 OR D1 in [].
			int minC = Integer.MAX_VALUE;
			if (A.isEmpty()) {
				return ans;
			}
			HashMap<Integer, Integer> map = new HashMap<>();
			for (int i = start; i < A.size(); i++) {
				if(i==b)
					continue;
				int a = A.get(i);
				int required = x - a;
				if (map.containsKey(required)) {
					int c = map.get(required);
					/*
					 *  Always replacing at array list. All the time we will have only one answer in the list.
					 *	Since we are updating the list at index 0 and 1.
					 */
					if (c < minC) {
						ans.add(0, c);
						ans.add(1, i);
						minC = c;
					}
				}
				map.putIfAbsent(a, i);
			}
	
			return ans;
	
		}
	
		public ArrayList<Integer> fourSum(ArrayList<Integer> input) {
			ArrayList<Integer> result = new ArrayList<>();
			/*
			 * Sorting not required, since index of original input matters to return the answer.
			 */
			// Collections.sort(input);
			for (int i = 0; i < input.size() - 3; i++) {
				for (int j = i + 1; j < input.size() - 2; j++) {
					int a = input.get(i);
					int b = input.get(j);
					int remaining = a + b;
					ArrayList<Integer> ans = twoSum(input, i + 1,j, remaining);
					// Return the first smallest answer we got.
					if (!ans.isEmpty()) {
						result.add(i);
						result.add(j);
						result.add(ans.get(0));
						result.add(ans.get(1));
						return result;
					}
				}
			}
	
			return result;
		}

	public ArrayList<Integer> equal(ArrayList<Integer> A) {
		return fourSum(A);
	}

	public static void main(String[] args) {
		Equal obj = new Equal();
		System.out.println(obj.equal(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1))));
	}
}
