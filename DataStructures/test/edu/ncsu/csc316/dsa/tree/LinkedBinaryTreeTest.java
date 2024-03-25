package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class LinkedBinaryTreeTest {

	/** The tree that contains positions one through ten */
    private LinkedBinaryTree<String> tree;
    
    /** The position one in the tree that contains the string one */
    private Position<String> one;
    
    /** The position two in the tree that contains the string two */
    private Position<String> two;
    
    /** The position three in the tree that contains the string three */
    private Position<String> three;
    
    /** The position four in the tree that contains the string four */
    private Position<String> four;
    
    /** The position five in the tree that contains the string five */
    private Position<String> five;
    
    /** The position six in the tree that contains the string six */
    private Position<String> six;
    
    /** The position seven in the tree that contains the string seven */
    private Position<String> seven;
    
    /** The position eight in the tree that contains the string eight */
    private Position<String> eight;
    
    /** The position nine in the tree that contains the string nine */
    private Position<String> nine;
    
    /** The position ten in the tree that contains the string ten */
    private Position<String> ten;
    
   /**
    * Helper class to create an invalid position to help test validate(p)
    * 
    * @author Dr. King 
    *
    * @param <E> Element stored within the given position
    */
    private class InvalidPosition<E> implements Position<E> {

    	/**
    	 * The element stored at the current position is null
    	 * {@inheritDoc}
    	 */
        @Override
        public E getElement() {
            return null;
        }
        
    }

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the validate method 
     */     
    @Test
    public void testValidate() {
    	try {
    		Position<String> invalid = new InvalidPosition<String>();
    		tree.validate(invalid);
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
        
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.root().getElement());
        assertEquals("two", tree.left(one).getElement());
        assertEquals("two", tree.set(two, "eleven"));
        assertEquals("eleven", tree.left(one).getElement());
        
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertFalse(tree.isEmpty());
        assertEquals(10, tree.size());
        tree.remove(eight);
        assertEquals(9, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(0, tree.numChildren(six));
        assertEquals(1, tree.numChildren(three));
    }
    
    /**
     * Test the iterator behavior
     */     
    @Test
    public void testIterator() {
        createTree();
        Iterable<Position<String>> childs = tree.children(one);
        Iterator<Position<String>> childIt = childs.iterator();
        assertTrue(childIt.hasNext());
        assertEquals("two", childIt.next().getElement());
        assertTrue(childIt.hasNext());
        assertEquals("three", childIt.next().getElement());
		assertFalse(childIt.hasNext());
		try {
			childIt.remove();
			fail("An UnsupportedOperationException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
        
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertNull(tree.parent(one));
        assertEquals(one, tree.parent(two));
        assertEquals(two, tree.parent(six));
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertNull(tree.sibling(four));
        assertEquals(three, tree.sibling(two));
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertFalse(tree.isInternal(six));
        assertTrue(tree.isInternal(two));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(six));
        assertFalse(tree.isLeaf(two));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterable<Position<String>> childs = tree.preOrder();
        Iterator<Position<String>> childIt = childs.iterator();
        assertTrue(childIt.hasNext());
        assertEquals(one, childIt.next());
        assertEquals(two, childIt.next());
		assertEquals(six, childIt.next());
		assertEquals(ten, childIt.next());
		assertEquals(seven, childIt.next());
		assertEquals(five, childIt.next());
		assertEquals(three, childIt.next());
		assertEquals(four, childIt.next());
		assertEquals(eight, childIt.next());
		assertEquals(nine, childIt.next());
        assertFalse(childIt.hasNext());
		
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterable<Position<String>> childs = tree.postOrder();
        Iterator<Position<String>> childIt = childs.iterator();
        assertTrue(childIt.hasNext());
        assertEquals(six, childIt.next());
        assertEquals(seven, childIt.next());
		assertEquals(five, childIt.next());
		assertEquals(ten, childIt.next());
		assertEquals(two, childIt.next());
		assertEquals(eight, childIt.next());
		assertEquals(nine, childIt.next());
		assertEquals(four, childIt.next());
		assertEquals(three, childIt.next());
		assertEquals(one, childIt.next());
        assertFalse(childIt.hasNext());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterable<Position<String>> childs = tree.inOrder();
        Iterator<Position<String>> childIt = childs.iterator();
        assertTrue(childIt.hasNext());
        assertEquals(six, childIt.next());
        assertEquals(two, childIt.next());
		assertEquals(seven, childIt.next());
		assertEquals(ten, childIt.next());
		assertEquals(five, childIt.next());
		assertEquals(one, childIt.next());
		assertEquals(eight, childIt.next());
		assertEquals(four, childIt.next());
		assertEquals(nine, childIt.next());
		assertEquals(three, childIt.next());
        assertFalse(childIt.hasNext());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	LinkedBinaryTree<String> emptyTree = new LinkedBinaryTree<String>(); 
    	assertTrue(emptyTree.isEmpty());
    	assertEquals(0, emptyTree.size());
    	assertNull(emptyTree.root());
    	try {
    		emptyTree.children(emptyTree.root());
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
    }
    
    /**
     * Test the output of the levelOrder traversal behavior
     */ 
    @Test
    public void testLevelOrder() {
        createTree();
        Iterable<Position<String>> childs = tree.levelOrder();
        Iterator<Position<String>> childIt = childs.iterator();
        assertTrue(childIt.hasNext());
        assertEquals(one, childIt.next());
        assertEquals(two, childIt.next());
		assertEquals(three, childIt.next());
		assertEquals(six, childIt.next());
		assertEquals(ten, childIt.next());
		assertEquals(four, childIt.next());
		assertEquals(seven, childIt.next());
		assertEquals(five, childIt.next());
		assertEquals(eight, childIt.next());
		assertEquals(nine, childIt.next());
        assertFalse(childIt.hasNext());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	createTree();
    	tree.addLeft(six, "eleven");
    	assertEquals("eleven", tree.left(six).getElement());
    	try {
    		tree.addLeft(four, "invalid");
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	createTree();
    	tree.addRight(six, "eleven");
    	assertEquals("eleven", tree.right(six).getElement());
    	try {
    		tree.addRight(four, "invalid");
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        tree.remove(seven);
        assertNull(tree.left(ten));
        tree.remove(three);
        assertEquals(four, tree.right(one));
        try {
    		tree.remove(two);
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
    }
    
    /**
     * Test the output of the toString() behavior
     */         
    @Test
    public void testToString() {
        createTree();
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  six\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ " three\n"
        		+ "  four\n"
        		+ "   eight\n"
        		+ "   nine\n"
        		+ "]", tree.toString());
    }
}