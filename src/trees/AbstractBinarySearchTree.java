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
    	//TODO
    	return null;
    }
    
    /**
     * 
     * @param deleteNode
     * @return
     */
    protected Node delete(Node deleteNode) 
    {
    	// TODO
    	return null;
    }
    
   
    //-------------------------------- TREE PRINTING ------------------------------------

    /**
     * 
     */
    public void printTree() {
        printSubtree(root);
    }
    
    /**
     * @param node
     */
    public void printSubtree(Node node) {
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
    protected void printNodeValue(Node node) {
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
