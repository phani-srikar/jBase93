package jBase93;
import java.util.*;
import jBase93.common;
/*Given an array a[1..N]. For each element at position i (1 <= i <= N). Where
L(i) is defined as closest index j such that j < i and a[j] > a[i]. 
If no such j exists then L(i) = 0.
R(i) is defined as closest index k such that k > i and a[k] > a[i]. 
If no such k exists then R(i) = 0.
LRProduct(i) = L(i)*R(i) .
We need to find an index with maximum LRProduct.
Input : 5 4 3 4 5
Output : 8
For {5, 4, 3, 4, 5}, L[] = {0, 1, 2, 1, 0} and R[]
= {0, 5, 4, 5, 0},
LRProduct = {0, 5, 8, 5, 0} and max in this is 8.
Note: In L and R, index starts from 1 */

public class MaxProdNextGreaterLR{
	private int[] NextGreaterRight(int[] arr){
		Stack<Integer> st = new Stack<Integer>();
		int n = arr.length;
		int[] R = new int[n];
		for(int i=0; i<n; i++){
			while(!st.isEmpty() && arr[i]>arr[st.peek()]){
				// while current elem is greater than elem at index given by st.peek(),
				// curr elem becomes the Next greater right for that elem.
				R[st.peek()] = i + 1; // 1 based index used for L & R. Hence add 1.
				st.pop();
			}
			st.push(i);
		}
		// For remaining elements in stack, there is no Next Greater Right
		while(!st.isEmpty()){
			R[st.peek()] = 0;
			st.pop();
		}
		return R;
	}
	
	private int[] NextGreaterLeft(int[] arr){
		Stack<Integer> st = new Stack<Integer>();
		int n = arr.length;
		int[] L = new int[n];
		for(int i=n-1; i>=0; i--){
			while(!st.isEmpty() && arr[i]>arr[st.peek()]){
				// while current elem is greater than elem at index given by st.peek(),
				// curr elem becomes the Next greater left for that elem.
				L[st.peek()] = i + 1; // 1 based index used for L & R. Hence add 1.
				st.pop();
			}
			st.push(i);
		}
		// For remaining elements in stack, there is no Next Greater Left
		while(!st.isEmpty()){
			L[st.peek()] = 0;
			st.pop();
		}
		return L;
	}
	
	private int maxProdNextGreaterLR(int[] arr){
		int[] R = NextGreaterRight(arr);
		System.out.print("Next Greater Right: ");
		common.print(R);
		int[] L = NextGreaterLeft(arr);
		System.out.print("Next Greater Left: ");
		common.print(L);
		int maxProd = L[0]*R[0];
		for(int i=1; i<arr.length; i++)
			maxProd = (maxProd > L[i]*R[i]) ? maxProd : L[i]*R[i];
		return maxProd;
	}
	
	public static void main(String[] args){
		int[] a = new int[]{5, 4, 3, 4, 5};
		MaxProdNextGreaterLR ng = new MaxProdNextGreaterLR();
		System.out.println(ng.maxProdNextGreaterLR(a));
	}
}







