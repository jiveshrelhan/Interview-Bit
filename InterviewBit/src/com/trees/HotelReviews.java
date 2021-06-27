package com.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class HotelReviews {

	private void populateTrieNode(TrieNode head, String word) {
		TrieNode curr = head;
		for (int i = 0; i < word.length(); i++) {
			int charIndex = word.charAt(i) - 'a';
			if (curr.chars[charIndex] == null) {
				curr.chars[charIndex] = new TrieNode();
			}
			curr = curr.chars[charIndex];
		}

		curr.isWordEnd = true;

	}

	private boolean search(TrieNode head, String searchWord) {
		TrieNode curr = head;
		for (int i = 0; i < searchWord.length(); i++) {
			int charIndex = searchWord.charAt(i) - 'a';
			if (curr.chars[charIndex] == null) {
				return false;
			}
			curr = curr.chars[charIndex];
		}

		return (curr != null && curr.isWordEnd) ? true : false;

	}

	public int[] solve(String dict, String[] words) {
		TrieNode head = new TrieNode();
		Map<Integer, List<Integer>> maxMatchMap = new TreeMap<>(Collections.reverseOrder());
		int[] ans = new int[words.length];
		String[] dicts_words = dict.split("_");

		for (String word : dicts_words) {
			populateTrieNode(head, word);
		}

		for (int index = 0; index < words.length; index++) {
			String toSearch = words[index];
			String[] minuteWords = toSearch.split("_");
			int match_count = 0;
			for (String minuteWord : minuteWords) {
				boolean found = search(head, minuteWord);
				if (found)
					match_count++;
			}
			List<Integer> indexes = maxMatchMap.getOrDefault(match_count, new ArrayList<>());
			indexes.add(index);
			maxMatchMap.put(match_count, indexes);
		}

		int ans_counter = 0;
		for (Entry<Integer, List<Integer>> entry : maxMatchMap.entrySet()) {
			List<Integer> list = entry.getValue();
			for (int i = 0; i < list.size(); i++) {
				ans[ans_counter++] = list.get(i);
			}
		}
		return ans;
	}
}
