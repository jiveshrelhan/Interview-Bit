package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TripletSum {

	public int solve(ArrayList<String> A) {

		PriorityQueue<Double> min = new PriorityQueue<>();
		PriorityQueue<Double> max = new PriorityQueue<>(new Comparator<Double>() {

			@Override
			public int compare(Double o1, Double o2) {
				return o2.compareTo(o1);
			}
		});

		int len = A.size();

		if (len < 3)
			return 0;
		double sum = 0;
		for (int i = 0; i < 3; i++) {
			double d = Double.parseDouble(A.get(i));
			min.add(d);
			max.add(d);
			sum += d;
		}
		
		if (sum > 1.0 && sum < 2.0) {
			return 1;
		}

		int i = 3;

		while (i < len) {
			double d = Double.parseDouble(A.get(i));

			if (sum > 2.0) {
				sum -= max.remove();
				sum += d;
				max.add(d);
			} else if (sum < 1.0) {
				sum -= min.remove();
				sum += d;
				min.add(d);
			}

			if (sum > 1.0 && sum < 2.0) {
				return 1;
			}

			i++;
		}
		return 0;
	}

	public static void main(String[] args) {
		TripletSum obj = new TripletSum();
		System.out.println(obj.solve(new ArrayList<>(
				Arrays.asList( "0.480294", "0.298444", "1.109334"))));
	}

}
