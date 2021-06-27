package com.graph;

public class Test {
public static void main(String[] args) {
	String a = "a\\b\\c\\";
	a = a.replaceAll("\\\\", "");
	System.out.println(a);
}
}
