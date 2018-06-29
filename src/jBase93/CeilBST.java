package jBase93;
import java.util.*;

public class CeilBST extends BTree{
	public int ceilBST(BTreeNode r, int val){
		int temp = Integer.MAX_VALUE;
		while(r != null){
			if(r.data == val)
				return val;
			else if(r.data > val){
				/* There may be a value less than r.data and greater than val
				 * in the left subtree. In that case that becomes the ceil. So 
				 * update temp to store the value > than but closer to val!
				 */
				temp = Math.min(r.data, temp);
				r = r.left;
			}
			else r = r.right; // move and check if right subtree has value >= val
		}
		if(temp == Integer.MAX_VALUE)
			temp = -1;
		return temp;
	}
	
	public static void main(String[] args){
		CeilBST tree = new CeilBST();
        tree.root = new BTreeNode(8);
        tree.root.left = new BTreeNode(4);
        tree.root.right = new BTreeNode(12);
        tree.root.left.left = new BTreeNode(2);
        tree.root.left.right = new BTreeNode(6);
        tree.root.right.left = new BTreeNode(10);
        tree.root.right.right = new BTreeNode(14);
        //System.out.println(tree.ceilBST(tree.root, 1));
        for (int i = 0; i < 16; i++) {
            System.out.println(i + " " + tree.ceilBST(tree.root, i));
        }
	}
}