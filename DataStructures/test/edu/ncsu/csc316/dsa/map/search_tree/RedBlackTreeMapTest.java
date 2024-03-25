package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 *
 */
public class RedBlackTreeMapTest {

	/** A Red Black Tree Map with entries that contains Integer and String */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        // Root node that is black
        tree.put(3, "3");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(0, (int)tree.getProperty(tree.root()));
        
        // Internal node that is red
        tree.put(2, "2");
        assertEquals(2, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.getProperty(tree.left(tree.root())));
        
        // Double red with black sibling
        tree.put(1, "1");
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals("1", tree.left(tree.root()).getElement().getValue());
        assertEquals(1, (int)tree.getProperty(tree.left(tree.root())));
        
        // Double red with red sibling
        tree.put(4, "4");
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals("3", tree.right(tree.root()).getElement().getValue());
        assertEquals(0, (int)tree.getProperty(tree.left(tree.root())));
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        // Get for an empty tree
        assertNull(tree.get(1));
        
        // Tree with elements
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        //test valid key
        assertEquals("one", tree.get(1));
        
        //test invalid key
        assertNull(tree.get(2));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	// 5 7 2 4 9 6 1 10
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(5,  "5"); 
        tree.put(7,  "7"); 
        tree.put(2,  "2"); 
        tree.put(4,  "4"); 
        tree.put(9,  "9"); 
        tree.put(6,  "6"); 
        tree.put(1,  "1"); 
        tree.put(10,  "10"); 
        // Case 1: uncle is black and has red child
        assertEquals("6", tree.remove(6));
        assertEquals(9, (int)tree.right(tree.root()).getElement().getKey());
        // Case 2: uncle is black and both children are black
        assertEquals("10", tree.remove(10));
        assertEquals(9, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(0, tree.getProperty(tree.right(tree.root())));
        
        tree.put(10,  "10"); 
        tree.put(11,  "11");
        assertEquals("2", tree.remove(2));
        assertEquals(9, (int)tree.right(tree.root()).getElement().getKey());
    }
    
	/**
	 * Test the output of the tree with a comparator
	 */
	@Test
	public void testComparator() {
		BinarySearchTreeMap<Student, Integer> newTree = new RedBlackTreeMap<Student, Integer>(new StudentIDComparator());

		Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");

		Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");

		Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");

		Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		
		Student invalid = new Student("invalid", "invlid", 0, 0, 0.0, "0");

		assertTrue(newTree.isEmpty());
		assertNull(newTree.remove(sOne));
		// Get for an empty tree
		assertNull(newTree.get(sOne));

		// Root node that is black
		newTree.put(sThree, 3);
        assertEquals(1, newTree.size());
        assertFalse(newTree.isEmpty());
        assertEquals(sThree, newTree.root().getElement().getKey());
        assertEquals(0, (int)newTree.getProperty(newTree.root()));
        
        // Internal node that is red
        newTree.put(sTwo, 2);
        assertEquals(2, newTree.size());
        assertEquals(sThree, newTree.root().getElement().getKey());
        assertEquals(1, (int)newTree.getProperty(newTree.left(newTree.root())));
        
        // Double red with black sibling
        newTree.put(sOne, 1);
        assertEquals(sTwo, newTree.root().getElement().getKey());
        assertEquals(1, (int)newTree.left(newTree.root()).getElement().getValue());
        assertEquals(1, (int)newTree.getProperty(newTree.left(newTree.root())));
        
        // Double red with red sibling
        newTree.put(sFour, 4);
        assertEquals(sTwo, newTree.root().getElement().getKey());
        assertEquals(3, (int)newTree.right(newTree.root()).getElement().getValue());
        assertEquals(0, (int)newTree.getProperty(newTree.left(newTree.root())));

		// test valid key
		assertEquals(1, (int)newTree.get(sOne));
		
		// Test invalid key
		assertNull(newTree.get(invalid));
		
		assertEquals(4, (int) newTree.remove(sFour));
		assertEquals(sTwo, newTree.root().getElement().getKey());
		assertNull(newTree.right(newTree.right(newTree.root())).getElement());
		assertEquals(2, (int) newTree.remove(sTwo));
		assertEquals(sThree, newTree.root().getElement().getKey());
	}
}