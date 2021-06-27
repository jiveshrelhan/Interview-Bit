package com.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;

public class Painters {
		private boolean isValid(ArrayList<Integer> C, int A, int B, long mid) {
			int painters = 1;
			long sum = 0;
			for (int x : C) {
				sum += x;
	
				if (sum > mid) {
					painters++;
					sum = x;
				}
	
				if (painters > A) {
					return false;
				}
	
			}
	
			return true;
	
		}
	
		public int paint(int A, int B, ArrayList<Integer> C) {
	
			long start = 0;
			long end = 0;
			long res = 0;
			int mod = 10000003;
			for (int x : C) {
				start = Math.max(start, (x % mod));
				end += x;
			}
	
			while (start <= end) {
				long mid = start + (end - start) / 2;
				if (isValid(C, A, B, mid)) {
					res = mid;
					end = mid - 1;
				} else {
					start = mid + 1;
				}
	
			}
	
			return (int) ((B * res) % mod);
		}

	public static void main(String[] args) {
		Painters obj = new Painters();
		System.out.println(obj.paint(1, 1000000, new ArrayList<>(Arrays.asList(1000000, 1000000))));
	}
}