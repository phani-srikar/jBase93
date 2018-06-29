package jBase93;
import java.util.*;
import jBase93.common;

// https://www.geeksforgeeks.org/the-stock-span-problem/
/*S[i] on day i can be easily computed if we know the closest day preceding i, 
 * such that the price is greater than on that day than the price on day i. 
 * If such a day exists, let’s call it h(i), otherwise, we define h(i) = -1.
 * The span is now computed as S[i] = i – h(i).
 * we use a stack as an abstract data type to store the days i, h(i), h(h(i)) 
 * and so on. When we go from day i-1 to i, we pop the days when the price 
 * of the stock was less than or equal to price[i] and then push the value of 
 * day i back into the stack.
 * For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, 
 * then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 */
public class SpanProblem{
	public int[] spanProblem(int[] arr){
		int[] S = new int[arr.length]; // To Store the spans
		Stack<Integer> st = new Stack<Integer>(); // stack to store indices of preceding larger
		S[0] = 1; //First Element span=1
		st.push(0);
		for(int i=1; i<arr.length; i++){
			while(!st.isEmpty() && arr[i] >= arr[st.peek()])
				st.pop();
			S[i] = (st.isEmpty() ? i+1 : i-st.peek());
			st.push(i);
		}
		return S;
	}
	
	public static void main(String[] args){
		int[] a = new int[] {10, 4, 5, 90, 120, 80};
		System.out.println("Span of ");
		common.print(a);
		System.out.println("is: ");
		SpanProblem sp = new SpanProblem();
		common.print(sp.spanProblem(a));
		
	}
}