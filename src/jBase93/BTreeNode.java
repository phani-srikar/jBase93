package jBase93;
import java.util.*;
import java.util.LinkedList;

public class BTreeNode{
	int data;
	BTreeNode left;
	BTreeNode right;
	
	public BTreeNode(int val){
		data = val;
		left = right = null;
	}
}