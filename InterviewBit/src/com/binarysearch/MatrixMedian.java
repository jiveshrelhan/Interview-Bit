package com.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;

public class MatrixMedian {

	private int binarySearch(ArrayList<Integer> row, int key) {
		int start = 0, end = row.size() - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (row.get(mid) >= key) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;

	}

	private int searchOnMatrix(ArrayList<ArrayList<Integer>> A, int key) {
		int noOfSmallerElements = 0;
		int rows = A.size();

		for (int i = 0; i < rows; i++) {
			noOfSmallerElements += binarySearch(A.get(i), key);
		}

		return noOfSmallerElements;
	}

	public int findMedian(ArrayList<ArrayList<Integer>> A) {
		int n = !A.isEmpty() ? A.size() * A.get(0).size() : 0;
		int start = Integer.MIN_VALUE, end = Integer.MAX_VALUE;

		while (start <= end) {
			int mid = (int) (start + ((long) end - start) / 2);

			int noOfSmallerElements = searchOnMatrix(A, mid);

			if (noOfSmallerElements == n / 2) {
				// continue to find last occurrence.
				/**
				 * I some what understood, there can be many numbers where smaller elements are
				 * equal to n/2 but that element can't be originally present in matrix. For
				 * example in matrix having one row : 5, 17,100 All number between 6 to 17 have
				 * only 1 element smaller than that in other words no of smaller elements are
				 * equal to n/2, therefore all of [6,17] can be our answer. But not all of them
				 * are present in matrix, we have only 17.
				 * 
				 */

				/**
				 * So even if we got our smallerElement to n/2, we should keep hunting towards
				 * right [last occurrence]. Why only right side, why not first/left occurrence ?
				 * Answer can't be on left side. Visualize, Suppose array is 5, 7, 10, 17, 100.
				 * Now median is no longer 17. Imagine array was 5, 17 , 18, , 100. Answer is
				 * still not 17 (May be part of median ) but here even no is out of scope.
				 */

				/**
				 * We need to find the element which satisfy condition [noOfSmallerElements ==
				 * n/2] and present in matrix. Ideally this no will be last number which is
				 * present in matrix, we will search on right side. As soon as we point to 100
				 * [5,17,100] our condition is not longer true, we will again point back to 17.
				 * start will be at 100 and end will be at 17. This is why we are returning end
				 * in the end.
				 */
				start = mid + 1;

			} else if (noOfSmallerElements > n / 2) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}

		}
		/**
		 * Critical point: Why we are not returning when we get smaller == n/2 and why
		 * we are returning end instead of mid.
		 */
		return end;
	}

	public static void main(String[] args) {

		/**
		 * Driver : BinarySearch on Integer.MIN_VALUE to Integer.MAX_VALUE Calculate the
		 * no of elements are less than to mid. This can be calculated by seeing the
		 * index of mid value if value is found Since there can be duplicate, we need to
		 * continue search in the same direction to find the first occurrence of the mid
		 * element.
		 * 
		 * If at any stage no of smaller elements are equal to n/2 then return the ans.
		 * if no of smaller elements are more than n/2, end = mid - 1; else start = mid
		 * + 1;
		 */

		MatrixMedian obj = new MatrixMedian();

		ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();

		A.add(new ArrayList<>(Arrays.asList(1, 3, 5)));
		A.add(new ArrayList<>(Arrays.asList(2, 6, 9)));
		A.add(new ArrayList<>(Arrays.asList(3, 6, 9)));

		System.out.println(obj.findMedian(A));
	}

}