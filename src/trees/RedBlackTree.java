/**
 * 
 */
package trees;

import trees.AbstractBinarySearchTree.Node;

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
        
        // critério I: se nó é externo, então é negro
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
		// Pintando o novo nó de rubro o critério IV é preservado.
		 return new RedBlackNode(value, parent, left, right, ColorEnum.RED);
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void rotateConditional(Node node, Node temp, Node n)
    {
        if (temp.parent != nilNode) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
    }

	/**
	 * 
	 * @param currentNode
	 */
    private void insertRBFixup(RedBlackNode currentNode) 
    {
    	// Caso 1: se pai é negro, nada precisa ser feito já que o critério IV foi mantido
    	 while (currentNode.parent != root && ((RedBlackNode) currentNode.parent).color == ColorEnum.RED) {
             
    		 RedBlackNode parent = (RedBlackNode) currentNode.parent;
             RedBlackNode grandParent = (RedBlackNode) parent.parent;
             
             if (parent == grandParent.left) {
             
            	 RedBlackNode uncle = (RedBlackNode) grandParent.right;
            	 
                 // caso 2 - o tio e o pai são rubros
                 // re-coloração de avó, tio e pai
                 if (((RedBlackNode) uncle).color == ColorEnum.RED) {
                     
                	 parent.color = ColorEnum.BLACK;
                     uncle.color = ColorEnum.BLACK;
                     grandParent.color = ColorEnum.RED;
                  
                     // O avô foi re-colorido para vermelho, então na próxima iteração verificamos se ele não quebra a propriedade rubro negra
                     currentNode = grandParent;
                     
                 } else { // caso 3: quando nó atual é rubro, pai negro e irmão negro
                	 
                	 // caso 3d: rotação direita dupla
                     if (currentNode == parent.right) { 
                         currentNode = parent;
                         rotateLeft(currentNode);
                         
                         parent.color = ColorEnum.RED;
                         grandParent.color = ColorEnum.RED; 
                         
                         rotateRight(grandParent);
                     } else {
                    	 // Caso 3a: Rotação direita simples
                         parent.color = ColorEnum.BLACK;
                         grandParent.color = ColorEnum.RED; 
                         
                         rotateRight(grandParent); 
                     }
                 }
            	 
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
                 } else { // caso 3: quando nó atual é rubro, pai negro e irmão negro
                	

                	 
                	 // caso 3c: rotação esquerda dupla
                     if (currentNode == parent.left) { 
                         currentNode = parent;
                         rotateRight(currentNode);
                         
                         parent.color = ColorEnum.RED;
                         grandParent.color = ColorEnum.RED;
                         rotateLeft(grandParent);
                     } else {
                         // caso 3b: rotação esquerda simples
                         parent.color = ColorEnum.BLACK;
                         grandParent.color = ColorEnum.RED;
                         rotateLeft(grandParent);
                     }
                     

                 }
             }
    	 }
    	 
    	 // critério II: O nó raiz é negro
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
