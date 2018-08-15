package com.leetcodepractise;

import java.io.*;

public class PalindromeNumber{
	public boolean isPalindromeNumber(int x) {
		if(x==0) return true;
		else if(x < 0 || x%10 == 0) return false; // if negative or ends with 0, it can never be a palindrome
		int halfReverse = 0;
		while(x > halfReverse) {
			int d = x%10;
			x = x/10;
			halfReverse = halfReverse * 10 + d;
		}
		if(halfReverse == x || halfReverse/10 == x) // takes care of odd and even number of digits
			return true;
		else return false;
	}
	
	public static void main(String[] args) {
		int i = 1212;
		PalindromeNumber pn = new PalindromeNumber();
		System.out.println(pn.isPalindromeNumber(i));
	}
}