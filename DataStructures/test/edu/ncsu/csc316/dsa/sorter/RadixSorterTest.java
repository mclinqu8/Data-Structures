package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
/**
 * Test the RadixSorter class.
 * 
 * @author Dr. King
 * @author Maggie Lin
 */
public class RadixSorterTest {

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

	/** RadixSorter for Student */
	private RadixSorter<Student> sorter;

	/**
	 * Sets up the Students and the RadixSorter.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		sorter = new RadixSorter<Student>();
	}

	/**
	 * Test whether RadixSorter is correctly sorting Students.
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
}
