package com.arrays;

import java.util.ArrayList;

/*
 * https://www.youtube.com/watch?v=ANkkTJSk3KU
 */
public class MergeIntervals {
	/*-
	 *  [ 1,3] [0,1]
	 */
	class Interval {
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
	}

	public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval toAdjust) {
		if (intervals == null || intervals.isEmpty()) {
			intervals = new ArrayList<MergeIntervals.Interval>();
			intervals.add(toAdjust);
			return intervals;
		}
		int i = 0;

		while (i < intervals.size()) {
			Interval current = intervals.get(i);
			if (current.end < toAdjust.start) {
				i++;
			} else if (toAdjust.end < current.start) {
				intervals.add(i, toAdjust);
				break;
			} else {
				toAdjust.start = Math.min(toAdjust.start, current.start);
				toAdjust.end = Math.max(toAdjust.end, current.end);
				intervals.remove(i);
			}
		}

		if (i == intervals.size()) {
			intervals.add(toAdjust);
		}

		return intervals;
	}

}
