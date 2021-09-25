package com.string;

public class CompareVersionNumber {
	public int compareVersion(String A, String B) {

		if (A.isEmpty() && B.isEmpty()) {
			return 0;
		}
		if (A.isEmpty()) {
			return -1;
		}
		if (B.isEmpty()) {
			return 1;
		}

		String[] aminor = A.split("\\.");
		String[] bminor = B.split("\\.");
		int i = 0, j = 0;

		while (i < aminor.length && j < bminor.length) {
			Long aVersion = Long.valueOf(aminor[i]);
			Long bVersion = Long.valueOf(bminor[j]);
			if (aVersion.longValue() == bVersion.longValue()) {
				i++;
				j++;
			} else if (aVersion.longValue() > bVersion.longValue()) {
				return 1;
			} else {
				return -1;
			}
		}
		
		while (i < aminor.length) {
			Long aVersion = Long.valueOf(aminor[i]);
			if(aVersion.longValue() != 0) {
				break;
			}
			i++;
		}

		while (j < bminor.length) {
			Long bVersion = Long.valueOf(bminor[j]);
			if(bVersion.longValue() != 0) {
				break;
			}
			j++;
		}

		
		if (i < aminor.length) {
			return 1;
		} else if (j < bminor.length) {
			return -1;
		} else
			return 0;
	}

	public static void main(String[] args) {
		CompareVersionNumber obj = new CompareVersionNumber();
		System.out.println(obj.compareVersion("444444444444444444444444", "444444444444444444444444"));
	}
}
