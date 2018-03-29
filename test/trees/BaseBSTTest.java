/**
 * 
 */
package trees;

import trees.AbstractBinarySearchTree.Node;
//import org.junit.Assert;
import org.junit.Assert;
/**
 * @author Renato Soares
 *
 */
public class BaseBSTTest
{
	protected void testTreeBSTProperties(Node entry) 
	{
        if (entry != null) {
            // test heap properties and BST properties
            if (entry.left != null) {
                Assert.assertTrue(entry.value >= entry.left.value);
            }
            if (entry.right != null) {
                Assert.assertTrue(entry.value <= entry.right.value);
            }
            testTreeBSTProperties(entry.left);
            testTreeBSTProperties(entry.right);
        }
    }
}
