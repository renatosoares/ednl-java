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
	public AVLNode insert(int element)
	{
		AVLNode newNode = (AVLNode) super.insert(element);

		newNode.setBalanceFactor(0);
		
		rebalance(newNode);
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
		
		while (node != null) {
			
			if ((AVLNode) node.parent == null) break;
			
			AVLNode parent = (AVLNode) node.parent;
			
			int leftBalancefactor = (parent.left != null) ? 1 : 0; // FIXME não vai dar certo dessa forma, pois sempre vai deixar a sub com zero
			int rightBalancefactor = (parent.right != null) ? 1 : 0;
			
			int nodeBalanceFactor = leftBalancefactor - rightBalancefactor;
			
			int nbf = 0; 
			
			if (nodeBalanceFactor != 0) {				
				nbf = parent.getBalanceFactor() + nodeBalanceFactor;
			}
			
			parent.setBalanceFactor(nbf);
			
			if (parent.getBalanceFactor() > 1) {
			   parent = (AVLNode) this.avlRotateRight(parent);
               break;
			} else if (parent.getBalanceFactor() < -1) {
				parent = (AVLNode) this.avlRotateLeft(parent);
				break;
			}
			
			node =  parent;
			if ((AVLNode) node.parent != null)
				
				if ( ((AVLNode)node.parent).getBalanceFactor() == 0 && node.getBalanceFactor() == 0) break;

			
		}

	}

    
	
	private Node updateBalanceFactorRotateLeft(Node node)
	{
		int nOldB_BF = ((AVLNode) node.left).getBalanceFactor();
		int nOldA_BF = ((AVLNode) node).getBalanceFactor();
		
		int BF_B_new = nOldB_BF + 1 - Math.min(nOldA_BF, 0); 
		int BF_A_new = nOldA_BF + 1 + Math.max(BF_B_new, 0);
		
		((AVLNode) node.left).setBalanceFactor(BF_B_new);
		((AVLNode) node).setBalanceFactor(BF_A_new);
		
		return node;
	}
	
	
	private Node updateBalanceFactorRotateRight(Node node)
	{
		int nOldB_BF = ((AVLNode) node.right).getBalanceFactor();
		int nOldA_BF = ((AVLNode) node).getBalanceFactor();
		
		int BF_B_new = nOldB_BF - 1 - Math.max(nOldA_BF, 0); 
		int BF_A_new = nOldA_BF - 1 + Math.min(BF_B_new, 0);
		
		((AVLNode) node.right).setBalanceFactor(BF_B_new);
		((AVLNode) node).setBalanceFactor(BF_A_new);
		
		return node;
	}
	
    /**
     * @param node
     * @return
     */
    private Node avlRotateLeft(Node node) 
    {
        Node temp = super.rotateLeft(node);
        
    	temp = this.updateBalanceFactorRotateLeft(temp);
        return temp;
    }
 
    /**
     * @param node
     * @return
     */
    private Node avlRotateRight(Node node) 
    {
        Node temp = super.rotateRight(node);

        temp = this.updateBalanceFactorRotateRight(temp);
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
            System.out.print(node.value.toString() + " <fb> " + avlNode.getBalanceFactor());
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
