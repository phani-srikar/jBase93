package jBase93;
import java.util.*;

public class SortQueue{
	public static void sortInsert(Queue<Integer> q, int x){
		/* Example: 1 4 0 5 queue: recursively pop all elements and pass one by one 
		 * to sortInsert. First passed = 5; Insert q=5.
		 * Next 0 -> Insert q=5 0. Pop&PushBack all elements greater than 0;
		 * q = 0 5. Next 4 -> Pop all elements less than 4 and push back.
		 * q = 5 0. Insert 4 => q=5 0 4. Pop and pushBack all elements greater than 4.
		 * q = 0 4 5. Next -> 1. Pop&PushBack 0. => q=4 5 0. Insert 1.
		 * Pop$PushBack 4,5 => q=0 1 4 5. Sorted! YAAY!!
		 */
		int n = q.size();
		int i,j;
		for(i=0; i<n; i++){
			if(q.peek() <= x)
				q.add(q.poll());
			else break;
		}
		q.add(x);
		for(j=i; j<n; j++)
			q.add(q.poll());
	}
	
	public static void sortQueue(Queue<Integer> q){
		if(!q.isEmpty()){
			int temp = q.poll();
			sortQueue(q);
			sortInsert(q, temp);
		}
	}
	
	public static void main(String[] args){
		Queue<Integer> Q = new java.util.LinkedList<Integer>();
		Q.add(30);
		Q.add(11);
		Q.add(4);
		Q.add(15);
		Q.add(4);
		common.print(Q);
		sortQueue(Q);
		System.out.println("Sorted Queue:");
		common.print(Q);
	}
	
}