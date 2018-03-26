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

	    tree.insert(20);
	    tree.insert(15);
	    tree.insert(25);
	    tree.insert(35); 

	    
	    tree.printTree();
	}
	
    
}
