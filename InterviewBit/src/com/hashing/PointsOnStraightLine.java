package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PointsOnStraightLine {

	public int maxPoints(ArrayList<Integer> x, ArrayList<Integer> y) {
		if (x.isEmpty())
			return 0;
		int maxPoints = 0;
		Map<Double, Integer> slopCount = null;
		for (int i = 0; i < x.size(); i++) {
			slopCount = new HashMap<>();
			// includes self points
			int duplicates = 1; 
			int sameXAxis = 0;
			int sameYAxis = 0;
			int sameSlope = 0;
			int x1 = x.get(i), y1 = y.get(i);
			for (int j = i + 1; j < y.size(); j++) {
				int x2 = x.get(j), y2 = y.get(j);
				if (x1 == x2 && y1 == y2) {
					duplicates++;
				} else if (x1 == x2) {
					sameXAxis++;
				} else if (y1 == y2) {
					sameYAxis++;
				} else {
					int yNet = y2 - y1;
					int xNet = x2 - x1;
					double slope = xNet != 0 ? (yNet * 1.0) / xNet : 1e9;
					int count = slopCount.getOrDefault(slope, 0) + 1;
					sameSlope = Math.max(sameSlope, count);
					slopCount.put(slope, count);
				}
			}
			maxPoints = Math.max(maxPoints, Math.max(sameSlope, Math.max(sameXAxis, sameYAxis)) + duplicates);
		}
		return maxPoints;
	}

	private void call(ArrayList<Integer> test) {
		ArrayList<Integer> x = new ArrayList<>();
		ArrayList<Integer> y = new ArrayList<>();
		for (int i = 1; i < test.size(); i++) {
			if (i % 2 == 1) {
				x.add(test.get(i));
			} else {
				y.add(test.get(i));
			}
		}
		System.out.println(maxPoints(x, y));
	}

	public static void main(String[] args) {
		PointsOnStraightLine obj = new PointsOnStraightLine();

		ArrayList<Integer> test = new ArrayList<>(Arrays.asList(3, 0, 0, 1, 1, -1, -1));
		ArrayList<Integer> test1 = new ArrayList<>(Arrays.asList(3, 4, -4, 8, -4, -4, -4));

		obj.call(test);
		obj.call(test1);

	}
}
