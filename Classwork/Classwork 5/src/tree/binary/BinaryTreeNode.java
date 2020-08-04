 
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  BinaryTreeNode.java
 *  Purpose       :  To do the classwork in order to not fail
 *  @author       :  Ona Igbinedion
 *  @author       :  Ameya Mellacheruvu
 *  @author       :  Elise Sawan
 *  @author       :  Andrew Forney( all methods except sameTree and doubleTree) 
 *  Date written  :  2019-10-30
 *  Notes         :  None right now.  We'll add some as they occur.
 *  Warnings      :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ---------------  -----------------------------------------------------------
 *  @version 1.0.0  2019-10-30  Ona/Ameya/Elise  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package tree.binary;

public class BinaryTreeNode {
    
    private String data;
    private BinaryTreeNode left, right;
    
    /**
     * Creates a new BinaryTreeNode that can be linked to
     * others to form a tree of arbitrary depth
     * @param s The data to store at this tree node
     */
    
    BinaryTreeNode (String s) {
        data = s;
    }
    
    /**
     * Creates a new BinaryTreeNode storing data String s
     * at the left or right child of the current one.
     * @param s The data to store
     * @param child String "L" or "R" indicating desired child
     */
    
    public void add (String s, String child) {
        if (child.equals("L")) {
            left = new BinaryTreeNode(s);
        } else if (child.equals("R")) {
            right = new BinaryTreeNode(s);
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Returns the BinaryTreeNode located at the desired
     * location relative to this one.
     * @param child String "L" or "R" indicating desired child
     * @return The BinaryTreeNode at that position
     */
    
    public BinaryTreeNode getChild (String child) {
        return (child.equals("L")) ? left : right;
    }
    
    /**
     * Returns this node's String data
     * @return The data stored
     */
    
    public String getString () {
        return data;
    }
    
    /**
     * Doubles the tree rooted at the node on which this method
     * is called, creating a duplicate of each node, storing the
     * duplicate at the left reference of the original, and then
     * moving any previous left-child from the original to the
     * left child of the duplicate.
     */
    
    public void doubleTree () {
    	if (this != null) {
    		BinaryTreeNode placeholder = this.left;
            this.left = new BinaryTreeNode(this.data);
            this.left.left = placeholder;
            if (placeholder != null) {
            	this.left.left.doubleTree();
            }
            if (this.right != null) {
            	this.right.doubleTree();
            }
    	}
    }
    
    /**
     * Given two Binary Trees rooted at the provided BinaryTreeNodes
     * n1 and n2, determines whether or not the two trees are
     * equivalent (i.e., have the same nodes with the same values in
     * the same locations in the tree).
     * @param n1 The root of tree 1
     * @param n2 The root of tree 2
     * @return Whether or not n1 and n2 represent the same tree
     */
    
    public static boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
    	if (n1 == null && n2 == null) {
    		return true;
    	}
    	else if ((n1 == null && n2 != null) || (n2 == null && n1 != null)) {
    		return false;
    	}
    	if (n1.data != n2.data) {
    		return false;
    	}else {
    		sameTree(n1.left, n2.left);
    		sameTree(n1.right, n2.right);
    	}
    	return true;
        }
}
