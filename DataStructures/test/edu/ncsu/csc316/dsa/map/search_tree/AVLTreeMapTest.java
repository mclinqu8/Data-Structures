package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for AVLTreeMap Checks the expected outputs of the Map abstract
 * data type behaviors when using an AVL tree data structure
 *
 * @author Dr. King
 *
 */
public class AVLTreeMapTest {

	/** An AVL Tree Map */
	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Create a new instance of an AVL tree-based map before each test case executes
	 */
	@Before
	public void setUp() {
		tree = new AVLTreeMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
//      Sample tree to help with testing
//      Visually:
//                     (  4  )
//                    /       \
//                (  2  )   (  5  )
//                  /  \        
//            (  1  ) (  3  )   
//               
// 
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(5, "5");
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(5, (int) tree.root().getElement().getKey());
		tree.put(1, "1");
		assertFalse(tree.isRoot(tree.left(tree.root())));
		assertFalse(tree.isLeaf(tree.left(tree.root())));
		assertTrue(tree.isInternal(tree.left(tree.root())));
		tree.put(4, "4");
		assertEquals(4, (int) tree.root().getElement().getKey());
		tree.put(3, "3");
		tree.put(2, "2");
		assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
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
		tree.put(1, "one");
		assertEquals(1, tree.size());

		// test valid key
		assertEquals("one", tree.get(1));

		// test invalid key
		assertNull(tree.get(2));
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
//      Sample tree to help with testing
//      Visually:
//                     (  4  )
//                    /       \
//                (  2  )   (  5  )
//                  /  \        
//            (  1  ) (  3  )   
//               
// 
		assertTrue(tree.isEmpty());
		assertNull(tree.remove(1));
		tree.put(5, "5");
		tree.put(1, "1");
		tree.put(4, "4");
		tree.put(3, "3");
		tree.put(2, "2");
		assertEquals("5", tree.remove(5));
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(3, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals("1", tree.remove(1));
		assertEquals(3, (int) tree.root().getElement().getKey());
	}

	/**
	 * Test the output of the tree with a comparator
	 */
	@Test
	public void testComparator() {
		BinarySearchTreeMap<Student, Integer> newTree = new AVLTreeMap<Student, Integer>(new StudentIDComparator());

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
		newTree.put(sFive, 5);
		newTree.put(sOne, 1);
		newTree.put(sFour, 4);
		newTree.put(sThree, 3);
		newTree.put(sTwo, 2);

		// test valid key
		assertEquals(1, (int)newTree.get(sOne));
		
		// Test invalid key
		assertNull(newTree.get(invalid));
		
		assertEquals(5, (int) newTree.remove(sFive));
		assertEquals(sTwo, newTree.root().getElement().getKey());
		assertEquals(sThree, newTree.left(newTree.right(newTree.root())).getElement().getKey());
		assertEquals(1, (int) newTree.remove(sOne));
		assertEquals(sThree, newTree.root().getElement().getKey());
	}
}