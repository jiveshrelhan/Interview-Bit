package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StringConcatenation {

	/*
	 * Algo : Create map of all words with their frequencies. Since in B words can
	 * repeat. Now try to create all possible words of length word K given in the B.
	 * if possible word is present in frequencies map then reduce the frequency. At
	 * any index if we get all the words present in B then current index to answer.
	 * reset the frequencies map update above.
	 */
	public ArrayList<Integer> findSubstring(String A, final List<String> B) {
		ArrayList<Integer> res = new ArrayList<>();
		int words = B.size();
		int wordLength = B.get(0).length();
		// Base case, we B is larger than the input string or B is empty
		if (wordLength * words > A.length() || B.isEmpty()) {
			return res;
		}
		// Original Frequency map
		Map<String, Integer> wordMap = new HashMap<>();
		for (String w : B) {
			wordMap.compute(w, (k, v) -> (v == null ? 1 : v + 1));
		}

		// Try from all index i to Len - B.size()
		for (int i = 0; i <= A.length() - B.size(); i++) {
			int count = 0;
			Map<String, Integer> copy = new HashMap<>();
			// Explore right and create word of length wordLength. At any point current word
			// doesn't exist break.
			for (int j = i; j < A.length(); j += wordLength) {
				StringBuilder formWord = new StringBuilder();
				// Helper to create word of wordLength
				for (int k = 0; k < wordLength && j + k < A.length(); k++) {
					char c = A.charAt(j + k);
					formWord.append(c);
				}
				String possibleWord = formWord.toString();
				// Important condition to check if length was same or not. and it was present in
				// frequencies map.
				if (possibleWord.length() == wordLength && wordMap.containsKey(possibleWord)) {
					count++;
					int freq = wordMap.get(possibleWord);
					// if freq is 1 remove from map.
					if (freq == 1) {
						wordMap.remove(possibleWord);
					} else {
						wordMap.put(possibleWord, freq - 1);
					}
					// Whatever changes are done in original frequency map are copied in copy_map.
					copy.compute(possibleWord, (k, v) -> (v == null ? 1 : v + 1));
				} else {
					break;
				}
			}
			// store the ans.
			if (count == words) {
				res.add(i);
			}
			// restore the values from the copy freq map to original freq map. Equivalent to
			// backtracking or help in finding other possible solutions.
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
		System.out.println(obj.findSubstring("bcabbcaabbccacacbabccacaababcbb",
				Arrays.asList("c", "b", "a", "c", "a", "a", "a", "b", "c")));
	}

}
