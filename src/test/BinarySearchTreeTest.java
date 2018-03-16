package test;

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
        
        testTreeBSTProperties(tree.root);
        
        tree.printTree();
    }
}
