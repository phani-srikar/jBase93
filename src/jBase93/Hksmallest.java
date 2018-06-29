package jBase93;
import java.util.*;

public class Hksmallest extends HmyMaxHeap{
	public void ksmallest(int[] varr, int k){
		/* To find the k smallest element of an array,
		 * create a max-heap from k elements and then for each of the
		 * remaining elements, if it's less than root, replace root.
		 */
		/*
		 * Another Alternate solution is to create minHeap from given array,
		 * then print root and heapify (i.e delete min) k times. -- expensive
		 */
		HmyMaxHeap vmaxheap = new HmyMaxHeap(Arrays.copyOfRange(varr, 0, k));
		for(int i=k; i<varr.length; i++){
			if(varr[i] < vmaxheap.arr[0]){
				vmaxheap.arr[0] = varr[i];
				vmaxheap.percolateDown(vmaxheap.size, 0);
			}
		}
		common.print(vmaxheap);
	}
	
	public static void main(String[] args){
		int[] varr = new int[]{1, 23, 12, 9, 30, 2, 50};
		Hksmallest ksm = new Hksmallest();
		ksm.ksmallest(varr, 3);
	}
}