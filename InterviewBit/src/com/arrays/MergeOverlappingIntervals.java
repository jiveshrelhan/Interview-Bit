package com.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.stream.Collectors;

/*
 * https://www.youtube.com/watch?v=ANkkTJSk3KU
 */
public class MergeOverlappingIntervals {
	/*-
	 *  [ 1,3] [0,1]
	 */
	static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		public String toString() {
			return "Interval [start=" + start + ", end=" + end + "]";
		}
		
		
	}

	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		if(intervals == null || intervals.isEmpty()) {
			return intervals;
		}
		Comparator<Interval> com = (Interval a, Interval b) -> Integer.valueOf(a.start).compareTo(Integer.valueOf(b.start));
		Collections.sort(intervals, com);
		Stack<Interval> stack = new Stack<>();
		stack.push(intervals.get(0));
		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			Interval top = stack.peek();

			if (top.end < current.start) {
				stack.push(current);
			} else {
				stack.pop();
				top.start = Math.min(top.start, current.start);
				top.end = Math.max(top.end, current.end);
				stack.push(top);
			}
		}

		ArrayList<Interval> ans = new ArrayList<>(stack.stream().collect(Collectors.toList()));
		return ans;
	}

	public static void main(String[] args) {
		MergeOverlappingIntervals obj = new MergeOverlappingIntervals();
		ArrayList<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(1,10));
		list.add(new Interval(2,9));
		list.add(new Interval(3,8));
		list.add(new Interval(4,7));
		list.add(new Interval(5,6));
		list.add(new Interval(6,6));

		System.out.println(obj.merge(list));
	}
}
