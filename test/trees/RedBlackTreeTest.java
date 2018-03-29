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
    public void testSimpleDelete() 
    {
        RedBlackTree tree = new RedBlackTree();
        
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);
        
        Assert.assertEquals(((RedBlackNode)tree.root).color, ColorEnum.BLACK);
        Assert.assertEquals(tree.size, 4);
        
        tree.delete(15);
//        
        Assert.assertEquals(tree.size, 3);
//        Assert.assertEquals(tree.root.value, (Integer)23); 
        
        testTreeBSTProperties(tree.root);
        tree.printTree();
    }
    
    /**
     * 
     */
    @Test
    public void testDelete() 
    {
        RedBlackTree tree = new RedBlackTree();
        
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);
        tree.insert(27);
        
        Assert.assertEquals(((RedBlackNode)tree.root).color, ColorEnum.BLACK);
        Assert.assertEquals(tree.size, 5);
        Assert.assertEquals(tree.root.right.value, (Integer)25);
        Assert.assertEquals(tree.root.right.left.value, (Integer)23);
        Assert.assertEquals(((RedBlackNode)tree.root.right.left).color, ColorEnum.RED);
        
        tree.delete(25);
        
        Assert.assertEquals(tree.size, 4);
        Assert.assertEquals(tree.root.value, (Integer)20);
        Assert.assertEquals(tree.root.right.value, (Integer)27);
        Assert.assertEquals(((RedBlackNode)tree.root.right).color, ColorEnum.BLACK);
        Assert.assertEquals(tree.root.right.right.value, null);
        Assert.assertEquals(tree.root.right.left.value, (Integer)23);
        Assert.assertEquals(((RedBlackNode)tree.root.right.left).color, ColorEnum.RED);
        
        testTreeBSTProperties(tree.root);
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
