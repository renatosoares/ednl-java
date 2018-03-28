/**
 * 
 */
package trees;

/**
 * @author Renato Soares
 */
public abstract class AbstractSelfBalancingBinarySearchTree extends AbstractBinarySearchTree
{
	/**
	 * 
	 * @param node
	 * @return
	 */
	protected Node rotateLeft(Node node) 
	{
        Node temp = node.right;
        temp.parent = node.parent;
        node.right = temp.left;
        
        if (node.right != null) {
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;
      
        this.rotateConditional(node, temp, null);
        
        return temp;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
    protected Node rotateRight(Node node) 
    {
        Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if (node.left != null) {
            node.left.parent = node;
        }

        temp.right = node;
        node.parent = temp;

        this.rotateConditional(node, temp, null);
        
        return temp;
    }
    
    /**
     * 
     */
    protected void rotateConditional(Node node, Node temp, Node n)
    {
        if (temp.parent != n) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
    }

}
