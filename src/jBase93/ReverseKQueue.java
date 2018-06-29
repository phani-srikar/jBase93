package jBase93;
import java.util.*;
import java.util.LinkedList;

/* q= 12345; k=2
 * call reverseK 5/2=2 times;
 * After 1: 34521; After 2: 52143. 
 * Then pop&PushBack 5%2=1 remaining elem inside reverseKQueue => q=21435.
 */
public class ReverseKQueue{
	public void reverseK(Queue<Integer> q, int k, int currentpass, int totalpasses){
		/* Recursively call same function till total passes.
		 * Each Pass: pop & push k elem to stack; Pop from stack and pushBack again.
		 */
		if(currentpass > totalpasses)
			return;
		currentpass++;
		Stack<Integer> st = new Stack<Integer>();
		for(int i=0; i<k; i++)
			st.push(q.poll());
		for(int i=0; i<k; i++)
			q.add(st.pop());
		reverseK(q, k, currentpass, totalpasses);
	}
	
	public void reverseKQueue(Queue<Integer> q, int k){
		int n = q.size();
		reverseK(q, k, 1, n/k);
		int num_remaining = n%k;
		while(num_remaining > 0){
			q.add(q.poll());
			num_remaining--;
		}
	}
	
	public static void main(String[] args){
		Queue<Integer> Q = new LinkedList<Integer>();
		for(int i=0; i<5; i++)
			Q.add(i+1);
		common.print(Q);
		ReverseKQueue rkq = new ReverseKQueue();
		rkq.reverseKQueue(Q, 2);
		System.out.println("After k reverse:");
		common.print(Q);
	}
}