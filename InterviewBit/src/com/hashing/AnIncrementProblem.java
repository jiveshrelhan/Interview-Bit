package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnIncrementProblem {

	/*
	 * Not completely optimized
	 */
	/*
	 * Algo: Maintain map of number and their index of occurrences. if no doesn't
	 * exists in map; then add no+1 to ans and update map. else get the first index
	 * from map which needs to be updated; increase the value by 1; remove index
	 * from the map. since the value is already updated; we need to put the new no's
	 * index in the map in sorted order.
	 * 
	 * Ideally could have used TreeSet instead of list. It will auto maintain the sorted order of index.
	 */
	public ArrayList<Integer> solve(ArrayList<Integer> A) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		Map<Integer, List<Integer>> map = new HashMap<>();

		for (int i = 0; i < A.size(); i++) {
			// x is no. we need to update the ans.
			int x = A.get(i);
			
			//This is helper flag to check if the x + 1 index needs to be added at last or not.
			boolean adjusted = false;
			
			//Check if have some list against x
			if (map.containsKey(x) && !map.get(x).isEmpty()) {
				List<Integer> existingIndex = map.get(x);
				// Load the first index
				int setIndex = existingIndex.get(0);
				// update the value by 1.
				ans.set(setIndex, x + 1);
				// remove from current list.
				existingIndex.remove(0);
				
				//Now adjust the x + 1 in the map
				// If available then adjust the new index (setIndex here in the x + 1's list)
				if (map.containsKey(x + 1) && !map.get(x + 1).isEmpty()) {
					List<Integer> updateList = map.get(x + 1);
					for (int j = 0; j < updateList.size(); j++) {
						int index = updateList.get(j);
						// this is right place to add new index 
						if (index > setIndex) {
							updateList.add(j, setIndex);
							adjusted = true;
							break;
						}
					}
					// if we were not able to add, then add it to last.
					if (!adjusted) {
						updateList.add(setIndex);
					}
					// update the map.
					map.put(x + 1, updateList);
				} else {
					// create the list add the setIndex into map.
					List<Integer> index = new ArrayList<>();
					index.add(setIndex);
					map.put(x + 1, index);
				}
			}
			// else put the x in ans and update map.
			ans.add(x);
			List<Integer> index = new ArrayList<>();
			index.add(i);
			map.put(x, index);

		}

		return ans;
	}

	public static void main(String[] args) {
		AnIncrementProblem obj = new AnIncrementProblem();
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(1, 1, 1, 1))));
	}
}
