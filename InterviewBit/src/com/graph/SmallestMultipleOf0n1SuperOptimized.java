package com.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Need to replace Pair and Visited set to Arrays 
 * @author JiveshR1
 *
 */
public class SmallestMultipleOf0n1SuperOptimized {

	private String constructString(int[] state, char[] value, int currentState) {
		StringBuilder ans = new StringBuilder();
		while (currentState >= 0) {
			ans.append(value[currentState]);
			currentState = state[currentState];
		}
		return ans.reverse().toString();
	}

	public String multiple(int N) {
		if (N == 1)
			return "1";
		Queue<Integer> queue = new LinkedList<>();
		boolean[]  visited = new boolean[N+1];
		int[] state = new int[N + 1];
		char[] value = new char[N + 1];
		queue.add(1 % N);
		state[1] = -1;
		value[1] = '1';
		visited[1] = true;
		while (!queue.isEmpty()) {
			int remainder = queue.poll();
			if (remainder == 0) {
				return constructString(state, value, remainder);
			}
			int stateWithZero = (remainder * 10) % N;
			int stateWithOne = (remainder * 10 + 1) % N;

			if (!visited[stateWithZero]) {
				state[stateWithZero] = remainder;
				value[stateWithZero] = '0';
				queue.add(stateWithZero);
				visited[stateWithZero] = true;
			}
			if (!visited[stateWithOne]) {
				state[stateWithOne] = remainder;
				value[stateWithOne] = '1';
				queue.add(stateWithOne);
				visited[stateWithOne] = true;
			}
		}
		return "";
	}

	public static void main(String[] args) {
		SmallestMultipleOf0n1SuperOptimized obj = new SmallestMultipleOf0n1SuperOptimized();
		System.out.println(obj.multiple(2));
		// System.out.println(obj.multiple(2));
	}
}
