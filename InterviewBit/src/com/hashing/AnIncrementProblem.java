package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnIncrementProblem {
	
	//Need to redo this
	public ArrayList<Integer> solve(ArrayList<Integer> A) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		Map<Integer, List<Integer>> map = new HashMap<>();

		for (int i = 0; i < A.size(); i++) {
			int x = A.get(i);
			boolean adjusted = false;
			if (map.containsKey(x) && !map.get(x).isEmpty()) {
				List<Integer> existingIndex = map.get(x);
				int setIndex = existingIndex.get(0);
				ans.set(setIndex, x + 1);
				existingIndex.remove(0);
				if (map.containsKey(x + 1) && !map.get(x + 1).isEmpty()) {
					List<Integer> updateList = map.get(x + 1);
					for (int j = 0; j < updateList.size(); j++) {
						int index = updateList.get(0);
						if (index > setIndex) {
							updateList.add(j, setIndex);
							adjusted = true;
							break;
						}
					}
					if (!adjusted) {
						updateList.add(setIndex);
					}
					map.put(x + 1, updateList);
				} else {
					List<Integer> index = new ArrayList<>();
					index.add(setIndex);
					map.put(x + 1, index);
				}
			}
			ans.add(x);
			List<Integer> index = new ArrayList<>();
			index.add(i);
			map.put(x, index);

		}

		return ans;
	}

	public static void main(String[] args) {
		AnIncrementProblem obj = new AnIncrementProblem();
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(1, 1,1,1))));
	}
}
