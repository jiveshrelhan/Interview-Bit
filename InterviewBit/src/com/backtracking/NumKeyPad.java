package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NumKeyPad {

	private ArrayList<String> helper(String n, HashMap<Integer, ArrayList<Character>> map) {
		ArrayList<String> prev = new ArrayList<String>();
		for (int i = 0; i < n.length(); i++) {
			int number = Integer.valueOf(n.charAt(i)-'0');
			ArrayList<String> next = new ArrayList<String>();
			ArrayList<Character> mapping = map.get(number);
			if (prev.isEmpty()) {
				for (Character x : mapping) {
					next.add(x.toString());
				}
			} else {
				for (String x : prev) {
					for (Character c : mapping) {
						next.add(x + c.toString());
					}
				}
			}
			prev = next;
		}
		return prev;
	}

	public ArrayList<String> letterCombinations(String A) {
		HashMap<Integer, ArrayList<Character>> map = new HashMap<Integer, ArrayList<Character>>();
		map.put(0, new ArrayList<>(Arrays.asList('0')));
		map.put(1, new ArrayList<>(Arrays.asList('1')));
		map.put(2, new ArrayList<>(Arrays.asList('a', 'b', 'c')));
		map.put(3, new ArrayList<>(Arrays.asList('d', 'e', 'f')));
		map.put(4, new ArrayList<>(Arrays.asList('g', 'h', 'i')));
		map.put(5, new ArrayList<>(Arrays.asList('j', 'k', 'l')));
		map.put(6, new ArrayList<>(Arrays.asList('m', 'n', 'o')));
		map.put(7, new ArrayList<>(Arrays.asList('p', 'q', 'r', 's')));
		map.put(8, new ArrayList<>(Arrays.asList('t', 'u', 'v')));
		map.put(9, new ArrayList<>(Arrays.asList('w','x', 'y', 'z')));
		return helper(A, map);
	}

	public static void main(String[] args) {
		NumKeyPad obj = new NumKeyPad();
		System.out.println(obj.letterCombinations("230"));
	}
}
