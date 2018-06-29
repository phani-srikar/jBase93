package jBase93;
import java.util.*;

public class HmyMaxHeap extends Hmyheap{
	public HmyMaxHeap(){
		super();
	}
	public HmyMaxHeap(int[] varr){
		super(varr);
	}
	
	public int getMaximum(){
		if(this.size == 0)
			return -1;
		else return arr[0];
	}
	
	public void percolateUp(int i){
		if(i<0 || i>size-1 || getParentIndex(i)==-1
		   || arr[getParentIndex(i)] >= arr[i])
			return;
		else{
			int vtemp = arr[i];
			arr[i] = arr[getParentIndex(i)];
			arr[getParentIndex(i)] = vtemp;
			percolateUp(getParentIndex(i));
		}
	}
	
	public void percolateDown(int n, int i){
		if(i<0 || i>n-1)
			return;
		int l,r,max,temp;
		l = getLeftChildIndex(i);
		r = getRightChildIndex(i);
		if(l!=-1 && l<n && arr[l]>arr[i])
			max = l;
		else max = i;
		if(r!=-1 && r<n && arr[r]>arr[max])
			max = r;
		if(max != i){
			temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
			percolateDown(n, max);
		}
	}
	
	public static void main(String[] args){
		int[] varr = new int[]{12, 11, 13, 5, 6, 7};
		HmyMaxHeap maxh = new HmyMaxHeap(varr);
		common.print(maxh);
		maxh.insert(14);
		maxh.insert(10);
		common.print(maxh);
		maxh.delete(0);
		maxh.delete(3);
		common.print(maxh);
		maxh.heapsort();
		common.print(maxh);
	}
}