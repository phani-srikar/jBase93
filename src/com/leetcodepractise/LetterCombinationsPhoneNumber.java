package com.leetcodepractise;

import java.io.*;
import java.util.*;

public class LetterCombinationsPhoneNumber{
	public List<String> letterCombinations(String d){
		List<String> res = new ArrayList<String>();
		String[] vmap = new String[] {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		for(int i=0; i<d.length(); i++) {
			int vindex = d.charAt(i)-'0'-2;
			String vstr = vmap[vindex];
			if(res.isEmpty()) {
				for(int j=0; j<vstr.length(); j++)
					res.add(Character.toString(vstr.charAt(j)));
			}
			else {
				for(int k=0; k<res.size(); k++) {
					for(int l=0; l<vstr.length(); l++) {
						res.add(res.get(k) + vstr.charAt(l));
					}
					res.remove(res.get(k));
					System.out.println(res);
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		String vnum = "23";
		LetterCombinationsPhoneNumber lcpn = new LetterCombinationsPhoneNumber();
		List<String> vres = lcpn.letterCombinations(vnum);
		for(String vtemp:vres) {
			System.out.println(vtemp);
		}
	}
}