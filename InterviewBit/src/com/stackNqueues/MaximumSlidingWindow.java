package com.stackNqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumSlidingWindow {

	private Queue<Integer> addToQueue(List<Integer> A, int index, int noOfElements, Queue<Integer> queue) {

		int i = 0;
		while (i < noOfElements) {
			int x = A.get(index + i);
			if (queue.isEmpty()) {
				queue.add(x);
			} else {
				Queue<Integer> newQueue = new LinkedList<Integer>();
				while (!queue.isEmpty() && queue.peek() >= x) {
					newQueue.add(queue.poll());
				}
				newQueue.add(x);
				queue = newQueue;
			}
			i++;
		}
		return queue;
	}

	// DO NOT MODIFY THE LIST. IT IS READ ONLY
	public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue = addToQueue(A, 0, B, queue);
		output.add(queue.peek());
		int i = 0, j = B;
		while (j < A.size()) {
			int remove = A.get(i);
			if (remove == queue.peek()) {
				queue.poll();
			}
			queue = addToQueue(A, j, 1, queue);
			output.add(queue.peek());
			i++;
			j++;
		}
		return output;
	}

	public static void main(String[] args) {
		MaximumSlidingWindow obj = new MaximumSlidingWindow();
		System.out.println(obj.slidingMaximum(Arrays.asList(268, 202, 139, 744, 502, 582, 94, 81, 117, 258, 506, 461,
				531, 768, 827, 128, 592, 571, 559, 374, 910, 610, 561, 489, 647, 246, 355, 313, 158, 922, 557, 36, 430,
				983, 913, 303, 765, 945, 167, 340, 869, 869, 609, 809, 529, 715, 34, 13, 657, 407, 684, 801, 129, 952,
				159, 250, 546, 508, 540, 948, 429, 174), 6));
	}

}
