package com.dp.derived;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;


/*
 * Both initial try don't work because i am sorting the input.
 * 
 * Order is important since only adjacent element needs to be merged.
 * 
 * Read comment from abhinav-singh_415
 */
public class MergeElements {

	/*
	 * Algorithm : Merge two smallest number 4, 9, 13, 15, 6
	 * 
	 * My Answer : 108, expected : 107
	 */
	class GreedyNotWorking {
		public int solve(ArrayList<Integer> A) {
			if (A.size() < 2) {
				return 0;
			}
			Collections.sort(A);
			int cost = 0, prev = A.get(0);
			for (int i = 1; i < A.size(); i++) {
				prev = prev + A.get(i);
				cost = cost + prev;
			}
			return cost;
		}
	}

	/*
	 * This also didn't work. 
	 */
	class AnotherApproach {
		public int solve(ArrayList<Integer> A) {
			if (A.size() < 2) {
				return 0;
			}
			PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
			for (int x : A) {
				heap.add(x);
			}

			int cost = 0;

			while (heap.size() >= 2) {
				int smallest = heap.poll();
				int secondSmallest = heap.poll();
				int sum = smallest + secondSmallest;
				cost += sum;
				heap.add(sum);
			}
			
			return cost;
		}
	}
	
	public static void main(String[] args) {
		MergeElements obj = new MergeElements();
		
		System.out.println(obj.new GreedyNotWorking().solve(new ArrayList<>(Arrays.asList( 4, 9, 13, 15, 6))));
		System.out.println(obj.new AnotherApproach().solve(new ArrayList<>(Arrays.asList( 4, 9, 13, 15, 6))));
	}
}
