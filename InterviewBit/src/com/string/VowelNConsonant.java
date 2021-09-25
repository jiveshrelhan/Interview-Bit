package com.string;

public class VowelNConsonant {
	/*- 10 11 12
	 *  a b c
	 */
	class TLE {
		private boolean isVowel(char c) {
			return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
		}

		public int solve(String word) {
			int count = 0;
			int mod = (int) (1e9 + 7);

			for (int from = 0; from < word.length(); from++) {
				for (int to = from + 1; to <= word.length(); to++) {
					String currentSet = word.substring(from, to);
					if (!currentSet.isEmpty()
							&& ((isVowel(currentSet.charAt(0)) && !isVowel(currentSet.charAt(currentSet.length() - 1)))
									|| (!isVowel(currentSet.charAt(0))
											&& isVowel(currentSet.charAt(currentSet.length() - 1))))) {
						count++;
						count %= mod;
					}
				}
			}

			return count % mod;
		}
	}

	class Optimized {
		private boolean isVowel(char c) {
			return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
		}

		public int solve(String word) {
			int count = 0;
			int mod = (int) (1e9 + 7);
			int[] vowel = new int[word.length()];
			int[] consonant = new int[word.length()];

			char[] chars = word.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				vowel[i] = (i > 0 ? vowel[i - 1] : 0);
				consonant[i] = (i > 0 ? consonant[i - 1] : 0);

				if (isVowel(chars[i])) {
					vowel[i] += 1;
				} else {
					consonant[i] += 1;
				}
			}

			for (int i = word.length() - 1; i >= 1; i--) {
				char c = chars[i];
				if (isVowel(c)) {
					count += consonant[i - 1];
				} else {
					count += vowel[i - 1];
				}
				count %= mod;
			}

			return count % mod;
		}
	}

	class SuperOptmized {
		private boolean isVowel(char c) {
			return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
		}

		public int solve(String word) {
			int count = 0;
			int mod = (int) (1e9 + 7);
			int vCount = 0, cCount = 0;
			char[] chars = word.toCharArray();
			for (int i = 0; i < chars.length; i++) {

				if (isVowel(chars[i])) {
					vCount++;
					count += cCount;
				} else {
					cCount++;
					count += vCount;
				}
				count %= mod;
			}

			return count % mod;
		}
	}

	public static void main(String[] args) {
		VowelNConsonant obj = new VowelNConsonant();
		System.out.println(obj.new TLE().solve("inttnikjmjbemrberk"));
		System.out.println(obj.new Optimized().solve("inttnikjmjbemrberk"));
		System.out.println(obj.new SuperOptmized().solve("inttnikjmjbemrberk"));
	}
}
