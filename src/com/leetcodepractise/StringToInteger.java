package com.leetcodepractise;

import java.io.*;

public class StringToInteger{
	public int stringToInteger(String str) {
		int i=0;
		int ans=0;
		boolean neg = false;
		while(i<str.length()) {
			if(str.charAt(i) == ' ')
				i++;
			else break;
		}
		if(i == str.length()) return 0;
		if(str.charAt(i) == '-') {
			neg = !neg;
			i++;
		}
		else if(str.charAt(i) == '+') i++;
		if(i == str.length()) return 0;
		try {
			int temp = Integer.parseInt(Character.toString(str.charAt(i)));
		}
		catch(NumberFormatException ex) {
			return 0;
		}
		while(i<str.length()) {
			try {
				int d = Integer.parseInt(Character.toString(str.charAt(i)));
				if(neg) d = -1*d;
				if((ans>Integer.MAX_VALUE/10) || (ans==Integer.MAX_VALUE/10 && d>7)) return Integer.MAX_VALUE;
				else if((ans<Integer.MIN_VALUE/10) || (ans==Integer.MIN_VALUE/10 && d<-8)) return Integer.MIN_VALUE;
				ans = ans*10 + d;
			}
			catch(NumberFormatException ex) {
				return ans;
			}
			i++;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		String s = "-91283472332";
		StringToInteger sti = new StringToInteger();
		int i = sti.stringToInteger(s);
		System.out.println(i);
	}
}