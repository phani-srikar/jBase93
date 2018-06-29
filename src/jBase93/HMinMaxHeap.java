package jBase93;
import java.util.*;

public class HMinMaxHeap{
	class LNode{
		int data;
		int minheapIndex;
		int maxheapIndex;
		LNode next;
		LNode prev;
		
		public LNode(int pData) {
			data = pData;
			minheapIndex = -1;
			maxheapIndex = -1;
			next = null;
			prev = null;
		}
	}
	class LList{
		LNode head;
		
		public LList() {
			this.head = null;
		}
	}
	
	class Minheap{
		int size;
		int capacity;
		LNode[] arr;
		
		public Minheap(int pCapacity) {
			this.capacity = pCapacity;
			this.size = 0;
			arr = new LNode[pCapacity];
		}
	}
	class Maxheap{
		int size;
		int capacity;
		LNode[] arr;
		
		public Maxheap(int pCapacity) {
			this.capacity = pCapacity;
			this.size = 0;
			arr = new LNode[pCapacity];
		}
	}
	
	LList LL;
	Minheap minheap;
	Maxheap maxheap;
	
	public HMinMaxHeap(int pCapacity) {
		LL = new LList();
		minheap = new Minheap(pCapacity);
		maxheap = new Maxheap(pCapacity);
	}
	
	public void InsertAtHead(LNode pNode) {
		if(LL.head==null) {
			LL.head = pNode;
			return;
		}
		pNode.next = LL.head;
		LL.head.prev = pNode;
		LL.head = pNode;
	}
	public void InsertMinHeap(LNode pNode) {
		if(minheap.size == minheap.capacity)
			return;
		minheap.size++;
		//percolate Up!
		int i = minheap.size-1;
		while(i>0 && pNode.data<minheap.arr[(i-1)/2].data) {
			minheap.arr[i] = minheap.arr[(i-1)/2];
			minheap.arr[i].minheapIndex = i;
			i = (i-1)/2;
		}
		minheap.arr[i] = pNode;
		minheap.arr[i].minheapIndex = i;
	}
	public void InsertMaxHeap(LNode pNode) {
		if(maxheap.size == maxheap.capacity)
			return;
		maxheap.size++;
		//percolate Up!
		int i = maxheap.size-1;
		while(i>0 && pNode.data>maxheap.arr[(i-1)/2].data) {
			maxheap.arr[i] = maxheap.arr[(i-1)/2];
			maxheap.arr[i].maxheapIndex = i;
			i = (i-1)/2;
		}
		maxheap.arr[i] = pNode;
		maxheap.arr[i].maxheapIndex = i;
	}
	
	public void Insert(int data) {
		LNode vnode = new LNode(data);
		InsertAtHead(vnode);
		InsertMinHeap(vnode);
		InsertMaxHeap(vnode);
	}
	
	public int findMax() {
		if(maxheap.size == 0)
			return -1;
		else return maxheap.arr[0].data;
	}
	public int findMin() {
		if(minheap.size == 0)
			return -1;
		else return minheap.arr[0].data;
	}
	
	public void deleteMax() {
		if(maxheap.size == 0)
			return;
		LNode vnode = maxheap.arr[0];
		//set last element as root and call maxheapify
		maxheap.arr[0] = maxheap.arr[maxheap.size-1];
		maxheap.arr[0].maxheapIndex = 0;
		maxheap.size--;
		maxheapify(0);
		
		//The index of this node in minheap is stored inside! Use it to delete.
		minheap.arr[vnode.minheapIndex] = minheap.arr[minheap.size-1];
		minheap.arr[vnode.minheapIndex].minheapIndex = vnode.minheapIndex;
		minheap.size--;
		minheapify(vnode.minheapIndex);
		
		//also delete from the linkedList
		deleteInLL(vnode);
	}
	
	public void maxheapify(int i) {
		//percolate down for max heap
		int l = (2*i)+1;
		int r = (2*i)+2;
		int max = i;
		if(i<0 || i>maxheap.size)
			return;
		if(l<maxheap.size && maxheap.arr[l].data > maxheap.arr[max].data)
			max = l;
		if(r<maxheap.size && maxheap.arr[r].data > maxheap.arr[max].data)
			max = r;
		if(max != i) {
			LNode temp = maxheap.arr[i];
			maxheap.arr[i] = maxheap.arr[max];
			maxheap.arr[i].maxheapIndex = maxheap.arr[max].maxheapIndex;
			maxheap.arr[max] = temp;
			maxheap.arr[max].maxheapIndex = temp.maxheapIndex;
			maxheapify(max);
		}
	}
	
	public void minheapify(int i) {
		//percolate down for max heap
		int l = (2*i)+1;
		int r = (2*i)+2;
		int min = i;
		if(i<0 || i>minheap.size)
			return;
		if(l<minheap.size && minheap.arr[l].data < minheap.arr[min].data)
			min = l;
		if(r<minheap.size && minheap.arr[r].data > minheap.arr[min].data)
			min = r;
		if(min != i) {
			LNode temp = minheap.arr[i];
			minheap.arr[i] = minheap.arr[min];
			minheap.arr[i].minheapIndex = minheap.arr[min].minheapIndex;
			minheap.arr[min] = temp;
			minheap.arr[min].minheapIndex = temp.minheapIndex;
			minheapify(min);
		}
	}
	
	public void deleteInLL(LNode pNode) {
		if(LL.head == null || LL.head.next == null) // 1 or 0 nodes
			return;
		else if(pNode.prev == null) { // first node
			LL.head = pNode.next;
			LL.head.prev = null;
			return;
		}
		pNode.prev.next = pNode.next;
		pNode.next.prev = pNode.prev;
	}
	
	public static void main(String[] args) {
		HMinMaxHeap mmheap = new HMinMaxHeap(20);
		mmheap.Insert(10);
		mmheap.Insert(20);
		mmheap.Insert(30);
		mmheap.Insert(40);
		mmheap.Insert(50);
	 
	    System.out.println("Maximum = " + mmheap.findMax());
	    System.out.println("Minimum = " + mmheap.findMin());
	 
	    mmheap.deleteMax();  // 50 is deleted
	    System.out.println("After deleteMax()");
	    System.out.println("Maximum = " + mmheap.findMax());
	    System.out.println("Minimum = " + mmheap.findMin());
	 
	    /*mmheap.deleteMin(); // 10 is deleted
	    System.out.println("After deleteMin()\n");
	    System.out.println("Maximum = " + mmheap.findMax());
	    System.out.println("Minimum = " + mmheap.findMin());
	 
	    Delete(40); // 40 is deleted
	    System.out.println("After Delete()");
	    System.out.println("Maximum = " + mmheap.findMax());
	    System.out.println("Minimum = " + mmheap.findMin());*/
	}
}













