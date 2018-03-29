package trees;

import org.junit.Assert;
import org.junit.Test;
import trees.BinarySearchTree;

public class BinarySearchTreeTest extends BaseBSTTest
{
    @Test
    public void testInsertDelete() 
    {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(16);
        tree.insert(1);
        tree.insert(8);
        
        Assert.assertTrue(tree.contains(10));
        Assert.assertTrue(tree.contains(16));
        Assert.assertTrue(tree.contains(1));
        Assert.assertFalse(tree.contains(9));
        
        tree.delete(16);
        tree.delete(1);
        
        Assert.assertFalse(tree.contains(16));
        Assert.assertFalse(tree.contains(1));
        
        testTreeBSTProperties(tree.root);
        
        tree.printTree();
    }
}
