package jBase93;
import java.util.*;
import java.util.LinkedList;

public class BTree{
	BTreeNode root;
	static BTreeNode inordersuctemp = null;
	public BTree(){
		root = null;
	}
	
	public BTree(int val){
		root = new BTreeNode(val);
	}
	
	public BTree(BTreeNode head){
		root = head;
	}
	
	public void printLevelOrder(){
		if(root == null)
			return;
		Queue<BTreeNode> q = new LinkedList<BTreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			BTreeNode curr = q.poll();
			System.out.print(curr.data + " ");
			if(curr.left != null)
				q.add(curr.left);
			if(curr.right != null)
				q.add(curr.right);
		}
	}
	
	public void printInorder(BTreeNode r){
		if(r != null){
			printInorder(r.left);
			System.out.print(r.data + " ");
			printInorder(r.right);
		}
	}
	
	public void printInorderStack(BTreeNode r){
		/*Initialize current node as root
			3) Push the current node to S and set current = current->left until current is NULL
			4) If current is NULL and stack is not empty then 
			     a) Pop the top item from stack.
			     b) Print the popped item, set current = popped_item->right 
			     c) Go to step 3.
			5) If current is NULL and stack is empty then we are done.
			https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
		*/
		
		Stack<BTreeNode> st = new Stack<BTreeNode>();
		if(r == null)
			return;
		while(r != null){
			st.push(r);
			r = r.left;
		}
		while(!st.isEmpty()){
			BTreeNode curr = st.pop();
			System.out.print(curr.data + " ");
			
			if(curr.right != null){
				curr = curr.right;
				while(curr != null){
					st.push(curr);
					curr = curr.left;
				}
			}
				
		}
	}
	
	public void printInorderMorris(BTreeNode r){
		if(r == null)
			return;
		BTreeNode curr = r;
		BTreeNode temp;
		
		while(curr != null){
			if(curr.left == null){
				System.out.print(curr.data + " ");
				curr = curr.right;
			}
			else{
				/* r.left exists. Then:
				 * Find in-order predecessor of r (right most child of r.left) and set right of it to r.
				 * Set r=r.left
				 * If right of that is not null and already set to r, then remove this connection.
				 * set r=r.right
				 */
				temp = curr.left;
				while(temp.right != null && temp.right != curr)
					temp = temp.right;
				
				if(temp.right == null){
					// Ony shift this print statement to get pre-order morris traversal
					//System.out.print(curr.data + " ");
					temp.right = curr;
					curr = curr.left;
				}
				else{
					temp.right = null;
					System.out.print(curr.data + " ");
					curr = curr.right;
				}
			}
		}
		
	}
	
	public void printPreorderStack(BTreeNode r){
		Stack<BTreeNode> st = new Stack<BTreeNode>();
		if(r == null)
			return;
		st.push(r);
		
		while(!st.isEmpty()){
			BTreeNode temp = st.peek();
			System.out.print(temp.data + " ");
			st.pop();
			
			if(temp.right != null)
				st.push(temp.right);
			if(temp.left != null)
				st.push(temp.left);
		}
	}
	
	public void printPostorderStack(){
		Stack<BTreeNode> s1 = new Stack<BTreeNode>();
		Stack<BTreeNode> s2 = new Stack<BTreeNode>();
		if(root == null)
			return;
		s1.push(root);
		
		while(!s1.isEmpty()){
			BTreeNode temp = s1.pop();
			s2.push(temp); // pop the item from s1 and push it to s2.
			
			if(temp.left != null)
				s1.push(temp.left);  // push left then right of popped node into s1.
			if(temp.right != null)
				s1.push(temp.right);
		}
		
		while(!s2.isEmpty())
			System.out.print(s2.pop().data + " ");
		System.out.println("");
	}
	
	public void insert(int val){
		if(root == null)
			root = new BTreeNode(val);
		else{
			// Using LevelOrder traversal, find the first Half-Node and insert there.
			Queue<BTreeNode> q = new LinkedList<BTreeNode>();
			q.add(root);
			while(!q.isEmpty()){
				BTreeNode curr = q.poll();
				if(curr.left == null){
					curr.left = new BTreeNode(val);
					return;
				}
				else q.add(curr.left);
				if(curr.right == null){
					curr.right = new BTreeNode(val);
					return;
				}
				else q.add(curr.right);
			}
		}
	}
	
	private void deleteDeepest(BTreeNode deep){
		Queue<BTreeNode> q = new LinkedList<BTreeNode>();
		BTreeNode curr = null;
		if(root == deep){
			root = null;
			return;
		}
		q.add(root);
		while(!q.isEmpty()){
			curr = q.poll();
			
			if(curr.left == deep)
				curr.left = null;
			else if(curr.right == deep)
				curr.right = null;
			
			if(curr.left != null)
				q.add(curr.left);
			if(curr.right != null)
				q.add(curr.right);
		}
	}
	
	public void delete(int val){
		if(root == null)
			return;
		else{
			/* Using LevelOrder traversal,
			 * Find the node to be deleted and replace its value with the
			 * value of the deepest node (right-most) and delete the deepest.
			 */
			
			Queue<BTreeNode> q = new LinkedList<BTreeNode>();
			BTreeNode del = null;
			BTreeNode curr = null;
			q.add(root);
			while(!q.isEmpty()){
				curr = q.poll();
				if(curr.data == val)
					del = curr;
				
				if(curr.left != null)
					q.add(curr.left);
				if(curr.right != null)
					q.add(curr.right);
			}
			
			if(del == null){
				System.out.println("Error in delete: value not found");
				return;
			}
			else{
				del.data = curr.data;
				deleteDeepest(curr); // cannot simply set curr=null; have to set parent pointer pointing to null;
			}
		}
	}
	
	private BTreeNode inorderSuccessorRecursive(BTreeNode r, BTreeNode x){
		if(r == null)
			return null;

		if((r == x) || ((inordersuctemp = inorderSuccessorRecursive(r.left,x)) != null)
					|| ((inordersuctemp = inorderSuccessorRecursive(r.right,x))) != null){
			
			if(inordersuctemp != null){
				if(r.left == inordersuctemp){
					System.out.println("InOrder Successor of "+x.data+" is: " + r.data);
					return null;
				}
			}
			return r;
		}
		return null;
	}
	
	public void inorderSuccessor(BTreeNode r){
		/* If r.right exists=> return leftmost child of r.right.
		 * Else, Find that ancestor of r to which r is to the left side.
		 * For this: Use recursive approach and travel a level up comparing 
		 * if child is to left of parent.
		 */
		BTreeNode temp;
		if(r.right != null){
			temp = r.right;
			while(temp.left != null)
				temp = temp.left;
			System.out.println("InOrder Successor of "+r.data+" is: " + temp.data);
			return;
		}
		else{
			// Another check that's not included here:
			// Check if r is the rightmost node in the tree. If yes return null.
			inorderSuccessorRecursive(root, r);
		}
		
	}
	
	public static void main(String[] args){
		BTree btree = new BTree();
		for(int i=1; i<6; i++)
			btree.insert(i);
		
		/*btree.root = new BTreeNode(1);
		btree.root.left = new BTreeNode(2);
        btree.root.right = new BTreeNode(3);
        btree.root.left.left = new BTreeNode(4);
        btree.root.left.right = new BTreeNode(5);*/
        
        /* 			1
         * 		2		3
         *    4   5
        */
 
        System.out.println("Level order traversal of binary tree is - ");
        btree.printLevelOrder();
        System.out.println("");
        
        System.out.println("In-order Recursive traversal of binary tree is - ");
        btree.printInorder(btree.root);
        System.out.println("");
        
        System.out.println("In-order Non-Recursive traversal using Stack is - ");
        btree.printInorderStack(btree.root);
        System.out.println("");
        
        System.out.println("In-order Non-Recursive traversal Without Stack is - ");
        btree.printInorderMorris(btree.root);
        System.out.println("");
        
        System.out.println("Pre-order Non-Recursive traversal Using Stack is - ");
        btree.printPreorderStack(btree.root);
        System.out.println("");
        
        System.out.println("Post-order Non-Recursive traversal Using Stack is - ");
        btree.printPostorderStack();
        System.out.println("");
        
        System.out.println("BTree InOrder before insertion - ");
        btree.printInorder(btree.root);
        System.out.println("");
        btree.insert(6);
        System.out.println("BTree InOrder after insertion - ");
        btree.printInorder(btree.root);
        System.out.println("");
        
        btree.delete(6);
        System.out.println("BTree InOrder after deletion - ");
        btree.printInorder(btree.root);
        System.out.println("");
        
        btree.inorderSuccessor(btree.root.left.right);
        System.out.println("");
	}
}