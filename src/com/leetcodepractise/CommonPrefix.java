package com.leetcodepractise;

import java.io.*;

public class CommonPrefix{
	public String commonPrefix(String[] str) {
		if(str == null || str.length == 0)
			return "";
		else return commonPrefix(str, 0, str.length-1); 
	}
	
	private String commonPrefix(String[] str, int l, int r) {
		if(l == r)
			return str[l];
		else {
			int mid = (l+r)/2;
			String vleft = commonPrefix(str, l, mid);
			String vright = commonPrefix(str, mid+1, r);
			return commonPrefix(vleft, vright);
		}
	}
	
	private String commonPrefix(String vleft, String vright) {
		int n = Math.min(vleft.length(), vright.length());
		int i = 0;
		for(; i<n; i++) {
			if(vleft.charAt(i) != vright.charAt(i))
				break;
		}
		return vleft.substring(0, i);
	}
	
	public static void main(String[] args) {
		String[] str = new String[] {"flower","fow","flight"};
		CommonPrefix cp = new CommonPrefix();
		System.out.println(cp.commonPrefix(str));
	}
	
}