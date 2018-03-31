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
     * {@inheritDoc}
     */
    @Override
    protected Node delete(Node deleteNode) 
    {
        Node replaceNode = null;
        
        if (deleteNode != null && deleteNode != nilNode) {
        	
            Node successorNode = deleteNode;
            ColorEnum successorNodeColor = ((RedBlackNode)successorNode).color;
        
            if (deleteNode.left == nilNode) {
            	// TODO
            } else if (deleteNode.right == nilNode) {
            	// TODO
            } else { 
            	successorNode = getMinimum(deleteNode.right);
            	successorNodeColor = ((RedBlackNode)successorNode).color;
            	
            	replaceNode = successorNode;

                if (((RedBlackNode)deleteNode).color == ColorEnum.RED && successorNodeColor == ColorEnum.RED) {
                	// situação 1
                	rbTreeTransplant(successorNode, successorNode.right);
                	
                	successorNode.right = deleteNode.right;
                	successorNode.right.parent = successorNode;
                	
                    rbTreeTransplant(deleteNode, successorNode);
                    successorNode.left = deleteNode.left;
                    successorNode.left.parent = successorNode;
                } else if (((RedBlackNode)deleteNode).color == ColorEnum.BLACK && successorNodeColor == ColorEnum.RED) {
                	// situação 2
                	((RedBlackNode)successorNode).color = ColorEnum.BLACK;

                	rbTreeTransplant(deleteNode, successorNode);
                	successorNode.left = deleteNode.left;
                    successorNode.left.parent = successorNode;
                	
                }
            }
            size--; 
        }
 
        return replaceNode;
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
    protected Node getMinimum(Node node) 
    {
        while (node.left != nilNode) {
            node = node.left;
        }
        return node;
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
     */
    private Node rbTreeTransplant(Node nodeToReplace, Node newNode) 
    {
        if (nodeToReplace.parent == nilNode) {
            this.root = newNode;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }
        
        newNode.parent = nodeToReplace.parent;
        return newNode;
    }
    
	/**
	 * 
	 * @param x
	 */
    private void deleteRBFixup(RedBlackNode x) 
    {
        // TODO
    }
    
    /**
     * 
     * @param node
     * @return
     */
    private boolean isBlack(Node node) 
    {
        return node != null ? ((RedBlackNode)node).color == ColorEnum.BLACK : false;
    }
    
    /**
     * 
     * @param node
     * @return
     */
    private boolean isRed(Node node) 
    {
        return node != null ? ((RedBlackNode)node).color == ColorEnum.RED : false;
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
