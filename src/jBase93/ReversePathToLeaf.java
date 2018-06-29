package jBase93;
import java.util.*;
import java.util.LinkedList;

// https://www.geeksforgeeks.org/reverse-tree-path/

public class ReversePathToLeaf extends BTree{
	private static LinkedList<Integer> L = new LinkedList<Integer>();
	public static boolean reversePathToLeaf(BTreeNode r, int val){
		if(r == null)
			return false;
		L.add(r.data); //store the nodes in the path
		if(r.data == val || reversePathToLeaf(r.left,val) || reversePathToLeaf(r.right,val)){
			r.data = L.removeFirst(); //Replace the data from bottom nodes with top nodes in the path
			return true;
		}
		else{
			L.removeLast(); // remove the node if it doesnt fall in the path
			return false;
		}
	}
	
	public static void main(String[] args){
		ReversePathToLeaf tree = new ReversePathToLeaf();
		BTreeNode vroot = new BTreeNode(7);
	    vroot.left = new BTreeNode(6);
	    vroot.right = new BTreeNode(5);
	    vroot.left.left = new BTreeNode(4);
	    vroot.left.right = new BTreeNode(3);
	    vroot.right.left = new BTreeNode(2);
	    vroot.right.right = new BTreeNode(1);
	    System.out.println("In-order before reverse :" );
	    tree.printInorder(vroot);
	    reversePathToLeaf(vroot, 4);
	    System.out.println("In-order after reverse :" );
	    tree.printInorder(vroot);
	}
}