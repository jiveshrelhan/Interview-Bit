package com.string;

public class LastWord {
	public int lengthOfLastWord(final String A) {
        int end = -1 ,start = -1,curr = A.length()-1;
        while(curr>=0 && A.charAt(curr) == ' '){
            curr--;
        }
        end = curr;
        start = curr;
        if(end == -1){
            return 0;
        }
        curr =curr-1;
        
        while(curr>=0){
            char c = A.charAt(curr);
            if(c==' '){
                break;
            }
            else {
            	start = curr;
            }
            curr--;
        }
        return end-start+1;
    }
	public static void main(String[] args) {
		LastWord obj = new LastWord();
		System.out.println(obj.lengthOfLastWord("  aa"));
	}
}
