package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.FirstElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.LastElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.MiddleElementSelector;

/**
 * Test the QuickSorter class.
 * 
 * @author Dr. King
 * @author Maggie Lin
 */
public class QuickSorterTest {

	/** Integers in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };

	/** Integers in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };

	/** Integers in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/** QuickSorter for Integers */
	private QuickSorter<Integer> integerSorter;
	
	/** QuickSorter for Students */
	Sorter<Student> studentSorter;
	
	/** QuickSorter for Students with Select First Pivot*/
	Sorter<Student> studentSorterFirst;
	
	/** QuickSorter for Students with Select Middle Pivot*/
	Sorter<Student> studentSorterMiddle;
	
	/** QuickSorter for Students with Select Last Pivot*/
	Sorter<Student> studentSorterLast;
	
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
	 * Sets up the Integer QuickSorter, StudentIDComparator, Student QuickSorter, 
	 * and pivot element selectors.
	 */
	@Before
	public void setUp() {
		integerSorter = new QuickSorter<Integer>();
		StudentIDComparator idComp = new StudentIDComparator();
		FirstElementSelector first = new FirstElementSelector();
		MiddleElementSelector middle = new MiddleElementSelector();
		LastElementSelector last = new LastElementSelector();
		studentSorter = new QuickSorter<Student>(idComp);
		studentSorterFirst = new QuickSorter<Student>(idComp, first);
		studentSorterMiddle = new QuickSorter<Student>(idComp, middle);
		studentSorterLast = new QuickSorter<Student>(idComp, last);
		
	}

	/**
	 * Test whether Integer QuickSorter is sorting Integers into ascending order.
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
	 * Test whether Student QuickSorter is sorting Students into ascending order based on ID.
	 */
	@Test
	public void testSortStudents() {
		studentSorter.sort(studentAscending);
		assertEquals(1, studentAscending[0].getId());
		assertEquals(2, studentAscending[1].getId());
		assertEquals(3, studentAscending[2].getId());
		assertEquals(4, studentAscending[3].getId());
		assertEquals(5, studentAscending[4].getId());
	}
	
	/**
	 * Test whether Student QuickSorter is sorting Students into ascending order based on ID
	 * using first pivot, middle pivot, and last pivot.
	 */
	@Test
	public void testSortStudentsSelectors() {	
		studentSorterFirst.sort(studentDescending);
		assertEquals(1, studentDescending[0].getId());
		assertEquals(2, studentDescending[1].getId());
		assertEquals(3, studentDescending[2].getId());
		assertEquals(4, studentDescending[3].getId());
		assertEquals(5, studentDescending[4].getId());
		
		studentSorterMiddle.sort(studentDescending);
		assertEquals(1, studentDescending[0].getId());
		assertEquals(2, studentDescending[1].getId());
		assertEquals(3, studentDescending[2].getId());
		assertEquals(4, studentDescending[3].getId());
		assertEquals(5, studentDescending[4].getId());
		
		studentSorterLast.sort(studentDescending);
		assertEquals(1, studentDescending[0].getId());
		assertEquals(2, studentDescending[1].getId());
		assertEquals(3, studentDescending[2].getId());
		assertEquals(4, studentDescending[3].getId());
		assertEquals(5, studentDescending[4].getId());
	}
}
