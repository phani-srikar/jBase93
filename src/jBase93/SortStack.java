package jBase93;
import java.util.*;
import jBase93.common;

//https://www.geeksforgeeks.org/sort-a-stack-using-recursion/
/*sortStack(stack S)
	if stack is not empty:
	temp = pop(S);  
	sortStack(S); 
	sortedInsert(S, temp);
sortedInsert(Stack S, element)
	if stack is empty OR element > top element
	    push(S, elem)
	else
	    temp = pop(S)
	    sortedInsert(S, element)
	    push(S, temp)
*/

public class SortStack{
	public void sortInsert(Stack<Integer> st, int x){
		if(st.isEmpty() || x > st.peek()){
			st.push(x);
			return;
		}
		int temp1 = st.pop();
		sortInsert(st, x);
		st.push(temp1);
	}
	
	public void sortStack(Stack<Integer> st){
		if(!st.isEmpty()){
			int temp = st.pop();
			sortStack(st);
			sortInsert(st, temp);
		}
	}
	
	public static void main(String[] args){
		Stack<Integer> s = new Stack<>();
		SortStack ss = new SortStack();
        s.push(30);
        s.push(-5);
        s.push(18);
        s.push(14);
        s.push(-3);
      
        System.out.println("Stack elements before sorting: ");
        common.print(s);
      
        ss.sortStack(s);
      
        System.out.println(" \n\nStack elements after sorting:");
        common.print(s);
	}
}