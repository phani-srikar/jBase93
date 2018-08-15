package com.leetcodepractise;

import java.io.*;
import java.util.Arrays;

public class ThreeSumClosest{
	public int threeSumClosest(int[] nums, int target) {
		if(nums.length < 3)
			return Integer.MIN_VALUE;
		int vsum = nums[0] + nums[1] + nums[2];
		
		Arrays.sort(nums);
		
		for(int k=1; k<nums.length-1; k++) {
			int i=0, j=nums.length-1;
			while(i<k && j>k) {
				int currsum = nums[i] + nums[j] + nums[k];
				if(currsum < target) {
					if(Math.abs(currsum-target) < Math.abs(vsum-target))
						vsum = currsum;
					i++;
				}
				else if(currsum > target) {
					if(Math.abs(currsum-target) < Math.abs(vsum-target))
						vsum = currsum;
					j--;
				}
				else return currsum;
			}
		}
		return vsum;
	}
	
	public static void main(String[] args) {
		int[] varr = new int[] {-1, 2, 1, -4};
		ThreeSumClosest tsc = new ThreeSumClosest();
		System.out.println(tsc.threeSumClosest(varr, 1));
	}
}