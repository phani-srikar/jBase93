package jBase93;
import java.util.*;
import java.util.LinkedList;

public class PostFromPreIn{
	public static Queue<Integer> Q = new LinkedList<Integer>();
	
	private static int searchInfix(int[] in, int val, int n){
		for(int i=0; i<n; i++){
			if(in[i] == val)
				return i;
		}
		return -1;
	}
	
	public static void postFromPreIn(int[] in, int[] pre, int n){
		int vroot = searchInfix(in,pre[0],n);
		if(vroot == -1)
			System.out.println("value " + pre[0] + " not found in infix but in prefix! ");
		else{
			if(vroot != 0)
				postFromPreIn(in, Arrays.copyOfRange(pre, 1, n), vroot);
			if(vroot != n-1)
				postFromPreIn(Arrays.copyOfRange(in, vroot+1, n), Arrays.copyOfRange(pre, vroot+1, n), n-vroot-1);
		}
		Q.add(pre[0]);
	}
	
	public static BTreeNode treeFromPostIn(int[] in, int[] post, int n){
		BTreeNode newNode = null;
		int vroot = searchInfix(in,post[n-1],n);
		if(vroot == -1)
			System.out.println("value " + post[n-1] + " not found in infix but in prefix! ");
		else{
			newNode = new BTreeNode(post[n-1]);
			if(vroot != 0)
				newNode.left = treeFromPostIn(in, Arrays.copyOfRange(post, 0, vroot), vroot);
			if(vroot != n-1)
				newNode.right = treeFromPostIn(Arrays.copyOfRange(in, vroot+1, n), Arrays.copyOfRange(post, vroot, n-1), n-vroot-1);
		}
		return newNode;
	}
	
	
	public static void main(String[] args){
		int in[] = {4, 2, 5, 1, 3, 6};
		int pre[] =  {1, 2, 4, 5, 3, 6};
		/*System.out.println("Postorder traversal - ");
		PostFromPreIn ppi= new PostFromPreIn();
		ppi.postFromPreIn(in, pre, in.length);
		common.print(Q);*/
		int post[] = {4,5,2,6,3,1};
		BTreeNode r = treeFromPostIn(in, post, in.length);
		BTree tree = new BTree(r);
		tree.printPreorderStack(tree.root);
	}
}