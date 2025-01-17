package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for SearchTableMap Checks the expected outputs of the Map abstract
 * data type behaviors when using a sorted array-based data structure that uses
 * binary search to locate entries based on the key of the entry
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class SearchTableMapTest {

	/**
	 * Declare a SearchTableMap with keys as type Integers and values as type Strings
	 */
	private Map<Integer, String> map;
	
	/**
	 * Declare a SearchTableMap of students with the keys as Students and values as Integers
	 */
	private Map<Student, Integer> studentMap;

	/**
	 * Create a new instance of a search table map before each test case executes
	 */
	@Before
	public void setUp() {
		map = new SearchTableMap<Integer, String>();
		studentMap = new SearchTableMap<Student, Integer>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SearchTableMap[3]", map.toString());
		assertEquals(1, map.size());
		// put after 3
		assertNull(map.put(4, "string4"));
		assertEquals("SearchTableMap[3, 4]", map.toString());
		assertEquals(2, map.size());
		// put before 3
		assertNull(map.put(2, "string2"));
		assertEquals("SearchTableMap[2, 3, 4]", map.toString());
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
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

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
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

		// test remove
		assertEquals("string1", map.remove(1));
		assertEquals("SearchTableMap[2, 3, 4, 5]", map.toString());
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
		assertEquals("SearchTableMap[Student's first name: L, last name: B, id: 5, "
				+ "creditHours: 0, gpa: 0.0, unityID: lb, Student's first name: S, "
				+ "last name: H, id: 3, creditHours: 0, gpa: 0.0, unityID: sh, Student's"
				+ " first name: J, last name: J, id: 4, creditHours: 0, gpa: 0.0, unityID: "
				+ "jj, Student's first name: J, last name: K, id: 1, creditHours: 0, gpa: 0.0,"
				+ " unityID: jk, Student's first name: J, last name: S, id: 2, creditHours: 0, "
				+ "gpa: 0.0, unityID: js]", studentMap.toString());
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