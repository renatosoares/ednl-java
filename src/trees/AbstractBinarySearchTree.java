/**
 * 
 */
package trees;

/**
 * @author Renato Soares
 *
 */

public abstract class AbstractBinarySearchTree
{
	/**
	 * 
	 */
	public Node root;
	
	/**
	 * 
	 */
	protected int size;
	
	/**
	 * @param value
	 * @param parent
	 * @param left
	 * @param right
	 * @return
	 */
	protected abstract Node createNode(int value, Node parent, Node left, Node right);
	
	/**
	 * 
	 * @param element
	 * @return
	 */
    public Node search(int element) 
    {
        Node node = root;
        
        while (node != null && node.value != null && node.value != element) {
            
        	if (element < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        
        return node;
    }
	
    /**
     * @param element
     * @return
     */
    public Node insert(int element) 
    {
        if (root == null) {
            root = createNode(element, null, null, null);
            size++;
            return root;
        }

        Node insertParentNode = null;
        Node searchTempNode = root;
        
        while (searchTempNode != null && searchTempNode.value != null) {
        	
            insertParentNode = searchTempNode;
            
            if (element < searchTempNode.value) {
                searchTempNode = searchTempNode.left;
            } else {
                searchTempNode = searchTempNode.right;
            }
        }

        Node newNode = createNode(element, insertParentNode, null, null);
        
        if (insertParentNode.value > newNode.value) {
            insertParentNode.left = newNode;
        } else {
            insertParentNode.right = newNode;
        }

        size++;
        return newNode;
    }
    
    /**
     * 
     * @param element
     * @return
     */
    public Node delete(int element) 
    {
        Node deleteNode = search(element);
        if (deleteNode != null) {
            return delete(deleteNode);
        } else {
            return null;
        }
    }
    
    /**
     * 
     * @param deleteNode
     * @return
     */
    protected Node delete(Node deleteNode) 
    {
        if (deleteNode != null) {
        	
            Node nodeToReturn = null;
            
            if (deleteNode != null) {
            	
                if (deleteNode.left == null) {
                    nodeToReturn = transplant(deleteNode, deleteNode.right); 
                } else if (deleteNode.right == null) {
                    nodeToReturn = transplant(deleteNode, deleteNode.left); 
                } else {
                    Node successorNode = getMinimum(deleteNode.right);
                    
                    if (successorNode.parent != deleteNode) {
                        transplant(successorNode, successorNode.right);
                        successorNode.right = deleteNode.right;
                        successorNode.right.parent = successorNode;
                    }
                    
                    transplant(deleteNode, successorNode);
                    successorNode.left = deleteNode.left;
                    successorNode.left.parent = successorNode;
                    nodeToReturn = successorNode;
                }
                size--;
            }
    
            return nodeToReturn;
        }
        return null;
    }
    
    /**
     * 
     * @param nodeToReplace
     * @param newNode
     * @return
     */
    private Node transplant(Node nodeToReplace, Node newNode) 
    {
        if (nodeToReplace.parent == null) {
            this.root = newNode; 
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode; 
        } else {
            nodeToReplace.parent.right = newNode; 
        }
        
        if (newNode != null) {
            newNode.parent = nodeToReplace.parent; 
        }
        
        return newNode;
    }
    
    /**
     * 
     * @param element
     * @return
     */
    public boolean contains(int element) 
    {
        return search(element) != null;
    }
    
    /**
     * 
     * @return
     */
    public int getMinimum() 
    {
        return getMinimum(root).value;
    }
    
   
    /*-------------------PRIVATE HELPER METHODS-------------------*/
    
    /**
     * 
     * @param node
     * @return
     */
    protected Node getMinimum(Node node) 
    {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    //-------------------------------- TREE PRINTING ------------------------------------

    /**
     * 
     */
    public void printTree() 
    {
        printSubtree(root);
    }
    
    /**
     * @param node
     */
    public void printSubtree(Node node) 
    {
        if (node.right != null) {
            printTree(node.right, true, "");
        }
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, "");
        }
    }
    
    /**
     * @param node
     */
    protected void printNodeValue(Node node) 
    {
        if (node.value == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.value.toString());
        }
        System.out.println();
    }
    
    /**
     * @param node
     * @param isRight
     * @param indent
     */
    private void printTree(Node node, boolean isRight, String indent) 
    {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "));
        }
    }
	
    /**
     * @author Renato Soares
     *
     */
    public static class Node 
    {
        public Node(Integer value, Node parent, Node left, Node right) 
        {
            super();
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Integer value;
        public Node parent;
        public Node left;
        public Node right;
    }
}
