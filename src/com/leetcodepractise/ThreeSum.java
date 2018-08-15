package com.leetcodepractise;

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class ThreeSum{
	public ArrayList<ArrayList<Integer>> threeSum(int[] nums){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		
		Arrays.sort(nums); // sort the array
		
		for(int k=1; k<nums.length-1; k++) {
			int i=0, j=nums.length-1;
			while(i<k && j>k) {
				int vsum = nums[i] + nums[k] + nums[j];
				if(vsum > 0)
					j--;
				else if(vsum < 0)
					i++;
				else {
					ArrayList<Integer> varr = new ArrayList<Integer>();
					varr.add(nums[k]);
					varr.add(nums[i]);
					varr.add(nums[j]);
					if(!res.contains(varr)) {
						res.add(varr);
					}
					j--;
					i++;
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] varr = new int[] {-1, 0, 1, 2, -1, -4,0,-1,1};
		ThreeSum ts = new ThreeSum();
		System.out.println(ts.threeSum(varr));
	}
}

