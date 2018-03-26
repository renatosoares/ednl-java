package test;

import trees.RedBlackTree;
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
	    tree.insert(23);
	    tree.insert(43); 
	    tree.insert(83);
	    tree.insert(63); 
	    tree.insert(93); 
	    tree.insert(96);
	    
	    tree.printTree();
	}
	
    
}
