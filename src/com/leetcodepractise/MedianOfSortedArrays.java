package com.leetcodepractise;

import java.io.*;

//https://leetcode.com/problems/median-of-two-sorted-arrays/solution/
/*   left_part          |        right_part
    A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
    B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
    
    if we ensure len(left_part) = len(right_part) and max(left_part)<=min(right_part)
    Then median = max(left_part)+min(right_part)/2 if m+n is even else max(LP)
	Binary search i from 0 to m to find i satisfying the cond.
*/

public class MedianOfSortedArrays{
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        // ensure n>=m as j should be always +ve
        if(m>n) {
        	int[] temp = nums1;
        	nums1 = nums2;
        	nums2 = temp;
        	int temp1 = m;
        	m = n;
        	n = temp1;
        }
        
        int i=0, j=0, imin=0, imax=m;
        double maxLeft, minRight;
        while(imin <= imax) {
        	i = (imin+imax)/2;
        	j = (m+n+1)/2 - i;
        	if(i<imax && nums2[j-1] > nums1[i])
        		imin = imin+1;
        	else if(i>imin && nums1[i-1] > nums2[j])
        		imax = imax-1;
        	else {
        		if(i==0) maxLeft = nums2[j-1];
        		else if(j==0) maxLeft = nums1[i-1];
        		else maxLeft = Math.max(nums1[i-1], nums2[j-1]);
        		
        		if(((m+n)%2)==1)
        			return maxLeft;
        		
        		if(i==m) minRight = nums2[j];
        		else if(j==n) minRight = nums1[i];
        		else minRight = Math.min(nums1[i], nums2[j]);
        		return (maxLeft+minRight)/2;
        	}
        }
		return 0.0;
    }
	
	public static void main(String[] args) {
		int[] nums1= new int[] {1,3};
		int[] nums2= new int[] {2};
		MedianOfSortedArrays tj = new MedianOfSortedArrays();
		double vresult = tj.findMedianSortedArrays(nums1,nums2);
		System.out.println(vresult);
	}
}
