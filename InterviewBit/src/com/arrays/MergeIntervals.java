package com.arrays;

import java.util.ArrayList;

public class MergeIntervals {

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

	@SuppressWarnings("unused")
	private Interval findAndUpdateStartingInterval(ArrayList<Interval> intervals, Interval toAdjust) {

		int N = intervals.size();
		int start = toAdjust.start;
		int end = toAdjust.end;

		int current_i = 0;

		while (current_i < N && start > intervals.get(current_i).end) {
			current_i++;
		}

		if (current_i == 0) {
			intervals.get(0).start = start;
		}
		return null;

	}

	public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval toAdjust) {

		int N = intervals.size();
		int start = toAdjust.start;
		int end = toAdjust.end;

		if (N > 0 && start > intervals.get(N - 1).end) {
			intervals.add(toAdjust);
			return intervals;
		}

		if (N > 0 && end < intervals.get(0).start) {
			intervals.add(0, toAdjust);
			return intervals;
		}

		return null;
	}

}
