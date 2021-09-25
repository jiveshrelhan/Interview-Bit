package com.dp.prefix;

/*
 * We need to ignore first and last horse. 
 * since first horse will always be in first set and last will be in last set.
 * 
 * Problem reduce to arrange horse - 2 in K set.
 * 
 * Recursion is sometime similar to : either we choose current horse in current set or not.
 * If yes : horse + 1, K else horse, k + 1
 * 
 * We also needs to maintain number of white and black horse in current set.
 * 
 * F(int currentHorse, int currentSet, int noOfWhite, int noOfBlack)
 * 
 * Feeling challenge in calculating noOfWhite * noOfBlack. 
 * Confused either create actual sets or just numbers is fine.
 * 
 * 
 * Base case : no of sets can't be greater than no of horses. Since no set can be empty return -1
 */
public class ArrangeII {
	public int arrange(String A, int B) {
		return -1;
	}
}
