package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadder2 {

	class Node {
		String parent;
		String current;

		Node(String current, String parent) {
			this.current = current;
			this.parent = parent;
		}
	}

	public ArrayList<ArrayList<String>> findLadders(String start, String target, ArrayList<String> dict) {
		if (start.equals(target)) {
			ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
			ans.add(new ArrayList<>(Arrays.asList(target)));
			return ans;
		}
		Set<String> words = new HashSet<>();
		for (String word : dict) {
			words.add(word);
		}
		words.remove(start);
		return BFS(words, start, target);
	}

	private ArrayList<ArrayList<String>> BFS(Set<String> dict, String start, String target) {
		ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
		int minCount = Integer.MAX_VALUE;
		Queue<Node> queue = new LinkedList<>();
		Node startNode = new Node(start, "-1");
		queue.add(startNode);
		int count = 0;
		Set<String> visited = new HashSet<>();
		HashMap<String, Node> parentMap = new HashMap<>();
		parentMap.put(start, startNode);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node currentNode = queue.poll();
				// System.out.println("Processing : " + currentNode.current);

				for (int character = 0; character < target.length(); character++) {
					for (int alphabet = 0; alphabet < 26; alphabet++) {
						String currentWord = currentNode.current;
						char c = (char) (alphabet + 'a');
						String newWord = currentWord.substring(0, character) + c
								+ currentWord.substring(character + 1, currentWord.length());
						if (!visited.contains(newWord) && dict.contains(newWord)) {
							if (newWord.equals(target)) {
								if (count < minCount) {
									minCount = count;
									new ArrayList<ArrayList<String>>();
								}
								ArrayList<String> path = extractPath(parentMap, currentWord, target);
								// System.out.println(path);
								ans.add(path);
								continue;
							}
							visited.add(newWord);
							Node temp = new Node(newWord, currentWord);
							parentMap.put(newWord, temp);
							// System.out.println(newWord + " prev : " + currentWord);
							queue.add(temp);
						}
					}
				}
			}
			count++;
		}
		return ans;
	}

	private ArrayList<String> extractPath(HashMap<String, Node> parentMap, String curr, String target) {
		ArrayList<String> path = new ArrayList<String>();
		path.add(target);
		while (!curr.equals("-1") && parentMap.containsKey(curr)) {
			path.add(curr);
			curr = parentMap.get(curr).parent;
		}
		Collections.reverse(path);
		return path;
	}

	public static void main(String[] args) {
		WordLadder2 obj = new WordLadder2();
		/*
		 * ArrayList<String> dict = new
		 * ArrayList<String>(Arrays.asList("hot","dot","dog","lot","log","hit","cog"));
		 * System.out.println(obj.findLadders("hit","cog", dict));
		 */
		ArrayList<String> dict = new ArrayList<String>(Arrays.asList("bb", "bb"));
		System.out.println(obj.findLadders("bb", "bb", dict));
	}
}
