package jBase93;
import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.LinkedList;

public class LevelOrderZigZag extends BTree{
	
	public void printZigZag(){
		if(root == null)
			return;
		Stack<BTreeNode> s1 = new Stack<BTreeNode>();
		Stack<BTreeNode> s2 = new Stack<BTreeNode>();
		BTreeNode temp;
		s1.push(root);
		
		while(!s1.isEmpty() || !s2.isEmpty()){
			while(!s1.isEmpty()){
				temp = s1.peek();
				s1.pop();
				System.out.print(temp.data + " ");
				if(temp.right != null)
					s2.push(temp.right);
				if(temp.left != null)
					s2.push(temp.left);
			}
			while(!s2.isEmpty()){
				temp = s2.peek();
				s2.pop();
				System.out.print(temp.data + " ");
				if(temp.left != null)
					s1.push(temp.left);
				if(temp.right != null)
					s1.push(temp.right);
			}
		}
		
	}
	
	public void SimpleReverseLevelOrder(){
		/*			1
		 * 		2		3
		 * 	  4   5
		 * Out: 45231
		 * Use Queue to traverse tree(But push right first)
		 * and store the data in stack.
		 * After 1 => Q=3 2. After 2=> Q=5 4, s=1 3 2
		 * After 4=> s=1 3 2 5 4. pop and print.
		 */
		if(root == null)
			return;
		Stack<Integer> s = new Stack<Integer>();
		Queue<BTreeNode> Q = new LinkedList<BTreeNode>();
		BTreeNode temp = null;
		Q.add(root);
		
		while(!Q.isEmpty()){
			temp = Q.poll();
			s.push(temp.data);
			
			if(temp.right != null)
				Q.add(temp.right);
			if(temp.left != null)
				Q.add(temp.left);
		}
		
		while(!s.isEmpty())
			System.out.print(s.pop() + " ");
	}
	
	// Applicable on a perfect BTree only
	// https://www.geeksforgeeks.org/perfect-binary-tree-specific-level-order-traversal-set-2/
	public void reverseLRAlternateLevelOrder(){
		if(root == null)
			return;
		if(root.left == null)
		{
			System.out.println(root.data);
			return;
		}
		Stack<Integer> s = new Stack<Integer>();
		Queue<BTreeNode> Q = new LinkedList<BTreeNode>();
		BTreeNode node1;
		BTreeNode node2;
		s.push(root.data); // stack for reverse printing ie bottom->up
		Q.add(root.right);
		Q.add(root.left);
		
		while(!Q.isEmpty()){
			node1 = Q.poll();
			s.push(node1.data);
			node2 = Q.poll();
			s.push(node2.data);
			
			if(node1.left != null){
				Q.add(node1.left);
				Q.add(node2.right);
				Q.add(node1.right);
				Q.add(node2.left);
			}
		}
		
		while(!s.isEmpty())
			System.out.print(s.pop() + " ");
	}
	
	public void flipLRAlternateLevels(){
		/* Given tree: 
		    a
		 /     \
		b       c
		/  \     /  \
		d    e    f    g
		/ \  / \  / \  / \
		h  i j  k l  m  n  o 
		
		Modified tree:
		     a
		  /     \
		  c       b
		/  \     /  \
		d    e    f    g
		/ \  / \  / \  / \
		o  n m  l k  j  i  h 
		
		*/
		if(root == null)
			return;
		if(root.left == null)
		{
			System.out.println(root.data);
			return;
		}
		Queue<BTreeNode> Q = new LinkedList<BTreeNode>();
		BTreeNode node1;
		BTreeNode node2;
		boolean swapFlag = true;
		Q.add(root.left);
		Q.add(root.right);
		Q.add(null);
		
		while(!Q.isEmpty()){
			
			if(Q.peek() == null){
				Q.poll();
				Q.add(null);
				swapFlag = !swapFlag;
			}
			
			node1 = Q.poll();
			node2 = Q.poll();
			if(swapFlag){
				int temp = node1.data;
				node1.data = node2.data;
				node2.data = temp;
			}
			
			if(node1 != null && node2 != null && node1.left != null){
				Q.add(node1.left);
				Q.add(node2.right);
				Q.add(node1.right);
				Q.add(node2.left);
			}
		}
	}
	
	public void DiagonalIterative(){
		/*				8
		 * 			3		10
		 * 		   1	  6   14
		 *               4 7 13
		 *  
		 *  Out: 8 10 14 3 6 7 13 1 4
		 *  Print all that is to right and push to queue left children on the path
		 */
		if(root == null)
			return;
		Queue<BTreeNode> Q = new LinkedList<BTreeNode>();
		Q.add(root);
		BTreeNode temp = null;
		while(!Q.isEmpty() || temp != null){
			if(temp == null)
				temp = Q.poll();
			System.out.print(temp.data + " ");
			if(temp.left != null)
				Q.add(temp.left);
			temp = temp.right;
		}
	}
	
	private void DiagonalRecursiveUtil(BTreeNode r, int level, HashMap<Integer,Vector<Integer>> map){
		if(r == null)
			return;
		
		if(map.containsKey(level))
			map.get(level).add(r.data);
		else{
			Vector<Integer> v = new Vector<Integer>();
			v.add(r.data);
			map.put(level, v);
		}
		DiagonalRecursiveUtil(r.left,level+1,map);
		DiagonalRecursiveUtil(r.right,level,map);
	}
	
	public void DiagonalRecursive(){
		// create a map of vectors to store Diagonal elements
        HashMap<Integer,Vector<Integer>> map = new HashMap<>();
        DiagonalRecursiveUtil(root, 0, map);
         
        System.out.println("Diagonal Traversal of Binnary Tree - ");
        for (Entry<Integer, Vector<Integer>> entry : map.entrySet())
        {
            System.out.println(entry.getValue());
        }
	}
	
	public static void main(String[] args){
        
		LevelOrderZigZag tree = new LevelOrderZigZag();
		tree.root = new BTreeNode(1);
        tree.root.left = new BTreeNode(2);
        tree.root.right = new BTreeNode(3);
        tree.root.left.left = new BTreeNode(4);
        tree.root.left.right = new BTreeNode(5);
        
        System.out.println("Simple Reverse Level Order traversal of BTree is ");
        tree.SimpleReverseLevelOrder();
        
        tree.root.right.left = new BTreeNode(6);
        tree.root.right.right = new BTreeNode(7);
        System.out.println("");
        System.out.println("");
        System.out.println("Spiral Order traversal of Binary Tree is ");
        tree.printZigZag();
        
        tree = new LevelOrderZigZag();
        for(int i=1; i<32; i++)
        	tree.insert(i);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Level Order before reverseAlt is: ");
        tree.printLevelOrder();
        System.out.println("");
        System.out.println("Level Order after reverseAlt is: ");
        tree.reverseLRAlternateLevelOrder();
        
        tree = new LevelOrderZigZag();
        for(int i=1; i<16; i++)
        	tree.insert(i);
        System.out.println("");
        System.out.println("");
        System.out.println("Level Order before flipLRAlternateLevels is: ");
        tree.printLevelOrder();
        System.out.println("");
        System.out.println("Level Order after flipLRAlternateLevels is: ");
        tree.flipLRAlternateLevels();
        tree.printLevelOrder();
        System.out.println("");
        System.out.println("");
        
        LevelOrderZigZag tree1 = new LevelOrderZigZag();
        tree1.root = new BTreeNode(8);
        tree1.root.left = new BTreeNode(3);
        tree1.root.right = new BTreeNode(10);
        tree1.root.left.left = new BTreeNode(1);
        tree1.root.left.right = new BTreeNode(6);
        tree1.root.right.right = new BTreeNode(14);
        tree1.root.right.right.left = new BTreeNode(13);
        tree1.root.left.right.left = new BTreeNode(4);
        tree1.root.left.right.right = new BTreeNode(7);
        System.out.println("Tree printed diagonally Iterative approach - ");
        tree1.DiagonalIterative();
        System.out.println("");
        System.out.println("Tree printed diagonally Recursive approach - ");
        tree1.DiagonalRecursive();
        System.out.println("");
	}
}












