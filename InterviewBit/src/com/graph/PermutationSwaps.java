package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author JiveshR1
 *
 *         Think this as a graph. We need to go 1 to N, we can only go to next
 *         state. if [0 to L] matches with B. if we are stage N and and whole []
 *         aligns with B return true;
 * 
 *         Visited to should have subset(0,L) and index t
 */
public class PermutationSwaps {

	int n = 0;
	List<Integer>[] graph = null;
	ArrayList<Integer> A;
	ArrayList<Integer> B;
	ArrayList<Integer> a_res = new ArrayList<Integer>();
	ArrayList<Integer> b_res = new ArrayList<Integer>();
	int[] visited;
	
	@SuppressWarnings("unchecked")
	public int solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> swaps) {
		this.n = A.size();
		this.visited = new int[n];
		this.graph = new ArrayList[n];
		this.A = A;
		this.B = B;
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (ArrayList<Integer> swap : swaps) {
			int u = swap.get(0), v = swap.get(1);
			// swaps are 1 index based
			u--;
			v--;
			graph[u].add(v);
			graph[v].add(u);
		}

		for (int index = 0; index < n; index++) {
			if (visited[index] != 1) {
				a_res.clear();
				b_res.clear();
				DFS(index);
				Collections.sort(a_res);
				Collections.sort(b_res);
				if (!a_res.equals(b_res)) {
					return 0;
				}
			}
		}
		return 1;
	}

	private void DFS(int index) {
		visited[index] = 1;
		a_res.add(A.get(index));
		b_res.add(B.get(index));

		for (int neighbour_index : graph[index]) {
			if (visited[neighbour_index] != 1) {
				DFS(neighbour_index);
			}
		}
	}

	public static void main(String[] args) {
		PermutationSwaps obj = new PermutationSwaps();
		ArrayList<ArrayList<Integer>> swaps = new ArrayList<>();
		swaps.add(new ArrayList<>(Arrays.asList(2, 4)));
		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(1, 3, 2, 4)),
				new ArrayList<>(Arrays.asList(1, 4, 2, 3)), swaps));
	}

}
