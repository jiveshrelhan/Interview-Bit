package com.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class NPairMaxCombination {
	/*
	 * https://www.youtube.com/watch?v=btjG9eLNYcg
	 * https://discuss.interviewbit.com/t/java-solution-o-n-log-n/60817
	 */
	public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
		ArrayList<Integer> ans = new ArrayList<>();
		Collections.sort(A, Collections.reverseOrder());
		Collections.sort(B, Collections.reverseOrder());
		PriorityQueue<int[]> queue = new PriorityQueue<>(A.size(), new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		/*
		 * Very strange Set<int[]> didn't work
		 */
		Set<List<Integer>> indexes = new HashSet<>();
		int aPointer = 0, bPointer = 0;
		indexes.add(Arrays.asList(0, 0));
		queue.add(new int[] { A.get(0) + B.get(0), 0, 0 });

		while (ans.size() < A.size()) {
			int[] maxFromHeap = queue.poll();
			ans.add(maxFromHeap[0]);
			aPointer = maxFromHeap[1];
			bPointer = maxFromHeap[2];

			List<Integer> choice1 = Arrays.asList(aPointer, bPointer + 1);
			List<Integer> choice2 = Arrays.asList(aPointer + 1, bPointer);

			if (bPointer + 1 < B.size() && !indexes.contains(choice1)) {
				queue.add(new int[] { A.get(choice1.get(0)) + B.get(choice1.get(1)), choice1.get(0), choice1.get(1) });
				indexes.add(choice1);
			}
			if (aPointer + 1 < A.size() && !indexes.contains(choice2)) {
				queue.add(new int[] { A.get(choice2.get(0)) + B.get(choice2.get(1)), choice2.get(0), choice2.get(1) });
				indexes.add(choice2);
			}
		}

		return ans;
	}

	public ArrayList<Integer> solveBruteForce(ArrayList<Integer> A, ArrayList<Integer> B) {
		ArrayList<Integer> ans = new ArrayList<>();
		PriorityQueue<Integer> queue = new PriorityQueue<>(A.size(), new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {

				return o2 - o1;
			}
		});
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < B.size(); j++) {
				int sum = A.get(i) + B.get(j);
				queue.add(sum);
			}
		}

		while (ans.size() < A.size()) {
			ans.add(queue.poll());
		}

		return ans;
	}

	public static void main(String[] args) {
		NPairMaxCombination obj = new NPairMaxCombination();
		System.out.println(obj.solve(new ArrayList<Integer>(Arrays.asList(1, 4, 2, 3)),
				new ArrayList<Integer>(Arrays.asList(2, 5, 1, 6))));
	}
}
