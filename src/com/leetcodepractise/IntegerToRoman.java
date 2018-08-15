package com.leetcodepractise;

import java.io.*;

public class IntegerToRoman{
	public String integerToRoman(int x) {
		String[] romanVal = new String[] {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
		int[] intVal = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		int i=0;
		String ans = "";
		while(x!=0) {
			if(intVal[i] <= x) {
				ans = ans + romanVal[i];
				x = x - intVal[i];
			}
			else i++;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int r = 1994;
		IntegerToRoman itr = new IntegerToRoman();
		System.out.println(itr.integerToRoman(r));
	}
}