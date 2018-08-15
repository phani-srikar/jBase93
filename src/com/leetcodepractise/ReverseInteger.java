package com.leetcodepractise;

import java.io.*;

public class ReverseInteger{
	public int reverseInteger(int x) {
		int ans = 0;
		while(x!=0) {
			int d = x%10;
			x = x/10;
			System.out.println("d=" + d + "  x=" + x);
			//check for overflow. Max integer value is 2,147,483,647
			if((ans>Integer.MAX_VALUE/10) || (ans==Integer.MAX_VALUE/10 && d>7))
				return 0;
			//check for underflow. Min integer value is -2,147,483,648
			if((ans<Integer.MIN_VALUE/10) || (ans==Integer.MIN_VALUE/10 && d<-8))
				return 0;
			ans = ans*10 + d;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int num = -123;
		ReverseInteger ri = new ReverseInteger();
		System.out.println(ri.reverseInteger(num));
	}
}