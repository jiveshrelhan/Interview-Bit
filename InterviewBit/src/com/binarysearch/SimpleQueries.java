package com.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class SimpleQueries {

	private int getDivisors(int x) {

		long productOfDivisors = 1;
		int mod = 1000000007;
		for (int i = (int) Math.sqrt(x); i >= 1; i--) {
			if (x % i == 0) {
				productOfDivisors = ((x / i) * productOfDivisors) % mod;
			}
		}

		for (int i = 1; i * i < x; i++) {
			if (x % i == 0) {
				productOfDivisors = (i * productOfDivisors) % mod;
			}
		}

		return (int) (productOfDivisors % mod);
	}

	public List<int[]> sumSubarrayMins(ArrayList<Integer> A) {

		List<int[]> counts = new ArrayList<int[]>();

		int n = A.size();
		Stack<int[]> dpLeftBoundary = new Stack<>(), dpRightBoundary = new Stack<>();

		int left[] = new int[n], right[] = new int[n];

		for (int i = 0; i < n; i++) {
			int x = A.get(i);
			int count = 1;
			/*
			 * Find the last index, till there the current x can be minimum. In otherwords,
			 * go towards left until you get the smaller value than x.
			 */
			while (!dpLeftBoundary.isEmpty() && dpLeftBoundary.peek()[0] < x) {
				count += dpLeftBoundary.pop()[1];
			}
			dpLeftBoundary.push(new int[] { x, count });
			left[i] = count;
		}

		for (int i = n - 1; i >= 0; i--) {
			int x = A.get(i);
			// Minimum count is 1. Single {1} digit sub-array.
			int count = 1;
			/*
			 * Find the last index, till there the current x can be minimum. In otherwords,
			 * go towards right until you get the smaller value than x. Here we will also
			 * include, if there is duplicate elements on the right side.
			 */
			while (!dpRightBoundary.isEmpty() && dpRightBoundary.peek()[0] <= x) {
				count += dpRightBoundary.pop()[1];
			}
			dpRightBoundary.push(new int[] { x, count });
			right[i] = count;
		}
		for (int i = 0; i < n; i++) {
			int product = getDivisors(A.get(i));
			counts.add(new int[] { product, left[i] * right[i] });
		}

		return counts;
	}

	@SuppressWarnings("unused")
	private int binarySearch(int[] G, long[] preFixSum, int query) {
		int start = 0;
		int end = preFixSum.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			long midKey = preFixSum[mid];
			if (query == midKey) {
				return G[mid];
			} else if (query < midKey) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		return G[start];
	}

	public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
		int n = A.size();
		int[] G = new int[n];
		long[] prefixSum = new long[n];
		ArrayList<Integer> result = new ArrayList<>();
		List<int[]> productFreqList = new ArrayList<int[]>();
		Stack<Integer> st = new Stack<Integer>();
		int[] left = new int[n];
		for (int i = 0; i < n; ++i) {
			while (!st.isEmpty() && A.get(st.peek()) < A.get(i))
				st.pop();
			left[i] = st.isEmpty() ? -1 : st.peek();
			st.push(i);
		}
		st = new Stack<Integer>();
		int[] right = new int[n];
		for (int i = n - 1; i >= 0; --i) {
			while (!st.isEmpty() && A.get(st.peek()) <= A.get(i))
				st.pop();
			right[i] = st.isEmpty() ? n : st.peek();
			st.push(i);
		}
		for (int i = 0; i < n; ++i) {
			int cnt = (i - left[i]) * (right[i] - i);
			int a = A.get(i);
			int pr = getDivisors(a);
			productFreqList.add(new int[] { pr, cnt });
		}
		Collections.sort(productFreqList, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});

		for (int i = 0; i < productFreqList.size(); i++) {
			int[] k = productFreqList.get(i);
			G[i] = k[0];
			if (i > 0) {
				prefixSum[i] = k[1] + prefixSum[i - 1];
			} else {
				prefixSum[i] = k[1];
			}
		}

		for (int x : B) {
			int pos = Arrays.binarySearch(prefixSum, x);
			if (pos < 0) {
				pos = -1 - pos;
			}
			result.add(G[pos]);
		}

		return result;
	}

	public static void main(String[] args) {
		SimpleQueries obj = new SimpleQueries();

		ArrayList<Integer> B = new ArrayList<Integer>(Arrays.asList(1221, 360, 459, 651, 958, 584, 345, 181, 536, 116,
				1310, 403, 669, 1044, 1281, 711, 222, 280, 1255, 257, 811, 409, 698, 74, 838));

		System.out.println(obj.solve(new ArrayList<>(Arrays.asList(39, 99, 70, 24, 49, 13, 86, 43, 88, 74, 45, 92, 72,
				71, 90, 32, 19, 76, 84, 46, 63, 15, 87, 1, 39, 58, 17, 65, 99, 43, 83, 29, 64, 67, 100, 14, 17, 100, 81,
				26, 45, 40, 95, 94, 86, 2, 89, 57, 52, 91, 45)), B));

	}
}
