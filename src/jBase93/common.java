package jBase93;
import java.util.*;

public class common{
	public static void print(int[] arr){
		System.out.println(Arrays.toString(arr));
	}
	
	public static<T> void print(Stack<T> st){
		ListIterator<T> lt = st.listIterator();
	       // printing from bottom to top i.e as stored
	       while(lt.hasNext())
	           System.out.print(lt.next()+" ");
	       System.out.println("");
	}
	
	public static<T> void print(Queue<T> q){
		Iterator<T> lt = q.iterator();
	       // printing from bottom to top i.e as stored
	       while(lt.hasNext())
	           System.out.print(lt.next()+" ");
	       System.out.println("");
	}
	
	public static void print(Hmyheap mhp){
		for(int i=0; i<mhp.size; i++)
			System.out.print(mhp.arr[i] + " ");
		System.out.println("");
	}
	
	public static void print(int[][] arr) {
		int r = arr.length;
		int c = arr[0].length;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
				
	}
}