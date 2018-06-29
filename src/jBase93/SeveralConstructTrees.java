package jBase93;
import java.util.*;
import java.util.LinkedList;

public class SeveralConstructTrees extends BTree{
	
	public void fromParentArrayUtil(int[] parent, int i, BTreeNode[] created){
		if(created[i] != null) //Already created
			return;
		BTreeNode temp = new BTreeNode(i);
		if(parent[i] == -1){ //check root
			root = temp;
			created[i] = temp;
			return;
		}
		if(created[parent[i]] == null)
			//construct parent if not exists
			fromParentArrayUtil(parent, parent[i], created);
		
		BTreeNode vparent = created[parent[i]];
		//Appropriately set this node to left or child of its parent.
		if(vparent.left == null)
			vparent.left = temp;
		else vparent.right = temp;
		created[i] = temp;
	}
	public BTreeNode fromParentArray(int[] parent){
		/*	Input: parent[] = {1, 5, 5, 2, 2, -1, 3}
			Output: root of below tree
			          5
			        /  \
			       1    2
			      /    / \
			     0    3   4
			         /
			        6 
		 */
		int n = parent.length;
		BTreeNode[] created = new BTreeNode[n];
		for(int i=0; i<n; i++)
			created[i] = null; // For efficient implementation, track nodes that are already created.
		for(int i=0; i<n; i++)
			fromParentArrayUtil(parent, i, created);
		return root;
	}
	
	public static void main(String[] args){
		SeveralConstructTrees construct = new SeveralConstructTrees();
        int parent[] = new int[]{-1, 0, 0, 1, 1, 3, 5};
        construct.root = construct.fromParentArray(parent);
        System.out.println("Inorder traversal of constructed tree - ");
        construct.printInorder(construct.root);
	}
}