package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for SkipListMap Checks the expected outputs of the Map abstract
 * data type behaviors when using a sorted array-based data structure that uses
 * binary search to locate entries based on the key of the entry
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class SkipListMapTest {

	/**
	 * Declare a SkipListMap with keys as type Integers and values as type Strings
	 */
	private Map<Integer, String> map;
	
	/**
	 * Declare a SkipListMap of students with the keys as Students and values as Integers
	 */
	private Map<Student, Integer> studentMap;

	/**
	 * Create a new instance of a search table map before each test case executes
	 */
	@Before
	public void setUp() {
		map = new SkipListMap<Integer, String>();
		studentMap = new SkipListMap<Student, Integer>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SkipListMap[3]", map.toString());
		assertEquals(1, map.size());
		// put after 3
		assertNull(map.put(4, "string4"));
		assertEquals("SkipListMap[3, 4]", map.toString());
		assertEquals(2, map.size());
		// put before 3
		assertNull(map.put(2, "string2"));
		assertEquals("SkipListMap[2, 3, 4]", map.toString());
		assertEquals(3, map.size());
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		// get invalid key
		assertNull(map.get(6));
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		// test remove
		assertEquals("string1", map.remove(1));
		assertEquals("SkipListMap[2, 3, 4, 5]", map.toString());
		assertEquals("string2", map.remove(2));
		assertEquals("SkipListMap[3, 4, 5]", map.toString());
		assertEquals("string3", map.remove(3));
		assertEquals("SkipListMap[4, 5]", map.toString());
		assertEquals("string4", map.remove(4));
		assertEquals("SkipListMap[5]", map.toString());
		assertEquals("string5", map.remove(5));
		assertEquals("SkipListMap[]", map.toString());
		assertNull(map.remove(0));
	}

	/**
	 * Tests Map abstract data type behaviors to ensure the behaviors work as
	 * expected when using arbitrary objects as keys
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		Student s2 = new Student("J", "S", 2, 0, 0, "js");
		Student s3 = new Student("S", "H", 3, 0, 0, "sh");
		Student s4 = new Student("J", "J", 4, 0, 0, "jj");
		Student s5 = new Student("L", "B", 5, 0, 0, "lb");

		// Suggestions: since search table map keys are Comparable,
		// make sure the search table works with Comparable objects like Students
		assertEquals(0, studentMap.size());
		assertTrue(studentMap.isEmpty());
		assertNull(studentMap.put(s1, 1));
		assertNull(studentMap.put(s3, 3));
		assertNull(studentMap.put(s5, 5));
		assertNull(studentMap.put(s2, 2));
		assertNull(studentMap.put(s4, 4));
		assertEquals(5, studentMap.size());
		assertTrue(studentMap.remove(s4).equals(4));
		assertTrue(studentMap.remove(s5).equals(5));
	}

	/**
	 * Test the output of the iterator behavior, including expected exceptions
	 */
	@Test
	public void testIterator() {
		Iterator<Integer> keyIt = map.iterator();
		assertFalse(keyIt.hasNext());
		
		assertNull(map.put(3, "string3"));

		keyIt = map.iterator();
		Iterable<Entry<Integer, String>> collection = map.entrySet();
		Iterator<Entry<Integer, String>> entryIt = collection.iterator();
		assertTrue(keyIt.hasNext());
		assertEquals(entryIt.next().getKey(), keyIt.next());
		assertFalse(keyIt.hasNext());
		try {
			entryIt.remove();
			fail("An UnsupportedOperationException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
	}

	/**
	 * Test the output of the entrySet() behavior, including expected exceptions
	 */
	@Test
	public void testEntrySet() {
		Iterable<Entry<Integer, String>> collection = map.entrySet();
		Iterator<Entry<Integer, String>> entryIt = collection.iterator();
		
		assertFalse(entryIt.hasNext());
		
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		collection = map.entrySet();
		entryIt = collection.iterator();
		try {
			entryIt.remove();
			fail("An UnsupportedOperationException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
		assertTrue(entryIt.hasNext());
		assertEquals(map.get(1), entryIt.next().getValue());
		
	}

	/**
	 * Test the output of the values() behavior, including expected exceptions
	 */
	@Test
	public void testValues() {
		Iterable<String> values = map.values();
		Iterator<String> valueIt = values.iterator();
		assertFalse(valueIt.hasNext());
		
		assertNull(map.put(3, "string3"));

		valueIt = values.iterator();
		Iterable<Entry<Integer, String>> collection = map.entrySet();
		Iterator<Entry<Integer, String>> entryIt = collection.iterator();
		assertTrue(valueIt.hasNext());
		assertEquals(entryIt.next().getValue(), valueIt.next());
		assertFalse(valueIt.hasNext());
		try {
			valueIt.remove();
			fail("An UnsupportedOperationException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
	}
}