package com.dp.arrays;

import java.util.HashMap;

/*-
 * The key hint of question is the Grid is 3 X K.
 * so there will be always 3 rows.
 * 
 * Think from base case : 3 * 1 : 3 rows 1 column
 * There are only 2 ways to fill the column/grid. 
 * 1 cell across 0th and 1st Index : In this case last cell will be empty.
 * 1 cell across 1th and 2nd Index : In this case first cell will be empty.
 * 
 */

/*-
 * Another base case: Suppose grid is of 3 X 2.
 * Then there is another orientation : place (2x1) cells horizontally.
 * In this case whole gird is completely filled.
 */

/*
 * Summary: For last column there are only 3 possibilities: Either whole column is filled,
 * first cell or last cell is empty.
 */

/*
 * Equation becomes : DpForCol[i] = How many ways to completely fill the column 
 * + How many ways to fill the column such that first cell is empty 
 * + How many ways to completely fill the column that last
 * cell is empty.
 * 
 * As per diagram it looks like : No of ways where first cell is empty == No of ways 
 * where last cell is empty.
 * 
 * Therefore,
 * equation becomes = Find Total Ways to completely filled 
 * + (2*(Find total ways to fill where 1 corner cell is empty).
 */

/*-
 * With the help of diagrams, we can find the how many ways to fill completely and partially,
 */

/*-
 * Base case are difficult to understand: 
 * When horizontal place which is in case of completely filled: ( cells[1] = 1 and cells[0] = 0;
 *
 * When there is a simple column base cases:
 * at position at cell 1st index cell[1] = 1 // since it will always filled.
 * where as cell[0] can be filled or not. so take the minimum : cell[0] = 0;
 * // Not sure above the minimum statement. The base cases should be unique and static.  
 */
public class TilingWithDominoes {

	class Recursion {
		public int mod = 1000000007;

		public int solve(int k) {
			if (k % 2 != 0)
				return 0;
			HashMap<Integer, Integer> aMap = new HashMap<>();
			HashMap<Integer, Integer> bMap = new HashMap<>();

			return aType(k, aMap, bMap);
		}

		private int aType(int k, HashMap<Integer, Integer> aMap, HashMap<Integer, Integer> bMap) {
			if (k == 0)
				return 1;
			if (k == 1)
				return 0;

			if (aMap.containsKey(k)) {
				return aMap.get(k);
			}

			int value = (aType(k - 2, aMap, bMap) + (2 * (bType(k - 1, aMap, bMap)) % mod)) % mod;
			aMap.put(k, value);
			return value;
		}

		private int bType(int k, HashMap<Integer, Integer> aMap, HashMap<Integer, Integer> bMap) {
			if (k == 0) {
				return 0;
			}
			if (k == 1) {
				return 1;
			}

			if (bMap.containsKey(k)) {
				return bMap.get(k);
			}
			int value = (aType(k - 1, aMap, bMap) + bType(k - 2, aMap, bMap)) % mod;
			bMap.put(k, value);
			return value;
		}
	}

	class DP {
		/*-
		 * D is completely filled and B is where corner cell is empty.
		 */
		public int solve(int A) {

			if (A % 2 == 1)
				return 0;

			int[] D = new int[A + 1];
			int[] B = new int[A + 1];
			D[0] = 1;
			D[1] = 0;
			B[0] = 0;
			B[1] = 1;
			for (int i = 2; i <= A; i++) {
				D[i] = ((D[i - 2]) % 1000000007 + (2 * B[i - 1]) % 1000000007) % 1000000007;
				B[i] = ((D[i - 1]) % 1000000007 + B[i - 2]) % 1000000007;
			}

			return D[A];
		}
	}
}
