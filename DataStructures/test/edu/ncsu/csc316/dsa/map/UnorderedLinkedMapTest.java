package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap Checks the expected outputs of the Map
 * abstract data type behaviors when using an unordered link-based list data
 * structure that uses the move-to-front heuristic for self-organizing entries
 * based on access frequency
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class UnorderedLinkedMapTest {

	/**
	 * Declare an UnorderedLinkedMap with keys as type Integers and values as type Strings
	 */
	private Map<Integer, String> map;
	
	/**
	 * Declare an UnorderedLinkedMap of students with the keys as Students and values as Integers
	 */
	private Map<Student, Integer> studentMap;

	/**
	 * Create a new instance of an unordered link-based map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		map = new UnorderedLinkedMap<Integer, String>();
		studentMap = new UnorderedLinkedMap<Student, Integer>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedLinkedMap[3]", map.toString());
		assertEquals(1, map.size());

		// test put for the same key
		assertEquals("string3", map.put(3, "string4"));
		assertEquals("UnorderedLinkedMap[3]", map.toString());
		assertEquals(1, map.size());

		// test put for several with move-to-front heuristic
		assertNull(map.put(2, "string2"));
		assertEquals("UnorderedLinkedMap[2, 3]", map.toString());
		assertNull(map.put(1, "string1"));
		assertEquals("UnorderedLinkedMap[1, 2, 3]", map.toString());

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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.remove(1));
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		String valueRemoved = map.get(1);
		assertEquals(valueRemoved, map.remove(1));
		assertEquals("UnorderedLinkedMap[4, 2, 5, 3]", map.toString());
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
		
		assertEquals(0, studentMap.size());
		assertTrue(studentMap.isEmpty());
		assertNull(studentMap.put(s1, 1));
		assertNull(studentMap.put(s3, 3));
		assertNull(studentMap.put(s5, 5));
		assertNull(studentMap.put(s2, 2));
		assertNull(studentMap.put(s4, 4));
		assertTrue(studentMap.put(s4, 6).equals(4));
		assertEquals(5, studentMap.size());
		
		assertTrue(studentMap.get(s3).equals(3));
		assertTrue(studentMap.get(s4).equals(6));
	}
}