package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NobleInteger {
	public int solve(ArrayList<Integer> A) {
		Collections.sort(A);
		int n = A.size();
		int curr = 0;
		
		while(curr < n) {
			int element = A.get(curr);
			
			while((curr + 1) < n && element == A.get(curr + 1)){
				curr++;
			}
			
			if(Math.abs(element) == (n-1-curr))
				return 1;
			
			curr++;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		NobleInteger obj = new NobleInteger();
		int ans = obj.solve(new ArrayList<>(Arrays.asList(1, 2, 7, 0, 9, 3, 6, 0, 6)));
		System.out.println(ans);
	}

}
