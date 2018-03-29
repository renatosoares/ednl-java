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
	
    
}
