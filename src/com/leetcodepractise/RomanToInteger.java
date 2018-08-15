package com.leetcodepractise;

import java.io.*;
import java.util.*;

public class RomanToInteger{
	public int romanToInteger(String r) {
		int i=0,sum=0;
		HashMap<Character,Integer> vmap = new HashMap<Character, Integer>();
		vmap.put('M', 1000);
		vmap.put('D',500);
		vmap.put('C', 100);
		vmap.put('L', 50);
		vmap.put('X',10);
		vmap.put('V',5);
		vmap.put('I', 1);
		
		while(i<r.length()-1) {
			char vroman = r.charAt(i);
			if(vmap.get(vroman) >= vmap.get(r.charAt(i+1))) {
				sum = sum + vmap.get(vroman);
				i++;
			}
			else {
				sum = sum + vmap.get(r.charAt(i+1)) - vmap.get(vroman);
				i = i + 2;
			}
		}
		if(i == r.length()-1)
			sum = sum + vmap.get(r.charAt(i));
		return sum;
	}
	
	public static void main(String[] args) {
		String vroman = "XXVI";
		RomanToInteger rti = new RomanToInteger();
		System.out.println(rti.romanToInteger(vroman));
	}
}