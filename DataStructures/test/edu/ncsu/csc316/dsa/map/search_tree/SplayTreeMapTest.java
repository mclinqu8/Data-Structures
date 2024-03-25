package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for SplayTreeMap Checks the expected outputs of the Map abstract
 * data type behaviors when using a splay tree data structure
 *
 * @author Dr. King
 *
 */
public class SplayTreeMapTest {

	/** A Binary Search Tree Map with entries that contains Integers and Strings */
	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Create a new instance of a splay tree-based map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		tree = new SplayTreeMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(5, "5");
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(5, (int) tree.root().getElement().getKey());
		tree.put(1, "1");
		assertEquals(1, (int) tree.root().getElement().getKey());
		assertFalse(tree.isRoot(tree.left(tree.root())));
		assertFalse(tree.isLeaf(tree.right(tree.root())));
		assertTrue(tree.isInternal(tree.right(tree.root())));
		tree.put(4, "4");
		assertEquals(4, (int) tree.root().getElement().getKey());
		tree.put(3, "3");
		assertEquals(3, (int) tree.root().getElement().getKey());
		tree.put(2, "2");
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());
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
		assertTrue(tree.isEmpty());
		assertNull(tree.remove(1));
		tree.put(5, "5");
		tree.put(9, "9");
		tree.put(2, "2");
		tree.put(8, "8");
		tree.put(4, "4");
		tree.put(7, "7");
		// ZIG ZAG
		assertNull(tree.remove(6));
		assertEquals(5, (int) tree.root().getElement().getKey());
		assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
		// ZIG ZIG
		assertEquals("5", tree.remove(5));
		assertEquals(7, (int) tree.root().getElement().getKey());
		assertEquals(8, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(9, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		// ZIG
		assertEquals("9", tree.remove(9));
		assertEquals(8, (int) tree.root().getElement().getKey());

	}

	/**
	 * Test the output of the tree with a comparator
	 */
	@Test
	public void testComparator() {
		BinarySearchTreeMap<Student, Integer> newTree = new SplayTreeMap<Student, Integer>(new StudentIDComparator());

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
		assertEquals(sFive, newTree.root().getElement().getKey());
		newTree.put(sOne, 1);
		assertEquals(sOne, newTree.root().getElement().getKey());
		newTree.put(sFour, 4);
		assertEquals(sFour, newTree.root().getElement().getKey());
		newTree.put(sThree, 3);
		assertEquals(sThree, newTree.root().getElement().getKey());
		newTree.put(sTwo, 2);
		assertEquals(sTwo, newTree.root().getElement().getKey());

		// test valid key
		assertEquals(1, (int) newTree.get(sOne));

		// Test invalid key
		assertNull(newTree.get(invalid));

		assertEquals(4, (int) newTree.remove(sFour));
		assertEquals(sThree, newTree.root().getElement().getKey());
		assertNull(newTree.right(newTree.right(newTree.root())).getElement());
		assertEquals(1, (int) newTree.remove(sOne));
		assertEquals(sTwo, newTree.root().getElement().getKey());
	}
}