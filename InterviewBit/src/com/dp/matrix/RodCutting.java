package com.dp.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*-
 * Its not a standard DP question as per me.
 * Doesn't belong to any variation of knapsack.
 * 
 * The challenge is: when we decided we will make cut on point i, you can't make a cut of same point again.
 * Similarly, initially you have decided not to make a cut on point i, 
 * then how will you come back and say point i cut has not been made.
 * 
 * 
 * Somehow we need to keep track of what point's cut has been made and what are remaining, may 
 * be in the set
 *
 * Idea of recursion is : Function (Set remainingPoints, Length)
 * 	
 * 	for( all remaining points){
 * 		case 1 : we make a cut : Length + Function (RemainingPoints - ith Point, i) 
 * 	    + Function(RemainingPoints - ith Point, Length - i)
 * 
 *  	case 2: we don't make a cut Function( Don't do anything)	
 * }
 *
 *	May be instead of for loop we need 3 states
 *
 */
public class RodCutting {

	int minimumSeenSoFar = Integer.MAX_VALUE;
	ArrayList<Integer> minimum = new ArrayList<Integer>();
	int total_length = 0;

	private int helper(int point_start, int point_end, ArrayList<Integer> point_cost) {
		if (point_start >= point_end) {
			return 0;
		}

		int actual_end = point_end == point_cost.size() ? total_length : point_cost.get(point_end);
		int actual_start = point_start == 0 ? 0 : point_cost.get(point_start - 1);
		int cost = actual_end - actual_start;
		int minCost = Integer.MAX_VALUE;
		for (int point = point_start; point < point_end; point++) {
			minCost = Math.min(minCost,
					cost + helper(point_start, point, point_cost) + helper(point + 1, point_end, point_cost));

		}

		return minCost;
	}

	public ArrayList<Integer> rodCut(int A, ArrayList<Integer> points) {
		Collections.sort(points);
		this.total_length = A;
		System.out.println(helper(0, points.size(), points));
		return minimum;
	}

	public static void main(String[] args) {
		RodCutting obj = new RodCutting();
		System.out.println(obj.rodCut(6, new ArrayList<>(Arrays.asList(1, 2))));
	}
}
