package com.hashing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Fraction {
	//Youtube
	// Integer corner case 2^32
	public String fractionToDecimal(int A, int B) {
		if (A == 0)
			return "0";

		StringBuilder str = new StringBuilder();
		if (A < 0 && B > 0 || A > 0 && B < 0) {
			str.append("-");
		}
		//inside long
		long numo = Math.abs((long)A), deno = Math.abs((long)B);
		Map<Long, Integer> remainderMap = new HashMap<>();
		long remainder = numo % deno;
		if (remainder < 0)
			remainder *= -1;
		str.append(numo / deno);
		if (remainder == 0) {
			return str.toString();
		}

		//map with index mechanism
		str.append('.');
		while (remainder != 0) {
			remainder *= 10;
			if (remainderMap.containsKey(remainder)) {
				str.insert(remainderMap.get(remainder), "(");
				str.append(')');
				break;
			}
			remainderMap.put(remainder, str.length());
			str.append(remainder / deno);
			remainder = remainder % deno;
		}

		return str.toString();
	}

	public String fractionToDecimalNotWorking(int A, int B) {
		BigDecimal result = new BigDecimal(A).divide(new BigDecimal(B));
		if (result == BigDecimal.ZERO) {
			return String.valueOf(0);
		}
		boolean isSame = true;
		String[] str = String.valueOf(result).split("\\.");
		String x = str[1];
		int c = x.charAt(0) - '0';
		for (int i = 1; i < x.length(); i++) {
			if (x.charAt(i) - '0' != c) {
				isSame = false;
				break;
			}
		}
		return !isSame ? String.valueOf(result) : str[0] + ".(" + c + ")";
	}

	public static void main(String[] args) {
		Fraction obj = new Fraction();
		System.out.println(obj.fractionToDecimal(-2147483648, -1));
	}
}
