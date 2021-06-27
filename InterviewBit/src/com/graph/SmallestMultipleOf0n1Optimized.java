package com.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SmallestMultipleOf0n1Optimized {
	class Pair {
		int state;
		char c;

		Pair(int state, char c) {
			this.state = state;
			this.c = c;
		}
	}

	private String constructString(Pair[] parent, int currentState) {
		StringBuilder ans = new StringBuilder();
		while (currentState>=0 && parent[currentState] != null) {
			ans.append(parent[currentState].c);
			currentState = parent[currentState].state;
		}
		return ans.reverse().toString();
	}

	public String multiple(int N) {
		if(N==1)
			return "1";
		Queue<Integer> queue = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		Pair[] parent = new Pair[N + 1];
		queue.add(1 % N);
		parent[1] = new Pair(-1, '1');
		visited.add(1);
		while (!queue.isEmpty()) {
			int remainder = queue.poll();
			if (remainder == 0) {
				return constructString(parent, remainder);
			}
			int stateWithZero = (remainder * 10) % N;
			int stateWithOne = (remainder * 10 + 1) % N;

			if (!visited.contains(stateWithZero)) {
				parent[stateWithZero] = new Pair(remainder, '0');
				queue.add(stateWithZero);
				visited.add(stateWithZero);
			}
			if (!visited.contains(stateWithOne)) {
				parent[stateWithOne] = new Pair(remainder, '1');
				queue.add(stateWithOne);
				visited.add(stateWithOne);
			}
		}
		return "";
	}

	public static void main(String[] args) {
		SmallestMultipleOf0n1Optimized obj = new SmallestMultipleOf0n1Optimized();
		System.out.println(obj.multiple(1));
		//System.out.println(obj.multiple(2));
	}
}
