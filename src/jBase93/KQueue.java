package jBase93;
import java.util.*;

public class KQueue{
	int arr[];
	int next[];
	int front[];
	int rear[];
	int n,k,free;
	
	public KQueue(int n1, int k1){
		n = n1;
		k = k1;
		free = 0;
		arr = new int[n];
		next = new int[n];
		front = new int[k];
		rear = new int[k];
		for(int i=0; i<n-1; i++)
			next[i] = i+1;
		next[n-1] = -1;
		for(int i=0; i<k; i++)
			front[i] = -1;
	}
	
	public void enqueue(int value, int k1){
		if(free == -1){
			System.out.println("Overflow");
			return;
		}
		else{
			int temp = free;
			free = next[free];
			arr[temp] = value;
			if(front[k1] == -1)   // missed :(
				front[k1] = temp;
			else next[rear[k1]] = temp;
			rear[k1] = temp;
			next[temp] = -1;   // missed :(
		}
	}
	
	public int dequeue(int k1){
		if(front[k1] == -1){
			System.out.println("Underflow");
			return -1;
		}
		else{
			int temp = front[k1];
			front[k1] = next[front[k1]];
			next[temp] = free; // missed :(
			free = temp;
			return arr[temp];
		}
	}
	
	public static void main(String[] args){
		int k = 3, n = 10;
	    KQueue ks = new KQueue(n,k);
	 
	    // Let us put some items in queue number 2
	    ks.enqueue(15, 2);
	    ks.enqueue(45, 2);
	 
	    // Let us put some items in queue number 1
	    ks.enqueue(17, 1);
	    ks.enqueue(49, 1);
	    ks.enqueue(39, 1);
	 
	    // Let us put some items in queue number 0
	    ks.enqueue(11, 0);
	    ks.enqueue(9, 0);
	    ks.enqueue(7, 0);
	    System.out.println("Dequeued element from queue 2 is " + ks.dequeue(2));
	    System.out.println("Dequeued element from queue 1 is " + ks.dequeue(1));
	    System.out.println("Dequeued element from queue 0 is " + ks.dequeue(0));
	}
	
}






