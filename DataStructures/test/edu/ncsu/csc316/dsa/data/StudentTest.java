package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the Student class.
 * 
 * @author Dr. King
 * @author Maggie Lin
 */
public class StudentTest {

	/** Student One */
	private Student sOne;

	/** Student Two */
	private Student sTwo;

	/** Student Three */
	private Student sThree;

	/** Student Four */
	private Student sFour;

	/** Student Five */
	private Student sFive;
	
	/** Student Six */
	private Student sSix;

	/** Student Seven */
	private Student sSeven;

	/** Student Eight */
	private Student sEight;

	/**
	 * Sets up all students.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sSix = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sSeven = new Student("OneSecond", "OneLast", 1, 1, 1.0, "oneUnityID");
		sEight = new Student("OneSecond", "OneLast", 1, 2, 1.0, "oneUnityID");
	}

	/**
	 * Test the setFirst() method.
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Test the setLast() method.
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Test the setId() method.
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Test the setGpa() method.
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Test the setUnityID() method.
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}
	
	/**
	 * Test the setCreditHours() method.
	 */
	@Test
	public void testSetCreditHours() {
		sOne.setCreditHours(1);
		assertEquals(1, sOne.getCreditHours());
	}

	/**
	 * Test the compareTo() method.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sSeven) < 0);
		assertTrue(sThree.compareTo(sFour) > 0);
		assertEquals(0, sOne.compareTo(sOne));
		assertEquals(0, sTwo.compareTo(sTwo));
		assertEquals(0, sOne.compareTo(sSix));
	}
	
	/**
	 * Test the equals() method.
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.equals(sOne));
		assertFalse(sOne.equals(sFive));
		assertTrue(sOne.equals(sSix));
		assertFalse(sOne.equals(sSeven));
		assertFalse(sOne.equals(sEight));
	}
	
	/**
	 * Test the hashCode() method.
	 */
	@Test
	public void testHash() {
		assertEquals(1492839428, sOne.hashCode());
	}
	
	/**
	 * Test the toString() method.
	 */
	@Test
	public void testToString() {
		assertEquals("Student's first name: OneFirst, last name: OneLast, id: 1, creditHours: 1, gpa: 1.0, unityID: oneUnityID", sOne.toString());
	}
}
