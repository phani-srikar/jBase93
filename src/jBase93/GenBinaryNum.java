package jBase93;
import java.util.*;
import java.util.LinkedList;

public class GenBinaryNum{
	public void genBinaryNum(int n){
		Queue<String> q = new LinkedList<String>();
		q.add("1");
		while(n-- > 0){
			String temp = q.peek();
			System.out.println(temp);
			q.poll();
			q.add(temp + "0");
			q.add(temp + "1");
		}
	}
	
	public static void main(String[] args){
		int n = 10;
		GenBinaryNum gbn = new GenBinaryNum();
		gbn.genBinaryNum(n);
	}
}