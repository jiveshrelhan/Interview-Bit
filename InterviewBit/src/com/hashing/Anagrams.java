package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Anagrams {
	@SuppressWarnings("unused")
	private boolean isAnagram(Map<Character, Integer> src, Map<Character, Integer> current) {
		boolean result = true;
		if (src.size() != current.size()) {
			return false;
		}

		for (Entry<Character, Integer> k : src.entrySet()) {
			char character = k.getKey();
			int count = k.getValue();
			if (!current.containsKey(character) || count != current.get(character)) {
				result = false;
				break;
			}
		}
		return result;
	}
	private Map<Character, Integer> generateWordMap(String str) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		str.chars().forEach(k -> {
			if (map.containsKey((char) k)) {
				map.put((char) k, map.get((char) k) + 1);
			} else {
				map.put((char) k, 1);
			}
		});
		return map;
	}

	

	public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>(A.size() + 1);
		for (int i = 0; i < res.size(); i++) {
			res.add(new ArrayList<Integer>());
		}
		HashMap<Map<Character, Integer>, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i < A.size(); i++) {
			String str = A.get(i);
			char[] arr = str.toCharArray();
			Arrays.sort(arr);
			str = String.valueOf(arr);
			Map<Character, Integer> generatedMap = generateWordMap(str);
			if (map.containsKey(generatedMap)) {
				ArrayList<Integer> indexes = map.get(generatedMap);
				indexes.add(i + 1);
				map.put(generatedMap, indexes);
			} else {
				ArrayList<Integer> indexes = new ArrayList<Integer>();
				indexes.add(i + 1);
				map.put(generatedMap, indexes);
			}
		}
		System.out.println(map);
		map.values().forEach(k -> {
			res.add(k);
		});
		res.sort(new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {

				int size1 = 0;
				int size2 = 0;

				while (size1 < o1.size() && size2 < o2.size()) {
					int a = o1.get(size1);
					int b = o2.get(size2);
					if (a < b) {
						return -1;
					} else if (a > b) {
						return 1;
					}
					size1++;
					size2++;
				}

				if (size1 < o1.size()) {
					return 1;
				} else if (size2 < o2.size()) {
					return -1;
				}

				return 0;
			}
		});
		return res;
	}

	public static void main(String[] args) {
		Anagrams obj = new Anagrams();
		System.out.println(obj.anagrams(Arrays.asList("caat", "taac", "dog", "god", "acta","gt")));
	}
}
