package com.trees;

public class ShortestUniqueIndex {
	class TrieNode {

		TrieNode[] chars = new TrieNode[256];
		int frequency = 0;
		boolean isWordEnd = false;

		public TrieNode() {
			for (int i = 0; i < chars.length; i++) {
				chars[i] = null;
			}
		}

	}

	private void removeFromTrie(TrieNode head, String word) {
		TrieNode curr = head;
		for (int i = 0; i < word.length(); i++) {
			int charIndex = word.charAt(i) - 'a';
			if (curr != null && curr.chars[charIndex].frequency == 1) {
				curr.chars[charIndex] = null;
				break;
			} else {
				curr.chars[charIndex].frequency--;
			}
			curr = curr.chars[charIndex];
		}
		curr.isWordEnd = false;
	}

	private void populateTrieNode(TrieNode head, String word) {
		TrieNode curr = head;
		for (int i = 0; i < word.length(); i++) {
			int charIndex = word.charAt(i) - 'a';
			if (curr.chars[charIndex] == null) {
				curr.chars[charIndex] = new TrieNode();
			}
			curr.chars[charIndex].frequency++;
			curr = curr.chars[charIndex];
		}

		curr.isWordEnd = true;

	}

	private String commonPrefix(TrieNode head, String searchWord) {
		StringBuilder prefix = new StringBuilder();
		TrieNode curr = head;
		int i = 0;
		for (i = 0; i < searchWord.length(); i++) {
			int charIndex = searchWord.charAt(i) - 'a';
			if (curr.chars[charIndex] != null) {
				prefix.append(searchWord.charAt(i));
			} else {
				break;
			}
			curr = curr.chars[charIndex];
		}
		if (i < searchWord.length())
			prefix.append(searchWord.charAt(i));

		return prefix.toString();

	}

	public String[] prefix(String[] words) {
		TrieNode head = new TrieNode();
		String[] ans = new String[words.length];

		for (String word : words) {
			populateTrieNode(head, word);
		}

		int ans_counter = 0;
		for (String word : words) {
			removeFromTrie(head, word);
			ans[ans_counter++] = commonPrefix(head, word);
			populateTrieNode(head, word);
		}

		return ans;
	}

	public static void main(String[] args) {
		ShortestUniqueIndex obj = new ShortestUniqueIndex();
		System.out.println(obj.prefix(new String[] { "zebra", "dog", "duck", "dove" }));
	}
}
