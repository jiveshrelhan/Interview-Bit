package com.hashing;

import java.util.*;

public class CheckPalindrome {
	public int solve(String A) {
		boolean isOdd = A.length() % 2 == 1 ? true : false;
		int noOfOdds = 0;
		Map<Character, Integer> characterCount = new HashMap<>();
		for (int i = 0; i < A.length(); i++) {
			char c = A.charAt(i);
			int existingCount = characterCount.getOrDefault(c, 0);
			characterCount.put(c, existingCount + 1);
		}

		for (Integer v : characterCount.values()) {
			if (v % 2 == 1) 
				noOfOdds++;
		}
		
		if (isOdd && noOfOdds == 1) {
			return 1;
		} else if (!isOdd && noOfOdds == 0) {
			return 1;
		}

		return 0;
	}
}
