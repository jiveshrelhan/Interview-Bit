package com.math;

public class ExcelColumn {

	public int titleToNumber(String input) {
		int ans = 0;
		for (int i = 0; i < input.length(); i++) {
			ans = ans * 26;
			ans = ans + input.charAt(i) - 'A' + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(new ExcelColumn().titleToNumber("CV"));
	}

}
