package test;

import trees.AVLTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Renato Soares
 *
 */
public class AVLTreeTest extends BaseBSTTest
{
    @Test
    public void testInsert() {
        AVLTree treeAVL = new AVLTree();
        treeAVL.insert(50);
        treeAVL.printTree();
        treeAVL.insert(20);
        treeAVL.printTree();
        treeAVL.insert(80);
        treeAVL.printTree();
        treeAVL.insert(70);
        treeAVL.printTree();
        treeAVL.insert(90);
        treeAVL.printTree();
        treeAVL.insert(60);
//        treeAVL.insert(15);
//        treeAVL.insert(25);
//        treeAVL.insert(22);
//        treeAVL.insert(21);
//        treeAVL.insert(11);
//        treeAVL.insert(27);
//        treeAVL.insert(5);
        
//        Assert.assertEquals((int) treeAVL.root.value, 20);
//        Assert.assertEquals((int) treeAVL.root.left.value, 15);
        
//        testTreeBSTProperties(treeAVL.root);       
        
        treeAVL.printTree();
        
    }
}
