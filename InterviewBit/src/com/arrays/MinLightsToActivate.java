package com.arrays;

import java.util.Arrays;
import java.util.List;

public class MinLightsToActivate {

	public int solve(List<Integer> A, int B) {

		if(B > A.size()) {
			return 1;
		}
		
		int bulbs = 0;

		int curr = 0;
		
		while (curr < A.size()) {

			int rightBoundary = curr + B - 1;
			int leftBoundary = curr - B + 1;
			int explore  = rightBoundary;

			boolean bulbFound = false;

			while (explore >= leftBoundary && explore >= 0) {

				if (explore < A.size() && A.get(explore) == 1) {
					bulbFound = true;
					curr = explore + B - 1;
					break;
				}
				explore--;
			}

			if (!bulbFound)
				return -1;
			else
				bulbs++;

			curr++;
		}

		return bulbs;
	}

	public static void main(String[] args) {
		MinLightsToActivate obj = new MinLightsToActivate();
		int ans = obj.solve(Arrays.asList(1,1,1), 6);
		System.out.println(ans);
	}
}
