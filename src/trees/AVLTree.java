/**
 * 
 */
package trees;

import javax.xml.stream.events.NotationDeclaration;

import trees.AbstractBinarySearchTree.Node;

/**
 * @author Renato Soares
 *
 */
/**
 * @author Renato Soares
 *
 */
public class AVLTree extends AbstractSelfBalancingBinarySearchTree
{
	/* (non-Javadoc)
	 * @see trees.AbstractBinarySearchTree#insert(int)
	 */
	@Override
	public Node insert(int element)
	{
		Node newNode = super.insert(element);

		rebalance((AVLNode) newNode);
		return newNode;
	}

	/* (non-Javadoc)
	 * @see trees.AbstractBinarySearchTree#createNode(int, trees.AbstractBinarySearchTree.Node, trees.AbstractBinarySearchTree.Node, trees.AbstractBinarySearchTree.Node)
	 */
	@Override
	protected Node createNode(int value, Node parent, Node left, Node right)
	{
		return new AVLNode(value, parent, left, right);
	}

	/**
	 * @param node
	 */
	private void rebalance(AVLNode node)
	{
		node.setBalanceFactor(0);
		while (node != null) {
						
			AVLNode parent = (AVLNode) node.parent;
			
			
			
			if (parent == null) break;
		
			int leftBalancefactor = (parent.left != null) ? 1 : 0;
			int rightBalancefactor = (parent.right != null) ? 1 : 0;
			
			int nodeBalanceFactor = leftBalancefactor - rightBalancefactor;
			int nbf = parent.getBalanceFactor() + nodeBalanceFactor;
			parent.setBalanceFactor(nbf);
			
			
			if (parent.getBalanceFactor() > 1) {
			   parent = (AVLNode) this.avlRotateRight(parent);
               break;
			} else if (parent.getBalanceFactor() < -1) {
				parent = (AVLNode) this.avlRotateLeft(parent);
				break;
			} else {
//				updateBalanceFactor(parent);
			}
			
			node =  parent;

		}

	}

    /**
     * @param node
     * @return
     */
    private Node avlRotateLeft(Node node) 
    {
        Node temp = super.rotateLeft(node);
        
        temp.left = updateBalanceFactor((AVLNode)temp.left);
        temp = updateBalanceFactor((AVLNode)temp);
        return temp;
    }
 
    /**
     * @param node
     * @return
     */
    private Node avlRotateRight(Node node) 
    {
        Node temp = super.rotateRight(node);

        temp.right = updateBalanceFactor((AVLNode)temp.right);
        temp = updateBalanceFactor((AVLNode)temp);
        return temp;
    }

    /**
     * @param node
     * @return
     */
    protected Node doubleRotateRightLeft(Node node) 
    {
        return null;
    }

    /**
     * @param node
     * @return
     */
    protected Node doubleRotateLeftRight(Node node) 
    {
    	return null;
    }
    
    private AVLNode updateBalanceFactor(AVLNode node) 
    {
		int leftBalancefactor = (node.left != null) ? 1 : 0;
		int rightBalancefactor = (node.right != null) ? 1 : 0;
		
		int nodeBalanceFactor = leftBalancefactor - rightBalancefactor;
		int nbf = node.getBalanceFactor() + nodeBalanceFactor;
		node.setBalanceFactor(nbf);
		
		return node;
    }
    
    //-------------------------------- TREE PRINTING ------------------------------------
    
    /**
     * @param node
     */
    protected void printNodeValue(Node node) {
    	
    	AVLNode avlNode = (AVLNode) node;
        if (node.value == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.value.toString() + '-'+ avlNode.getBalanceFactor());
        }
        System.out.println();
    }
	
	/**
	 * @author Renato Soares
	 *
	 */
	protected static class AVLNode extends Node
	{
		public int balanceFactor;

		public AVLNode(int value, Node parent, Node left, Node right)
		{
			super(value, parent, left, right);
		}

		public void setBalanceFactor(int bf)
		{
			this.balanceFactor = bf;
		}
		
		public int getBalanceFactor()
		{
			return this.balanceFactor;
			
		}
	}
}
