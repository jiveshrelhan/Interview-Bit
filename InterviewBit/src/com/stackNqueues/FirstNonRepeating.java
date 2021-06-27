package com.stackNqueues;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FirstNonRepeating {
	public String solve(String A) {
		StringBuilder res = new StringBuilder();
		Queue<Character> queue = new LinkedList<Character>();
		Set<Character> potentialRepeated = new HashSet<>();

		for (char x : A.toCharArray()) {

			if (potentialRepeated.contains(x)) {
				Queue<Character> newQueue = new LinkedList<>();
				while (!queue.isEmpty()) {
					char y = queue.poll();
					if (y != x) {
						newQueue.add(y);
					}
				}
				queue = newQueue;
				if (queue.isEmpty()) {
					res.append("#");
				} else {
					res.append(queue.peek());
				}
				continue;
			}
			if (queue.isEmpty()) {
				res.append(x);
			} else {
				res.append(queue.peek());
			}
			potentialRepeated.add(x);
			queue.add(x);
		}

		return res.toString();
	}

	public static void main(String[] args) {
		FirstNonRepeating obj = new FirstNonRepeating();
		System.out.println(obj.solve("xxikrwmjvsvckfrqxnibkcasompsuyuogauacjrr"));
		// jjjjjjjjjjjjjjjjjjjjjjyyyyyyyyyyyyyyyyyyyyyyyyyyy
	}
}
