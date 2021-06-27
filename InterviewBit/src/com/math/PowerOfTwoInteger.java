package com.math;

public class PowerOfTwoInteger {
	public int isPower(int num) {
		if(num < 2) {
			return 1;
		}
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				int temp = num;
				while (temp % i == 0) {
					if (temp / i == 1) {
						return 1;
					}
					temp = temp / i;
				}
			}
		}
		return 0;
	}
public static void main(String[] args) {
	PowerOfTwoInteger obj = new PowerOfTwoInteger();
	System.out.println(obj.isPower(49));
}
}

