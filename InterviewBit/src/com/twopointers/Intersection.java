package com.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intersection {
	public ArrayList<Integer> intersect(final List<Integer> A, final List<Integer> B) {
        ArrayList<Integer> answer = new ArrayList<>();
        int i = 0,j = 0;
        
        while(i < A.size() && j < B.size()){
            if(A.get(i).equals(B.get(i))){
                answer.add(A.get(i));
                i++;
                j++;
            }
            else if(A.get(i) < B.get(j)){
                i++;
            }
            else{
                j++;
            }
        }
        return answer;
    }
	public static void main(String[] args) {
		Intersection obj =  new Intersection();
		System.out.println(obj.intersect(Arrays.asList(9999), Arrays.asList(9999)));
	}
}
