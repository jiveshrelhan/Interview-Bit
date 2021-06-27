package com.trees;

public class TrieNode {

	TrieNode[] chars = new TrieNode[256];
	boolean isWordEnd = false;

	public TrieNode() {
		for (int i = 0; i < chars.length; i++) {
			chars[i] = null;
		}
	}

}
