/**
 * 
 */
package trees;

/**
 * @author renato soares
 *
 */
public class RedBlackTree extends AbstractSelfBalancingBinarySearchTree 
{

	/**
	 * 
	 *
	 */
    protected enum ColorEnum 
    {
        RED,
        BLACK
    };
    
    /**
     * 
     */
    protected static final RedBlackNode nilNode = new RedBlackNode(null, null, null, null, ColorEnum.BLACK);
	
    /**
     * 
     */
    @Override
    public Node insert(int element)
    {
        Node newNode = super.insert(element);
        newNode.left = nilNode;
        newNode.right = nilNode;
        root.parent = nilNode;
        insertRBFixup((RedBlackNode) newNode);
        return newNode;
    }

    /**
     * 
     */
    @Override
    protected Node delete(Node deleteNode) 
    {
    	// TODO
    	return null;
    }
    
    /**
     * 
     */
	@Override
	protected Node createNode(int value, Node parent, Node left, Node right) 
	{
		 return new RedBlackNode(value, parent, left, right, ColorEnum.RED);
	}

	/**
	 * 
	 * @param currentNode
	 */
    private void insertRBFixup(RedBlackNode currentNode) 
    {
    	 while (currentNode.parent != root && ((RedBlackNode) currentNode.parent).color == ColorEnum.RED) {
             
    		 RedBlackNode parent = (RedBlackNode) currentNode.parent;
             RedBlackNode grandParent = (RedBlackNode) parent.parent;
             
             if (parent == grandParent.left) {
             
             } else if (parent == grandParent.right) {
                 
            	 RedBlackNode uncle = (RedBlackNode) grandParent.left;
                 // caso 2 - o tio e o pai são rubros
                 // re-coloração de t(Rubro),u(Negro) e w(Negro)
                 if (((RedBlackNode) uncle).color == ColorEnum.RED) {
                     parent.color = ColorEnum.BLACK;
                     uncle.color = ColorEnum.BLACK;
                     grandParent.color = ColorEnum.RED;
                     
                     // O avô foi re-colorido para vermelho, então na próxima iteração verificamos se ele não quebra a propriedade rubro negra
                     currentNode = grandParent;
                 } else {
                	 
                 }
             }
    	 }
    	 
    	 ((RedBlackNode) root).color = ColorEnum.BLACK;
    }
    
    //-------------------------------- TREE PRINTING ------------------------------------
    
    /**
     * @param node
     */
   @Override
    protected void printNodeValue(Node node) 
   {
    	
    	RedBlackNode RBNode = (RedBlackNode) node;
        if (node.value == null) {
            System.out.print("[null]" + " <color> " + RBNode.color);
        } else {
            System.out.print(node.value.toString() + " <color> " + RBNode.color);
        }
        System.out.println();
    }
	
	/**
	 * 
	 *
	 */
	protected static class RedBlackNode extends Node 
	{
        public ColorEnum color;

        public RedBlackNode(Integer value, Node parent, Node left, Node right, ColorEnum color) 
        {
            super(value, parent, left, right);
            this.color = color;
        }
    }

}
