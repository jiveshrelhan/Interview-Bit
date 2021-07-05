package com.hashing;

import java.util.HashMap;

public class WindowString {

	public String minWindow(String A, String B) {
		String res = "";
		if (B.isEmpty())
			return res;
		
		int currentScore = 0,targetScore = 0,start = 0,end = 0;
		HashMap<Character, Integer> hashB = new HashMap<>(),hashA = new HashMap<>();
		int minWindowLengthSeenSoFar = Integer.MAX_VALUE;

		currentScore = 0;
		for (char c : B.toCharArray()) {
			hashB.put(c, hashB.getOrDefault(c, 0) + 1);
		}
		targetScore = hashB.size();
		while (end < A.length()) {
			// If requirement is met then shrink (start++) else expand (end++)
			char c = A.charAt(end);
			hashA.put(c, hashA.getOrDefault(c, 0) + 1);
			/*
			 * Important Point: We will only update the currentScore when the character c
			 * and its required frequencies completed met. If 2 x are required then don't
			 * update current score twice.
			 */
			if (hashB.containsKey(c) && hashA.get(c).intValue() == hashB.get(c).intValue()) {
				currentScore++;
			}

			while (start <= end && currentScore == targetScore) {
				int windowSize = end - start + 1;

				if (windowSize < minWindowLengthSeenSoFar) {
					minWindowLengthSeenSoFar = windowSize;
					res = A.substring(start, end + 1);
				}

				c = A.charAt(start);
				hashA.put(c, hashA.get(c) - 1);
				// At any point the current captured frequency get reduced then required then currentScore --
				if (hashB.containsKey(c) && hashA.get(c).intValue() < hashB.get(c).intValue()) {
					currentScore--;
				}
				start++;
			}
			end++;
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(new WindowString().minWindow("A", "AA"));
		System.out.println(new WindowString().minWindow("ADOBECODEBANC", "ABC"));

	}
}
