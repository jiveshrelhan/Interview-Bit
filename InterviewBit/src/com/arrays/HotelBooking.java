package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HotelBooking {

	static class Pair {
		char x;
		int day;

		Pair(char x, int day) {
			this.x = x;
			this.day = day;
		}
	}

	public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {

		int n = arrive.size();
		int m = depart.size();

		if (n != m)
			return false;

		List<Pair> sortedList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (!arrive.get(i).equals(depart.get(i))) {
				sortedList.add(new Pair('A', arrive.get(i)));
				sortedList.add(new Pair('D', depart.get(i)));

			}
		}

		Collections.sort(sortedList, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {

				if (o1.day == o2.day) {
					if (o1.x == 'D') {
						return -1;
					} else if (o2.x == 'D') {
						return 1;
					} else {
						return 0;
					}
				} else if (o1.day < o2.day) {
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

			if (rooms < 0)
				return false;

			maxRooms = Math.max(maxRooms, rooms);

		}

		return K >= maxRooms;

	}

	public static void main(String[] args) {
		HotelBooking obj = new HotelBooking();
		boolean ans = obj.hotel(new ArrayList<>(Arrays.asList(1, 3, 5)), new ArrayList<>(Arrays.asList(2, 6, 8)), 1);
		System.out.println(ans);
	}

}
