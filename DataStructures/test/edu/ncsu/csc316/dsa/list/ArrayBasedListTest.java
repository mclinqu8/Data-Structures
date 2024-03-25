package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList. Checks the expected outputs of the List
 * abstract data type behaviors when using an array-based list data structure
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class ArrayBasedListTest {

	/** Array List */
	private List<String> list;

	/**
	 * Create a new instance of an array-based list before each test case executes
	 */
	@Before
	public void setUp() {
		list = new ArrayBasedList<String>();
	}

	/**
	 * Test the output of the add(index, e) behavior, including expected exceptions
	 */
	@Test
	public void testAddIndex() {
		// Make sure the starting list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Valid add index
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		// Invalid add index (index is way larger than size)
		try {
			list.add(15, "fifteen");
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

	}

	/**
	 * Test the output of the addLast behavior
	 */
	@Test
	public void testAddLast() {
		// Make sure the starting list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Valid add last
		list.addLast("one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		// Valid add last
		list.addLast("two");
		assertEquals(2, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());
	}

	/**
	 * Test the output of the last() behavior, including expected exceptions
	 */
	@Test
	public void testLast() {
		// Make sure the starting list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Correct last
		list.addLast("one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		assertEquals("one", list.last());

		// Correct last
		list.addLast("two");
		assertEquals(2, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());
		assertEquals("two", list.last());
	}

	/**
	 * Test the output of the addFirst behavior
	 */
	@Test
	public void testAddFirst() {
		// Make sure the starting list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Valid add first
		list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		// Valid add first
		list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(0));
		assertEquals("one", list.get(1));
		assertFalse(list.isEmpty());
	}

	/**
	 * Test the output of the first() behavior, including expected exceptions
	 */
	@Test
	public void testFirst() {
		// Make sure the starting list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Correct first
		list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		assertEquals("one", list.first());

		// Correct first
		list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(0));
		assertEquals("one", list.get(1));
		assertFalse(list.isEmpty());
		assertEquals("two", list.first());
	}

	/**
	 * Test the iterator behaviors, including expected exceptions
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();

		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try {
			it.remove();
			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");

		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));

		// Create an iterator for the list that has 1 element
		it = list.iterator();

		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		
		// Make sure iterator remove works
		it.remove();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		try {
			it.next();
			fail("A NoSuchElementException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		try {
			it.remove();
			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	/**
	 * Test the output of the remove(index) behavior, including expected exceptions
	 */
	@Test
	public void testRemoveIndex() {
		// Make sure the starting list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Invalid remove index (index is smaller than zero)
		try {
			list.remove(-1);
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

		// Valid add index
		list.add(0, "one");
		list.add(1, "two");
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());

		// Valid remove index
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("two", list.get(0));
		assertFalse(list.isEmpty());

		// Invalid remove index (index is larger than size)
		try {
			list.remove(1);
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}

	/**
	 * Test the output of the removeFirst() behavior, including expected exceptions
	 */
	@Test
	public void testRemoveFirst() {
		// Make sure the starting list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Invalid remove index (index is smaller than zero)
		try {
			list.removeFirst();
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

		// Valid add index
		list.add(0, "one");
		list.add(1, "two");
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());

		// Valid remove index
		list.removeFirst();
		assertEquals(1, list.size());
		assertEquals("two", list.get(0));
		assertFalse(list.isEmpty());

		// Valid remove index
		list.removeFirst();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Invalid removeFirst (nothing to remove)
		try {
			list.removeFirst();
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}

	/**
	 * Test the output of the removeLast() behavior, including expected exceptions
	 */
	@Test
	public void testRemoveLast() {
		// Make sure the starting list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Invalid remove index (index is bigger than size)
		try {
			list.removeLast();
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

		// Valid add index
		list.add(0, "one");
		list.add(1, "two");
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());

		// Valid remove index
		list.removeLast();
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		// Valid remove index
		list.removeLast();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Invalid removeLast (nothing to remove)
		try {
			list.removeLast();
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}

	/**
	 * Test the output of the set(index, e) behavior, including expected exceptions
	 */
	@Test
	public void testSet() {
		// Make sure the starting list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Invalid set (index is bigger than size)
		try {
			list.set(0, "one");
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		// Invalid set (index is smaller than zero)
		try {
			list.set(-1, "one");
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

		// Valid add index
		list.add(0, "one");
		list.add(1, "two");
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());

		// Valid set
		list.set(0, "three");
		assertEquals(2, list.size());
		assertEquals("three", list.get(0));
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());

		// Valid set
		list.set(1, "four");
		assertEquals(2, list.size());
		assertEquals("three", list.get(0));
		assertEquals("four", list.get(1));
		assertFalse(list.isEmpty());
	}
}