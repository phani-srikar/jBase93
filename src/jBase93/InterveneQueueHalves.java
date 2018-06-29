package jBase93;
import java.util.*;
import java.util.LinkedList;

/* Input :  1 2 3 4  Even sized queue!
Output : 1 3 2 4
Hint :::: Use Stack!
*/

public class InterveneQueueHalves{
	public void interveneQueueHalves(Queue<Integer> q){
		/* Pop&Push first half to stack -> q=3 4 s= 1 2
		 * PushBack s to q -> q = 3 4 2 1. Pop&PushBack first half -> 2 1 3 4
		 * Pop$Push First half into stack again -> q=3 4 ; s = 2 1
		 * PushBack alternatively from s & q => 341, 413, 4132, 1324.
		 */
		Stack<Integer> st = new Stack<Integer>();
		int mid = q.size()/2;
		for(int i=0; i< mid; i++)
			st.push(q.poll());
		for(int i=0; i< mid; i++)
			q.add(st.pop());
		for(int i=0; i< mid; i++)
			q.add(q.poll());
		for(int i=0; i< mid; i++)
			st.push(q.poll());
		for(int i=0; i< mid; i++){
			q.add(st.pop());
			q.add(q.poll());
		}
	}
	
	public static void main(String[] args){
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(1);
		Q.add(2);
		Q.add(3);
		Q.add(4);
		common.print(Q);
		InterveneQueueHalves iqh = new InterveneQueueHalves();
		iqh.interveneQueueHalves(Q);
		common.print(Q);
	}
}