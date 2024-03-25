package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test the BubbleSorter class.
 * 
 * @author Dr. King
 * @author Maggie Lin
 */
public class BubbleSorterTest {

	/** Integers in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };

	/** Integers in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };

	/** Integers in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/** BubbleSorter for Integers */
	private BubbleSorter<Integer> integerSorter;
	
	/** BubbleSorter for Students */
	Sorter<Student> bubbleSorter;

	/** Student One */
	private Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");;

	/** Student Two */
	private Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");

	/** Student Three */
	private Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");

	/** Student Four */
	private Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");

	/** Student Five */
	private Student sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	
	/** Student in ascending order */
	private Student[] studentAscending = { sOne, sTwo, sThree, sFour, sFive };
	
	/** Student in descending order */
	private Student[] studentDescending = { sFive, sFour, sThree, sTwo, sOne };
	
	/**
	 * Sets up the Integer BubbleSorter
	 */
	@Before
	public void setUp() {
		integerSorter = new BubbleSorter<Integer>();
		StudentIDComparator idComp = new StudentIDComparator();
		bubbleSorter = new BubbleSorter<Student>(idComp);
	}

	/**
	 * Test whether Integer BubbleSorter is sorting Integers into ascending order.
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals((Integer) 1, dataAscending[0]);
		assertEquals((Integer) 2, dataAscending[1]);
		assertEquals((Integer) 3, dataAscending[2]);
		assertEquals((Integer) 4, dataAscending[3]);
		assertEquals((Integer) 5, dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals((Integer) 1, dataDescending[0]);
		assertEquals((Integer) 2, dataDescending[1]);
		assertEquals((Integer) 3, dataDescending[2]);
		assertEquals((Integer) 4, dataDescending[3]);
		assertEquals((Integer) 5, dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals((Integer) 1, dataRandom[0]);
		assertEquals((Integer) 2, dataRandom[1]);
		assertEquals((Integer) 3, dataRandom[2]);
		assertEquals((Integer) 4, dataRandom[3]);
		assertEquals((Integer) 5, dataRandom[4]);
	}
	
	/**
	 * Test whether Student BubbleSorter is sorting Student into ascending order based on ID.
	 */
	@Test
	public void testSortStudent() {
		bubbleSorter.sort(studentAscending);
		assertEquals(1, studentAscending[0].getId());
		assertEquals(2, studentAscending[1].getId());
		assertEquals(3, studentAscending[2].getId());
		assertEquals(4, studentAscending[3].getId());
		assertEquals(5, studentAscending[4].getId());
		
		bubbleSorter.sort(studentDescending);
		assertEquals(1, studentDescending[0].getId());
		assertEquals(2, studentDescending[1].getId());
		assertEquals(3, studentDescending[2].getId());
		assertEquals(4, studentDescending[3].getId());
		assertEquals(5, studentDescending[4].getId());
	}
}
