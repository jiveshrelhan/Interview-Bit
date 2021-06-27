package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StringConcatenation {

	/*
	 * Algo, Extract length;
	 */
	public ArrayList<Integer> findSubstring(String A, final List<String> B) {
		ArrayList<Integer> res = new ArrayList<>();
		int words = B.size();
		int wordLength = B.get(0).length();
		if (wordLength * words > A.length() || B.isEmpty()) {
			return res;
		}
		Map<String, Integer> wordMap = new HashMap<>();
		for (String w : B) {
			wordMap.compute(w, (k, v) -> (v == null ? 1 : v + 1));
		}

		for (int i = 0; i <= A.length() - B.size(); i++) {
			int count = 0;
			Map<String, Integer> copy = new HashMap<>();
			for (int j = i; j < A.length(); j += wordLength) {
				StringBuilder formWord = new StringBuilder();
				for (int k = 0; k < wordLength && j + k < A.length(); k++) {
					char c = A.charAt(j + k);
					formWord.append(c);
				}
				String possibleWord = formWord.toString();
				if (possibleWord.length() == wordLength && wordMap.containsKey(possibleWord)) {
					count++;
					int freq = wordMap.get(possibleWord);
					if (freq == 1) {
						wordMap.remove(possibleWord);
					} else {
						wordMap.put(possibleWord, freq - 1);
					}
					copy.compute(possibleWord, (k, v) -> (v == null ? 1 : v + 1));
				} else {
					break;
				}
			}
			if (count == words) {
				res.add(i);
			}
			for (Entry<String, Integer> s : copy.entrySet()) {
				if (wordMap.containsKey(s.getKey())) {
					wordMap.put(s.getKey(), wordMap.get(s.getKey()) + s.getValue());
				} else {
					wordMap.put(s.getKey(), s.getValue());
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		StringConcatenation obj = new StringConcatenation();
		System.out.println(obj.findSubstring(
				"bcabbcaabbccacacbabccacaababcbb",
				Arrays.asList( "c", "b", "a", "c", "a", "a", "a", "b", "c")));
	}

}
