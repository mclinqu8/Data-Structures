package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the StudentIDComparator class.
 * 
 * @author Dr. King
 * @author Maggie Lin
 */
public class StudentIDComparatorTest {

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

	/** StudentID Comparator */
	private StudentIDComparator comparator;

	/**
	 * Sets up all students and the StudentIDComparator.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Test if StudentIDComparator compares Students' ID correctly.
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		assertTrue(comparator.compare(sFour, sThree) > 0);
		assertTrue(comparator.compare(sFive, sFour) > 0);
	}

}
