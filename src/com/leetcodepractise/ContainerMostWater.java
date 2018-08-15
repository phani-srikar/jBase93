package com.leetcodepractise;

import java.io.*;

public class ContainerMostWater{
	public int containerMostWater(int[] h) {
		int i = 0, j = h.length-1;
		int maxArea = 0;
		while(i <= j) {
			int currArea = Math.min(h[i], h[j]) * (j-i);
			maxArea = Math.max(maxArea, currArea);
			if(h[i] <= h[j])
				i++;
			else j--;
		}
		return maxArea;
	}
	
	public static void main(String[] args) {
		int[] barHeights = new int[] {1,8,6,2,5,4,8,3,7};
		ContainerMostWater cmw = new ContainerMostWater();
		System.out.println(cmw.containerMostWater(barHeights));
	}
}