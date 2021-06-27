package com.arrays;

public class Test {

	public static void main(String[] args) {
		String  a = "(.*(?<!((51[0-9]<)|(9<55)))$)";
		a= a.replaceAll("<", "&lg;");
		System.out.println(a);
	}
	
}
