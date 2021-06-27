package com.graph;

import java.util.ArrayList;

public class SumOfFib {

	static ArrayList<Long> fibs = new ArrayList<>();

	private static void computeFibs() {
		fibs.add((long)1);
		fibs.add((long)1);
		long last = 1;
		while (last <= (long) 10e9) {
			last = fibs.get(fibs.size() - 2) + fibs.get(fibs.size() - 1);
			//System.out.println(last);
			fibs.add(last);
		}
	}

	SumOfFib() {
		computeFibs();
	}

	public int fibsum(int k) {
		int i = fibs.size() - 1;
		while (i >= 0 && fibs.get(i) > k) {
			i--;
		}

		int count = 0;
		while (k != 0) {
			if (k == 0)
				return count;
			if (fibs.get(i) <= k) {
				count++;
				k = (int) (k - fibs.get(i));
			} else {
				i--;
			}
		}

		return count;
	}
	public static void main(String[] args) {
		SumOfFib obj = new SumOfFib();
		System.out.println(obj.fibsum(1));
	}
}
