package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class BinarySearchTreeMapTest {
	
	/** Binary Search Tree */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        tree.put(-1, "negone");
        assertFalse(tree.isRoot(tree.left(tree.root())));
        assertFalse(tree.isLeaf(tree.left(tree.root())));
        assertTrue(tree.isInternal(tree.left(tree.root())));
        tree.put(2, "two");
        tree.put(3, "three");
         
//          Sample tree to help with testing
//          Visually:
//                             one
//                         /        \
//                    neg one       two
//                                    \
//                                   three
//                   
//           
        assertEquals(4, tree.size());
        assertEquals(-1, (int)tree.left(tree.root()).getElement().getKey());  
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals("negone", tree.left(tree.root()).getElement().getValue());  
        tree.put(-1, "negative one");
        assertEquals("negative one", tree.left(tree.root()).getElement().getValue()); 
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
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
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        // Remove the root
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(1,  "one");
        tree.put(3, "three");
        tree.put(2, "two");
        tree.put(4, "four");
        tree.put(-1, "negative one");
        tree.put(-2, "negative two");
//      Sample tree to help with testing
//      Visually:
//                         one
//                     /        \
//                neg one       three
//                  /            /  \
//             neg two         two  four
//               
// 
        //Removing from a node that has both children
        assertEquals("one", tree.remove(1));
        assertEquals("two", tree.root().getElement().getValue());
        assertEquals(5, tree.size());
        // Removing from a node that has only a left child
        assertEquals("negative one", tree.remove(-1));
        assertEquals("negative two", tree.left(tree.root()).getElement().getValue());
        assertEquals(4, tree.size());
        // Removing from a node with only sentinel nodes
        assertEquals("negative two", tree.remove(-2));
        assertNull(tree.left(tree.root()).getElement());
        assertEquals(3, tree.size());
        // Removing from a node that has only a right child
        assertEquals("three", tree.remove(3));
        assertEquals("four", tree.right(tree.root()).getElement().getValue());
        assertEquals(2, tree.size());
    }
    
    /**
     * Test the output of the setProperty(position, value) behavior
     */     
    @Test
    public void testSetProperty() {
        tree.put(1,  "one");
        assertEquals(0, (int)tree.getProperty(tree.root()));
        tree.setProperty(tree.root(), 1);
        assertEquals(1, (int)tree.getProperty(tree.root()));  
    }
    
	/**
	 * Test the output of the entrySet() behavior, including expected exceptions
	 */
	@Test
	public void testEntrySet() {
		Iterable<Entry<Integer, String>> collection = tree.entrySet();
		Iterator<Entry<Integer, String>> entryIt = collection.iterator();
		
		assertFalse(entryIt.hasNext());
		
		assertNull(tree.put(3, "string3"));
		assertNull(tree.put(5, "string5"));
		assertNull(tree.put(2, "string2"));
		assertNull(tree.put(4, "string4"));
		assertNull(tree.put(1, "string1"));
		
		collection = tree.entrySet();
		entryIt = collection.iterator();
		try {
			entryIt.remove();
			fail("An UnsupportedOperationException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
		assertTrue(entryIt.hasNext());
		assertEquals(tree.get(1), entryIt.next().getValue());
	}
	
	/**
	 * Test the output of the entrySet() behavior, including expected exceptions
	 */
	@Test
	public void testToString() {
		tree.put(1,  "one");
        tree.put(3, "three");
        tree.put(2, "two");
        tree.put(4, "four");
        tree.put(-1, "negative one");
        tree.put(-2, "negative two");
        assertEquals("BinarySearchTreeMap[\n"
        		+ "-2 \n"
        		+ "-1 \n"
        		+ "1 \n"
        		+ "2 \n"
        		+ "3 \n"
        		+ "4 \n"
        		+ "]", tree.toString());
	}
	
	/**
	 * Test the output of the tree with a comparator
	 */
	@Test
	public void testComparator() {
		BinarySearchTreeMap<Student, Integer> newTree = new BinarySearchTreeMap<Student, Integer>(new StudentIDComparator());

		Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");

		Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");

		Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");

		Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");

		Student sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		Student invalid = new Student("invalid", "invlid", 0, 0, 0.0, "0");

		assertTrue(newTree.isEmpty());
		assertNull(newTree.remove(sOne));
		// Get for an empty tree
		assertNull(newTree.get(sOne));

		// Insert into the tree
		newTree.put(sThree, 3);
		newTree.put(sTwo, 2);
		newTree.put(sFour, 4);
		newTree.put(sOne, 1);
		newTree.put(sFive, 5);

		// test valid key
		assertEquals(1, (int)newTree.get(sOne));
		
		// Test invalid key
		assertNull(newTree.get(invalid));
		
		assertEquals(5, (int) newTree.remove(sFive));
		assertEquals(sThree, newTree.root().getElement().getKey());
		assertNull(newTree.right(newTree.right(newTree.root())).getElement());
		assertEquals(3, (int) newTree.remove(sThree));
		assertEquals(sFour, newTree.root().getElement().getKey());
	}
    
}