package com.heaps;

import java.util.*;

public class ProfitMax {

	public int solve(ArrayList<Integer> A, int B) {
		Collections.sort(A, Collections.reverseOrder());
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
		int totalCost = 0, accomodated = 0;

		for (int a : A)
			queue.add(a);

		while (accomodated < B && !queue.isEmpty()) {
			int cost = queue.poll();
			totalCost += cost;
			queue.add(cost - 1);
			accomodated++;
		}

		return totalCost;
	}

	public int solveNotOptimized(ArrayList<Integer> A, int B) {
		Set<Integer> indexes = new HashSet<Integer>();
		Collections.sort(A, Collections.reverseOrder());
		PriorityQueue<List<Integer>> queue = new PriorityQueue<List<Integer>>(new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o2.get(0) - o1.get(0);
			}
		});
		int totalCost = 0, accomodated = 0;
		indexes.add(0);
		queue.add(Arrays.asList(A.get(0), 0));
		while (accomodated < B && !queue.isEmpty()) {
			List<Integer> maxRowSeats = queue.poll();
			int cost = maxRowSeats.get(0);
			totalCost += (cost);
			int nextPossible = maxRowSeats.get(1) + 1;
			if (nextPossible < A.size() && !indexes.contains(nextPossible)) {
				queue.add(Arrays.asList(A.get(nextPossible), nextPossible));
				indexes.add(nextPossible);
			}
			if (cost > 0)
				queue.add(Arrays.asList(cost - 1, maxRowSeats.get(1)));
			accomodated++;
		}
		return totalCost;
	}

	public static void main(String[] args) {
		ProfitMax obj = new ProfitMax();
		System.out.println(obj.solve(new ArrayList<Integer>(Arrays.asList(2, 3)), 3));
	}

}
