package com.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SmallestMultipleOf0n1 {

	class Pair {
		Integer remainder;
		String value;

		public Pair(Integer rem, String value) {
			this.remainder = rem;
			this.value = value;
		}
	}

	public String multiple(int N) {
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(1 % N, "1"));
		HashSet<Integer> set = new HashSet<>();
		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			int newRemainder = p.remainder % N;
			if (newRemainder % N == 0) {
				return p.value;
			}
			if (!set.contains(newRemainder)) {
				queue.add(new Pair((newRemainder * 10) % N, p.value + "0"));
				queue.add(new Pair((newRemainder * 10 + 1) % N, p.value + "1"));
			}
		}
		return "";
	}

	public static void main(String[] args) {
		SmallestMultipleOf0n1 obj = new SmallestMultipleOf0n1();
		System.out.println(obj.multiple(89793));
	}
}
