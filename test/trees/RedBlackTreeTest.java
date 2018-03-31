package trees;

import trees.RedBlackTree;

import trees.AbstractBinarySearchTree.Node;
import trees.RedBlackTree.ColorEnum;
import trees.RedBlackTree.RedBlackNode;
import org.junit.Assert;
import org.junit.Test;

public class RedBlackTreeTest 
{

	@Test
	public void testInsert()
	{
		RedBlackTree tree = new RedBlackTree();

	    tree.insert(30);
	    tree.insert(13);
	    tree.insert(53);
	    tree.insert(8); 

	    Assert.assertEquals(((RedBlackNode)tree.root).color, ColorEnum.BLACK);
        Assert.assertEquals(((RedBlackNode)tree.search(13)).color, ColorEnum.BLACK);
        Assert.assertEquals(((RedBlackNode)tree.search(53)).color, ColorEnum.BLACK);
        
	    tree.insert(23);
	    tree.insert(43); 
   
	    Assert.assertEquals(((RedBlackNode)tree.search(23)).color, ColorEnum.RED);
        
        Assert.assertEquals(((RedBlackNode)tree.search(53)).color, ColorEnum.BLACK);
        Assert.assertEquals(((RedBlackNode)tree.search(8)).color, ColorEnum.RED);

	    tree.insert(83); 
        tree.insert(63); 
	    tree.insert(93); 
	    
	    Assert.assertEquals(((RedBlackNode)tree.search(83)).color, ColorEnum.BLACK);
        Assert.assertEquals(((RedBlackNode)tree.search(53)).color, ColorEnum.RED);
        
        tree.insert(96); 
        
        Assert.assertEquals(((RedBlackNode)tree.search(53)).color, ColorEnum.BLACK);
        Assert.assertEquals(((RedBlackNode)tree.search(83)).color, ColorEnum.RED);
        Assert.assertEquals(((RedBlackNode)tree.search(30)).left.value, (Integer)13);
	    
	    tree.printTree();
	}
	
    /**
     * 
     */
    @Test
    public void testSituation1Delete() 
    {
        RedBlackTree tree = new RedBlackTree();
        
        tree.insert(7);
        tree.insert(2);
        tree.insert(10);
        tree.insert(11);
        tree.insert(1);
        tree.insert(5);
        tree.insert(4);
        
        tree.printTree();
        
        Assert.assertEquals(((RedBlackNode)tree.root).color, ColorEnum.BLACK);
        
        tree.delete(2);

        Assert.assertEquals(tree.root.value, (Integer)7); 
        
        Assert.assertEquals(((RedBlackNode)tree.search(4)).color, ColorEnum.RED);
        Assert.assertEquals(((RedBlackNode)tree.search(5)).color, ColorEnum.BLACK);
        
        testTreeBSTProperties(tree.root);
        tree.printTree();
    }
    
	
    /**
     * 
     */
    @Test
    public void testSituation2Delete() 
    {
        RedBlackTree tree = new RedBlackTree();
        
        tree.insert(7);
        tree.insert(2);
        tree.insert(10);
        tree.insert(11);
        tree.insert(1);
        tree.insert(5);
        
        tree.printTree();
        
        Assert.assertEquals(((RedBlackNode)tree.search(5)).color, ColorEnum.RED);
        
        tree.delete(2);
         
        Assert.assertEquals(((RedBlackNode)tree.search(7)).color, ColorEnum.BLACK);
 
        tree.printTree();
    }
    
    /**
     * 
     */
    private void testTreeBSTProperties(Node entry) 
    {
        if (entry != RedBlackTree.nilNode) {
            
            if (entry.left != RedBlackTree.nilNode) {
                Assert.assertTrue(entry.value >= entry.left.value);
            }
            if (entry.right != RedBlackTree.nilNode) {
                Assert.assertTrue(entry.value <= entry.right.value);
            }
            testTreeBSTProperties(entry.left);
            testTreeBSTProperties(entry.right);
        }
    }
	
    
}
