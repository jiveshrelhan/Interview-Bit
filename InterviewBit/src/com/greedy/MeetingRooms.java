package com.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeetingRooms {
	static class Pair {
		char x;
		int time;

		Pair(char x, int day) {
			this.x = x;
			this.time = day;
		}
	}

	public int solve(int[][] A) {
		int n = A.length;
		List<Pair> sortedList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			sortedList.add(new Pair('A', A[i][0]));
			sortedList.add(new Pair('D', A[i][1]));
		}

		Collections.sort(sortedList, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {

				if (o1.time == o2.time) {
					if (o1.x == 'D') {
						return -1;
					} else if (o2.x == 'D') {
						return 1;
					} else {
						return 0;
					}
				} else if (o1.time < o2.time) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		int rooms = 0;
		int maxRooms = 0;
		for (int i = 0; i < sortedList.size(); i++) {

			Pair p = sortedList.get(i);

			if (p.x == 'A') {
				rooms++;
			} else {
				rooms--;
			}

			maxRooms = Math.max(maxRooms, rooms);

		}

		return maxRooms;
	}

}
