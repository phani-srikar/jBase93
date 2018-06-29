package jBase93;
import java.util.*;

public abstract class Hmyheap{
	int[] arr;
	int size;
	int capacity = 20;
	
	public Hmyheap(){
		this.arr = new int[this.capacity];
		this.size = 0;
	}
	
	public Hmyheap(int[] varr){
		arr = varr;
		capacity = varr.length;
		size = varr.length;
		//Heapify the heap!!
		// percolate down all nodes except leaf nodes
		for (int i = size / 2 - 1; i >= 0; i--)
            percolateDown(size, i);
	}
	
	public int getParentIndex(int i){
		if(i<=0 || i>size-1)
			return -1;
		else return (i-1)/2;
	}
	
	public int getLeftChildIndex(int i){
		int vindex = (2*i)+1;
		if(vindex<=0 || vindex>size-1)
			return -1;
		else return vindex;
	}
	
	public int getRightChildIndex(int i){
		int vindex = (2*i)+2;
		if(vindex<=0 || vindex>size-1)
			return -1;
		else return vindex;
	}
	
	public abstract void percolateUp(int i);
	public abstract void percolateDown(int n, int i);
	
	@SuppressWarnings("unused")
	public void resize(){
		int[] varr = new int[this.capacity * 2];
		if(varr == null){
			System.out.println("Out of memory to resize");
			return;
		}
		this.capacity = this.capacity * 2;
		for(int i=0; i<this.arr.length; i++)
			varr[i] = this.arr[i];
		this.arr = varr;
	}
	
	public void insert(int data){
		if(this.size == this.capacity)
			this.resize();
		this.arr[this.size] = data;
		this.size++;
		this.percolateUp(this.size-1);
	}
	
	public int delete(int i){
		if(size == 0 || size < i || i < 0)
			return -1;
		int vtemp = arr[i];
		arr[i] = arr[size-1];
		size = size-1;
		percolateDown(size,i);
		return vtemp;
	}
	
	//below procedure gives ascending order for max-heap
	// and descending order for min-heap!!
	public void heapsort(){
		if(size == 0)
			return;
		//Exchange first and last elements of heap
		// and heapify the sub-array excluding already sorted last elements of array
		for(int i=size-1; i>=0; i--){
			int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // call max heapify on the reduced heap
            percolateDown(i, 0);
		}
	}
}







