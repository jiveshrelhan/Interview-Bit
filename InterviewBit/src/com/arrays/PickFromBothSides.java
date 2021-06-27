package com.arrays;

import java.util.Arrays;
import java.util.List;

public class PickFromBothSides {

	
	public int solve(List<Integer> input, int B) {
		
		int length = input.size();
		
		int sum = Integer.MIN_VALUE;
		
		int[] prefix = new int[input.size()];

		int[] suffix = new int[input.size()];

		for (int i = 0; i < length; i++) {
			prefix[i] = i > 0 ? (prefix[i - 1] + input.get(i)) : input.get(0);
		}

		for (int i = length - 1; i >= 0; i--) {
			suffix[i] = i < length - 1 ? (suffix[i + 1] + input.get(i)) : input.get(length - 1);
		}

		for (int elementFromLeft = 0; elementFromLeft <= B; elementFromLeft++) {
			//If nothing is selected from left side then select B elements from right side.
			if (elementFromLeft == 0) {
				sum = Math.max(sum, suffix[length - B]);
			} 
			//If all B elements are selected from left side then no need to select any thing from right side.
			else if (elementFromLeft == B) {
				sum = Math.max(sum, prefix[B - 1]);
			} 
			//Else take selected elements say x from left and (B-x) from right side.
			else {
				int rightIndex = length - (B - elementFromLeft);
				int temp = prefix[elementFromLeft - 1] + suffix[rightIndex];
				sum = Math.max(sum, temp);
			}
		}

		return sum;

	}

	public int solveWrongAlgo(List<Integer> input, int B) {
		int sum = 0;
		int left = 0;
		int right = input.size() - 1;
		while (B > 0) {

			int leftElement = input.get(left);
			int rightElement = input.get(right);

			//If left element is larger
			if (leftElement > rightElement) {
				sum += leftElement;
				left++;
			}
			//If right element is larger
			else if (rightElement > leftElement) {
				sum += rightElement;
				right--;
			} 
			//If both are same, take any of them, here i took left one.
			else {
				sum += leftElement;
				left++;
			}

			B--;

		}
		return sum;
	}

	public static void main(String[] args) {
		PickFromBothSides obj = new PickFromBothSides();
		List<Integer> input = Arrays.asList(-584, -714, -895, -512, -718, -545, 734, -886, 701, 316, -329, 786, -737,
				-687, -645, 185, -947, -88, -192, 832, -55, -687, 756, -679, 558, 646, 982, -519, -856, 196, -778, 129,
				-839, 535, -550, 173, -534, -956, 659, -708, -561, 253, -976, -846, 510, -255, -351, 186, -687, -526,
				-978, -832, -982, -213, 905, 958, 391, -798, 625, -523, -586, 314, 824, 334, 874, -628, -841, 833, -930,
				487, -703, 518, -823, 773, -730, 763, -332, 192, 985, 102, -520, 213, 627, -198, -901, -473, -375, 543,
				924, 23, 972, 61, -819, 3, 432, 505, 593, -275, 31, -508, -858, 222, 286, 64, 900, 187, -640, -587, -26,
				-730, 170, -765, -167, 711, 760, -104, -333);

		System.out.println(obj.solve(input, 32));

	}

}
