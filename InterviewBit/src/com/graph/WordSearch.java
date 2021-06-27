package com.graph;

import java.util.ArrayList;

public class WordSearch {
	public int exist(ArrayList<String> board, String word) {
		if (word.isEmpty() || board.isEmpty()) {
			return 0;
		}
		n = board.size();
		m = board.get(0).length();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board.get(i).charAt(j) == word.charAt(0) && DFS(board, i, j, word, 1)) {
					return 1;
				}
			}
		}
		return 0;
	}

	int n = 0, m = 0;
	int X[] = { 0, -1, 0, 1 };
	int Y[] = { -1, 0, 1, 0 };

	private boolean isValid(int i, int j, int n, int m) {
		if (i >= 0 && i < n && j >= 0 && j < m) {
			return true;
		}
		return false;
	}

	private boolean DFS(ArrayList<String> board, int i, int j, String word, int charToFind) {
		if (charToFind >= word.length()) {
			return true;
		}
		/*
		 * char c = board.get(i).charAt(j);
		 * 
		 * if (c == word.charAt(charToFind)) { if (DFS(board, i, j, word, charToFind +
		 * 1)) { return true; } }
		 */ 
		for (int p = 0; p < 4; p++) {
			int newI = i + X[p];
			int newJ = j + Y[p];
			if (isValid(newI, newJ, n, m)) {
				char x = board.get(newI).charAt(newJ);
				if (x == word.charAt(charToFind)) {
					if (DFS(board, newI, newJ, word, charToFind + 1)) {
						return true;
					}
				}
			}
		}

		return false;

	}

	public static void main(String[] args) {
		WordSearch obj = new WordSearch();
		ArrayList<String> board = new ArrayList<String>();
		
		  board.add("BGGAGBGE"); board.add("EFFAGBEG"); board.add("FGGCBBFF");
		  board.add("BEEBDEDC"); board.add("FACABDCD"); board.add("ECGEFFED");
		  board.add("GDBEGACG"); board.add("GCECFBBD");
		  
		  String toSearch = "CABBFFEAC";
		 
		/*
		 * board.add("FFBBCFE"); board.add("BFFCCAF"); board.add("FBDGAEG");
		 * board.add("GAGBBEE"); board.add("FEAFCDF"); board.add("CCDGECG");
		 * board.add("EGDDDDA");
		 * 
		 * String toSearch = "BCDDFFEEE";
		 */
		System.out.println(obj.exist(board, toSearch));
	}
}
